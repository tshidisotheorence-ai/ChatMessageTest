package rangs;

public class MessageManager {

    private String[] sentMessages = new String[50];
    private String[] storedMessages = new String[50];

    private int sentIndex = 0;
    private int storedIndex = 0;

    // Add Sent Message
    public void captureSentMessage(String message) {

        sentMessages[sentIndex] = message;
        sentIndex++;
    }

    // Add Stored Message
    public void captureStoredMessage(String message) {

        storedMessages[storedIndex] = message;
        storedIndex++;
    }

    // Display Sent Messages
    public void showSentMessages() {

        System.out.println("===== SENT MESSAGE LIST =====");

        for (String message : sentMessages) {

            if (message != null) {

                System.out.println(message);
                System.out.println();
            }
        }
    }

    // Display Stored Messages
    public void showStoredMessages() {

        System.out.println("===== STORED MESSAGE LIST =====");

        for (String message : storedMessages) {

            if (message != null) {

                System.out.println(message);
                System.out.println();
            }
        }
    }
}//RnR