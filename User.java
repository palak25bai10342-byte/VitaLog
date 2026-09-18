/**
 * Represents the app's single user profile.
 * Used to calculate a rough daily calorie target (TDEE).
 */
public class User {
    private String name;
    private int age;
    private double weightKg;
    private double heightCm;
    private int activityLevel; // 1=sedentary, 2=light, 3=moderate, 4=active

    public User(String name, int age, double weightKg, double heightCm, int activityLevel) {
        this.name = name;
        this.age = age;
        this.weightKg = weightKg;
        this.heightCm = heightCm;
        this.activityLevel = activityLevel;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getWeightKg() { return weightKg; }
    public double getHeightCm() { return heightCm; }
    public int getActivityLevel() { return activityLevel; }

    /**
     * Rough BMR using a simplified Mifflin-St Jeor formula (gender-neutral average).
     */
    public double calculateBMR() {
        return (10 * weightKg) + (6.25 * heightCm) - (5 * age);
    }

    /**
     * Applies an activity multiplier to BMR to estimate daily calorie needs (TDEE).
     */
    public double calculateDailyCalorieTarget() {
        double multiplier;
        switch (activityLevel) {
            case 1: multiplier = 1.2; break;   // sedentary
            case 2: multiplier = 1.375; break; // light activity
            case 3: multiplier = 1.55; break;  // moderate activity
            case 4: multiplier = 1.725; break; // very active
            default: multiplier = 1.2;
        }
        return calculateBMR() * multiplier;
    }

    /**
     * Serializes this user to a single CSV line for storage.
     */
    public String toCsv() {
        return name + "," + age + "," + weightKg + "," + heightCm + "," + activityLevel;
    }

    /**
     * Parses a User back from a CSV line.
     */
    public static User fromCsv(String line) {
        String[] parts = line.split(",");
        return new User(
            parts[0],
            Integer.parseInt(parts[1]),
            Double.parseDouble(parts[2]),
            Double.parseDouble(parts[3]),
            Integer.parseInt(parts[4])
        );
    }
}