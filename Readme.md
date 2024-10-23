Real-Time Chat Application
A full-stack real-time chat application built using React.js, Spring Boot, MySQL, and WebSocket. The app provides features like authentication, JWT-based security, email OTP validation, individual and group messaging with multimedia support, and more. Ideal for demonstrating your skills in developing scalable, real-time applications with modern technologies.

Features
1. User Authentication & Authorization
JWT-based Authentication: Secure login and user session management using JSON Web Tokens (JWT).
Password Hashing: All passwords are securely hashed using BCrypt.
2. Email OTP Verification
Email Validation: Users must verify their email by entering a One-Time Password (OTP) sent via email.
OTP Expiration: OTPs expire after 10 minutes, and users can request a new OTP if needed.
Scheduled Cleanup: Expired OTPs are automatically cleaned from the database using a MySQL trigger that runs hourly.
3. Real-Time Messaging
WebSocket-based Messaging: Real-time communication is enabled using WebSockets for seamless individual and group chats.
Multimedia Support: Users can send images and videos in chat.
Search Functionality: Users can search for others by first name, last name, or email to start conversations.
Pagination in Chats: Initially loads the most recent N messages, with the option to load more as the user scrolls.
4. Group Management
Create Groups: Users can create groups, add members, and assign admin roles.
Manage Group Membership: Group admins can remove users or delete the entire group if necessary.
5. User Interface
Homepage: Displays all conversations, sorted by the latest message timestamp.
Responsive Design: Built with React.js for a dynamic, responsive UI.


Tech Stack

Frontend:

React.js: Dynamic UI for chat, group management, and user interaction.
WebSocket: Enables real-time communication between users.
Axios: For making secure API calls to the backend.

Backend:

Spring Boot: RESTful API services for handling user authentication, messaging, group management, and more.
Spring Security: Provides JWT-based security and session management.
Spring Data JPA: For ORM and database interaction with MySQL.
WebSocket (Spring): Backend support for real-time WebSocket messaging.

Database:

MySQL: Relational database for storing user data, messages, groups, and chat information.
Spring Data JPA: Handles ORM (Object Relational Mapping) between Java objects and MySQL tables.

DevOps & Tools:

Maven: Dependency management and project build tool for the Spring Boot backend.
Git: Version control system for managing codebase.
Postman: API testing and development.
