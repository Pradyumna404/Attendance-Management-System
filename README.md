# Attendance App

The Attendance App is a web application developed with Spring Boot and Thymeleaf for managing user attendance. The application allows users to log in, register, sign in/out for the day, and view their attendance reports. Admins can also access a comprehensive report of all users' attendance.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Future Enhancements](#future-enhancements)
- [Contact](#contact)
  
## Features

1. **Login Page**:
   - Users can log in with their credentials.
   - Admins can log in using the default credentials (`admin`, `admin`).
   ![image](https://github.com/user-attachments/assets/15bae9b2-8b39-4b38-98a8-66b101f94134)

2. **Register Page**:
   - New users can register by filling in the required details.
   ![image](https://github.com/user-attachments/assets/8171a503-e9da-4823-982b-81198cfcac66)

3. **Home Attendance Page**:
   - Users can sign in for the current day.
   - If the user has already signed in, a sign-out button will be visible.
   - Users can view their attendance report by clicking the "View Report" button.
   ![image](https://github.com/user-attachments/assets/d760680a-28f7-49e5-ac91-069f3190bdff)

4. **User Attendance Report**:
   - Displays all sign-in/sign-out details of the user.
   - Days when the user was absent are marked accordingly.
   ![image](https://github.com/user-attachments/assets/8f3ddc4e-a0cb-4808-8b06-8d1c91fee3ac)

5. **Admin Report Page**:
   - Available only to admins upon login.
   - Displays a list of all users.
   - Clicking on any user will navigate to the detailed attendance page of the selected user.
   ![image](https://github.com/user-attachments/assets/5982a584-3ddf-4453-9432-31cc3f83c365)

## Technology Stack

- **Backend**: Spring Boot
- **Frontend**: Thymeleaf
- **Database**: MySQL (or any SQL database)

## Setup and Installation

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL Server (or another SQL database)
- IDE (Eclipse recommended)

### Follow the following steps to run the program

1. Import the Attendance-Management-System folder in the Java editor(eclipse).
2. Change the application.properties file according to your database
3. Run the Program.
4. Open your browser and go to "http://localhost:9090".

## Usage

-User Login: Use the login page to access the app.
-Register: New users can register and start using the app immediately.
-Attendance Management: Sign in and sign out daily, and view your attendance history.
-Admin Access: Log in with admin credentials to view all users' attendance.

## Future Enhancements

-Role-based access control improvements.
-Email notifications for attendance reminders.
-Data export options (e.g., CSV, PDF).

## Contact

Author: Pradyumna Nalawade
GitHub: Pradyumna404
Email: pnalawade404@gmail.com
