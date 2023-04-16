# Automated Payroll System

This project is an automated payroll system developed by a team of three members. The system aims to simplify and streamline the payroll process for businesses.

## Team Members

1. Φίλιππος Παπαδάκης ΑΜ 4453
2. Ευθύμης Μητκούσης ΑΜ 4493
3. Ευγενία Κουναλάκη ΑΜ 4365

## How to Run with XAMPP

To run the automated payroll system with XAMPP, follow these steps:

1. Clone the project repository to your local machine.
2. Navigate to the project directory in your terminal/command prompt.
3. Install the required packages by running the command `gradle build`.
4. Start the Apache and MySQL servers in XAMPP.
5. Open your web browser and navigate to `http://localhost/phpmyadmin/`.
6. Click on the "New" button in the left sidebar to create a new database.
7. Enter a name for the new database (e.g. "payroll") and click the "Create" button.
8. Update the database connection details in the `PayrollDatabase.java` file with your XAMPP server details.
9. Compile the `Main.java` file by running the command `gradle compileJava`.
10. Start the application by running the command `gradle run`.
11. The application will now be running and you can interact with it via the terminal/command prompt.

That's it! You should now be able to use the automated payroll system with XAMPP to manage payroll for your business. If you encounter any issues or have any questions, feel free to reach out to one of our team members listed above for assistance.

## Required Packages

The following packages are required to run the automated payroll system with XAMPP:

- MySQL Connector/J (version 8.0.22 or higher)

These packages are included in the `build.gradle` file and will be automatically installed when running the `gradle build` command. If you encounter any issues with package installation, please refer to the Gradle documentation or reach out to one of our team members for assistance.
