
package docter;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a QuickChat message.
 */
public class ChatRecord {

    private String uniqueMessageID;
    private int messageCounter;
    private String recipientNumber;
    private String messageText;
    private String uniqueHash;

    // Generate random 10-digit message ID
    private void createMessageID() {

        Random generator = new Random();

        long randomNumber =
                1000000000L
                + (long) (generator.nextDouble()
                * 8999999999L);

        uniqueMessageID =
                String.valueOf(randomNumber);
    }

    // Validate recipient number
    public String validateRecipient(
            String cellphoneNumber) {

        if (cellphoneNumber.matches(
                "^\\+27\\d{9}$")) {

            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Validate message length
    public String validateMessageText(
            String text) {

        if (text.length() <= 250) {

            return "Message ready to send.";
        }

        int extraCharacters =
                text.length() - 250;

        return "Message exceeds 250 characters by "
                + extraCharacters
                + ", please reduce the size.";
    }

    // Generate message hash
    public String generateHash() {

        String[] words =
                messageText.split(" ");

        String firstWord =
                words[0].toUpperCase();

        String lastWord =
                words[words.length - 1]
                        .toUpperCase();

        return uniqueMessageID.substring(0, 2)
                + ":"
                + messageCounter
                + ":"
                + firstWord
                + lastWord;
    }

    // Save message information
    public void captureMessage(
            int number,
            String recipient,
            String text) {

        this.messageCounter = number;
        this.recipientNumber = recipient;
        this.messageText = text;

        createMessageID();

        this.uniqueHash =
                generateHash();
    }

    // Process send option
    public String processMessage(
            int selectedOption) {

        switch (selectedOption) {

            case 1:
                return "Message successfully sent.";

            case 2:
                return "Message disregarded.";

            case 3:

                saveToJson();

                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    // Store message to JSON file
    public void saveToJson() {

        try {

            FileWriter writer =
                    new FileWriter(
                            "storedMessages.json",
                            true);

            String jsonData =
                    "{"
                    + "\"MessageID\":\""
                    + uniqueMessageID
                    + "\","
                    + "\"Recipient\":\""
                    + recipientNumber
                    + "\","
                    + "\"Message\":\""
                    + messageText
                    + "\","
                    + "\"MessageHash\":\""
                    + uniqueHash
                    + "\""
                    + "}";

            writer.write(jsonData);
            writer.write(System.lineSeparator());

            writer.close();

        } catch (IOException error) {

            System.out.println(
                    "Error saving message.");
        }
    }

    // Display message details
    public String displayMessage() {

        return "Message ID: "
                + uniqueMessageID
                + "\nMessage Hash: "
                + uniqueHash
                + "\nRecipient: "
                + recipientNumber
                + "\nMessage: "
                + messageText;
    }

    // Getters

    public String getMessageID() {

        return uniqueMessageID;
    }

    public String getMessageHash() {

        return uniqueHash;
    }

    public String getRecipientNumber() {

        return recipientNumber;
    }

    public String getMessageText() {

        return messageText;
    }

    public int getMessageCounter() {

        return messageCounter;
    }
}