import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;
import java.util.Scanner;

public class DatabaseScanner {
    public File database;
    public File database1;
    public Scanner myReader;
    public Scanner myReader2;
    public String line1;
    public String line2;
    public boolean hasDrift;
    public Instant time;
    public String drifts;
// getDatabase() method is used to search the database file to be compared
    public void getDatabase() throws IOException {
        File database = new File("src/database.sql");
        File database1 = new File("src/database1.sql");
        this.database = database;
        this.database1 = database1;
    }
    public void makeReadyToScan() throws IOException {
        Scanner myReader = new Scanner(this.database);
        Scanner myReader2 = new Scanner(this.database1);

        this.myReader = myReader;
        this.myReader2 = myReader2;

        this.drifts = "";
    }
    //scanDatabase() this method is used to read both database files line by line
    public void readNextLine() {
        this.line1 = "";
        this.line2  = "";

        if (this.myReader.hasNextLine()) {
            this.line1 = this.myReader.nextLine();
        }
        if (this.myReader2.hasNextLine()) {
            this.line2 = this.myReader2.nextLine();
        }
    }
    //hasComments() this method is used to ignore comments in database filesa
    public boolean hasComments() {
        //to ignore comments starting with "#" , "/*" and "--"
        return (
                this.line1.startsWith("#")
                && this.line2.startsWith("#")
        ) || (
                this.line1.startsWith("--")
                && this.line2.startsWith("--")
        ) || (
                this.line1.startsWith("/*")
                && this.line2.startsWith("/*")
        );

    }
    //detectDrift() this method compares both database lines by ignoring the spaces,upper and lowercase and detect if there is any change in both lines or not
    // if it detects the drift in any line it saves those lines
    public void detectDrift() {
        if (!Objects.equals(this.line2.replace(" ", "").toLowerCase(), this.line1.replace(" ", "").toLowerCase())) {
            //to record time and date
            //here I recorded the time and date of drift detection to use it in future in history log
            this.time = Instant.now(); //
            this.hasDrift = true;

            this.drifts += "Drift detected in this line\n Date and Time-->";
            this.drifts += this.time;
            this.drifts += "\nFirst database line('database.sql')-->";
            this.drifts += line1;
            this.drifts += "\nSecond database line('database1.sql')-->  ";
            this.drifts += line2;
        }
    }
    //outputDrift() method is used to display the drift details along with date and time
    public void outputDrift(){
        System.out.println(this.drifts);
    }
    // writeHistory() method is used to write the details of drift along with date and time in history file
    public void writeHistory() throws IOException {
        FileWriter historyWriter = new FileWriter("history.sql", true);
        historyWriter.write(this.drifts);
        historyWriter.close();
    }
}

