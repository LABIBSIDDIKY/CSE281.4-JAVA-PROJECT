Topic: - LOST AND FOUND MANAGEMENT SYSTEM


Assignment: CSE282 JAVA GROUP PROJECT

Course: CSE 282.4 - PROGRAMMING LANGUAGE II LAB (JAVA)


👥 Group Tehreek e Muhandis

| SL | Student Name              | ID          |
|----|--------------------------|--------------|
| 1  | SAIFUL SIDDIKY LABIB     | 2024100000469|
| 2  | ALID HOSSAIN SHUVO       | 2024100000072|
| 3  | MD. RAYHAN KAMAL         | 2024100000250|
| 4  | ARMAN HOSSAIN SAMI       | 2024100000448|
| 5  | TARAQ AZIZ               | 2024100000244|



🖱️ Project Overview
The Lost and Found Management System (LFMS) is a Java desktop application designed to streamline the process of reporting, tracking, and returning misplaced items within a university or institutional campus.

The system centralizes all reports of lost and found items, allowing users to:

Report lost or found items via a GUI.
Search for lost or found records by keyword, category, or date.
Submit claims for items believed to belong to them.
Administer workflows through verification, approval, or rejection of claims.



🖱️ Project Objectives
Create a centralized database for lost and found items.
Implement all four OOP principles (Encapsulation, Abstraction, Inheritance, Polymorphism).
Provide a smooth graphical user interface using Java Swing.
Design a secure and user-friendly system that validates user input and manages errors gracefully.
Ensure admins have full control over stored records and claims.


⚙️ System Theory
In most institutions, managing lost and found items is handled informally through notices or social media posts. This leads to loss of accountability and mismatched claims. LFMS overcomes these issues through:

Object-Oriented Architecture: clear class relationships (Item, User, Admin, ClaimRequest).
Structured Data Flow: from user input → validation → database → admin verification.
Encapsulated Modules: each class handles a defined responsibility to reduce coupling.




|PRINCIPLES                |IMPLEMENTATION                                                                      |
--------------------------|-------------------------------------------------------------------------------------|
|Encapsulation            |Private fields with getters/setters in every entity (e.g., Item, User, ClaimRequest) |
| Abstraction             |Abstract base class Item contains shared structure for LostItem and FoundItem        |
| Inheritance             |Class hierarchies: Admin extends User, LostItem/FoundItem extend Item                |
| Polymorphism            |Overridden displayDetails() methods for both LostItem and FoundItem                  |





🖱️ Methodology
1. Requirement Analysis
Understand the problem of lost and found tracking within the university.
Define user roles: User and Admin.
Specify functional requirements (report, search, claim, verify).
2. System Design
Frontend: Built using Java Swing with JTabbedPane separating the main functions.
Backend Services: ItemService and ClaimService handle data operations and validations.
Exception Handling: Custom exceptions (InvalidClaimException, DuplicateItemException, etc.) ensure robust error reporting.


3. Object Model
   
|CLASS                    |DESCRIPTION                                                                          |
--------------------------|-------------------------------------------------------------------------------------|
| App                     |The main driver function that run all the other classes and methods  .               |
| Item (abstract)         |Base class defining shared item fields.                                              |
| LostItem                |Extends Item with lostLocation, rewardOffered, contactInfo.                          |
| FoundItem               |Extends Item with foundLocation, storedAt, currentHolder.                            |
| User                    |Holds personal details and item-related actions.                                     |
| Admin                   |Inherits from User with additional privileges (approve/reject claims).               |
| ClaimStatus             |To verify if the item has been claimed, Approved or Pending                          |
| ClaimRequest            |Links users and items through claim processing and validation.                       |
| authService             | To make sure that users don't accidentally get admin access  .                      |
| ClaimService            |Making sure that the claim process is well documented and valid via exception   .    |
| itemService             |Ensuring that the item input is valid and no duplicates are made via exception  .    |
| userService             |Ensuring that the user input is valid  and no duplicates are made via exception .    |




4. Implementation
Developed in Java SE using OOP design.
Uses try-catch blocks and custom exceptions for input safety.
GUI forms mapped to backend services ensuring smooth communication.
5. Testing & Validation
GUI event functions validated for both success and error scenarios.
Admin claim approval workflow tested with edge cases (duplicate items, missing user IDs).


🧮 Functional Modules
Register Lost Items — users register details of missing belongings.
Register Found Items — record items discovered on campus.
Search Records — keyword/category/date filters.
Submit Claims — users claim an item believed to be theirs.
Admin Dashboard — manage items, view activity logs, approve/deny claims.


💡 Exception Handling
Custom exceptions ensure data validity and user feedback:

DuplicateItemException → when adding an existing item.
InvalidClaimException → when re-claiming an already approved item.
EmptyFieldException → when form fields are missing.
UserNotFoundException → thrown on invalid login or user ID.
Friendly user-facing messages shown via JOptionPane.




🧾 Conclusion
The Lost and Found Management System successfully demonstrates all essential OOP and Java programming principles while solving a real-world problem on campus. It provides an organized, scalable, and user-centered solution for managing lost and found items efficiently.

This project serves as a model for structured software design effectively integrating object-oriented programming with GUI development and robust exception management.



⏳ Future Enhancements
Integration with MySQL or SQLite for persistent database storage.
Add email notifications for claim status updates.
Implement image upload for item verification.
Extend system to a web or mobile platform for cross-device accessibility.
