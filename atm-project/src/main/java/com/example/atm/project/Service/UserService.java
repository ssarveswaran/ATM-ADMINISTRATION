package com.example.atm.project.Service;

import com.example.atm.project.DTO.UserRequest;
import com.example.atm.project.Entity.MyUser;

import com.example.atm.project.Exception.DuplicateUserNameException;
import com.example.atm.project.Repository.UserRepo;
import com.example.atm.project.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder encoder;
    public List<MyUser> GetUser(){
        return repo.findAll();}
    public MyUser GetById(int i)throws Exception{

        MyUser u=repo.findUserById(i);

        if(u!=null){
            return u;
        }
        else {
            throw new UserNotFoundException(" USER NOT FOUND "+i);
        }
    }
    public MyUser update(int i, UserRequest req) {
        MyUser use=repo.findUserById(i);
        if (req.getName() != null) {
            use.setName(req.getName());
        }

        if (req.getPassword() != null) {
            use.setPassword(encoder.encode(req.getPassword()));
        }
        if(req.getRole()!=null){
            use.setRole(String.valueOf(req.getRole()));

        }
        return repo.save(use);
    }
    public void DeleteAll(){
        repo.deleteAll();
    }

    public MyUser DeleteById(int id) throws Exception{
        MyUser userToDelete = repo.findById(id).orElse(null);
        if (userToDelete != null) {
            repo.deleteById(id);
            return userToDelete;
        }
        else{
            throw new UserNotFoundException(" ID NOT FOUND "+id);
        }
    }


    public MyUser AddUser(UserRequest req) throws Exception {
        try {
            MyUser use = MyUser.connect(0, req.getName(), encoder.encode(req.getPassword())
                    , String.valueOf(req.getRole()));
            return repo.save(use);}
            catch (DataIntegrityViolationException e) {
                // Handle specific exceptions here
                throw new DuplicateUserNameException("User with this name already exists: " + req.getName());
            }
         catch (Exception e) {
            // Handle other exceptions
            throw new Exception("Failed to add user: " + e.getMessage());
        }
    }
}
