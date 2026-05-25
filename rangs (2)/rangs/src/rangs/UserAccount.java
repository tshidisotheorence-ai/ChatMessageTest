package rangs;

public class UserAccount {

    private String name;
    private String surname;
    private String userName;
    private String userPassword;
    private String phoneNumber;

    public UserAccount(String name,
                       String surname,
                       String userName,
                       String userPassword,
                       String phoneNumber) {

        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.userPassword = userPassword;
        this.phoneNumber = phoneNumber;
    }

    // Validate Username
    public boolean validateUsername() {

        return userName.contains("_")
                && userName.length() <= 5;
    }

    // Validate Password
    public boolean validatePassword() {

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char ch : userPassword.toCharArray()) {

            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            }

            if (Character.isDigit(ch)) {
                
              hasNumber = true;
            }

            if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return userPassword.length() >= 8
                && hasCapital
                && hasNumber
                && hasSpecial;
    }

    // Validate Cell Number
    public boolean validateCellNumber() {

        return phoneNumber.startsWith("+27")
                && phoneNumber.length() == 12;
    }

    // Register User
    public String registerAccount() {

        if (!validateUsername()) {

            return "Username is incorrectly formatted.";
        }

        if (!validatePassword()) {

            return "Password does not meet complexity requirements.";
        }

        if (!validateCellNumber()) {

            return "Cell phone number incorrectly formatted.";
        }

        return "Registration successful.";
    }

    // Login Verification
    public boolean verifyLogin(String enteredUser,
                               String enteredPass) {

        return userName.equals(enteredUser)
                && userPassword.equals(enteredPass);
    }

    // Login Message
    public String loginMessage(boolean status) {

        if (status) {

            return "Welcome back " + name + " " + surname;
        }

        return "Login failed. Incorrect credentials.";
    }
}