Topic: - LOST AND FOUND MANAGEMENT SYSTEM


Assignment: CSE281 JAVA GROUP PROJECT

Course: CSE 281.4 - PROGRAMMING LANGUAGE II (JAVA)


👥 Group EXCEED

| SL | Student Name              | ID          |
|----|--------------------------|--------------|
| 1  | SAIFUL SIDDIKY LABIB     | 2024100000469|
| 2  | ALID HOSSAIN SHUVO       | 2024100000072|
| 3  | MD. RAYHAN KAMAL         | 2024100000250|
| 4  | ARMAN HOSSAIN SAMI       | 2024100000448|
| 5  | TARAQ AZIZ               | 2024100000244|



🧩 Project Overview
The Lost and Found Management System (LFMS) is a Java desktop application designed to streamline the process of reporting, tracking, and returning misplaced items within a university or institutional campus.

The system centralizes all reports of lost and found items, allowing users to:

Report lost or found items via a GUI.
Search for lost or found records by keyword, category, or date.
Submit claims for items believed to belong to them.
Administer workflows through verification, approval, or rejection of claims.



🎯 Project Objectives
Create a centralized database for lost and found items.
Implement all four OOP principles (Encapsulation, Abstraction, Inheritance, Polymorphism).
Provide a smooth graphical user interface using Java Swing.
Design a secure and user-friendly system that validates user input and manages errors gracefully.
Ensure admins have full control over stored records and claims.


⚙️ System Theory
In most institutions, managing lost and found items is handled informally — through notices or social media posts. This leads to loss of accountability and mismatched claims. LFMS overcomes these issues through:

Object-Oriented Architecture: clear class relationships (Item, User, Admin, ClaimRequest).
Structured Data Flow: from user input → validation → database → admin verification.
Encapsulated Modules: each class handles a defined responsibility to reduce coupling.




|PRINCIPLES                |IMPLEMENTATION                                                                      |
--------------------------|-------------------------------------------------------------------------------------|
|Encapsulation            |Private fields with getters/setters in every entity (e.g., Item, User, ClaimRequest) |
| Abstraction             |Abstract base class Item contains shared structure for LostItem and FoundItem        |
| Inheritance             |Class hierarchies: Admin extends User, LostItem/FoundItem extend Item                |
| Polymorphism            |Overridden displayDetails() methods for both LostItem and FoundItem                  |





