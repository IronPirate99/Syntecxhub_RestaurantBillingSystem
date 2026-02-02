import java.util.*;


class main {
   
  
    
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

    public class MenuItem {
        String Name;
        double  Price;

        public MenuItem (String name, double price)
        {
            this.Name = name;
            this.Price = price;

        }

        public String getName() {
            return Name;
        }

        public double getPrice() {
            return Price;
        }
    }

    

    public class Menu 
    {

        private ArrayList<MenuItem> items;

        public void AddItem(MenuItem item){ items.add(item); }

        public void removeItem(String itemName){ items.removeIf(item -> item.getName().equals(itemName));}

        public void displayMenu(){
        System.out.println("\n----- MENU -----");
        for (MenuItem item : items) {
            System.out.printf("%d. %s - R%.2f%n",
                     item.getName(), item.getPrice());
        }
    }
    }



}
       

