package com.example.atm.project;

import com.example.atm.project.DTO.MoneyRequest;
import com.example.atm.project.Entity.Money;
import com.example.atm.project.Entity.MyUser;
import com.example.atm.project.Repository.MoneyRepo;
import com.example.atm.project.Repository.UserRepo;
import org.hibernate.metamodel.internal.AbstractDynamicMapInstantiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private MoneyRepo repo;
    @Autowired
    private PasswordEncoder encoder;
@Autowired
private UserRepo user;
    private long Hun = 0;
    private long Two = 0;
    private long Five = 0;

    @Override
    public void run(String... args) throws Exception {
        repo.save(Money.connect(1,0,0,0));
        user.save(MyUser.connect(1,"sarvesh",encoder.encode("123"),"ADMIN"));
    }
}
