package com.example.Auth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Auth2.DTO.responseDTO;
import com.example.Auth2.DTO.userDTO;
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

    public List<user> findAll() {
        return data.getListUserActive();
    }

    public List<user> getListUserForName(String email) {
        return data.getListUserForName(email);
    }
    public List<user> getname(String Filter) {
        return data.getname(Filter);
    }
    public List<user> getUserById(int id) {
        return data.getUserById(id);
    }
    public Optional<user> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteUser(int id) {
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
        if (userDTO.get_name().length() < 1 || userDTO.get_name().length() > 50) {
            return new responseDTO(HttpStatus.BAD_REQUEST, "El nombre debe tener entre 1 y 50 caracteres");
        }
    
        try {
            user existingUser = userOptional.get();
            existingUser.set_breedName(userDTO.get_name());
            existingUser.setEmail(userDTO.getEmail());

    
            data.save(existingUser);
    
            return new responseDTO(HttpStatus.OK, "Usuario actualizado correctamente");
    
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }
    
    // register and update
    public responseDTO save(userDTO userDTO) {
        // validación longitud del nombre
        if (userDTO.get_name().length() < 1 ||
                userDTO.get_name().length() > 50) {
            responseDTO respuesta = new responseDTO(
                    HttpStatus.BAD_REQUEST,
                    "El nombre debe estar entre 1 y 50 caracteres");
            return respuesta;
        }
        // otras condiciones
        // n
        user userRegister = converToModel(userDTO);
        data.save(userRegister);
        responseDTO respuesta = new responseDTO(
                HttpStatus.OK,
                "Se guardó correctamente");
        return respuesta;
    }

    public userDTO convertToDTO(user user) {
        userDTO userdto = new userDTO(
                user.getId_user(),
                user.get_name(),
                user.getEmail(),
                user.get_registration_date(),
                user.getStatus()
                );
        return userdto;
    }

    public user converToModel(userDTO userDTO) {
        user user = new user(
                0,
                userDTO.get_name(),
                userDTO.getEmail(),
                LocalDateTime.now(),
                true);
        return user;
    }

}
