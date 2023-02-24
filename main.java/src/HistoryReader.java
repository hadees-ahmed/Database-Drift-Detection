import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HistoryReader {
    public void historyReader() throws IOException {
        File history = new File("history.sql");
        // validation if file is empty
        if (history.length() == 0){
            System.out.println("There are No drifts detected in Past");
        } else {
            //Read the history file and show history
            System.out.println("\t\tPAST CHANGES");
            try {
                Scanner historyReader = new Scanner(history);
                while (historyReader.hasNextLine()) {
                    String data = historyReader.nextLine();
                    System.out.println(data);
                }
                historyReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("file not found.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
