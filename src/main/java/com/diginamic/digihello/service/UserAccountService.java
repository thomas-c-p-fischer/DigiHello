package com.diginamic.digihello.service;

import com.diginamic.digihello.domain.UserAccount;
import com.diginamic.digihello.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @PostConstruct
    public void initUsers() {
        UserAccount userBdd = userAccountRepository.findByUsername("thomas");
        UserAccount adminBdd = userAccountRepository.findByUsername("thomasadmin");
        if (userBdd == null && adminBdd == null) {
            UserAccount user = new UserAccount("thomas", "thomas", "USER");
            UserAccount admin = new UserAccount("thomasadmin", "thomasadmin", "ADMIN");
            userAccountRepository.save(user);
            userAccountRepository.save(admin);
        }
    }
}
