package files.view;

import java.util.Scanner;

public class OrderViewLauncher {
    public static Scanner scanner = new Scanner(System.in);
    public static void run() {
        OrderView orderView = new OrderView();
        boolean is = false;
        do {
            Menu.orderMenu();
            System.out.println("\n Select Function");
            System.out.print("☛ ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    orderView.addOrder();
                    break;
                case 2:
                    orderView.showAllOrder();
                    break;
                case 3:
                    Menu.MainMenu();
                    break;
                case 4:
                    Menu.exit();
                    System.exit(0);
                    break;
                default:
                    OrderViewLauncher.run();
                    break;
            }
        } while (!is);
    }

}
