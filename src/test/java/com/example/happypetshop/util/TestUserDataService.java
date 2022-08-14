package com.example.happypetshop.util;

import com.example.happypetshop.models.user.PetShopUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TestUserDataService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new PetShopUserDetails(1L,
                "asdasdasd",
                username,
                "Test",
                "user",
                Collections.emptyList());
    }
}
