package oopang.controller.users;

import java.util.Optional;
import java.util.stream.Stream;
import oopang.controller.InstallManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is an implementation for the {@link UserManager} that loads and save Users in the FileSystem.
 */
public final class FileSystemUserManager implements UserManager {

    private static final String FIELD_SEPARATOR = " ";

    @Override
    public Optional<User> login(final String userName, final String password) {
       final Optional<String[]> logInfo = getUsersPassword()
                .filter(x -> x[0].equals(userName))
                .filter(x -> Integer.toString(password.hashCode()).equals(x[1]))
                .findFirst();
        if (logInfo.isPresent()) {
            try {
                return Optional.of(loadUser(userName));
            } catch (IOException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean saveUser(final User user) {
        try {
            writeUser(user);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Optional<User> registerUser(final String userName, final String password) {
        if (getUsersPassword().filter(l -> l[0].equals(userName)).findFirst().isPresent()) {
            return Optional.empty();
        } else {
            try (BufferedWriter write = new BufferedWriter(new FileWriter(InstallManager.USER_LIST_FILE, true))) {
                write.write(userName + FIELD_SEPARATOR + password.hashCode());
                write.newLine();
                final User newUser = new User(userName);
                this.writeUser(newUser);
                return Optional.of(newUser);
            } catch (IOException e) {
                return Optional.empty();
            }
        }
    }

    private Stream<String[]> getUsersPassword() {
        try {
            return Files.lines(Paths.get(InstallManager.USER_LIST_FILE))
                .map(l -> l.split(FIELD_SEPARATOR));
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    private User loadUser(final String userName) throws IOException {
        try (
           InputStream file = new FileInputStream(InstallManager.getUserFile(userName));
           InputStream bstream = new BufferedInputStream(file);
           ObjectInputStream ostream = new ObjectInputStream(bstream);
         ) {
            return (User) ostream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException();
        }
    }

    private void writeUser(final User user) throws IOException {
        try (OutputStream file = new FileOutputStream(InstallManager.getUserFile(user.getName()));
             OutputStream bstream = new BufferedOutputStream(file);
             ObjectOutputStream ostream = new ObjectOutputStream(bstream)) {
            ostream.writeObject(user);
        } catch (IOException e) {
            throw new IOException();
        }
    }

}
