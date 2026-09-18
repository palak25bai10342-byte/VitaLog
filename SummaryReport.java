import java.time.LocalDate;
import java.util.List;

/**
 * Builds and prints daily / weekly summaries from logged data.
 */
public class SummaryReport {

    public void printDailySummary(LocalDate date, List<FoodEntry> foods,
                                   List<ActivityEntry> activities, double calorieTarget) {
        int intake = 0;
        int burned = 0;

        System.out.println("\n--- Daily Summary: " + date + " ---");

        System.out.println("Food logged:");
        for (FoodEntry f : foods) {
            if (f.getDate().equals(date)) {
                System.out.println("  " + f.getFoodName() + " - " + f.getCalories() + " cal");
                intake += f.getCalories();
            }
        }

        System.out.println("Activity logged:");
        for (ActivityEntry a : activities) {
            if (a.getDate().equals(date)) {
                System.out.println("  " + a.getActivityType() + " (" + a.getMinutes()
                        + " min) - " + a.getCaloriesBurned() + " cal burned");
                burned += a.getCaloriesBurned();
            }
        }

        int net = intake - burned;
        System.out.println("Total intake: " + intake + " cal");
        System.out.println("Total burned: " + burned + " cal");
        System.out.println("Net calories: " + net + " cal");
        System.out.printf("Daily target: %.0f cal%n", calorieTarget);

        if (net > calorieTarget) {
            System.out.println("Status: above target");
        } else {
            System.out.println("Status: at or below target");
        }
    }

    public void printWeeklySummary(List<FoodEntry> foods, List<ActivityEntry> activities, LocalDate today) {
        LocalDate weekStart = today.minusDays(6);
        int totalIntake = 0;
        int totalBurned = 0;
        int activeDays = 0;

        System.out.println("\n--- Weekly Summary: " + weekStart + " to " + today + " ---");

        for (FoodEntry f : foods) {
            if (!f.getDate().isBefore(weekStart) && !f.getDate().isAfter(today)) {
                totalIntake += f.getCalories();
            }
        }

        for (ActivityEntry a : activities) {
            if (!a.getDate().isBefore(weekStart) && !a.getDate().isAfter(today)) {
                totalBurned += a.getCaloriesBurned();
                activeDays++;
            }
        }

        System.out.println("Total intake this week: " + totalIntake + " cal");
        System.out.println("Total burned this week: " + totalBurned + " cal");
        System.out.println("Activity log entries this week: " + activeDays);
        System.out.printf("Average daily intake: %.0f cal%n", totalIntake / 7.0);
    }
}