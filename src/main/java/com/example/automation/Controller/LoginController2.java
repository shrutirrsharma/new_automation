package com.example.automation.Controller;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.automation.Model.LoginModel;
import com.example.automation.Service.LoginService;

public class LoginController2 {
	
	  private LoginService userService;      

		@GetMapping("/sdsd")
		
		public ModelAndView login1() {
		 ModelAndView m_v = new ModelAndView("login");
		   // m_v.addObject("user", new LoginModel());
		    return m_v;
		}
		
		@GetMapping("/Shruti")
		
		public ModelAndView login2()
		{
			ModelAndView m_v2=new ModelAndView("login");
			 //m_v2.addObject("user", new LoginModel());
			 return m_v2;
			
	    }
		
        @GetMapping("/Shashank")
		
		public ModelAndView login3()
		{
			ModelAndView m_v3=new ModelAndView("login");
			// m_v3.addObject("user", new LoginModel());
			 return m_v3;
			
	    }
        
        @PostMapping("/login")
        public String login4(@ModelAttribute("user") LoginModel U ) {
        
         LoginModel OUser = userService.login(U.getUsername(), U.getPassword());
        
     
         System.out.print(OUser);
         if(Objects.isNull(OUser))
         {
      
         return "redirect:/login";
        
         } else {
         return "redirect:/Dashboard";
        
         }
         
        }
		
		
	
	

}
