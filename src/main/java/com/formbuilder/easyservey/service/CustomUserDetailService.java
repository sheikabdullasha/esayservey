package com.formbuilder.easyservey.service;


import com.formbuilder.easyservey.entity.User;
import com.formbuilder.easyservey.entity.UserSecurity;
import com.formbuilder.easyservey.repo.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final IUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String mailId) throws UsernameNotFoundException {
        List<User> usr=userRepository.findByEmail(mailId);

        log.error("***********>"+usr.get(0).toString());


        if(usr.size()==0) {
            throw new UsernameNotFoundException("user detail not found for the emailId : "+ mailId);
        }


        log.error("++++++++++>"+usr.get(0).toString());

        return new UserSecurity(usr.get(0));
    }
}
