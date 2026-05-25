package rangs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatMessageTest {

    // Test Valid Recipient
    @Test
    public void validRecipientTest() {

        ChatMessage message = new ChatMessage(
                1,
                "+27761234567",
                "Hello there"
        );

        assertTrue(message.isRecipientValid());
    }

    // Test Invalid Recipient
    @Test
    public void invalidRecipientTest() {

        ChatMessage message = new ChatMessage(
                1,
                "0761234567",
                "Hello there"
        );

        assertFalse(message.isRecipientValid());
    }

    // Test Message Length Valid
    @Test
    public void validMessageLengthTest() {

        ChatMessage message = new ChatMessage(
                1,
                "+27761234567",
                "This is a short message"
        );

        assertTrue(message.isMessageValid());
    }

    // Test Message Length Invalid
    @Test
    public void invalidMessageLengthTest() {

        String longText = "A".repeat(260);

        ChatMessage message = new ChatMessage(
                1,
                "+27761234567",
                longText
        );

        assertFalse(message.isMessageValid());
    }

    // Test Message Hash
    @Test
    public void messageHashTest() {

        ChatMessage message = new ChatMessage(
                1,
                "+27761234567",
                "Hello Mike"
        );

        String hash = message.buildHash();

        assertNotNull(hash);
    }

    // Test Send Message
    @Test
    public void sendMessageTest() {

        ChatMessage message = new ChatMessage(
                1,
                "+27761234567",
                "Testing message"
        );

        assertEquals(
                "Message sent.",
                message.processMessage(1)
        );
    }

    // Test Delete Message
    @Test
    public void deleteMessageTest() {

        ChatMessage message = new ChatMessage(
                1,
                "+27761234567",
                "Testing delete"
        );

        assertEquals(
                "Message deleted.",
                message.processMessage(2)
        );
    }

    // Test Save Message
    @Test
    public void saveMessageTest() {

        ChatMessage message = new ChatMessage(
                1,
                "+27761234567",
                "Testing save"
        );

        assertEquals(
                "Message saved.",
                message.processMessage(3)
        );
    }
}