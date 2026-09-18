import java.time.LocalDate;

/**
 * Represents one day's wellness check-in.
 * Ratings are self-reported on simple 1-10 scales; this is a self-awareness
 * tool, not a clinical instrument.
 */
public class WellnessEntry {
    private LocalDate date;
    private int moodRating;   // 1-10
    private int stressRating; // 1-10
    private double sleepHours;
    private String note;

    public WellnessEntry(LocalDate date, int moodRating, int stressRating, double sleepHours, String note) {
        this.date = date;
        this.moodRating = moodRating;
        this.stressRating = stressRating;
        this.sleepHours = sleepHours;
        this.note = note;
    }

    public LocalDate getDate() { return date; }
    public int getMoodRating() { return moodRating; }
    public int getStressRating() { return stressRating; }
    public double getSleepHours() { return sleepHours; }
    public String getNote() { return note; }

    public String toCsv() {
        String safeNote = (note == null || note.isBlank()) ? "-" : note.replace(",", " ").replace("\n", " ");
        return date + "," + moodRating + "," + stressRating + "," + sleepHours + "," + safeNote;
    }

    public static WellnessEntry fromCsv(String line) {
        String[] parts = line.split(",", 5);
        return new WellnessEntry(
            LocalDate.parse(parts[0]),
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2]),
            Double.parseDouble(parts[3]),
            parts.length > 4 ? parts[4] : "-"
        );
    }
}