/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 03:31:37 UTC+08:00
 ****************************************************/
package host.fairy.config;

import host.fairy.interceptor.JWTHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    private final JWTHandlerInterceptor jwtHandlerInterceptor;
    
    public WebMvcConfig(JWTHandlerInterceptor jwtHandlerInterceptor) {
        this.jwtHandlerInterceptor = jwtHandlerInterceptor;
    }
    
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.jwtHandlerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/test/hello");
    }
}
