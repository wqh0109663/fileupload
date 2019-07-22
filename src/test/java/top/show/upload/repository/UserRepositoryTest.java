package top.show.upload.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.show.upload.entity.User;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Administrator
 * @date 2019-7-27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void findUserByUsernameAndPassword() {
        User user1 = User.builder()
                .username("wqh")
                .password("12312323")
                .build();
        userRepository.save(user1);
        User user = userRepository.findUserByUsernameAndPassword("wqh", "123");
        assertNotNull(user);
    }
    @Test
    public void existsByUsernameAndPassword(){
        boolean wqh = userRepository.existsByUsernameAndPassword("wqh", "123");
        assertTrue(wqh);
    }


}