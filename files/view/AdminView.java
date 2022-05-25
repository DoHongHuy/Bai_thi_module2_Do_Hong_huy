package files.view;

import files.Main;
import files.model.Admin;
import files.model.Role;
import files.service.AdminService;
import files.service.IAdminService;
import files.utils.AppUtils;
import files.utils.InstantUtils;
import files.utils.ValidateUtils;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    private final IAdminService adminService;
    private final Scanner scanner = new Scanner(System.in);

    public AdminView() {
        adminService = new AdminService();
    }

    private boolean isRetry(InputOption inputOption) {
        do {
            try {
                switch (inputOption) {
                    case ADD:
                        System.out.println("Press 'y'To Add More Users \t|\t 'q' Turn Back \t|\t 't' Exit");
                        break;
                    case UPDATE:
                        System.out.println("Press 'y' To Edit more \t|\t 'q' Turn Back \t|\t 't' Exit");
                        break;
                    case SHOW:
                        System.out.println("Press 'q' Turn Back \t|\t 't' Exit");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + inputOption);

                }
                System.out.print("➲ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "q":
                        return false;
                    case "t":
                        Menu.exit();
                        break;
                    default:
                        System.out.println("Incorrect!Please Try Again");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Incorrect!Please Try Again");
                ex.printStackTrace();
            }
        } while (true);
    }

    private Long inputId(InputOption option) {
        Long id = null;
        switch (option) {
            case ADD:
                System.out.println("Nhập Id");
                System.out.print("➲ ");
                while (adminService.existById(id = Long.parseLong(scanner.nextLine()))) {
                    System.out.println("This Id Already Exists, Please Enter Another Id");
                    System.out.print("➲ ");
                }
                break;
            case UPDATE:
                System.out.println("Press Id You Wanna Edit: ");
                System.out.print("➲ ");
                while (!adminService.existById(id = Long.parseLong(scanner.nextLine()))) {
                    System.out.println("This Id Does Not Exist, Please Enter Another Id");
                    System.out.print("➲ ");
                }
                break;
        }
        return id;
    }

    public String inputAdminName() {
        boolean restry = true;
        String adminname = null;
        do {
            try {
                System.out.println("Nhập user name: ");
                System.out.print("➲ ");
                while (adminService.checkDuplicateUserName(adminname = scanner.nextLine())) {
                    System.out.println("This User Already Exists. Please Try Again!");
                    System.out.print("➲ ");
                }
                if (adminname.isEmpty()) {
                    System.out.println("Cannot Be Left Empty");
                    restry = false;
                } else {
                    restry = true;
                }
            } catch (Exception e) {
                System.out.println("Incorrect! please try again!!");
            }
        } while (!restry);
        return adminname;
    }

    private String inputFullname(InputOption option) {
        String fullName = null;
        try {
            switch (option) {
                case ADD:
                    System.out.println("Enter Your First And Last Name(Example: Nguyen Van Phuc): ");
                    break;
                case UPDATE:
                    System.out.println("Enter The First And Last Name You Want To Change");
                    break;
            }
            System.out.print("➲ ");
            while (!ValidateUtils.isNameValid(fullName = scanner.nextLine())) {
                System.out.println("Name " + fullName + " Malformed!Please Try Again(Capitalize First Letter And No Accents)");
                System.out.println("Enter Name (Example: Phuc Nguyen )");
                System.out.print("➲ ");

            }
        } catch (Exception e) {
            System.out.println("Incorrect! Please Try Again!!");
        }
        return fullName;
    }


    public String inputPhone(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Press The NumberPhone(Example: 0345567489): ");
                break;
            case UPDATE:
                System.out.println("Press The NumberPhone You Wanna change: ");
                break;
        }
        System.out.print("➲ ");
        String phone;
        while (!ValidateUtils.isPhoneValid(phone = scanner.nextLine())) {
            System.out.println("Number " + phone + " Incorrect!Please Try Again(Phone Numbers Start From 0 And Have 10 Numbers");
            System.out.println("Press The Number phone (Example: 0304456748)");
            System.out.print("➲ ");
            if (adminService.checkDuplicatePhone(phone)) {
                System.out.println("This Number Already Exists");
                System.out.print("➲ ");
            }
        }
        return phone;
    }

    private String inputEmail() {
        System.out.println("Press email(vd: phucnguyen@gmail.com) ");
        System.out.print("➲ ");
        String email;
        while (!ValidateUtils.isEmailValid(email = scanner.nextLine())) {
            System.out.println("Email " + email + " Incorrect!Please Try Again!!");
            System.out.println("Press email(Example: phucnguyen@gmail.com");
            System.out.print("➲ ");
        }
        return email;
    }

    private String inputPassword() {
        System.out.println("Press Password(Password Must Be More Than 8 Characters)");
        System.out.print("➲ ");
        String password;
        while (!ValidateUtils.isPasswordValid(password = scanner.nextLine())) {
            System.out.println("Weak Password! Please Try Again");
            System.out.print("➲ ");
        }
        return password;
    }

    private String inputAddress(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Pres Address(VD:Hue)");
                break;
            case UPDATE:
                System.out.println("Press The Address You Wanna Change: ");
                break;
        }
        boolean is = false;
        String address = null;
        do {
            try {
                System.out.print("➲ ");
                address = scanner.nextLine();
                if (address.isEmpty()) {
                    System.out.println("Cannot Be Left Empty");
                    is = false;
                } else {
                    is = true;
                }
            } catch (Exception e) {
                System.out.println("Incorrect! Please Try Again!!");
            }
        } while (!is);
        return address;
    }

    public void addAdmin() {
        do {
            try {
                Long id = System.currentTimeMillis() / 1000;
                String username = inputAdminName();
                String password = inputPassword();
                String fullName = inputFullname(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail();

                Admin admin = new Admin(id, username, password, fullName, phone, email, address, Role.USER);
                setRole(admin);
                adminService.add(admin);
            } catch (Exception e) {
                System.out.println("Incorrect! Please Try Again!");
            }
        } while (isRetry(InputOption.ADD));
    }

    public void setRole(Admin admin) {
        System.out.println("= = SET ROLE = =");
        System.out.println("∥              ∥");
        System.out.println("∥   1. USER    ∥");
        System.out.println("∥   2. ADMIN   ∥");
        System.out.println("∥              ∥");
        System.out.println("= = = =  = = = = ");
        System.out.println("Chọn Role: ");
        System.out.print("➲ ");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                admin.setRole(Role.USER);
                System.out.println("Successfully Added Account");

                break;
            case 2:
                admin.setRole(Role.ADMIN);
                System.out.println("Successfully Added Account");

                break;
            default:
                System.out.println("Incorrect! Please try again!!");
                break;
            //setRole(admin);
        }
    }

    public void showAdmin() {
        System.out.println("----------------------------------------------------------------------------LIST CUSTOMER----------------------------------------------------------------------------");
        System.out.printf("%-15s%-22s%-15s%-30s%-20s%-15s%-15s\n\n", "ID", "Name", "Number Phone", "Email", "Address", "ROLE", "Date Creat");
        List<Admin> admins = adminService.getAdmin();
        Collections.sort(admins);
        for (Admin admin : admins) {
            System.out.printf("%-15d%-22s%-15s%-30s%-20s%-15s%-15s  \n\n", admin.getId(), admin.getName(), admin.getPhone(), admin.getEmail(), admin.getAddress(), admin.getRole(), InstantUtils.instantToString(admin.getCreatDate()));

        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        isRetry(InputOption.SHOW);
    }

    public void updateAdmin() {
        boolean isRetry = false;
        do {
            try {
                showUser1();
                Long id = inputId(InputOption.UPDATE);
                System.out.println("\t┌ - - - - - - - EDIT - - - - - - - ┐");
                System.out.println("\t︲                                 ︲");
                System.out.println("\t︲        1. Edit Name             ︲");
                System.out.println("\t︲        2. Edit NumerPhone       ︲");
                System.out.println("\t︲        3. Edit Address          ︲");
                System.out.println("\t︲        4. Back                  ︲");
                System.out.println("\t︲                                 ︲");
                System.out.println("\t└ - - - - - - - - - - - - - - - -  ┘");
                int option;
                do {
                    System.out.println("Select Function");
                    System.out.print("➲ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 4 || option < 1) {
                        System.out.println("Incorrect! Please Try Again");
                        continue;
                    }
                    break;
                } while (true);
                Admin newAdmin = new Admin();
                newAdmin.setId(id);
                switch (option) {
                    case 1:
                        String name = inputFullname(InputOption.UPDATE);
                        newAdmin.setName(name);
                        adminService.updateName(newAdmin);
                        System.out.println("You Have Successfully Changed Your Name!");
                        showUser1();
                        break;
                    case 2:
                        String phone = inputPhone(InputOption.UPDATE);
                        newAdmin.setPhone(phone);
                        adminService.updatePhone(newAdmin);
                        System.out.println("You Have Successfully Changed Your Phone Number!!");
                        showUser1();
                        break;
                    case 3:
                        String address = inputAddress(InputOption.UPDATE);
                        newAdmin.setAddress(address);
                        adminService.updateAddress(newAdmin);
                        System.out.println("You Have Successfully Changed Your Address!!");
                        showUser1();
                        break;
                }
                isRetry = option != 4 && isRetry(InputOption.UPDATE);

            } catch (Exception e) {
                System.out.println("Incorrect!Please Try Again!!");
            }
        } while (isRetry);
    }

    public void adminLogin() {
        boolean isRetry = false;
        System.out.println("✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.println("✦                                                ✦");
        System.out.println("✦                Technology Market               ✦");
        System.out.println("✦                                                ✦");
        System.out.println("✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦\n");
        System.out.println("✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦  LOGIN MANAGER ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦  ");
        System.out.println("Press - 10 Turn Back ");
        do {
            try {
                String username;
                boolean check = false;
                do {
                    System.out.println("Username");
                    System.out.print("⭆ ");
                    username = scanner.nextLine();
                    ForgotPassword.backToEarlier1(username);
                    if (username.isEmpty()) {
                        System.out.println("Incorrect! please try again!!");
                        check = true;
                    } else {
                        check = false;
                    }
                } while (check);
                String password;
                do {
                    System.out.println("PassWord");
                    System.out.print(" ⭆ ");
                    password = scanner.nextLine();
                    ForgotPassword.backToEarlier1(password);
                    if (password.isEmpty()) {
                        System.out.println("Incorrect! please try again!!");
                        check = true;

                    } else {
                        check = false;
                    }
                } while (check);

                if (adminService.loginAdmin(username, password) != null) {
                    Admin admin = adminService.getUserByAdminName(username);
                    if (admin.getRole() == Role.ADMIN) {
                        System.out.println("Please Enter Email To Confirm");
                        System.out.print("⭆ ");
                        checkLoginEmailAdmin(admin.getEmail());
                    }
                } else {
                    System.out.println("This Account Is Invalid ");
                    isRetry = isRetry();

                }
            } catch (Exception e) {
                System.out.println("Incorrect! Please Try Again!!");
            }
        } while (isRetry);
    }


    private boolean isRetry() {
        do {
            try {
                System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ CHOICE ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
                System.out.println("\t✦                                                                ✦");
                System.out.println("\t✦                   1. Press 'y' To Go Back                      ✦");
                System.out.println("\t✦                   2. Press 'n' to Exit                         ✦");
                System.out.println("\t✦                                                                ✦");
                System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
                System.out.print("➲ ");
                String option = scanner.nextLine();
                switch (option) {
                    case "y":
                        return true;
                    case "n":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Enter Your Function!Please Try Again");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Incorrect!Please Try Again!!");
                ex.printStackTrace();
            }
        } while (true);
    }

    public void showUser1() {
        System.out.println("-----------------------------------------------------------------------LIST CUSTOMER-----------------------------------------------------------------------");
        System.out.printf("%-15s%-22s%-15s%-30s%-20s%-10s%-10s\n\n", "ID", "Name", "Number Phone", "Email", "Address", "ROLE", "Date Creat");
        List<Admin> admins = adminService.getAdmin();
        Collections.sort(admins);
        for (Admin admin : admins) {
            System.out.printf("%-15d%-22s%-15s%-30s%-20s%-10s%-10s  \n\n", admin.getId(), admin.getName(), admin.getPhone(), admin.getEmail(), admin.getAddress(), admin.getRole(), InstantUtils.instantToString(admin.getCreatDate()));

        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void remove() {
        try {
            showUser1();
            adminService.getAdmin();
            System.out.println("Press Id You Wanna Remove:");
            System.out.print("➲ ");
            long id = Long.parseLong(scanner.nextLine());
            Admin admin = adminService.getUserById(id);
            System.out.println(admin);
            if (admin == null) {
                System.out.println("Can't Find The Id To Delete!!");
                remove();
            } else {
                boolean check = true;
                System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄REMOVE COFIRM❄ ❄ ❄ ❄ ❄ ❄ ❄");
                System.out.println("\t❄                                          ❄");
                System.out.println("\t❄              1. Remove                   ❄");
                System.out.println("\t❄              2. Back                     ❄");
                System.out.println("\t❄                                          ❄");
                System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄");
                System.out.print("➲ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        adminService.remove(admin);
                        System.out.println("User Deleted Successfully!");
                        showUser1();
                        do {
                            System.out.println("\t------------------------------------------------------------");
                            System.out.println("\t| Press 'y' To Go Back\t|\t'n' To Exit  |");
                            System.out.println("\t------------------------------------------------------------");
                            System.out.print("➲ ");
                            String choic = scanner.nextLine();
                            switch (choic) {
                                case "y":
                                    AdminViewLaucher.run();
                                    break;
                                case "n":
                                    Menu.exit();
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("Incorrect!!Please Try Again");
                                    check = false;
                            }
                        } while (!check);
                        break;
                    case 2:
                        AdminViewLaucher.run();
                        break;
                    default:
                        System.out.println("Incorrect!!Please Try Again!!");
                }
            }
        } catch (Exception e) {
            System.out.println("Incorrect! Please try again!!");
        }
    }

    public void checkLoginEmailAdmin(String emailAdmin) {
        String email = scanner.nextLine().toLowerCase();
        if (!email.equals(emailAdmin)) {
            ForgotPassword.backToEarlier1(email);
            System.out.println("Enter '-10' Turn back ");
            System.out.println("Email Incorrect!Please try again!!");
            System.out.print("➲  ");
            checkLoginEmailAdmin(emailAdmin);
        } else {
            System.out.println("\tYou Successful login\n");
            System.out.println("\tWellcome to the Market Technology\n");
            Menu.MainMenu();
        }
    }
}