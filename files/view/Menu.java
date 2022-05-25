package files.view;

import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    private static ForgotPassword forgotPassword;


    public static void mainMenu() {
        System.out.println("\t❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ MAIN MENU ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂");
        System.out.println("\t❂                                             ❂");
        System.out.println("\t❂           1. Accounts Management            ❂");
        System.out.println("\t❂           2. Products Management            ❂");
        System.out.println("\t❂           3. Order Management               ❂");
        System.out.println("\t❂           4. Log Out                        ❂");
        System.out.println("\t❂           5. Exit                           ❂");
        System.out.println("\t❂                                             ❂");
        System.out.println("\t❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂");
    }

    public static void login() {
        AdminView adminView = new AdminView();
        adminView.adminLogin();
        MainMenu();
    }

    public static void MainMenu() {
        boolean is = false;
        do {
            try {
                mainMenu();
                System.out.println("\nSelect Function");
                System.out.print("☛ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        AdminViewLaucher.run();
                        break;
                    case 2:
                        TechsProductView.run();
                        break;
                    case 3:
                        OrderViewLauncher.run();
                        break;
                    case 4:
                        login();
                        break;
                    case 5:
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        Menu.MainMenu();
                        is = true;
                        break;
                }
            } catch (Exception E) {
                System.out.println("Incorrect! Please Try Again!");
            }
        } while (!is);
    }

    public static void firstMenu() {
        do {
            try {
                firstLauch();
                System.out.println("\nSelect Function");
                System.out.print("☛ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        ForgotPassword.menuForgot();
                        break;
                    default:
                        System.out.println("Incorrect! Please Try Again!!");
                }
                }catch(Exception e) {
                System.out.println("Incorrect! Please Try Again!!");
            }
        }while (true) ;
    }

    public static void userMenu() {
        System.out.println("\t卍 卍 卍 卍 卍 卍 卍 卍ADMIN MENU卍 卍 卍 卍 卍 卍 卍 卍");
        System.out.println("\t卍                                              卍");
        System.out.println("\t卍           1. Add New Account                 卍");
        System.out.println("\t卍           2. Edit Account Information        卍");
        System.out.println("\t卍           3. Delete Account                  卍");
        System.out.println("\t卍           4. Show All Account                卍");
        System.out.println("\t卍           5. Back Main Menu                  卍");
        System.out.println("\t卍           6. Exit                            卍");
        System.out.println("\t卍                                              卍");
        System.out.println("\t卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍 卍");
    }

    public static void managementMenu() {
        System.out.println("\t♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉MANAGENMENT MENU♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉");
        System.out.println("\t♉                                                   ♉");
        System.out.println("\t♉           1. Add Products                         ♉");
        System.out.println("\t♉           2. Edit Products Information            ♉");
        System.out.println("\t♉           3. Delete Products                      ♉");
        System.out.println("\t♉           4. Show All Products                    ♉");
        System.out.println("\t♉           5. Search By Name Product               ♉");
        System.out.println("\t♉           6. Back Main Menu                       ♉");
        System.out.println("\t♉           7. Exit                                 ♉");
        System.out.println("\t♉                                                   ♉");
        System.out.println("\t♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉ ♉");
    }

    public static void orderMenu() {
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ORDER MENU♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧               1. Creat Oder                        ♧");
        System.out.println("\t♧               2. Show All List Order               ♧");
        System.out.println("\t♧               3. Back Main Menu                    ♧");
        System.out.println("\t♧               4. Exit                              ♧");
        System.out.println("\t♧                                                    ♧");
        System.out.println("\t♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧ ♧  ♧");
    }

    public static void firstLauch() {
        System.out.println("\t♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎LOGIN♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎");
        System.out.println("\t♎                                        ♎");
        System.out.println("\t♎           1. Management Login          ♎");
        System.out.println("\t♎           2. Forgot Password           ♎");
        System.out.println("\t♎                                        ♎");
        System.out.println("\t♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎ ♎");

    }

    public static void exit() {
        System.out.println("\tTạm biệt! Hẹn gặp lại");
        System.exit(5);
    }
}


