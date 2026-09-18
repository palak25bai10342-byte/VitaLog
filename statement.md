# VitalLog

## A Personal Fitness and Wellness Tracker

VitalLog is a console-based Java application designed to help users maintain a simple record of their daily food intake, physical activities, and wellness information. The system stores the recorded information in CSV files and uses it to generate daily and weekly summaries along with basic observations about the user's logged patterns.

## 1. Problem Statement

Maintaining a consistent record of daily health and fitness activities can be difficult when food intake, physical activity, sleep, mood, and stress are recorded separately or not recorded at all. Without organized records, it becomes difficult for a user to review their daily habits and identify simple patterns over time.

VitalLog addresses this problem by providing a single Java-based system where users can record their food intake, physical activities, and daily wellness information. The collected data is stored systematically and processed to provide summaries and basic pattern-based insights.

## 2. Scope of the Project

The project focuses on personal tracking of basic fitness and wellness information through a console-based application.

The scope includes:

* Creating and maintaining a user profile.
* Calculating an estimated daily calorie target from the user's profile information.
* Recording food items and their calorie values.
* Recording physical activities, duration, and estimated calories burned.
* Recording daily mood, stress, sleep, and optional notes.
* Generating daily and weekly summaries from the stored records.
* Generating basic insights from logged food, activity, and wellness data.
* Storing and retrieving information using CSV files.
* Validating user input to reduce invalid or incorrect entries.

The project is intended for personal tracking and does not provide medical diagnosis or professional health advice.

## 3. Target Users

VitalLog is intended for:

* Students who want to maintain a simple record of their daily habits.
* Individuals interested in tracking food and physical activity.
* Users who want to monitor basic wellness information such as sleep, mood, and stress.
* Beginners who prefer a lightweight console-based tracking system instead of a complex fitness application.

## 4. High-Level Features

### 4.1 User Profile Management

Allows the user to enter and store basic profile information such as name, age, weight, height, and activity level. The information is used to calculate an estimated daily calorie target.

### 4.2 Food Logging

Allows users to record food items along with their calorie values. The entries are stored with the date for later summaries and analysis.

### 4.3 Activity Logging

Allows users to record physical activities, their duration, and estimated calories burned.

### 4.4 Daily Wellness Check-in

Allows users to record daily mood, stress level, sleep duration, and optional notes.

### 4.5 Daily and Weekly Reports

Processes stored food and activity records to display daily and weekly information such as calorie intake, calories burned, net calories, and activity details.

### 4.6 Smart Insights

Analyses the user's recorded data to identify simple patterns such as repeated low-calorie intake, gaps in activity, and relationships between sleep and mood.

### 4.7 CSV-Based File Storage

Uses separate CSV files to store user, food, activity, and wellness records so that information remains available after the application is closed.

### 4.8 Input Validation

Checks numeric inputs and predefined ranges to prevent invalid values from being accepted by the system.
