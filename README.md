📚 Student Management System










🚀 Overview

A Student Management System built using Java Swing and MySQL that allows users to perform full CRUD operations with a clean graphical interface.

✨ Features
➕ Insert new student records
✏️ Update existing data
❌ Delete records
🔍 Search student by ID
📋 Display all records in table format
🎨 User-friendly GUI
🛠️ Tech Stack
Java (Swing & AWT)
JDBC (Java Database Connectivity)
MySQL
🗂️ Database Setup
📌 Database
CREATE DATABASE ak_db;
📌 Table
CREATE TABLE students_data (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(255),
    mobile_no VARCHAR(15),
    email_id VARCHAR(100),
    branch VARCHAR(50),
    year INT,
    age INT
);
⚙️ Configuration

Update database credentials in MainModern.java:

private static final String URL ="jdbc:mysql://localhost:3306/ak_db";
private static final String USERNAME = "root";
private static final String PASSWORD = "MYSQL";
▶️ How to Run
# 1. Clone the repository
git clone https://github.com/your-username/student-management-system.git

# 2. Open project in IDE

# 3. Add MySQL JDBC Driver

# 4. Run the application
MainModern.java
🖥️ Application Modules
Module	Description
Insert	Add new student
Update	Modify existing data
Delete	Remove student
Search	Find student by ID
Display	Show all records
⚠️ Important Notes
MySQL server must be running
Ensure correct DB credentials
Numeric fields (ID, Age, Year) must be valid
📌 Future Improvements
🔐 Login Authentication
🌙 Dark Mode UI
✅ Input Validation
📊 Export to Excel/PDF
🧱 MVC Architecture
🤝 Contributing

Pull requests are welcome! For major changes, open an issue first to discuss what you'd like to change.

📄 License

This project is licensed under the MIT License.

👨‍💻 Author

Avi
