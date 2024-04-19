package com.diginamic.digihello.service;

import com.diginamic.digihello.domain.UserAccount;
import com.diginamic.digihello.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void initUsers() {
        UserAccount userBdd = userAccountRepository.findByUsername("thomas");
        UserAccount adminBdd = userAccountRepository.findByUsername("thomasadmin");
        String encodedPasswordUser = passwordEncoder.encode("thomas");
        String encodedPasswordAdmin = passwordEncoder.encode("thomasadmin");
        if (userBdd == null && adminBdd == null) {
            UserAccount user = new UserAccount("thomas", encodedPasswordUser, "USER");
            UserAccount admin = new UserAccount("thomasadmin", encodedPasswordAdmin, "ADMIN");
            userAccountRepository.save(user);
            userAccountRepository.save(admin);
        }
    }

    @GetMapping
    public UserAccount currentUser(String user) {
       return userAccountRepository.findByUsername(user);
    }
}
