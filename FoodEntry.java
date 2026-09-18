import java.time.LocalDate;

/**
 * Represents a single logged food item (calories in).
 */
public class FoodEntry {
    private LocalDate date;
    private String foodName;
    private int calories;

    public FoodEntry(LocalDate date, String foodName, int calories) {
        this.date = date;
        this.foodName = foodName;
        this.calories = calories;
    }

    public LocalDate getDate() { return date; }
    public String getFoodName() { return foodName; }
    public int getCalories() { return calories; }

    public String toCsv() {
        return date + "," + foodName.replace(",", " ") + "," + calories;
    }

    public static FoodEntry fromCsv(String line) {
        String[] parts = line.split(",");
        return new FoodEntry(LocalDate.parse(parts[0]), parts[1], Integer.parseInt(parts[2]));
    }
}