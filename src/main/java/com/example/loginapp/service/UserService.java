package com.example.loginapp.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exception.UsernameNotAvailableException;
//
//import com.example.Entities.User;
import com.example.Exception.UsernameNotAvailableException;
//import com.example.Repo.UserRepository;
import com.example.loginapp.model.UserMaster;

@Service
public interface UserService {


//    public User findByUsername(String username) ;
//
//    public boolean isValidUser(String username, String password) ;
//       
//    void saveUserInput(Long id, String userInput);
//    //===============================
//    Long authenticateAndGetUserId(String username, String password);
//    
//    User getUserById(Long id);
//    
//    void save(User user);
//
////==========================for duplicate user===========================
    boolean isUsernameAvailable(String username);
    void registerUser(UserMaster user) throws UsernameNotAvailableException;
    


}