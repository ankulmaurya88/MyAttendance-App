package com.example.loginapp.daorepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.example.Entities.User;
import com.example.loginapp.model.UserMaster;

@Repository
public interface DaoRepo extends JpaRepository<UserMaster, Integer> {

	UserMaster findByUsername(String username);

	UserMaster findByEmail(String email);

	//  User findByUsername(String username);
}
