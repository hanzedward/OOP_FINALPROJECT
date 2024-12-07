Restaurant Management System

I. Project Overview

This project is a Java-based Restaurant Management System designed to manage table reservations, orders, and receipts in a restaurant. The system allows users to:

Reserve tables.
Place orders from a predefined menu.
Generate receipts for completed orders.
View all placed orders.
The program uses Object-Oriented Programming (OOP) principles such as encapsulation, inheritance, and polymorphism to create an efficient and extensible system.

II. Explanation of OOP Principles Applied

Encapsulation: Data such as the table status, menu item details, and order information are encapsulated in respective classes like Table, MenuItem, and Order. Access to these attributes is controlled through getters and setters.

Inheritance: The Item class is an abstract class, and MenuItem extends this class to represent specific items in the menu. This showcases the relationship between general and specific classes.

Polymorphism: The getDetails() method is overridden in the MenuItem class to display menu item details in a formatted string, demonstrating polymorphism in action.

Abstraction: The Item class serves as an abstract representation of items, with specific details abstracted in its subclasses (such as MenuItem). This helps keep the code simple and focused on only relevant data.

III. Integration of SDG (Sustainable Development Goals)

This project aligns with SDG 12: Responsible Consumption and Production. By providing detailed menu descriptions, including dietary restrictions (e.g., vegetarian, gluten-free, vegan), it encourages responsible food choices and helps customers make informed decisions based on their dietary needs. The system can also be extended in the future to include options for food waste reduction or tracking consumption patterns.

IV. Instructions for Running the Program
exp.
1.Clone the Repository:
git clone https://github.com/hanzedward/OOP_FINALPROJECT.git

2.Navigate to the Project Folder:
cd RestaurantSystem

3.Compile the Program:
javac -d bin src/*.java

4.Run the Program:
java -cp bin RestaurantSystem

5.Interacting with the Program: 
1. Reserve a table
2. Place an order
3. View orders
4. Generate receipt
5. Exit
To Reserve a Table:
Type the number corresponding to "1" (Reserve a table) and press Enter.

The program will prompt you to enter the table number you wish to reserve. For example, it might display:
Enter table number to reserve: 
After entering a valid table number, the table will be reserved (if not already reserved).
To Place an Order:
Type "2" (Place an order) and press Enter.

The program will ask for the table number where you want to place the order.
Once a valid table is selected, the system will show the menu.
Type the number corresponding to the menu item ID to add it to your order.
Type 0 to finish placing the order.
To View Orders:
Type "3" (View orders) and press Enter.

The program will display a list of all orders placed so far, showing the order ID, table number, and the items ordered.
To Generate a Receipt:
Type "4" (Generate receipt) and press Enter.

You will be prompted to enter the order ID for which you want to generate a receipt.
The program will display the receipt with the customer name, table number, item names, and total cost.
To Exit the Program:
Type "5" and press Enter to exit the program.

