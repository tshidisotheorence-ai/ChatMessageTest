package docter;

/**
 * Handles user registration and login validation
 * for the QuickChat application.
 */
public class UserProfile {

    private String registeredUsername;
    private String registeredPassword;
    private String userFirstName;
    private String userLastName;

    // Save registered user details
    public void saveUserDetails(String firstName,
                                String lastName,
                                String username,
                                String password) {

        this.userFirstName = firstName;
        this.userLastName = lastName;
        this.registeredUsername = username;
        this.registeredPassword = password;
    }

    // Username validation
    public boolean validateUsername(String username) {

        return username.contains("_")
                && username.length() <= 5;
    }

    // Password validation
    public boolean validatePassword(String password) {

        boolean hasLength =
                password.length() >= 8;

        boolean hasCapital =
                password.matches(".*[A-Z].*");

        boolean hasNumber =
                password.matches(".*\\d.*");

        boolean hasSpecial =
                password.matches(".*[^a-zA-Z0-9].*");

        return hasLength
                && hasCapital
                && hasNumber
                && hasSpecial;
    }

    // South African number validation
    public boolean validatePhoneNumber(String phoneNumber) {

        return phoneNumber.matches("^\\+27\\d{9}$");
    }

    // Register new user
    public String createAccount(String username,
                                String password,
                                String phoneNumber) {

        if (!validateUsername(username)) {

            return "Username is not correctly formatted. "
                    + "Please ensure that your username "
                    + "contains an underscore and is no "
                    + "more than 5 characters long.";
        }

        if (!validatePassword(password)) {

            return "Password is not correctly formatted. "
                    + "Password must contain at least "
                    + "8 characters, a capital letter, "
                    + "a number and a special character.";
        }

        if (!validatePhoneNumber(phoneNumber)) {

            return "Cell phone number incorrectly formatted "
                    + "or does not contain international code.";
        }

        return "User successfully registered.";
    }

    // Login validation
    public boolean authenticateUser(String username,
                                    String password) {

        return username.equals(registeredUsername)
                && password.equals(registeredPassword);
    }

    // Login response message
    public String getLoginMessage(boolean loginSuccessful) {

        if (loginSuccessful) {

            return "Welcome "
                    + userFirstName
                    + " "
                    + userLastName
                    + ", it is great to see you again.";
        }

        return "Username or password incorrect.";
    }
}