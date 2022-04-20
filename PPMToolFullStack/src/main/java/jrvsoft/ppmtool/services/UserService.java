package jrvsoft.ppmtool.services;

import jrvsoft.ppmtool.domain.User;

import java.util.Optional;

public interface UserService {
     User saveUser(User user);
     User  findByUsername(String username);
}
