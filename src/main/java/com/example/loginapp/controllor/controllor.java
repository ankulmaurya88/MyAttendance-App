package com.example.loginapp.controllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Exception.UsernameNotAvailableException;
import com.example.loginapp.model.Timings;
import com.example.loginapp.model.UserMaster;
import com.example.loginapp.service.RegisterService;
import com.example.loginapp.service.TimingDaoService;
import com.example.loginapp.service.UserService;

@Controller
@RequestMapping("/")
public class controllor {
	
	@Autowired
	TimingDaoService tser;
	@Autowired
	RegisterService rs;
	  @Autowired
	    private UserService userService;
	
	@GetMapping("home")
	public String homepage() {
		return "home.html";
	}
	
	@GetMapping("register")
	public String registerMapping() {
		return "register.html";
	}
	
	///{username}/{password}/{email}/{phoneno}"
//	@RequestMapping("dbregistration")
//	public String dbregistration(@ModelAttribute UserMaster user) {
//		rs.newuser(user);
//		return "home.html";
//	}
//	
	
	

    @PostMapping("/dbregistration")
    public String dbregistration(@RequestParam String username, @RequestParam String password, @RequestParam Long phoneno,@RequestParam String email, Model model) {
        try {
            if (!userService.isUsernameAvailable(username) ){
                throw new UsernameNotAvailableException("username '" + username + "' is already taken.");
               
            }
          
            UserMaster user = new UserMaster();
            user.setUsername(username);
            user.setPassword(password);
            user.setPhoneno(phoneno);
            user.setEmail(email);

            userService.registerUser(user);
            
        //    return "successafterreg";
        	//rs.newuser(user);
    		return "home.html";
        } catch (UsernameNotAvailableException e) {
            model.addAttribute("errorMessage", e.getMessage());
            
            return "register.html";
        }
    }
	@PostMapping("dbloggedin")
	public String dblogin(@RequestParam(name="username") String username, @RequestParam("password") String password,Model model) {
		
		if(username.equals("admin")&& password.equals("admin")) {
			return "admin.html";
		}
		
		if(rs.checkcredentails(username, password)) {
//			rs.changeDbSigningStatustotrue();
//			rs.addsigningtime();
			String page = rs.signinoutmapping();
			return page;

		}else {
			//return "incorrectpass.html";
			
			 model.addAttribute("error", "Invalid username or password");
	          return "home.html";
		}
		
	}
	
	@GetMapping(value="/mappingusers", produces="application/json")
	//@RequestMapping("/mappingusers")
	@ResponseBody()
	public List<UserMaster> getusers(){
		return rs.getallusers();
	}
	
	@GetMapping("signin")
	//@PostMapping(/signin)
	public String signingdb() {
		rs.changeDbSigningStatustotrue();
		rs.addsigningtime();
		return "signout.html";
	}
	
//	@RequestMapping("signout")
//	public String signoutdb() {
//		rs.changeDbSigningStatustofalse();
//		rs.addsignouttime();
//		return "signout.html";
//	}
	
	@GetMapping("signout")
	public String signoutdb(Model model) {
		rs.changeDbSigningStatustofalse();
		rs.addsignouttime();
		model.addAttribute("success", "Signout Successfully");
		return "signout.html";
	}
	

//    @PutMapping("/updateSignoutime/{id}")
//    public String updateSignoutime(@PathVariable("id") int id, @RequestParam("newSignoutime") String newSignoutime) {
//    	tser.updateSignoutime(id, newSignoutime);
//        return "Sign-out time updated successfully.";
//    }
	
	@GetMapping(value="/viewreport", produces="application/json")
	@ResponseBody()
	public List<Timings> getbyId() {
		return rs.gettimings();
	}
	
	
	@RequestMapping("signinedit")
	public String Signedinedit() {
		rs.addsigningtime();
		return "signout.html";
	}
	
	@RequestMapping("signoutedit")
	public String SignedOutedit() {
		rs.addsignouttime();
		return "home.html";
	}
	
	@GetMapping("/mappinguser/attendance/{userid}")
	//@ResponseBodyl
	public List<Timings> findbysomevalue(@PathVariable() String userid) {
		return tser.findbyuserid(Long.parseLong(userid));
		
	}
}
