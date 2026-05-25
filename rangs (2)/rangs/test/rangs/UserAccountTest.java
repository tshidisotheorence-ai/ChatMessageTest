package rangs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserAccountTest {

    // Test Valid Username
    @Test
    public void usernameValidTest() {

        UserAccount account = new UserAccount(
                "John",
                "Doe",
                "jd_1",
                "Password@1",
                "+27831234567"
        );

        assertTrue(account.validateUsername());
    }

    // Test Invalid Username
    @Test
    public void usernameInvalidTest() {

        UserAccount account = new UserAccount(
                "John",
                "Doe",
                "johnny123",
                "Password@1",
                "+27831234567"
        );

        assertFalse(account.validateUsername());
    }

    // Test Valid Password
    @Test
    public void passwordValidTest() {

        UserAccount account = new UserAccount(
                "John",
                "Doe",
                "jd_1",
                "Password@1",
                "+27831234567"
        );

        assertTrue(account.validatePassword());
    }

    // Test Invalid Password
    @Test
    public void passwordInvalidTest() {

        UserAccount account = new UserAccount(
                "John",
                "Doe",
                "jd_1",
                "password",
                "+27831234567"
        );

        assertFalse(account.validatePassword());
    }

    // Test Valid Cell Number
    @Test
    public void validCellNumberTest() {

        UserAccount account = new UserAccount(
                "John",
                "Doe",
                "jd_1",
                "Password@1",
                "+27831234567"
        );

        assertTrue(account.validateCellNumber());
    }

    // Test Invalid Cell Number
    @Test
    public void invalidCellNumberTest() {

        UserAccount account = new UserAccount(
                "John",
                "Doe",
                "jd_1",
                "Password@1",
                "0831234567"
        );

        assertFalse(account.validateCellNumber());
    }

    // Test Login Success
    @Test
    public void loginSuccessfulTest() {

        UserAccount account = new UserAccount(
                "John",
                "Doe",
                "jd_1",
                "Password@1",
                "+27831234567"
        );

        assertTrue(
                account.verifyLogin(
                        "jd_1",
                        "Password@1"
                )
        );
    }

    // Test Login Failure
    @Test
    public void loginFailedTest() {

        UserAccount account = new UserAccount(
                "John",
                "Doe",
                "jd_1",
                "Password@1",
                "+27831234567"
        );

        assertFalse(
                account.verifyLogin(
                        "wrong",
                        "wrong"
                )
        );
    }
}