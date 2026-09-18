import java.time.LocalDate;

/**
 * Represents a single logged activity (calories burned).
 */
public class ActivityEntry {
    private LocalDate date;
    private String activityType;
    private int minutes;
    private int caloriesBurned;

    public ActivityEntry(LocalDate date, String activityType, int minutes, int caloriesBurned) {
        this.date = date;
        this.activityType = activityType;
        this.minutes = minutes;
        this.caloriesBurned = caloriesBurned;
    }

    public LocalDate getDate() { return date; }
    public String getActivityType() { return activityType; }
    public int getMinutes() { return minutes; }
    public int getCaloriesBurned() { return caloriesBurned; }

    public String toCsv() {
        return date + "," + activityType.replace(",", " ") + "," + minutes + "," + caloriesBurned;
    }

    public static ActivityEntry fromCsv(String line) {
        String[] parts = line.split(",");
        return new ActivityEntry(
            LocalDate.parse(parts[0]),
            parts[1],
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3])
        );
    }
}