package com.example.jobInfoSite.service;

import com.example.jobInfoSite.model.Role;
import com.example.jobInfoSite.model.User;
import com.example.jobInfoSite.repository.RoleRepository;
import com.example.jobInfoSite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        if (user.getAccountType().equals("personal")) {
            roles.add(roleRepository.findByName("개인회원").orElseThrow(() -> new RuntimeException("Role not found")));
        } else if (user.getAccountType().equals("corporate")) {
            roles.add(roleRepository.findByName("기업회원").orElseThrow(() -> new RuntimeException("Role not found")));
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
