import java.time.LocalDate;
import java.util.List;
import java.util.Scanner; 

/**
 * VitalLog - a fitness and wellness console tracker.
 * Entry point and menu loop. Ties together the storage layer,
 * summary reporting, and insight generation.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FileManager fileManager = new FileManager();
    private static final SummaryReport summaryReport = new SummaryReport();
    private static final InsightEngine insightEngine = new InsightEngine();
    private static User currentUser;
 
    public static void main(String[] args) {
        System.out.println("=== Welcome to VitalLog ===");
        currentUser = fileManager.loadUser();
        if (currentUser == null) {
            setupProfile();
        } else {
            System.out.println("Welcome back, " + currentUser.getName() + "!");
        }

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": logFood(); break;
                case "2": logActivity(); break;
                case "3": logWellness(); break;
                case "4": viewDailySummary(); break;
                case "5": viewWeeklySummary(); break;
                case "6": viewInsights(); break;
                case "7": running = false; break;
                default: System.out.println("Invalid option, try again.");
            }
        }

        System.out.println("Stay well. See you tomorrow.");
    }

    private static void printMenu() {
        System.out.println("\n===== VitalLog Menu =====");
        System.out.println("1. Log Food");
        System.out.println("2. Log Activity");
        System.out.println("3. Daily Wellness Check-in");
        System.out.println("4. View Daily Summary");
        System.out.println("5. View Weekly Summary");
        System.out.println("6. View Insights");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    private static void setupProfile() {
        System.out.println("\nLet's set up your profile first.");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        int age = readInt("Age: ", 5, 100);
        double weight = readDouble("Weight (kg): ", 20, 300);
        double height = readDouble("Height (cm): ", 100, 250);

        System.out.println("Activity level: 1) Sedentary  2) Light  3) Moderate  4) Very active");
        int activityLevel = readInt("Choose 1-4: ", 1, 4);

        currentUser = new User(name, age, weight, height, activityLevel);
        fileManager.saveUser(currentUser);
        System.out.println("Profile saved. Your daily calorie target is about "
                + Math.round(currentUser.calculateDailyCalorieTarget()) + " cal.");
    }

    private static void logFood() {
        System.out.print("Food name: ");
        String name = scanner.nextLine().trim();
        int calories = readInt("Calories: ", 0, 5000);
        FoodEntry entry = new FoodEntry(LocalDate.now(), name, calories);
        fileManager.appendFood(entry);
        System.out.println("Logged: " + name + " (" + calories + " cal)");
    }

    private static void logActivity() {
        System.out.print("Activity type (e.g. Walking, Gym, Cycling): ");
        String type = scanner.nextLine().trim();
        int minutes = readInt("Duration (minutes): ", 1, 600);
        int calories = readInt("Estimated calories burned: ", 0, 3000);
        ActivityEntry entry = new ActivityEntry(LocalDate.now(), type, minutes, calories);
        fileManager.appendActivity(entry);
        System.out.println("Logged: " + type + " for " + minutes + " min (" + calories + " cal burned)");
    }

    private static void logWellness() {
        int mood = readInt("Mood today (1-10): ", 1, 10);
        int stress = readInt("Stress today (1-10): ", 1, 10);
        double sleep = readDouble("Hours slept last night: ", 0, 24);
        System.out.print("Any note about today? (optional): ");
        String note = scanner.nextLine().trim();

        WellnessEntry entry = new WellnessEntry(LocalDate.now(), mood, stress, sleep, note);
        fileManager.appendWellness(entry);
        System.out.println("Check-in saved. Take care of yourself today.");
    }

    private static void viewDailySummary() {
        List<FoodEntry> foods = fileManager.loadFoods();
        List<ActivityEntry> activities = fileManager.loadActivities();
        summaryReport.printDailySummary(LocalDate.now(), foods, activities,
                currentUser.calculateDailyCalorieTarget());
    }

    private static void viewWeeklySummary() {
        List<FoodEntry> foods = fileManager.loadFoods();
        List<ActivityEntry> activities = fileManager.loadActivities();
        summaryReport.printWeeklySummary(foods, activities, LocalDate.now());
    }

    private static void viewInsights() {
        List<FoodEntry> foods = fileManager.loadFoods();
        List<ActivityEntry> activities = fileManager.loadActivities();
        List<WellnessEntry> wellness = fileManager.loadWellness();

        System.out.println("\n--- Insights ---");
        List<String> insights = insightEngine.generateInsights(
                foods, activities, wellness, currentUser.calculateDailyCalorieTarget());
        for (String insight : insights) {
            System.out.println("- " + insight);
        }
        System.out.println("(Note: these are observations based on your own logged data, not medical advice.)");
    }

    // ---------- Input helpers with basic validation ----------

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value < min || value > max) {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}