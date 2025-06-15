package com.example.Auth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.Auth2.DTO.responseDTO;

import com.example.Auth2.DTO.userDTO;

import com.example.Auth2.service.userService;


@RestController
@RequestMapping("/api/v1/user")
public class userController {
    @Autowired
    private userService userService;



    
    // @DeleteMapping("/{id}") //usuario, falta desarrollar
    // public ResponseEntity<Object> deleteUser(@PathVariable int id) {
    //     ResponsesDTO response = userService.deleteUser(id);
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    @PutMapping("/updateProfileUser")//falta
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody userDTO userDTO) {
        responseDTO response = userService.update(id, userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
