package files.utils;

import java.text.DecimalFormat;

public class AppUtils {
    public static void exit() {
        System.out.println("\tSee You!!");
        System.exit(5);
    }
    public static String doubleToVND(double value) {
        String patternVND = ",###₫";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }
}
