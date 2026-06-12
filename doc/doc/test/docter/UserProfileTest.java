package docter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserProfileTest {

    @Test
    public void testUsernameValid() {

        UserProfile user =
                new UserProfile();

        assertTrue(
                user.validateUsername(
                        "ab_cd"));
    }

  
    @Test
    public void testUsernameInvalid() {

        UserProfile user =
                new UserProfile();

        assertFalse(
                user.validateUsername(
                        "abcdef"));
    }

    @Test
    public void testPasswordValid() {

        UserProfile user =
                new UserProfile();

        assertTrue(
                user.validatePassword(
                        "Password1@"));
    }

    @Test
    public void testPasswordInvalid() {

        UserProfile user =
                new UserProfile();

        assertFalse(
                user.validatePassword(
                        "pass"));
    }

    @Test
    public void testPhoneNumberValid() {

        UserProfile user =
                new UserProfile();

        assertTrue(
                user.validatePhoneNumber(
                        "+27831234567"));
    }

    @Test
    public void testPhoneNumberInvalid() {

        UserProfile user =
                new UserProfile();

        assertFalse(
                user.validatePhoneNumber(
                        "0831234567"));
    }

    @Test
    public void testLoginSuccess() {

        UserProfile user =
                new UserProfile();

        user.saveUserDetails(
                "John",
                "Smith",
                "ab_cd",
                "Password1@");

        assertTrue(
                user.authenticateUser(
                        "ab_cd",
                        "Password1@"));
    }

    @Test
    public void testLoginFail() {

        UserProfile user =
                new UserProfile();

        user.saveUserDetails(
                "John",
                "Smith",
                "ab_cd",
                "Password1@");

        assertFalse(
                user.authenticateUser(
                        "wrong",
                        "wrong"));
    }
}