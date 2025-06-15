package com.example.Auth2.DTO;

import java.time.LocalDateTime;
public class userDTO {


   private int id_user;

   private String username;
   private String password;

   private String email;


   private LocalDateTime registration_date;

   private boolean status;
   public userDTO(int id_user, String username, String password, String email,LocalDateTime registration_date, boolean status) {
      this.id_user = id_user;
      this.username = username;
      this.password = password;
      this.email = email;
      this.registration_date = registration_date;
      this.status = status;
   }

   // get del ID
   public int getId_user() {
      return id_user;
   }

   // set del ID
   public void setId_user(int id_user) {
      this.id_user = id_user;
   }

   // get del firstName
   public String getUsername() {
      return username;
   }

   // set del firstName
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

   // set del phone
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
}
