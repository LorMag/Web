package org.example.Web2.service;

import org.example.Web2.domain.User;
import org.example.Web2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User tempUser = userRepo.findByUsername(userName);
        if (tempUser == null)
            throw new UsernameNotFoundException("Пользователь не найден");
        return tempUser;
    }

    public void addUser(User user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepo.save(user);
    }
}