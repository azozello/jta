package com.jta.shop.service.implementations;

import com.jta.shop.JtaApplication;
import com.jta.shop.entity.User;
import com.jta.shop.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        try {
            user = userService.getUserByUsername(username);
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                        user.getPassword(), getAuthorities(user));
        } catch (Exception e){
            e.printStackTrace();
            JtaApplication.getLogger().error(e.getMessage());
            return new org.springframework.security.core.userdetails.User(
                    "error","error", getAuthorities(user));
        }
    }

    private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());
        authorities.add(grantedAuthority);
        return authorities;
    }
}
