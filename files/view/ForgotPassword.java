package files.view;

import files.model.Admin;
import files.service.AdminService;
import files.service.IAdminService;

import java.util.Scanner;

public class ForgotPassword {
    private static AdminService adminService = new AdminService();
    private static Scanner scanner = new Scanner(System.in);

    public static void menuForgot() {
        do {
            try {
                System.out.println("\t❂ ❂ ❂ ❂ ❂ ❂  FORGOTPASSWORD  ❂ ❂ ❂ ❂ ❂ ❂ ❂");
                System.out.println("\t❂                                             ❂");
                System.out.println("\t❂           1. Change Password                ❂");
                System.out.println("\t❂           2. Login                          ❂");
                System.out.println("\t❂                                             ❂");
                System.out.println("\t❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂");
                System.out.print("⭆ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        changePassWord();
                        break;
                    case 2:
                        Menu.login();
                        break;
                    default:
                        System.out.println("Incorrect! Please Try Again!!");
                }
            } catch (Exception e) {
                System.out.println("Incorrect! Please Try Again!");
            }
        } while (true);
    }

    private static void changePassWord() {
        Admin admin = fressInforAdmin();
        adminService.update(admin);
        System.out.println("Password Has Been Reset Default! Password Is: 123456789 " );
    }

    private static Admin fressInforAdmin() {
        System.out.println("Enter '-10' Turn back Menu Forgot Password ");
        System.out.println("Please Enter Your Username");
        System.out.print("⭆ ");
        String username = scanner.nextLine();
        while (adminService.getUserByAdminName(username) == null) {
            backToEarlier(username);
            System.out.println("Username Does Not Exist");
            System.out.print("⭆ ");
            username = scanner.nextLine();
        }
        Admin admin = adminService.getUserByAdminName(username);

        System.out.println("Please Reconfirm Account Email: ");
        String email = scanner.nextLine();
        while (!admin.getEmail().equals(email)) {
            backToEarlier(email);
            System.out.println("The Email You Entered Is Incorrect!Please Try Again");
            System.out.print("⭆ ");
            email = scanner.nextLine();
        }
        return admin;
    }

    private static void backToEarlier(String str) {
        if (str.equals("-10")) {
            System.out.println("You're Turn Back");
            menuForgot();
        }
    }

    public static void backToEarlier1(String str) {
        if (str.equals("-10")) {
            System.out.println("You're Turn Back");
                Menu.firstMenu();
        }
    }
}