package docter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Handles all message storage, searching,
 * reporting and JSON loading.
 */
public class MessageArchive {

    // Sent Messages
    private String[] sentMessageList =
            new String[100];

    private String[] sentRecipientList =
            new String[100];

    private String[] sentHashList =
            new String[100];

    private String[] sentIDList =
            new String[100];

    // Stored Messages
    private String[] storedMessageList =
            new String[100];

    private String[] storedRecipientList =
            new String[100];

    private String[] storedHashList =
            new String[100];

    private String[] storedIDList =
            new String[100];

    // Discarded Messages
    private String[] discardedMessageList =
            new String[100];

    private int totalSent = 0;
    private int totalStored = 0;
    private int totalDiscarded = 0;

    // Save sent message
    public void saveSentMessage(
            String message,
            String hash,
            String id,
            String recipient) {

        sentMessageList[totalSent] =
                message;

        sentHashList[totalSent] =
                hash;

        sentIDList[totalSent] =
                id;

        sentRecipientList[totalSent] =
                recipient;

        totalSent++;
    }

    // Save stored message
    public void saveStoredMessage(
            String message,
            String hash,
            String id,
            String recipient) {

        storedMessageList[totalStored] =
                message;

        storedHashList[totalStored] =
                hash;

        storedIDList[totalStored] =
                id;

        storedRecipientList[totalStored] =
                recipient;

        totalStored++;
    }

    // Save discarded message
    public void saveDiscardedMessage(
            String message) {

        discardedMessageList[totalDiscarded]
                = message;

        totalDiscarded++;
    }

    // Read messages from JSON file
    public void loadJsonMessages() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "storedMessages.json"));

            String currentLine;

            while ((currentLine =
                    reader.readLine()) != null) {

                String id =
                        extractField(
                                currentLine,
                                "MessageID");

                String recipient =
                        extractField(
                                currentLine,
                                "Recipient");

                String message =
                        extractField(
                                currentLine,
                                "Message");

                String hash =
                        extractField(
                                currentLine,
                                "MessageHash");

                saveStoredMessage(
                        message,
                        hash,
                        id,
                        recipient);
            }

            reader.close();

        } catch (IOException error) {

            System.out.println(
                    "No stored messages found.");
        }
    }

    // Extract JSON values
    private String extractField(
            String json,
            String fieldName) {

        String search =
                "\"" + fieldName + "\":\"";

        int startPosition =
                json.indexOf(search)
                        + search.length();

        int endPosition =
                json.indexOf(
                        "\"",
                        startPosition);

        return json.substring(
                startPosition,
                endPosition);
    }

    // Display all stored messages
    public String showStoredMessages() {

        if (totalStored == 0) {

            return "No stored messages available.";
        }

        String output = "";

        for (int i = 0;
             i < totalStored;
             i++) {

            if (storedMessageList[i] != null) {

                output +=
                        "\nRecipient: "
                                + storedRecipientList[i]
                                + "\nMessage: "
                                + storedMessageList[i]
                                + "\n";
            }
        }

        return output;
    }

    // Find longest stored message
    public String findLongestStoredMessage() {

        if (totalStored == 0) {

            return "No stored messages.";
        }

        String longestMessage =
                storedMessageList[0];

        for (int i = 1;
             i < totalStored;
             i++) {

            if (storedMessageList[i] != null
                    && storedMessageList[i]
                    .length()
                    > longestMessage.length()) {

                longestMessage =
                        storedMessageList[i];
            }
        }

        return longestMessage;
    }

    // Search by recipient
    public String findMessagesByRecipient(
            String recipientNumber) {

        String results = "";

        for (int i = 0;
             i < totalStored;
             i++) {

            if (storedRecipientList[i] != null
                    && storedRecipientList[i]
                    .equals(recipientNumber)) {

                results +=
                        storedMessageList[i]
                                + "\n";
            }
        }

        if (results.isEmpty()) {

            return "No messages found.";
        }

        return results;
    }

    // Search by Message ID
    public String findMessageByID(
            String messageID) {

        for (int i = 0;
             i < totalStored;
             i++) {

            if (storedIDList[i] != null
                    && storedIDList[i]
                    .equals(messageID)) {

                return "Recipient: "
                        + storedRecipientList[i]
                        + "\nMessage: "
                        + storedMessageList[i];
            }
        }

        return "Message not found.";
    }

    // Delete using Hash
    public String removeMessageByHash(
            String hashCode) {

        for (int i = 0;
             i < totalStored;
             i++) {

            if (storedHashList[i] != null
                    && storedHashList[i]
                    .equals(hashCode)) {

                String deletedMessage =
                        storedMessageList[i];

                storedMessageList[i] = "";
                storedRecipientList[i] = "";
                storedHashList[i] = "";
                storedIDList[i] = "";

                return "Message: "
                        + deletedMessage
                        + " successfully deleted.";
            }
        }

        return "Message not found.";
    }

    // Generate report
    public String generateReport() {

        String reportOutput = "";

        for (int i = 0;
             i < totalStored;
             i++) {

            if (storedMessageList[i] != null
                    && !storedMessageList[i]
                    .equals("")) {

                reportOutput +=
                        "\n====================";

                reportOutput +=
                        "\nMessage Hash: "
                                + storedHashList[i];

                reportOutput +=
                        "\nRecipient: "
                                + storedRecipientList[i];

                reportOutput +=
                        "\nMessage: "
                                + storedMessageList[i];

                reportOutput +=
                        "\n====================\n";
            }
        }

        return reportOutput;
    }

    // Statistics
    public int getTotalSent() {

        return totalSent;
    }

    public int getTotalStored() {

        return totalStored;
    }

    public int getTotalDiscarded() {

        return totalDiscarded;
    }
}