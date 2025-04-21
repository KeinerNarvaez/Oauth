package com.example.Auth2.respository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.Auth2.model.user;


public interface Iuser extends JpaRepository
<user,Integer>
{

    @Query("SELECT u FROM user u WHERE u.status != false")
    List<user> getListUserActive();

    @Query("SELECT u FROM user u WHERE u.email = ?1")
    List<user> getListUserForName(String email);
    
    @Query("SELECT u FROM user u WHERE u.email like %?1%")
    List<user> getname(String Filter);

    @Query("SELECT u FROM user u WHERE u.id_user = ?1")
    List<user> getUserById(int id);

}