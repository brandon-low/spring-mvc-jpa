package hello.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
/**
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import hello.mvc.entity.User;
import hello.mvc.service.UserService;

@Controller
public class DefaultController {
	 private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);
	
	 	@Autowired
	    UserService userService;
	 
	 	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	    public String homePage(ModelMap model) {
	 		if (logger.isDebugEnabled()) {
	    		logger.debug("Home Page");
	    	}
	 		
	 		try {
	 			model.addAttribute("user", getPrincipal());
	 		} catch (Exception e) {
	 			logger.warn("caught an exception" + e.toString());
	 		}
	        return "/home";
	    }
	 
	    @RequestMapping(value = "/admin", method = RequestMethod.GET)
	    public String adminPage(ModelMap model) {
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("Admin Page");
	    	}
	    	
	        model.addAttribute("user", getPrincipal());
	        return "/test_admin";
	    }
	     
	    @RequestMapping(value = "/user", method = RequestMethod.GET)
	    public String userPage(ModelMap model) {
	    	
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("User Page");
	    	}
	    	
	        model.addAttribute("user", getPrincipal());
	        return "/test_user";
	    }
	    
	    @RequestMapping(value = "/about", method = RequestMethod.GET)
	    public String aboutPage(ModelMap model) {
	    	
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("About Page");
	    	}
	    	
	        model.addAttribute("user", getPrincipal());
	        return "/test_about";
	    }
	    
	    @GetMapping("/international")
	    public String getInternationalPage() {
	        return "test_international";
	    }
	 
	    @RequestMapping(value = {"/Access_Denied", "/403"}, method = RequestMethod.GET)
	    public String accessDeniedPage(ModelMap model) {
	    	if (logger.isDebugEnabled()) {
	    		logger.debug("Access Denied Page");
	    	}
	    	
	        model.addAttribute("user", getPrincipal());
	        return "/error/403";
	    }
	    
		
	   	 
	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String loginPage( @RequestParam(value = "error", required = false) String error,
	    						@RequestParam(value = "logout", required = false) String logout, 
	    						@RequestParam(value = "success", required = false) String success, 
	    						HttpServletRequest request, HttpServletResponse response, ModelMap model) {
	    	
	    	logger.debug("GET: LoginPage {}:{}:{} " , error , logout, success);
	    	
	    	if (error != null) {
	    		if (logger.isDebugEnabled())
	    			logger.debug("GET: LoginPage set error message session:{}", request.getSession().getAttribute("error"));
	    		model.addAttribute("error", request.getSession().getAttribute("error"));
			}

			if (logout != null) {
				if (logger.isDebugEnabled())
	    			logger.debug("GET: LoginPage set msg message");
				model.addAttribute("msg", "You've been logged out successfully.");
			}
			
			if (success != null) {
				if (logger.isDebugEnabled())
	    			logger.debug("GET: LoginPage set msg message");
				model.addAttribute("msg", "login successfully.");
			}
	        return "/login";
	    }
	     
	    @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public String loginPostPage( @RequestParam(value = "username", required = false) String username,
	    						@RequestParam(value = "password", required = false) String password, 
	    						HttpServletRequest request, HttpServletResponse response, ModelMap model) {	
	    	logger.debug("Login POST username {}:{} " , username , password);
	    	try {
	    		User u = userService.getUserByUsernamePassword(username, password);
	    		request.getSession().setAttribute("user", u.getUsername());
	    		request.getSession().setAttribute("roles", u.getUserRole());
	    		model.addAttribute("msg", "You've been logged out successfully.");
	    		//request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FOUND);
	    		return "redirect:/login?success";
	    	} catch (Exception e) {
	    		request.setAttribute("error", "Unable to login");
	    		request.getSession().setAttribute("error", "Unable to login");
	    		if (logger.isDebugEnabled())
	    			logger.debug("Login Post redirect:/login?error");
	    		//request.setAttribute( View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FOUND);
	    		return "redirect:/login?error";
	    	}
	    }
	 
	    @RequestMapping(value="/logout", method = RequestMethod.GET)
	    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    	// clear the user session
	    	request.getSession().removeAttribute("user");
	    	request.getSession().removeAttribute("roles");
	    	/*
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null){    
	            new SecurityContextLogoutHandler().logout(request, response, auth);
	        }
	        */
	        return "redirect:/login?logout";
	    }
	 
	    private String getPrincipal(){
	        String userName = null;
	        /*
	        try {
	        	
	        	if (SecurityContextHolder.getContext() != null) {
	        		logger.debug("Security Context:" +SecurityContextHolder.getContext() );
	        	}
		        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		        if (principal instanceof UserDetails) {
		            userName = ((UserDetails)principal).getUsername();
		        } else {
		            userName = principal.toString();
		        }
	        
	        } catch (Exception e) {
	        	//logger.warn(e.toString());
	        }
	        */
	        return userName;
	    }
	    
	 
}
