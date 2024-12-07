import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Item {
    int id;
    String name;
    double price;

    public Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public abstract String getDetails();
}

class MenuItem extends Item {
    String category;
    String dietaryRestrictions;

    public MenuItem(int id, String name, double price, String category, String dietaryRestrictions) {
        super(id, name, price);
        this.category = category;
        this.dietaryRestrictions = dietaryRestrictions;
    }

    @Override
    public String getDetails() {
        return String.format("%-3d %-20s Php %.2f  %-10s  %-15s", id, name, price, category, dietaryRestrictions);
    }
}

class Table {
    int tableNumber;
    boolean isReserved;

    public Table(int tableNumber, boolean isReserved) {
        this.tableNumber = tableNumber;
        this.isReserved = isReserved;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}

class Order {
    int orderId;
    int tableNumber;
    String customerName;
    List<MenuItem> items;

    private static int orderCounter = 1; 

    public Order(int tableNumber, String customerName) {
        this.orderId = orderCounter++;
        this.tableNumber = tableNumber;
        this.customerName = customerName;
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getOrderId() {
        return orderId;
    }
}

public class RestaurantSystem {

    public static void displayMenu(List<MenuItem> menu) {
        System.out.println("Menu:");
        System.out.println("------------------------------------");
        System.out.println("ID  Name                 Price   Category   Dietary Restrictions");
        System.out.println("------------------------------------");
        for (MenuItem item : menu) {
            System.out.println(item.getDetails());
        }
        System.out.println("------------------------------------");
    }

    public static void reserveTable(List<Table> tables) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter table number to reserve: ");
        int tableNumber = scanner.nextInt();
        if (tableNumber < 1 || tableNumber > tables.size()) {
            System.out.println("Invalid table number. Please try again.");
            return;
        }
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                if (!table.isReserved()) {
                    table.setReserved(true);
                    System.out.println("Table " + tableNumber + " reserved successfully.");
                    return;
                } else {
                    System.out.println("Table " + tableNumber + " is already reserved.");
                    return;
                }
            }
        }
        System.out.println("Table " + tableNumber + " does not exist.");
    }

    public static void placeOrder(List<Table> tables, List<Order> orders, List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter table number: ");
        int tableNumber = scanner.nextInt();
        if (tableNumber < 1 || tableNumber > tables.size()) {
            System.out.println("Invalid table number. Please try again.");
            return;
        }
        boolean tableReserved = false;
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber && table.isReserved()) {
                tableReserved = true;
                break;
            }
        }
        if (!tableReserved) {
            System.out.println("Table " + tableNumber + " is not reserved. Please reserve a table first.");
            return;
        }

        scanner.nextLine();  
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        Order order = new Order(tableNumber, customerName);
        displayMenu(menu);
        int choice;
        do {
            System.out.print("Enter item ID (0 to finish): ");
            choice = scanner.nextInt();
            if (choice < 0 || choice > menu.size()) {
                System.out.println("Invalid item ID. Please try again.");
            } else if (choice == 0) {
                orders.add(order);
                System.out.println("Order placed successfully.");
            } else {
                for (MenuItem item : menu) {
                    if (item.id == choice) {
                        order.addItem(item);
                        break;
                    }
                }
            }
        } while (choice != 0);
    }

    public static void generateReceipt(List<Order> orders) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter order ID to generate receipt: ");
        int orderId = scanner.nextInt();

        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                System.out.println("Receipt for Order ID " + orderId + ":");
                System.out.println("------------------------------------");
                System.out.println("Customer Name: " + order.getCustomerName());
                System.out.println("Table Number: " + order.getTableNumber());
                System.out.println("------------------------------------");
                double totalCost = 0;
                for (MenuItem item : order.getItems()) {
                    System.out.printf("%-20s Php %.2f%n", item.name, item.price);
                    totalCost += item.price;
                }
                System.out.println("------------------------------------");
                System.out.printf("Total Cost: Php %.2f%n", totalCost);
                System.out.println("------------------------------------");
                return;
            }
        }
        System.out.println("Order ID not found.");
    }

    public static void main(String[] args) {
 
        List<Table> tables = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            tables.add(new Table(i, false));
        }

        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem(1, "Cheese Pizza", 9.99, "Entrees", "Vegetarian"));
        menu.add(new MenuItem(2, "Grilled Chicken Salad", 10.99, "Salads", "Gluten-Free"));
        menu.add(new MenuItem(3, "Vegan Burger", 11.49, "Entrees", "Vegan"));
        menu.add(new MenuItem(4, "Fruit Smoothie", 5.49, "Drinks", "Vegan"));
        menu.add(new MenuItem(5, "Quinoa Bowl", 8.99, "Bowls", "Vegetarian"));
        menu.add(new MenuItem(6, "Pasta Primavera", 12.99, "Entrees", "Vegetarian"));
        menu.add(new MenuItem(7, "Spaghetti Bolognese", 13.99, "Entrees", "Meat"));
        menu.add(new MenuItem(8, "Chicken Alfredo", 14.99, "Entrees", "Meat"));
        menu.add(new MenuItem(9, "Caesar Salad", 6.99, "Salads", "Vegetarian"));
        menu.add(new MenuItem(10, "Tomato Soup", 4.99, "Appetizers", "Vegetarian"));
        menu.add(new MenuItem(11, "French Fries", 3.99, "Sides", "Vegetarian"));
        menu.add(new MenuItem(12, "Onion Rings", 5.99, "Sides", "Vegetarian"));
        menu.add(new MenuItem(13, "Grilled Fish", 15.99, "Entrees", "Meat"));
        menu.add(new MenuItem(14, "Chocolate Cake", 6.99, "Desserts", "Vegetarian"));
        menu.add(new MenuItem(15, "Vanilla Ice Cream", 4.49, "Desserts", "Vegetarian"));
        menu.add(new MenuItem(16, "Apple Pie", 5.49, "Desserts", "Vegetarian"));
        menu.add(new MenuItem(17, "Lemonade", 2.49, "Drinks", ""));
        menu.add(new MenuItem(18, "Iced Tea", 2.99, "Drinks", ""));
        menu.add(new MenuItem(19, "Mango Smoothie", 5.99, "Drinks", "Vegan"));
        menu.add(new MenuItem(20, "Pizza Margherita", 8.99, "Entrees", "Vegetarian"));

        List<Order> orders = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Reserve a table");
            System.out.println("2. Place an order");
            System.out.println("3. View orders");
            System.out.println("4. Generate receipt");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    reserveTable(tables);
                    break;
                case 2:
                    placeOrder(tables, orders, menu);
                    break;
                case 3:
                    if (orders.isEmpty()) {
                        System.out.println("No orders placed yet.");
                    } else {
                        for (Order order : orders) {
                            System.out.println("Order ID " + order.getOrderId() + " for table " + order.getTableNumber() + " (" + order.getCustomerName() + "):");
                            for (MenuItem item : order.getItems()) {
                                System.out.println("- " + item.name + " (Php " + item.price + ")");
                            }
                        }
                    }
                    break;
                case 4:
                    if (orders.isEmpty()) {
                        System.out.println("No orders placed yet.");
                    } else {
                        generateReceipt(orders);
                    }
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }
}
