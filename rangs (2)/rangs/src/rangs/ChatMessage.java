package rangs;

import java.util.UUID;

public class ChatMessage {

    private static int sentCounter = 0;

    private String uniqueID;
    private int messageIndex;
    private String receiver;
    private String messageBody;

    public ChatMessage(int messageIndex,
                       String receiver,
                       String messageBody) {

        this.messageIndex = messageIndex;
        this.receiver = receiver;
        this.messageBody = messageBody;

        uniqueID = createID();
    }

    // Create Unique Message ID
    private String createID() {

        String id = UUID.randomUUID().toString();

        return id.substring(0, 10);
    }

    // Validate Message Length
    public boolean isMessageValid() {

        return messageBody.length() <= 250;
    }

    // Validate Recipient
    public boolean isRecipientValid() {

        return receiver.startsWith("+27")
                && receiver.length() == 12;
    }

    // Generate Message Hash
    public String buildHash() {

        String[] data = messageBody.split(" ");

        String startWord = data[0].toUpperCase();
        String endWord = data[data.length - 1].toUpperCase();

        return uniqueID.substring(0, 2)
                + messageIndex
                + startWord
                + endWord;
    }
     // Send Option
    public String processMessage(int selection) {

        if (selection == 1) {

            sentCounter++;
            return "Message sent.";
        }

        if (selection == 2) {

            return "Message deleted.";
        }

        if (selection == 3) {

            return "Message saved.";
        }

        return "Invalid selection.";
    }

    // Display Message
    public String displayMessage() {

        return "ID: " + uniqueID
                + "\nHash: " + buildHash()
                + "\nRecipient: " + receiver
                + "\nMessage: " + messageBody;
    }

    // Total Messages
    public static int totalMessagesSent() {

        return sentCounter;
    }
}