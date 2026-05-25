package rangs;



import java.util.Scanner;

public class QuickChatSystem {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        // ==============================
        // USER REGISTRATION SECTION
        // ==============================

        System.out.println("===== QUICKCHAT SYSTEM =====");

        System.out.print("Enter First Name: ");
        String firstName = console.nextLine();

        System.out.print("Enter Surname: ");
        String surname = console.nextLine();

        System.out.print("Enter Username: ");
        String username = console.nextLine();

        System.out.print("Enter Password: ");
        String password = console.nextLine();

        System.out.print("Enter Cellphone Number: ");
        String cellphone = console.nextLine();

        // Create UserAccount Object
        UserAccount account = new UserAccount(
                firstName,
                surname,
                username,
                password,
                cellphone
        );

        // Registration Result
        System.out.println(account.registerAccount());

        // ==============================
        // LOGIN SECTION
        // ==============================

        System.out.println("\n===== LOGIN =====");

        System.out.print("Enter Username: ");
        String loginUser = console.nextLine();

        System.out.print("Enter Password: ");
        String loginPass = console.nextLine();

        boolean loginStatus =
                account.verifyLogin(
                        loginUser,
                        loginPass
                );

        System.out.println(
                account.loginMessage(loginStatus)
        );

        // Continue only if login successful
        if (loginStatus) {

            MessageManager manager =
                    new MessageManager();

            System.out.println(
                    "\nWelcome to QuickChat."
            );

            System.out.print(
                    "How many messages would you like to send? "
            );

            int totalMessages =
                    console.nextInt();

            console.nextLine();

            int menuChoice;

            do {

                // ==============================
                // MAIN MENU
                // ==============================

                System.out.println(
                        "\n===== QUICKCHAT MENU ====="
                );

                System.out.println(
                        "1. Send Messages"
                );

                System.out.println(
                        "2. Display Sent Messages"
                );

                System.out.println(
                        "3. Display Stored Messages"
                );

                System.out.println(
                        "4. Quit"
                );

                System.out.print(
                        "Choose an option: "
                );

                menuChoice =
                        console.nextInt();

                console.nextLine();

                switch (menuChoice) {

                    // ==============================
                    // SEND MESSAGE
                    // ==============================

                    case 1:

                        for (int i = 1;
                             i <= totalMessages;
                             i++) {

                            System.out.println(
                                    "\n===== MESSAGE "
                                            + i
                                            + " ====="
                            );

                            System.out.print(
                                    "Recipient Number: "
                            );

                            String recipient =
                                    console.nextLine();

                            System.out.print(
                                    "Enter Message: "
                            );

                            String text =
                                    console.nextLine();

                            // Create Message Object
                            ChatMessage message =
                                    new ChatMessage(
                                            i,
                                            recipient,
                                            text
                                    );

                            // Validate Recipient
                            if (!message.isRecipientValid()) {

                                System.out.println(
                                        "Recipient number invalid."
                                );

                                continue;
                            }

                            // Validate Message Length
                            if (!message.isMessageValid()) {

                                System.out.println(
                                        "Message exceeds 250 characters."
                                );

                                continue;
                            }

                            // Message Options
                            System.out.println(
                                    "\n1. Send Message"
                            );

                            System.out.println(
                                    "2. Delete Message"
                            );

                            System.out.println(
                                    "3. Save Message"
                            );

                            System.out.print(
                                    "Select Option: "
                            );

                            int option =
                                    console.nextInt();

                            console.nextLine();

                            String result =
                                    message.processMessage(option);

                            System.out.println(result);

                            // Store Message Depending on Option
                            if (option == 1) {

                                manager.captureSentMessage(
                                        message.displayMessage()
                                );
                            }

                            if (option == 3) {

                                manager.captureStoredMessage(
                                        message.displayMessage()
                                );
                            }

                            // Display Message Details
                            System.out.println(
                                    "\n===== MESSAGE DETAILS ====="
                            );

                            System.out.println(
                                    message.displayMessage()
                            );
                        }

                        System.out.println(
                                "\nTotal Messages Sent: "
                                        + ChatMessage.totalMessagesSent()
                        );

                        break;

                    // ==============================
                    // DISPLAY SENT MESSAGES
                    // ==============================

                    case 2:

                        manager.showSentMessages();

                        break;

                    // ==============================
                    // DISPLAY STORED MESSAGES
                    // ==============================

                    case 3:

                        manager.showStoredMessages();

                        break;

                    // ==============================
                    // EXIT PROGRAM
                    // ==============================

                    case 4:

                        System.out.println(
                                "Closing QuickChat System..."
                        );

                        break;

                    // ==============================
                    // INVALID OPTION
                    // ==============================

                    default:

                        System.out.println(
                                "Invalid menu option."
                        );
                }

            } while (menuChoice != 4);
        }

        console.close();
    }
}