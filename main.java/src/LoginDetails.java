import java.util.Objects;
import java.util.Scanner;
public class LoginDetails {
    public String user;
    public String password;
    public String providedPass;
    public String providedUser;
    LoginDetails(String user, String password) {
        this.user = user;
        this.password = password;
    }
    public void getLogindetails() {
        System.out.println("\t\t\t\tWelcome To The Database Drift Detector\n");
        System.out.println("\t\tLOGIN\n");
        //takes username
        System.out.println("User name:");
        Scanner user = new Scanner(System.in);
        //takes password
        this.providedUser = user.nextLine();
        System.out.println("Password:");
        Scanner pass = new Scanner(System.in);
        this.providedPass = pass.nextLine();
    }
            public void matchingDetails() {
                //matches username and password with the correct details
                if (Objects.equals(this.providedUser, this.user) && Objects.equals(this.providedPass, this.password)) {
                    System.out.println("Correct User and password");
                } else {
                    System.out.println("Incorrect Username or Password");
                }
            }
            public void resetPassword(){
                //allow user to reset password
                Scanner choice = new Scanner(System.in);
                System.out.println("Do you want to reset your password?(y or n)");
                String userChoice = choice.nextLine();
                if (Objects.equals(userChoice, "y")) {
                    Scanner newpass = new Scanner(System.in);
                    System.out.println("Enter new pass");
                    this.password = newpass.nextLine();
                    System.out.println("Successfully updated the password");
                } else if (Objects.equals(userChoice, "n")) {
                    System.out.println("Try again then");
                } else {
                    System.out.println("Invalid input");
                }
            }
        }




