package oopang.controller.users;

import java.util.Optional;

/**
 * Interface that manage the user.
 */
public interface UserManager {

    /**
     * Method that make the User login.
     * @param userName
     *      the name of the user.
     * @param password
     *      the password of the user.
     * @return
     *      Optional<User>.
     */
    Optional<User> login(String userName, String password);

    /**
     * Method that save any use modifications.
     * @param user
     *      the object user to save.
     * @return
     *      return true or false
     */
    boolean saveUser(User user);

    /**
     * Method that register a new User.
     * @param userName
     *      the name of the user.
     * @param password
     *      the password of the user.
     * @return
     *      Optional<User>.
     */
    Optional<User> registerUser(String userName, String password);

}
