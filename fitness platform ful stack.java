import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main class representing the Fitness and Wellness Platform
public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Fitness & Wellness Platform");

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Registration process
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    userService.registerUser(regUsername, regPassword);
                    break;
                case 2:
                    // Login process
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (userService.validateUser(loginUsername, loginPassword)) {
                        System.out.println("Login Successful!");
                        // Call other functionalities such as tracking workouts, etc.
                        showFitnessDashboard(scanner);
                    } else {
                        System.out.println("Invalid Credentials!");
                    }
                    break;
                case 3:
                    // Exit the program
                    System.out.println("Thank you for using the Fitness & Wellness Platform.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Display basic fitness tracking options
    private static void showFitnessDashboard(Scanner scanner) {
        System.out.println("\n--- Fitness Dashboard ---");
        System.out.println("1. Track Workout");
        System.out.println("2. View Progress");
        System.out.println("3. Logout");
        System.out.print("Choose an option: ");
        int dashboardChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (dashboardChoice) {
            case 1:
                System.out.println("Workout tracked successfully!");
                break;
            case 2:
                System.out.println("Progress: You have completed 5 workouts this week.");
                break;
            case 3:
                System.out.println("Logged out successfully!");
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }
}

// User class to hold user information
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// Service class to handle user registration and validation
class UserService {
    private List<User> users = new ArrayList<>();

    // Method to register a new user
    public void registerUser(String username, String password) {
        if (findUser(username) == null) {
            users.add(new User(username, password));
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Username already exists. Please try another one.");
        }
    }

    // Method to validate user login
    public boolean validateUser(String username, String password) {
        User user = findUser(username);
        return user != null && user.getPassword().equals(password);
    }

    // Helper method to find a user by username
    private User findUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }
}