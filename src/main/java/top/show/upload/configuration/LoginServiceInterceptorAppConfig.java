package top.show.upload.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.show.upload.interceptor.LoginInterceptor;

/**
 * description:注册登陆拦截器
 *
 * @author wqh
 * @date 2019-11-8
 */
@Configuration
@EnableWebMvc
public class LoginServiceInterceptorAppConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/imgcode")
                .excludePathPatterns("/login")
                .excludePathPatterns("/error")
                .excludePathPatterns("/")
                .excludePathPatterns("/switch")
                .excludePathPatterns("/checkImageCode");
    }
}
