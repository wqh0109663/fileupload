package top.show.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.show.upload.entity.User;

/**
 * @author Administrator
 * @date 2019-7-27
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 根据用户名和密码查询用户
     * @param userName 用户名
     * @param password 密码
     * @return 用户
     */
    User findUserByUsernameAndPassword(String userName,String password);

    /**
     * 判断用户是否存在
     * @param username
     * @param password
     * @return
     */
    boolean existsByUsernameAndPassword(String username,String password);

}
