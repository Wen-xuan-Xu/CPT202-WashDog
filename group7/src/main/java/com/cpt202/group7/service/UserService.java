package com.cpt202.group7.service;

import com.cpt202.group7.entity.User;
import com.cpt202.group7.mapper.UserMapper;
import com.cpt202.group7.utils.customexceptions.InvalidPasswordException;
import com.cpt202.group7.utils.customexceptions.UserAlreadyExistsException;
import com.cpt202.group7.utils.customexceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;


    public void saveUser(User user) throws UserAlreadyExistsException {
        User existingUser = userMapper.findByUsername(user.getUsername());
        if(existingUser!=null){
            throw new UserAlreadyExistsException("User with email " + user.getUsername() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.saveUser(user);
    }




    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}

