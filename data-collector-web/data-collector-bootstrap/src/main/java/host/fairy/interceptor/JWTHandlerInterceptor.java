/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 03:33:39 UTC+08:00
 ****************************************************/
package host.fairy.interceptor;

import host.fairy.enums.JWTClaimsKeyEnum;
import host.fairy.service.JWTAuthService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Slf4j
@Component
public class JWTHandlerInterceptor implements HandlerInterceptor {
    
    private final JWTAuthService authService;
    
    public JWTHandlerInterceptor(JWTAuthService authService) {
        this.authService = authService;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入 JWTHandlerInterceptor, 请求路径: {}, 方法: {}", request.getRequestURI(), request.getMethod());
        if (!(handler instanceof HandlerMethod)) {
            log.info("非Controller请求, 直接放行: {}", request.getRequestURI());
            return true;
        }
        
        String token = request.getHeader("Authorization");
        if (token == null) {
            log.error("请求未携带有效的Token, 请求路径: {}", request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        
        try {
            Claims claims = this.authService.parseToken(token);
            Integer userId = claims.get(JWTClaimsKeyEnum.USER_ID.getName(), Integer.class);
            String username = claims.get(JWTClaimsKeyEnum.USERNAME.getName(), String.class);
            request.setAttribute("userId", userId);
            request.setAttribute("username", username);
            return true;
        } catch (Exception e) {
            log.error("Token解析失败: {}, 请求路径: {}", e.getMessage(), request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
