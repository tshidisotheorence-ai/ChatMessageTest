package docter;

import java.util.Scanner;

public class QuickChatApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        UserProfile user = new UserProfile();

        MessageArchive archive =
                new MessageArchive();

        // Load stored JSON messages
        archive.loadJsonMessages();

        // ==========================
        // USER REGISTRATION
        // ==========================

        System.out.println("===== QUICKCHAT REGISTRATION =====");

        System.out.print("First Name: ");
        String firstName =
                input.nextLine();

        System.out.print("Last Name: ");
        String lastName =
                input.nextLine();

        System.out.print("Username: ");
        String username =
                input.nextLine();

        System.out.print("Password: ");
        String password =
                input.nextLine();

        System.out.print("Cell Number (+27...): ");
        String phoneNumber =
                input.nextLine();

        String registrationResult =
                user.createAccount(
                        username,
                        password,
                        phoneNumber);

        System.out.println(
                registrationResult);

        // Stop if registration failed
        if (!registrationResult.equals(
                "User successfully registered.")) {

            input.close();
            return;
        }

        // Save user information

        user.saveUserDetails(
                firstName,
                lastName,
                username,
                password);

        // ==========================
        // LOGIN
        // ==========================

        System.out.println(
                "\n===== LOGIN =====");

        System.out.print("Username: ");
        String loginUsername =
                input.nextLine();

        System.out.print("Password: ");
        String loginPassword =
                input.nextLine();

        boolean loginSuccessful =
                user.authenticateUser(
                        loginUsername,
                        loginPassword);

        System.out.println(
                user.getLoginMessage(
                        loginSuccessful));

        if (!loginSuccessful) {

            input.close();
            return;
        }

        // ==========================
        // QUICKCHAT MENU
        // ==========================

        System.out.println(
                "\nWelcome to QuickChat.");

        System.out.print(
                "How many messages would you like to process? ");

        int totalMessages =
                Integer.parseInt(
                        input.nextLine());

        int menuChoice = 0;

        while (menuChoice != 5) {

            System.out.println(
                    "\n===== MAIN MENU =====");

            System.out.println(
                    "1. Capture Messages");

            System.out.println(
                    "2. View Stored Messages");

            System.out.println(
                    "3. View Message Report");

            System.out.println(
                    "4. Message Utilities");

            System.out.println(
                    "5. Exit");

            System.out.print(
                    "Choose Option: ");

            menuChoice =
                    Integer.parseInt(
                            input.nextLine());

            switch (menuChoice) {

                // ==========================
                // CAPTURE MESSAGES
                // ==========================
                case 1:

                    for (int counter = 1;
                         counter <= totalMessages;
                         counter++) {

                        ChatRecord chat =
                                new ChatRecord();

                        System.out.println(
                                "\nMessage "
                                        + counter);

                        System.out.print(
                                "Recipient Number: ");

                        String recipient =
                                input.nextLine();

                        System.out.println(
                                chat.validateRecipient(
                                        recipient));

                        System.out.print(
                                "Enter Message: ");

                        String text =
                                input.nextLine();

                        String lengthCheck =
                                chat.validateMessageText(
                                        text);

                        System.out.println(
                                lengthCheck);

                        if (!lengthCheck.equals(
                                "Message ready to send.")) {

                            continue;
                        }

                        chat.captureMessage(
                                counter,
                                recipient,
                                text);

                        System.out.println(
                                "\n1. Send");

                        System.out.println(
                                "2. Disregard");

                        System.out.println(
                                "3. Store");

                        System.out.print(
                                "Select Option: ");

                        int action =
                                Integer.parseInt(
                                        input.nextLine());

                        System.out.println(
                                chat.processMessage(
                                        action));

                        // SEND

                        if (action == 1) {

                            archive.saveSentMessage(
                                    chat.getMessageText(),
                                    chat.getMessageHash(),
                                    chat.getMessageID(),
                                    chat.getRecipientNumber());

                            System.out.println(
                                    chat.displayMessage());
                        }

                        // DISREGARD

                        else if (action == 2) {

                            archive.saveDiscardedMessage(
                                    chat.getMessageText());
                        }

                        // STORE

                        else if (action == 3) {

                            archive.saveStoredMessage(
                                    chat.getMessageText(),
                                    chat.getMessageHash(),
                                    chat.getMessageID(),
                                    chat.getRecipientNumber());
                        }
                    }

                    break;

                // ==========================
                // VIEW STORED MESSAGES
                // ==========================
                case 2:

                    System.out.println(
                            archive.showStoredMessages());

                    break;

                // ==========================
                // REPORT
                // ==========================
                case 3:

                    System.out.println(
                            archive.generateReport());

                    break;

                // ==========================
                // UTILITIES
                // ==========================
                case 4:

                    int utilityChoice;

                    do {

                        System.out.println(
                                "\n===== MESSAGE UTILITIES =====");

                        System.out.println(
                                "1. Longest Stored Message");

                        System.out.println(
                                "2. Search Recipient");

                        System.out.println(
                                "3. Search Message ID");

                        System.out.println(
                                "4. Delete By Hash");

                        System.out.println(
                                "5. Back");

                        System.out.print(
                                "Choose Option: ");

                        utilityChoice =
                                Integer.parseInt(
                                        input.nextLine());

                        switch (utilityChoice) {

                            case 1:

                                System.out.println(
                                        archive.findLongestStoredMessage());

                                break;

                            case 2:

                                System.out.print(
                                        "Recipient Number: ");

                                String recipientSearch =
                                        input.nextLine();

                                System.out.println(
                                        archive.findMessagesByRecipient(
                                                recipientSearch));

                                break;

                            case 3:

                                System.out.print(
                                        "Message ID: ");

                                String idSearch =
                                        input.nextLine();

                                System.out.println(
                                        archive.findMessageByID(
                                                idSearch));

                                break;

                            case 4:

                                System.out.print(
                                        "Message Hash: ");

                                String hashSearch =
                                        input.nextLine();

                                System.out.println(
                                        archive.removeMessageByHash(
                                                hashSearch));

                                break;

                            case 5:

                                break;

                            default:

                                System.out.println(
                                        "Invalid option.");
                        }

                    } while (utilityChoice != 5);

                    break;

                // ==========================
                // EXIT
                // ==========================
                case 5:

                    System.out.println(
                            "\nThank you for using QuickChat.");

                    break;

                default:

                    System.out.println(
                            "Invalid option selected.");
            }
        }

        System.out.println(
                "\nStatistics");

        System.out.println(
                "Messages Sent: "
                        + archive.getTotalSent());

        System.out.println(
                "Messages Stored: "
                        + archive.getTotalStored());

        System.out.println(
                "Messages Discarded: "
                        + archive.getTotalDiscarded());

        input.close();
    }
}