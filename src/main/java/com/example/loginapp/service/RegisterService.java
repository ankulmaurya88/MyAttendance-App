package com.example.loginapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.app.Entities.User;
import com.example.Exception.UsernameNotAvailableException;
import com.example.loginapp.daorepo.DaoRepo;
import com.example.loginapp.daorepo.TimingDao;
import com.example.loginapp.model.Timings;
import com.example.loginapp.model.UserMaster;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Service
public class RegisterService { 
//	public HttpSession session = request.getSession();

	@Autowired
	TimingDaoService td;
	
	@Autowired
	DaoRepo dbop;
	public UserMaster um;
	
	
	public Timings time1;

	public void newuser(UserMaster user) {
		// TODO Auto-generated method stub
		dbop.save(user);
	}
	
	public String signinoutmapping() {
		if(um.isLoggedinstatus()!=true) {
			return "loggedin.html";
		}
		else {
			return "signout.html";
		}
	}

	public boolean checkcredentails(String username, String password) {
		// TODO Auto-generated method stub
		um = dbop.findByUsername(username);
		
		System.out.print(username + " " + password);
		
		
		//if(um.getUsername()!=null && um.getPassword()!=null) {}
		//redirecting onbasis of credentails validatity
		//if(um!=null) {}
		if(um.getPassword().equals(password)) {
			System.out.print("Login successful");
			
			
//			Cookie ck = new Cookie("userid",um.getPhoneno().toString());
//			ck.setAttribute("id",um.getPhoneno().toString());
			//session.setAttribute("phoneno", um.getPhoneno() );
			return true;
		}else {
			return false;
		}
		
	}

	public void changeDbSigningStatustotrue() {
		// TODO Auto-generated method stub
		//setting loggeindstaus flag to true
		um.setLoggedinstatus(true);
		
		dbop.save(um);
	}

	public void changeDbSigningStatustofalse() {
		// TODO Auto-generated method stub
		um.setLoggedinstatus(false);
		
		dbop.save(um);
	}

	public List<Timings> gettimings() {
		// TODO Auto-generated method stub
		//Long phonneno= (Long) //session.getAttribute("phoneno");
		return um.getTimings();
	}

	public void addsigningtime() {
		// TODO Auto-generated method stub
		td.addsiginingtime(um);
	}

	public void addsignouttime() {
		// TODO Auto-generated method stub
		td.addsigouttime(um);
	}

//	public void addsignouttime() {
//		// TODO Auto-generated method stub
//		td.addsigouttime(time1);
//	}
	public List<UserMaster> getallusers() {
		// TODO Auto-generated method stub
		return dbop.findAll();
	}
//	//  @Override
//	    public boolean isUsernameAvailable(String username) {
//	        return dbop.findByUsername(username) == null;
//	        
//	    }
		/*
		 * 
		 * //@Override public void registerUser(UserMaster user) throws
		 * UsernameNotAvailableException { if (!isUsernameAvailable(user.getUsername()))
		 * { throw new UsernameNotAvailableException("Username '" + user.getUsername() +
		 * "' is already taken."); }
		 * 
		 * dbop.save(user); }
		 */
}
