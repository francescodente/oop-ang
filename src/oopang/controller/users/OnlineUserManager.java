package oopang.controller.users;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import oopang.controller.database.DatabaseManager;
import oopang.controller.InstallManager;import oopang.controller.InstallManager;
/**
 * User manager backed by the online database.
 */
public final class OnlineUserManager implements UserManager {

    private static final String USERS_TABLE = "Users";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_PASSWORD_HASH = "PasswordHash";
    private static final String COLUMN_STATE = "UserData";
    private static final String COLUMN_LAST_MODIFIED = "LastModified";

    private final DatabaseManager manager;

    /**
     * Create a new online user manager.
     */
    public OnlineUserManager() {
        this.manager = new DatabaseManager();
    }

    private Optional<User> loadFromDatabase(final String userName, final String password) throws SQLException, IOException {
        final String query = "SELECT " + COLUMN_PASSWORD_HASH + ", " + COLUMN_STATE + ", " + COLUMN_LAST_MODIFIED
                + " FROM " + USERS_TABLE + " WHERE " + COLUMN_NAME + " = ?";
        try (PreparedStatement statement = this.manager.getConnection().prepareStatement(query)) {
            statement.setString(1, userName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()
                        && Integer.toString(password.hashCode()).equals(resultSet.getString(COLUMN_PASSWORD_HASH))) {
                    final User user = this.deserializeUser(resultSet.getBytes(COLUMN_STATE));
                    user.setLastModified(Math.max(user.getLastModified(), resultSet.getLong(COLUMN_LAST_MODIFIED)));
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

    private boolean userExists(final String userName) throws SQLException {
        final String query = "SELECT 1 FROM " + USERS_TABLE + " WHERE " + COLUMN_NAME + " = ?";
        try (PreparedStatement statement = this.manager.getConnection().prepareStatement(query)) {
            statement.setString(1, userName);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private User deserializeUser(final byte[] state) throws IOException {
        try (
                ByteArrayInputStream file = new ByteArrayInputStream(state);
                ObjectInputStream stream = new ObjectInputStream(file)) {
            return (User) stream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    private byte[] serializeUser(final User user) throws IOException {
        try (
                ByteArrayOutputStream file = new ByteArrayOutputStream();
                ObjectOutputStream stream = new ObjectOutputStream(file)) {
            stream.writeObject(user);
            stream.flush();
            return file.toByteArray();
        }
    }

    private void syncLocalCredentials(final String userName, final String password) {
        try {
            final FileSystemUserManager localManager = new FileSystemUserManager();
            localManager.registerUser(userName, password);
        } catch (Exception e) {
            // Ignore local sync failures
        }
    }

    @Override
    public Optional<User> login(final String userName, final String password) {
        try {
            this.manager.createConnection();
            final Optional<User> onlineUser = this.loadFromDatabase(userName, password);
            if (onlineUser.isPresent()) {
                this.syncLocalCredentials(userName, password);
            }
            return onlineUser;
        } catch (SQLException | IOException e) {
            return Optional.empty();
        } finally {
            this.manager.closeConnection();
        }
    }

    @Override
    public boolean saveUser(final User user) {
        try {
            this.manager.createConnection();
            final String query = "UPDATE " + USERS_TABLE + " SET " + COLUMN_STATE + " = ?, "
                    + COLUMN_LAST_MODIFIED + " = ? WHERE " + COLUMN_NAME + " = ?";
            try (PreparedStatement statement = this.manager.getConnection().prepareStatement(query)) {
                statement.setBytes(1, this.serializeUser(user));
                statement.setLong(2, user.getLastModified());
                statement.setString(3, user.getName());
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException | IOException e) {
            return false;
        } finally {
            this.manager.closeConnection();
        }
    }

    @Override
    public Optional<User> registerUser(final String userName, final String password) {
        try {
            this.manager.createConnection();
            if (this.userExists(userName)) {
                return Optional.empty();
            }
            final User newUser = new User(userName);
            final String query = "INSERT INTO " + USERS_TABLE + " ("
                    + COLUMN_NAME + ", " + COLUMN_PASSWORD_HASH + ", " + COLUMN_STATE + ", " + COLUMN_LAST_MODIFIED + ") VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = this.manager.getConnection().prepareStatement(query)) {
                statement.setString(1, userName);
                statement.setString(2, Integer.toString(password.hashCode()));
                statement.setBytes(3, this.serializeUser(newUser));
                statement.setLong(4, newUser.getLastModified());
                statement.executeUpdate();
                this.syncLocalCredentials(userName, password);
                return Optional.of(newUser);
            }
        } catch (SQLException | IOException e) {
            return Optional.empty();
        } finally {
            this.syncLocalCredentials(userName, password);
            this.manager.closeConnection();
        }
    }
}