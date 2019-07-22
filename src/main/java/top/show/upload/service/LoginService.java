package top.show.upload.service;

/**
 * description:
 *
 * @author wqh
 * @date 2019-11-8
 */
public interface LoginService {
    /**
     * 判断用户名和密码是否存在
     * @param username 用户名
     * @param password 密码
     * @return 是否存在
     */
     boolean existsByUsernameAndPassword(String  username,String password);


}
