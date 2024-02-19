package com.example.atm.project.Controller;

import com.example.atm.project.DTO.Role;
import com.example.atm.project.DTO.UserRequest;
import com.example.atm.project.Entity.MyUser;

import com.example.atm.project.Service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Atm/v1.0/User")
@SecurityRequirement(name = "basicAuth")
public class UserController {
    @Autowired
    private UserService ser;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MyUser> Add(@RequestBody @Valid UserRequest req)throws Exception{
        return new ResponseEntity<>(ser.AddUser(req), HttpStatus.CREATED);}

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ADMIN')")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<MyUser>> GetUser(){
        return new ResponseEntity<>(ser.GetUser(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MyUser> GetById(@PathVariable("id") String id) throws Exception {
        return new ResponseEntity<>(ser.GetById(Integer.parseInt(id)),HttpStatus.OK);
    }
//    @PostMapping("/add1")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<MyUser> Add(@RequestParam("name") String name,
//                                      @RequestParam("password") String password,
//                                      @RequestParam("role") String role) {
//        UserRequest req = UserRequest.connect(name, password, Role.valueOf(role));
//        return new ResponseEntity<>(ser.AddUser(req), HttpStatus.CREATED);
//    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MyUser> DeleteById(@PathVariable("id") String id)throws Exception{
        MyUser user=ser.DeleteById(Integer.parseInt(id));

            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);


    }
    @PutMapping("update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MyUser> Update(@PathVariable("id") String i, @RequestBody UserRequest req){
        return new ResponseEntity<>(ser.update(Integer.parseInt(i),req),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String Delete(){
        ser.DeleteAll();
        return "Deleted Successfully";
    }



}
