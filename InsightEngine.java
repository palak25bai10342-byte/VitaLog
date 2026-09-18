import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Looks across food, activity, and wellness logs together and flags simple,
 * plain-language patterns. Every message here is an observation, not a
 * diagnosis or medical claim.
 */
public class InsightEngine {

    public List<String> generateInsights(List<FoodEntry> foods, List<ActivityEntry> activities,
                                          List<WellnessEntry> wellnessEntries, double calorieTarget) {
        List<String> insights = new ArrayList<>();

        insights.addAll(checkCalorieDeficitStreak(foods, calorieTarget));
        insights.addAll(checkSedentaryStreak(activities));
        insights.addAll(checkSleepMoodPattern(wellnessEntries));

        if (insights.isEmpty()) {
            insights.add("No strong patterns yet - keep logging daily and check back in a few days.");
        }
        return insights;
    }

    /**
     * Flags 3+ consecutive days where total logged intake fell notably below target.
     */
    private List<String> checkCalorieDeficitStreak(List<FoodEntry> foods, double calorieTarget) {
        List<String> results = new ArrayList<>();
        Map<LocalDate, Integer> dailyIntake = new HashMap<>();
        for (FoodEntry f : foods) {
            dailyIntake.merge(f.getDate(), f.getCalories(), Integer::sum);
        }

        List<LocalDate> deficitDays = new ArrayList<>();
        for (Map.Entry<LocalDate, Integer> entry : dailyIntake.entrySet()) {
            if (entry.getValue() < calorieTarget * 0.8) {
                deficitDays.add(entry.getKey());
            }
        }
        deficitDays.sort(LocalDate::compareTo);

        int streak = 1;
        for (int i = 1; i < deficitDays.size(); i++) {
            if (deficitDays.get(i - 1).plusDays(1).equals(deficitDays.get(i))) {
                streak++;
            } else {
                streak = 1;
            }
            if (streak >= 3) {
                results.add("You've logged noticeably under your calorie target for " + streak +
                        " days in a row (ending " + deficitDays.get(i) + "). Worth keeping an eye on.");
                break;
            }
        }
        return results;
    }

    /**
     * Flags 3+ consecutive days with no logged activity.
     */
    private List<String> checkSedentaryStreak(List<ActivityEntry> activities) {
        List<String> results = new ArrayList<>();
        if (activities.isEmpty()) return results;

        List<LocalDate> activeDates = new ArrayList<>();
        for (ActivityEntry a : activities) {
            if (!activeDates.contains(a.getDate())) {
                activeDates.add(a.getDate());
            }
        }
        activeDates.sort(LocalDate::compareTo);

        LocalDate last = activeDates.get(activeDates.size() - 1);
        LocalDate today = LocalDate.now();
        long gap = java.time.temporal.ChronoUnit.DAYS.between(last, today);

        if (gap >= 3) {
            results.add("No activity logged for " + gap + " days (last entry: " + last + "). " +
                    "Might be worth a short walk today.");
        }
        return results;
    }

    /**
     * Flags a repeated pattern: a low-sleep night followed by a lower mood the next day.
     */
    private List<String> checkSleepMoodPattern(List<WellnessEntry> entries) {
        List<String> results = new ArrayList<>();
        if (entries.size() < 3) return results;

        List<WellnessEntry> sorted = new ArrayList<>(entries);
        sorted.sort((a, b) -> a.getDate().compareTo(b.getDate()));

        int matches = 0;
        for (int i = 0; i < sorted.size() - 1; i++) {
            WellnessEntry current = sorted.get(i);
            WellnessEntry next = sorted.get(i + 1);
            boolean consecutiveDays = current.getDate().plusDays(1).equals(next.getDate());
            if (consecutiveDays && current.getSleepHours() < 6.0 && next.getMoodRating() < 5) {
                matches++;
            }
        }

        if (matches >= 2) {
            results.add("Low-sleep nights (under 6 hrs) have been followed by lower mood scores " +
                    matches + " times recently. Sleep might be worth prioritizing.");
        }
        return results;
    }
}