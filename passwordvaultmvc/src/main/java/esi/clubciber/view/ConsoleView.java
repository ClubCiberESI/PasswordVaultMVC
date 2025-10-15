package esi.clubciber.view;

import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        String option;

        //TODO: login user

        do {
            displayMenu();
            option = getUserInput();
            switch (option) {
                case "1":
                    // TODO: Add password entry
                    displayMessage("Adding password entry...");
                    break;
                case "2":
                    // TODO: View password entries
                    displayMessage("Viewing password entries...");
                    break;
                case "3":
                    //TODO: logout user
                    displayMessage("Exiting...");
                    break;
                default:
                    displayMessage("Invalid option. Try again.");
                    break;
            }
        } while (!"3".equals(option));
    }

    public void displayMenu() {
        System.out.println("Password Vault Menu:");
        System.out.println("1. Add Password Entry");
        System.out.println("2. View Password Entries");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void displayPasswordEntries() {
        // TODO: Implement display logic
        displayMessage("Displaying entries...");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}