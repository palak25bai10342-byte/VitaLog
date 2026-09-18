# VitalLog

## A Personal Fitness and Wellness Tracker

VitalLog is a console-based Java application developed to help users record and review their daily fitness and wellness information. The application brings food intake, physical activity, and basic wellness tracking into one system.

The recorded information is stored in CSV files and is used to generate daily and weekly summaries as well as simple pattern-based insights.

---

## Features

* **User Profile Management**

  * Stores name, age, weight, height, and activity level.
  * Calculates an estimated daily calorie target.

* **Food Logging**

  * Records food items and their calorie values.
  * Stores entries with the date of logging.

* **Activity Logging**

  * Records physical activity type, duration, and estimated calories burned.

* **Daily Wellness Check-in**

  * Records mood, stress level, sleep duration, and optional notes.

* **Daily Summary**

  * Displays daily calorie intake, calories burned, net calories, and calorie target.

* **Weekly Summary**

  * Provides an overview of food and activity records for the previous seven days.

* **Smart Insights**

  * Identifies simple patterns in logged food, activity, sleep, mood, stress, and logging consistency.

* **CSV File Storage**

  * Stores user, food, activity, and wellness records in separate CSV files.

* **Input Validation**

  * Checks numeric inputs and acceptable value ranges to handle invalid user input.

---

## Technologies Used

* Java
* Object-Oriented Programming
* Java Collections Framework
* File Handling
* CSV File Storage
* Java Date and Time API
* VS Code
* Git
* GitHub

---

## Project Structure

```text
VitalLog/
│
├── Main.java
├── User.java
├── FoodEntry.java
├── ActivityEntry.java
├── WellnessEntry.java
├── FileManager.java
├── SummaryReport.java
├── InsightEngine.java
│
├── README.md
├── statement.md
├── .gitignore
│
└── data/
    ├── user.csv
    ├── food.csv
    ├── activity.csv
    └── wellness.csv
```

---

## Description of Main Classes

| Class                | Purpose                                                                            |
| -------------------- | ---------------------------------------------------------------------------------- |
| `Main.java`          | Entry point of the application and handles the main menu and user interaction.     |
| `User.java`          | Stores user profile information and calculates the estimated daily calorie target. |
| `FoodEntry.java`     | Represents a food record and its calorie information.                              |
| `ActivityEntry.java` | Represents a physical activity record.                                             |
| `WellnessEntry.java` | Stores daily mood, stress, sleep, and notes.                                       |
| `FileManager.java`   | Handles saving and loading data from CSV files.                                    |
| `SummaryReport.java` | Generates daily and weekly summaries.                                              |
| `InsightEngine.java` | Analyses logged data and generates basic pattern-based insights.                   |

---

## How to Run

### Requirements

* Java Development Kit (JDK)
* Visual Studio Code or any Java-compatible IDE
* Git (optional, for version control)

### Using VS Code

1. Clone or download this repository.
2. Open the `VitalLog` folder in VS Code.
3. Make sure Java is installed and configured.
4. Open the integrated terminal.
5. Compile the project:

```bash
javac *.java
```

6. Run the application:

```bash
java Main
```

---

## Using Command Line

Navigate to the project directory and run:

```bash
javac *.java
java Main
```

The application creates and uses the `data` folder for storing CSV records.

---

## How to Use

When the application starts, the user can create or load a profile.

The main menu provides the following options:

```text
1. Log Food
2. Log Activity
3. Daily Wellness Check-in
4. Daily Summary
5. Weekly Summary
6. Insights
7. Exit
```

The user selects an option and enters the required information. The application validates the input, stores the data when required, and displays the requested result.

---

## Data Storage

VitalLog uses CSV files instead of a database for simple local storage.

The following files are used:

```text
user.csv
food.csv
activity.csv
wellness.csv
```

`FileManager.java` is responsible for reading existing records and appending new records to the appropriate file.

---

## Insights

The `InsightEngine` examines the logged information and looks for basic patterns.

Examples include:

* Repeated days with intake below the estimated calorie target.
* A gap of several days without logged activity.
* Repeated cases where lower sleep is followed by a lower mood score.
* Average stress patterns.
* Consistency of data logging.

The insights are based only on the data entered by the user and are intended for personal tracking rather than medical diagnosis.

---

## Testing

The application was tested using different types of inputs and operations, including:

* Creating and loading a user profile.
* Entering valid food, activity, and wellness records.
* Entering invalid numeric values.
* Checking input range validation.
* Generating daily summaries.
* Generating weekly summaries.
* Generating insights from stored records.
* Saving and loading data through CSV files.
* Running the application after restarting it to verify stored data.

---

## Project Documentation

The repository also contains:

* `statement.md` — Problem Statement, Scope, Target Users, and High-Level Features.
* `README.md` — Project overview, features, technologies, structure, and usage instructions.

---

## Future Enhancements

Possible future improvements include:

* Graphical user interface.
* Database-based storage.
* More detailed progress charts.
* Exporting reports to PDF.
* User authentication.
* Mobile or web-based version.
* More advanced data analysis and personalized recommendations.

---

## Author

**Palak Verma**

**Registration Number:** 25BAI10342

**Branch:** Computer Science and Engineering with specialization in Artificial Intelligence and Machine Learning

**Faculty Guide:** Rizwan Ur Rehman

**University:** VIT Bhopal University
