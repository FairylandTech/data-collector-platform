/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-21 04:22:56 UTC+08:00
 ****************************************************/
package host.fairy.utils;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
public class EntityFieldConverterUtils {
    
    private EntityFieldConverterUtils() {
    }
    
    public static String convertUserGender(String gender) {
        if (gender.equalsIgnoreCase("female")) {
            return "女";
        } else if (gender.equalsIgnoreCase("male")) {
            return "男";
        } else {
            return "未知";
        }
    }
}
