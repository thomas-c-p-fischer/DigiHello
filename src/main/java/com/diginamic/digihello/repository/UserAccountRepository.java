package com.diginamic.digihello.repository;

import com.diginamic.digihello.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    UserAccount findByUsername(String username);
}