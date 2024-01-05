import java.util.Scanner;

public class SignIn {
    public static void main(String[] args) {
        String username = "Samantha";
        String password = "Java <3";

        /*
         * Task 1
         * 1. Pick up a username and password from the user.
         */
        Scanner scan = new Scanner(System.in);
        System.out.println("\nWelcome to Javagram! Sign in below\n");
        System.out.print("- Username: ");
        String usernameTry = scan.nextLine();
        // pick up username
        System.out.print("- Password: ");
        // pick up password
        String passTry = scan.nextLine();

        while (!username.equals(usernameTry) || !password.equals(passTry)) {
            System.out.println("\nIncorrect, please try again!\n");
            System.out.print("- Username: ");
            usernameTry = scan.nextLine();
            System.out.print("- Password: ");
            passTry = scan.nextLine();
        }

        System.out.println("\nSign in successful. Welcome!");

       
        scan.close();

    }
}
