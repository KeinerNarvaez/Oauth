package com.example.Auth2.controller;

import org.springframework.web.bind.annotation.RestController;


import com.example.Auth2.DTO.responseDTO;
import com.example.Auth2.DTO.userDTO;

import com.example.Auth2.service.userService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/user") 
public class userController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private userService userService;
    @PostMapping("/")
    public ResponseEntity<Object> registeruser(@RequestBody userDTO user) {
        responseDTO respuesta = userService.save(user);
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUser() {
        var listaUsuario = userService.findAll();
        return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
    }

    /*
     * Se requiere un dato, el ID
     * PathVariable=captura de información por la URL
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int id) {
        var usuario = userService.findById(id);
        if (!usuario.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<Object> getListUserForName(@PathVariable String email) {
        var userList = userService.getListUserForName(email);
        if (userList.isEmpty()) {
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    
    @GetMapping("/by-name/{filter}")
    public ResponseEntity<Object> getname(@PathVariable String filter) {
        var userList = userService.getname(filter);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        var message = userService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<responseDTO> updateuser(@PathVariable int id,@RequestBody userDTO userDTO) {
        responseDTO respuesta = userService.update(id, userDTO);
        return new ResponseEntity<>(respuesta, respuesta.getStatus());
    }

}
