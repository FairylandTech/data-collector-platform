/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 10:59:08 UTC+08:00
 ****************************************************/
package host.fairy.controller;

import host.fairy.entity.Response;
import host.fairy.enums.GenderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping("/hello")
    public Response<Void> hello() {
        log.info("GenderEnum MALE: {}", GenderEnum.MALE.name());
        return Response.success();
    }
    
}
