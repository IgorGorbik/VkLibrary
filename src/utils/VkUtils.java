package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Игорь
 */
public class VkUtils {
    
    /**
     * get date by longtime
     *
     * @return return date by String
     */
    public static String getData(String str) {
        Long l = Long.valueOf(str);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(l * 1000);
        return sdf.format(resultdate);
    }

}
