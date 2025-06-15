package com.example.Auth2.model;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "user")

public class user implements UserDetails {
   /*
    * atributos o columnas de la entidad
    */
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_user")
   private int id_user;

   @Column(name = "name", length = 50, nullable = false)
   private String username;
   @Column(name = "password", length = 150, nullable = false)
   private String password;

   @Column(name = "email", length = 150, nullable = false)
   private String email;


   @Column(name = "registration_date", nullable = false)
   private LocalDateTime registration_date;

   @Column(name="status",nullable =false)
   private boolean status;
   public user() {
   }
   public user(int id_user, String username,String password, String email,LocalDateTime registration_date, boolean status) {
      this.id_user = id_user;
      this.username = username;
      this.password = password;
      this.email = email;
      this.registration_date = registration_date;
      this.status = status;
   }


   public int getId_user() {
      return id_user;
   }


   public void setId_user(int id_user) {
      this.id_user = id_user;
   }

   public String getUsername() {
      return username;
   }


   public void setUsername(String username) {
      this.username = username;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }

   public LocalDateTime get_registration_date() {
      return registration_date;
   }


   public void set_registration_date(LocalDateTime registration_date) {
      this.registration_date = registration_date;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public boolean getStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return Collections.emptyList();
   }


}
