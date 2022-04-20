package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.User;
import jrvsoft.ppmtool.exception.UsernmaneAlreadyExistException;
import jrvsoft.ppmtool.exception.UsernmaneNotExistException;
import jrvsoft.ppmtool.repositories.UserRepository;
import jrvsoft.ppmtool.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {

        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw  new UsernmaneAlreadyExistException("username " + user.getUsername() + " is already exist.");
        }

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setConfirmPassword("");
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User>  user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new UsernmaneNotExistException("User not found!");
        }
        return user.get();
    }
}
