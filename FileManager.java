import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all reading and writing to the CSV data files.
 * Keeps every other class ignorant of file paths / formats.
 */
public class FileManager {
    private static final String DATA_DIR = "data";
    private static final String USER_FILE = DATA_DIR + "/user.csv";
    private static final String FOOD_FILE = DATA_DIR + "/food.csv";
    private static final String ACTIVITY_FILE = DATA_DIR + "/activity.csv";
    private static final String WELLNESS_FILE = DATA_DIR + "/wellness.csv";

    public FileManager() {
        ensureDataFolder();
    }

    private void ensureDataFolder() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // ---------- User profile ----------

    public void saveUser(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            writer.println(user.toCsv());
        } catch (IOException e) {
            System.out.println("Error saving user profile: " + e.getMessage());
        }
    }

    public User loadUser() {
        File file = new File(USER_FILE);
        if (!file.exists()) return null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null || line.isBlank()) return null;
            return User.fromCsv(line);
        } catch (IOException e) {
            System.out.println("Error loading user profile: " + e.getMessage());
            return null;
        }
    }

    // ---------- Food entries ----------

    public void appendFood(FoodEntry entry) {
        appendLine(FOOD_FILE, entry.toCsv());
    }

    public List<FoodEntry> loadFoods() {
        List<FoodEntry> entries = new ArrayList<>();
        for (String line : readLines(FOOD_FILE)) {
            entries.add(FoodEntry.fromCsv(line));
        }
        return entries;
    }

    // ---------- Activity entries ----------

    public void appendActivity(ActivityEntry entry) {
        appendLine(ACTIVITY_FILE, entry.toCsv());
    }

    public List<ActivityEntry> loadActivities() {
        List<ActivityEntry> entries = new ArrayList<>();
        for (String line : readLines(ACTIVITY_FILE)) {
            entries.add(ActivityEntry.fromCsv(line));
        }
        return entries;
    }

    // ---------- Wellness entries ----------

    public void appendWellness(WellnessEntry entry) {
        appendLine(WELLNESS_FILE, entry.toCsv());
    }

    public List<WellnessEntry> loadWellness() {
        List<WellnessEntry> entries = new ArrayList<>();
        for (String line : readLines(WELLNESS_FILE)) {
            entries.add(WellnessEntry.fromCsv(line));
        }
        return entries;
    }

    // ---------- Shared helpers ----------

    private void appendLine(String path, String line) {
        try (FileWriter fw = new FileWriter(path, true);
             PrintWriter writer = new PrintWriter(fw)) {
            writer.println(line);
        } catch (IOException e) {
            System.out.println("Error writing to " + path + ": " + e.getMessage());
        }
    }

    private List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) return lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading " + path + ": " + e.getMessage());
        }
        return lines;
    }
}