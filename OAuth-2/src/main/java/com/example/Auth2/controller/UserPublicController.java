package com.example.Auth2.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Auth2.DTO.RequestLoginDTO;
import com.example.Auth2.DTO.RequestRegisterUserDTO;
import com.example.Auth2.DTO.ResponseLogin;
import com.example.Auth2.DTO.responseDTO;

import com.example.Auth2.service.userService;


@RestController
@RequestMapping("/api/v1/public/users")
public class UserPublicController {
     @Autowired
    private userService userService;


   @PostMapping("/register")
    public ResponseEntity<Object> saveUser(@RequestBody RequestRegisterUserDTO user) {
        responseDTO response = userService.save(user);
        // ResponsesDTO response =null;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
        @GetMapping("/") // solo administrador
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

  
    @GetMapping("/profile")
    public ResponseEntity<Object> profile(@AuthenticationPrincipal UserDetails userDetails) {
            
        return new ResponseEntity<Object> (userDetails,HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //solo administrador, falta desarrollar
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        responseDTO response = userService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login") //falta desarrollar
    public ResponseEntity<ResponseLogin> login(@RequestBody RequestLoginDTO userDTO) {
        ResponseLogin response = userService.login(userDTO);
        // ResponseLogin response = null;
        return new ResponseEntity<ResponseLogin>(response, HttpStatus.OK);
    }

    //  @PostMapping("/forgot") //falta desarrollar
    // public ResponseEntity<Object> forgot(@RequestBody UserDTO userDTO) {
    //     // ResponsesDTO response = userService.save(userDTO);
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }
    
}
