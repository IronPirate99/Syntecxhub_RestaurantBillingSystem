import java.util.ArrayList;
import java.util.Scanner;

class MenuItem {
    private int id;
    private String name;
    private double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Menu {
    private ArrayList<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(int id) {
        items.removeIf(item -> item.getId() == id);
    }

    public MenuItem getItemById(int id) {
        for (MenuItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void displayMenu() {
        System.out.println("\n----- MENU -----");
        for (MenuItem item : items) {
            System.out.printf("%d. %s - R%.2f%n", item.getId(), item.getName(), item.getPrice());
        }
    }
}

class OrderItem {
    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }

    public String getItemName() {
        return item.getName();
    }

    public int getQuantity() {
        return quantity;
    }
}

class Bill {
    private ArrayList<OrderItem> orders = new ArrayList<>();
    private static final double GST_RATE = 0.15;

    public void addOrder(OrderItem order) {
        orders.add(order);
    }

    public double calculateSubtotal() {
        double total = 0;
        for (OrderItem order : orders) {
            total += order.getTotalPrice();
        }
        return total;
    }

    public double calculateGST() {
        return calculateSubtotal() * GST_RATE;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateGST();
    }

    public void printReceipt() {
        System.out.println("\n----- ITEMIZED RECEIPT -----");
        for (OrderItem order : orders) {
            System.out.printf("%s x%d = R%.2f%n", order.getItemName(), order.getQuantity(), order.getTotalPrice());
        }
        System.out.printf("Subtotal: R%.2f%n", calculateSubtotal());
        System.out.printf("GST (15%%): R%.2f%n", calculateGST());
        System.out.printf("TOTAL: R%.2f%n", calculateTotal());
        System.out.println("----------------------------");
    }
}

public class RestaurantBillingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        Bill bill = new Bill();

        menu.addItem(new MenuItem(1, "Burger", 45.00));
        menu.addItem(new MenuItem(2, "Pizza", 80.00));
        menu.addItem(new MenuItem(3, "Cold Drink", 20.00));

        int choice;

        do {
            System.out.println("\n1. View Menu");
            System.out.println("2. Add Menu Item");
            System.out.println("3. Remove Menu Item");
            System.out.println("4. Place Order");
            System.out.println("5. Print Bill");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    menu.displayMenu();
                    break;
                case 2:
                    System.out.print("Enter item ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    menu.addItem(new MenuItem(id, name, price));
                    break;
                case 3:
                    System.out.print("Enter item ID to remove: ");
                    menu.removeItem(scanner.nextInt());
                    break;
                case 4:
                    menu.displayMenu();
                    System.out.print("Enter item ID: ");
                    int itemId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int qty = scanner.nextInt();
                    MenuItem selectedItem = menu.getItemById(itemId);
                    if (selectedItem != null) {
                        bill.addOrder(new OrderItem(selectedItem, qty));
                    } else {
                        System.out.println("Invalid item ID.");
                    }
                    break;
                case 5:
                    bill.printReceipt();
                    break;
                case 0:
                    System.out.println("Thank you! Visit again.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        scanner.close();
    }
}