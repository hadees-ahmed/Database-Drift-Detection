import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //class for login details
        LoginDetails maintainer = new LoginDetails("hadees","abc1");
        //class for dashboard
        Dashboard menu = new Dashboard();
        //class for database scan and to save history
        DatabaseScanner scanner = new DatabaseScanner();
        // class to read saved history
        HistoryReader writtenHistory = new HistoryReader();
        //login methods
        do {
            maintainer.getLogindetails();
            maintainer.matchingDetails();
            if (!Objects.equals(maintainer.providedPass, maintainer.password)) {
                maintainer.resetPassword();
            }
        }while (!Objects.equals(maintainer.providedPass, maintainer.password));
        String userChoice;
        // loop to show dashboard until user choose option to log out
        do {
            System.out.println("\t \t DASHBOARD");
            Scanner userInput = new Scanner(System.in);
            menu.getListOfOptions();
            userChoice = userInput.nextLine();
            // case a of the switch statement process the working of database scanner class in this case the program find the given database files from the computer
            // after that it reads and compare both database files line by line and alert the user in notification panel with the details of the drift the has been detected in databases
            // here after it writes the details of drift along with date and time in history file
            switch (userChoice) {
                case "a", "A" -> {
                    scanner.getDatabase();
                    scanner.makeReadyToScan();
                    while (scanner.myReader.hasNextLine()
                            || scanner.myReader2.hasNextLine()) {
                        scanner.readNextLine();

                        if (scanner.hasComments()){
                            continue;
                        }
                        scanner.detectDrift();
                    }
                    scanner.outputDrift();
                    scanner.writeHistory();
                    if (!scanner.hasDrift) {
                        System.out.println("There is no current drift detected");
                    }
                }
                // Case B simply displays the history of past changes.
                case "b", "B" -> writtenHistory.historyReader();
                // Case C to logout the program
                case "c", "C" -> {
                   // scanner.writeHistory();
                    System.out.println("Thanks for using program BYE BYE!!!");
                }
                default -> {
                    System.out.println("Invalid input please choose from mentioned options(Aa or Bb or Cc)");
                }
            }

        } while (!userChoice.equals("c") && !userChoice.equals("C"));

    }
}
