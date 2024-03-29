package top.show.upload;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.show.upload.entity.User;
import top.show.upload.repository.UserRepository;
import top.show.upload.service.StorageProperties;
import top.show.upload.service.StorageService;

/**
 * @author 吴启欢
 * @version 1.0
 * @date 19-7-21 下午9:18
 * //@ComponentScan(basePackages = "top.show.upload.verification.config")
 */
@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
@EnableEncryptableProperties
public class FileuploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileuploadApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService, UserRepository userRepository) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
            userRepository.deleteAll();
            User user = User.builder()
                    .username("wqh")
                    .password("123")
                    .build();
            userRepository.save(user);
        };
    }
}
