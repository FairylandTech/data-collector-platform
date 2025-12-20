/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-20 02:54:47 UTC+08:00
 ****************************************************/
package host.fairy.se;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
public class SETests {
    @Test
    void testLength() {
        String key = "51&c&dVU3Yg8poq5jVA5A7wJ1wHsGdnXpb9bEcz9f#2ew^SZFD3I&qJrM#9!KDag";
        System.out.println("key.length() = " + key.length());
        System.out.println("key.getBytes(StandardCharsets.UTF_8).length = " + (key.getBytes(StandardCharsets.UTF_8).length) * 8);
    }
}
