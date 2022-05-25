package files.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantUtils {
    private static final String PATTERN_FOMAT = "HH:mm dd-MM-yy";

    public static String instantToString(Instant instant) {
//        if (instant == null) {
//            return null;
//        }
        return instantToString(instant, null);
    }

    public static String instantToString(Instant instant, String patternFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat != null ? patternFormat : PATTERN_FOMAT).withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
