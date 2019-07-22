package top.show.upload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.show.upload.repository.UserRepository;
import top.show.upload.service.LoginService;

/**
 * description:
 *
 * @author wqh
 * @date 2019-11-9
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean existsByUsernameAndPassword(String  username,String password){
        return userRepository.existsByUsernameAndPassword(username,password);
    }
}
