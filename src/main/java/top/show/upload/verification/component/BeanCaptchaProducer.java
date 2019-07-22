package top.show.upload.verification.component;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wqh
 * @date 2019-7-22
 */
@Configuration
public class BeanCaptchaProducer {
    @Bean
    public DefaultKaptcha defaultKaptcha(Config config) {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    @Bean
    public Config config() throws IOException {
        Resource res = new ClassPathResource("kaptchaConfig.properties");
        InputStream fileInputStream = res.getInputStream();
        Properties properties = new Properties();
        properties.load(fileInputStream);
        return new Config(properties);
    }


}
