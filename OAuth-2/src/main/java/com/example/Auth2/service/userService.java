package com.example.Auth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Auth2.DTO.RequestLoginDTO;
import com.example.Auth2.DTO.RequestRegisterUserDTO;
import com.example.Auth2.DTO.ResponseLogin;
import com.example.Auth2.DTO.responseDTO;
import com.example.Auth2.DTO.userDTO;
import com.example.Auth2.jwt.jwtServices;
import com.example.Auth2.model.user;
import com.example.Auth2.respository.Iuser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class userService {
    /*
     * save
     * findAll
     * findById
     * Delete
     */
    /* establish connection with the interface */
    @Autowired
    private Iuser data;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private jwtServices jwtService;

    public List<user> findAll() {
        return data.getListUserActive();
    }
    public List<user> getUserByEmail(String email) {
        return data.getUserByEmail(email);
    }

    // Buscar usuario por ID
    public Optional<user> findById(int id) {
        return data.findById(id);
    }

    // Buscar usuario por username
    public Optional<user> findByUsername(String username) {
        return data.findByUsername(username);
    }

    // Buscar usuario por email
    public Optional<user> findByEmail(String email) {
        return data.findByEmail(email);
    }

    public List<user> getUserById(int id) {
        return data.getUserById(id);
    }
    

    public responseDTO delete(int id) {
        Optional<user> user=findById(id);
        if (!user.isPresent()) {
            responseDTO respuesta = new responseDTO(
                    HttpStatus.OK,
                    "The register does not exist");
            return respuesta;
        }
        user.get().setStatus(false);
        data.save(user.get());
        // data.deleteById(id);
        
        responseDTO respuesta = new responseDTO(
                HttpStatus.OK,
                "Se eliminó correctamente");
        return respuesta;
    }
    public responseDTO update(int id, userDTO userDTO) {
        Optional<user> userOptional = findById(id);
        
        if (!userOptional.isPresent()) {
            return new responseDTO(HttpStatus.NOT_FOUND, "El usuario no existe");
        }
    
        // Validación del nombre
        if (userDTO.getUsername().length() < 1 || userDTO.getUsername().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST, "El nombre debe tener entre 1 y 50 caracteres");
        }
    
        try {
            user existingUser = userOptional.get();
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setEmail(userDTO.getEmail());

    
            data.save(existingUser);
    
            return new responseDTO(HttpStatus.OK, "Usuario actualizado correctamente");
    
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }
    // Login con autenticación y generación de JWT
    public ResponseLogin login(RequestLoginDTO loginDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), 
                loginDTO.getPassword()
            )
        );

        user userEntity = data.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String token = jwtService.getToken(userEntity);
        return new ResponseLogin(token);
    } 
    // Registrar nuevo usuario
    public responseDTO save(RequestRegisterUserDTO userDTO) {
        user usuario = convertToModelRegister(userDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        data.save(usuario);
        return new responseDTO(HttpStatus.OK, "Usuario guardado correctamente");
    }
    public user convertToModelRegister(RequestRegisterUserDTO usuario) {
        return new user(
                0,
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getEmail(),
                LocalDateTime.now(),
                true);
    }


    public user converToModel(userDTO userDTO) {
        user user = new user(
                0,
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                LocalDateTime.now(),
                true);
        return user;
    }

}