package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.User;
import jrvsoft.ppmtool.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
