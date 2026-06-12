package docter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatRecordTest {

    @Test
    public void testRecipientValid() {

        ChatRecord chat =
                new ChatRecord();

        assertEquals(
                "Cell phone number successfully captured.",
                chat.validateRecipient(
                        "+27831234567"));
    }

    @Test
    public void testRecipientInvalid() {

        ChatRecord chat =
                new ChatRecord();

        assertTrue(
                chat.validateRecipient(
                        "0831234567")
                        .contains(
                                "incorrectly"));
    }

    @Test
    public void testMessageLengthValid() {

        ChatRecord chat =
                new ChatRecord();

        assertEquals(
                "Message ready to send.",
                chat.validateMessageText(
                        "Hello"));
    }

    @Test
    public void testMessageLengthInvalid() {

        ChatRecord chat =
                new ChatRecord();

        String text =
                "A".repeat(260);

        assertTrue(
                chat.validateMessageText(
                        text)
                        .contains(
                                "exceeds"));
    }

    @Test
    public void testGenerateHash() {

        ChatRecord chat =
                new ChatRecord();

        chat.captureMessage(
                1,
                "+27831234567",
                "Hello World");

        assertNotNull(
                chat.getMessageHash());
    }

    @Test
    public void testStoreOption() {

        ChatRecord chat =
                new ChatRecord();

        chat.captureMessage(
                1,
                "+27831234567",
                "Stored Message");

        assertEquals(
                "Message successfully stored.",
                chat.processMessage(
                        3));
    }

    @Test
    public void testDisplayMessage() {

        ChatRecord chat =
                new ChatRecord();

        chat.captureMessage(
                1,
                "+27831234567",
                "Testing");

        assertTrue(
                chat.displayMessage()
                        .contains(
                                "Testing"));
    }
}