package files.view;

import files.model.Techs;
import files.service.ITechsSevice;
import files.service.TechsSevice;
import files.utils.AppUtils;
import files.utils.InstantUtils;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class TechsProduct {
    private final ITechsSevice techsSevice;
    private final Scanner scanner = new Scanner(System.in);

    public TechsProduct() {
        techsSevice = TechsSevice.getInstance();
    }

    private boolean isRetry(InputOption inputOption) {
        do {
            try {
                switch (inputOption) {
                    case ADD:
                        System.out.println("Press 'y' To Add More Users \t|\t 'q' Turn Back \t|\t 't' Exit");
                        break;
                    case UPDATE:
                        System.out.println("Press 'y' To Continue Editing \t|\t 'q' Turn back \t|\t 't' Exit");
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
                System.out.println("Press Id");
                System.out.print("➲ ");
                while (techsSevice.existById(id = Long.parseLong(scanner.nextLine()))) {
                    System.out.println("This Id Already Exists!Please Try Again!!");
                    System.out.print("➲ ");
                    break;
                }
                break;
            case UPDATE:
                System.out.println("Enter Your Id You Wanna Change: ");
                System.out.print("➲ ");
                while (!techsSevice.existById(id = Long.parseLong(scanner.nextLine()))) {
                    System.out.println("This Id Already Exists!Please Try Again!!");
                    System.out.print("➲ ");
                    break;
                }
                break;
        }
        return id;
    }

    private String inputTechsName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Enter Product Name");
                break;
            case UPDATE:
                System.out.println("Enter The Product Name You Want To Modify");
                break;
        }
        boolean is = true;
        String name = "";
        do {
            try {
                System.out.print("➲ ");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                    System.out.println("Incorrect!Please Try Again!!");
                    is = false;
                } else {
                    is = true;
                }
            } catch (Exception e) {
                System.out.println("Incorrect! Please Try Again!!");
            }
        } while (!is);
        return name;
    }

    private Double inputPriceTechs(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Enter Your Price Techs: ");
                break;
            case UPDATE:
                System.out.println("Enter your Price Techs Change: ");
                break;
        }
        boolean is = false;
        double price = 0;
        do {
            try {
                System.out.print("➲ ");
                price = Double.parseDouble(scanner.nextLine());
                if (price < 0) {
                    System.out.println("Incorrect! please try again!");
                    is = false;
                } else {
                    is = true;
                }
            } catch (Exception e) {
                System.out.println("Incorrect! please try again!");
            }
        } while (!is);
        return price;
    }

    private String inputType(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Choice your Type Product: \n" +
                        "1. SmartPhone\n" +
                        "2. Laptop\n" +
                        "3. Accessory");
                break;
            case UPDATE:
                System.out.println("Choice your new Type Product: \n" +
                        "1. SmartPhone\n" +
                        "2. Laptop\n" +
                        "3. Accessory ");
                break;
        }
        boolean is = true;
        String type = null;
        do {
            try {
                System.out.print("➲ ");
                type = scanner.nextLine();
                if (type.isEmpty()) {
                    System.out.println("Incorrect! Please Try Again!");
                    is = true;
                } else {
                    is = false;
                }
            } catch (Exception e) {
                System.out.println("Incorrect! Please Try Again!");
            }
        } while (is);

        return type;
    }

    private Integer inputQuantityTechs(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Press Your Quantity: ");
                break;
            case UPDATE:
                System.out.println("Press Your New Quantity: ");
                break;
        }
        boolean is = false;
        int quantity = 0;
        do {
            try {
                System.out.print("➲ ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) {
                    System.out.println("Incorrect! Please Try Again");
                    is = false;
                } else {
                    is = true;
                }
            } catch (Exception e) {
                System.out.println("Incorrect! Please Try Again!");
            }
        } while (!is);
        return quantity;
    }

    public void addTechs() {
        do {
            try {
                Long id = System.currentTimeMillis() / 1000;
                String nameTechs = inputTechsName(InputOption.ADD);
                Double priceTechs = inputPriceTechs(InputOption.ADD);
                Integer quantityTechs = inputQuantityTechs(InputOption.ADD);
                String type = inputType(InputOption.ADD);
                Techs techs = new Techs(id, nameTechs, priceTechs, quantityTechs, type);
                System.out.println(techs);
                techsSevice.add(techs);
                showTechs1();
                System.out.println("You Have Successfully Added");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Incorrect! Please Try Again!!");
            }
        } while (isRetry(InputOption.ADD));
    }

    public void showTechs() {
        System.out.println("--------------------------------------------------------------------------LISTTECHS--------------------------------------------------------");
        System.out.printf("%-15s %-25s %-15s %-10s %-20s %-20s %-20s\n\n", "Id", "Product Name", "Price", "Quantity", "Type", "Date Creat", "Date Update");
        List<Techs> techs = techsSevice.getTechs();
        Collections.sort(techs);
        for (Techs techs1 : techs) {
            System.out.printf("%-15d %-25s %-15s %-10d %-20s %-20s %-20s \n\n", techs1.getId(), techs1.getNameTechs(), AppUtils.doubleToVND(techs1.getPriceTechs()), techs1.getQuantityTechs(), techs1.getType(), InstantUtils.instantToString(techs1.getCreatDate()), techs1.getUpdateDate() == null ? "" : InstantUtils.instantToString(techs1.getUpdateDate()));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
        isRetry(InputOption.SHOW);
    }

    public static void showTechs1() {
        TechsSevice techsSevice = new TechsSevice();
        System.out.println("------------------------------------------------------------LISTPRODUCT---------------------------------------------------------");
        System.out.printf("%-15s %-25s %-15s %-10s %-20s %-20s %-20s\n\n", "Id", "Product Name", "Price", "Quantity", "Type", "Date Creat", "Date Update");
        List<Techs> techs = techsSevice.getTechs();
        Collections.sort(techs);
        for (Techs techs1 : techs) {
            System.out.printf("%-15d %-25s %-15s %-10d %-20s %-20s %-20s \n\n", techs1.getId(), techs1.getNameTechs(), AppUtils.doubleToVND(techs1.getPriceTechs()), techs1.getQuantityTechs(), techs1.getType(), InstantUtils.instantToString(techs1.getCreatDate()), techs1.getUpdateDate() == null ? "" : InstantUtils.instantToString(techs1.getUpdateDate()));
            ;
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
    }

    public void updateProductTechs() {
        boolean isRetry = false;
        do {
            try {
                showTechs1();
                Long id = inputId(InputOption.UPDATE);
                System.out.println("\t┌ - - - - - - - EDIT - - - - - - - ┐");
                System.out.println("\t︲                                 ︲");
                System.out.println("\t︲        1. Edit Name             ︲");
                System.out.println("\t︲        2. Edit Price            ︲");
                System.out.println("\t︲        3. Edit Quantity         ︲");
                System.out.println("\t︲        4. Edit Type             ︲");
                System.out.println("\t︲        5. Back                  ︲");
                System.out.println("\t︲                                 ︲");
                System.out.println("\t└ - - - - - - - - - - - - - - - -  ┘");
                int option;
                do {
                    System.out.println("Select Function");
                    System.out.print("➲ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 4 || option < 1) {
                        System.out.println("Incorrect! Please Try Again!!");
                        continue;
                    }
                    break;
                } while (true);
                Techs newTechs = new Techs();
                newTechs.setId(id);
                switch (option) {
                    case 1:
                        String name = inputTechsName(InputOption.UPDATE);
                        newTechs.setNameTechs(name);
                        techsSevice.updateName(newTechs);
                        System.out.println("You Have Successfully Changed The Name");
                        showTechs1();
                        break;
                    case 2:
                        Double price = inputPriceTechs(InputOption.UPDATE);
                        newTechs.setPriceTechs(price);
                        techsSevice.updatePrice(newTechs);
                        System.out.println("You Have Successfully Changed The Price");
                        showTechs1();
                        break;
                    case 3:
                        Integer quantity = inputQuantityTechs(InputOption.UPDATE);
                        newTechs.setQuantityTechs(quantity);
                        techsSevice.updateQuantity(newTechs);
                        System.out.println("You Have Successfully Changed The Number");
                        showTechs1();
                        break;
                    case 4:
                        String description = inputType(InputOption.UPDATE);
                        newTechs.setType(description);
                        techsSevice.updateDescription(newTechs);
                        System.out.println("You Have Successfully Changed The Description");
                        showTechs1();
                        break;
                }
                isRetry = option != 5 && isRetry(InputOption.UPDATE);
            } catch (Exception e) {
                System.out.println("Incorrect!Please Try Again!");
            }
        } while (isRetry);
    }

    public void remove() {
        boolean is = true;
        do {
            try {
                showTechs1();
                techsSevice.getTechs();
                System.out.println("Press Id You Wanna Remove: ");
                System.out.print("➲ ");
                long id = Long.parseLong(scanner.nextLine());
                Techs techs = techsSevice.getTechsById(id);
                System.out.println(techs);
                if (techs == null) {
                    System.out.println("ID Not Found To Remove");
                    is = false;
                } else {
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
                            techsSevice.remove(techs);
                            System.out.println("Successfully Remove!");
                            showTechs1();
                            do {
                                System.out.println("\t------------------------------------------------------------");
                                System.out.println("\t| Press 'y' To Go Back\t|\t'n' To Exit  |");
                                System.out.println("\t------------------------------------------------------------");
                                System.out.print("➲ ");
                                String chose = scanner.nextLine();
                                switch (chose) {
                                    case "y":
                                        TechsProductView.run();
                                        break;
                                    case "n":
                                        Menu.exit();
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("Incorrect! Please Try Again!!");
                                        is = false;
                                }
                            } while (is);
                            break;
                        case 2:
                            TechsProductView.run();
                            break;
                        default:
                            System.out.println("Incorrect! Please Try Again!!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Incorrect! please try again!!");
            }
        } while (is);
    }

    public void searchByNameProduct() {
        List<Techs> techs = techsSevice.getTechs();
        int count = 0;
        System.out.println();
        System.out.println("Press Your Product You Wanna Search: ");
        String search = scanner.nextLine();
        System.out.println("Keyword search results '" + search + "' Are:");
        search = search.toLowerCase();
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-20s %-20s %-15s %-20s %-20s\n",
                "Id", "Name Product", "Type", "Quantity","Price","Creat Date" );
        for (Techs techs1 : techs){
            if(techs1.toString().toLowerCase().contains(search)){
                count++;
                System.out.printf("%-15d %-20s %-20s %-15d %-20s %-20s\n",
                        techs1.getId(),
                        techs1.getNameTechs(),
                        techs1.getType(),
                        techs1.getQuantityTechs(),
                        AppUtils.doubleToVND(techs1.getPriceTechs()) ,
                        InstantUtils.instantToString(techs1.getCreatDate()));
            }
        }
        showReturnSearch(count);
        System.out.println();
        TechsProductView.run();
    }
    public void showReturnSearch(int count){
        System.out.println();
        System.out.println();
        System.out.println("We Have a '" + count +  "'Be Found\n");
        char press = ' ';
        do {
            System.out.println("Press 'r' Turn back ");
            try {
                press = scanner.nextLine().charAt(0);
            }catch (Exception e){
                press = ' ';
            }
            switch (press){
                case 'r':
                case 'R':
                    TechsProductView.run();
                    break;
                default:
            }
        }while (true);
    }
}