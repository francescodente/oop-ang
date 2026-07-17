package oopang.controller.users;

import java.util.Optional;

/**
 * User manager that prefers the online database and keeps a local cache on disk.
 */
public final class FallbackUserManager implements UserManager {

    private final UserManager onlineManager;
    private final UserManager localManager;

    /**
     * Create a fallback manager using the default online and file system managers.
     */
    public FallbackUserManager() {
        this(new OnlineUserManager(), new FileSystemUserManager());
    }

    /**
     * Create a fallback manager with explicit delegates.
     * @param onlineManager
     *      the online user manager.
     * @param localManager
     *      the local user manager.
     */
    public FallbackUserManager(final UserManager onlineManager, final UserManager localManager) {
        this.onlineManager = onlineManager;
        this.localManager = localManager;
    }

    @Override
    public Optional<User> login(final String userName, final String password) {
        final Optional<User> onlineUser = this.onlineManager.login(userName, password);
        if (onlineUser.isPresent()) {
            final Optional<User> localUser = this.localManager.login(userName, password);
            if (localUser.isPresent() && localUser.get().getLastModified() > onlineUser.get().getLastModified()) {
                this.onlineManager.saveUser(localUser.get());
                return localUser;
            }
            this.localManager.saveUser(onlineUser.get());
            return onlineUser;
        }
        return this.localManager.login(userName, password);
    }

    @Override
    public boolean saveUser(final User user) {
        final boolean onlineSaved = this.onlineManager.saveUser(user);
        final boolean localSaved = this.localManager.saveUser(user);
        return onlineSaved || localSaved;
    }

    @Override
    public Optional<User> registerUser(final String userName, final String password) {
        final Optional<User> onlineUser = this.onlineManager.registerUser(userName, password);
        onlineUser.ifPresent(this.localManager::saveUser);
        return onlineUser;
    }
}