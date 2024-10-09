package com.example.automation.Controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.automation.Model.LoginModel;
import com.example.automation.Service.LoginService;
 
@Controller                                                         //attributes
public class LoginController {
@Autowired

    private LoginService userService;                                  
    @GetMapping("/")
    //controller  render(call) my html
    public ModelAndView login(HttpSession session) {    	
    
     ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new LoginModel());        
        return mav;
    }
    
    @GetMapping("/login")
    //controller  render(call) my html
    public ModelAndView login1(HttpSession session) {    	
    
     ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new LoginModel());        
        return mav;
    }
    
    @GetMapping("/login1")
    
    public String login(Model model) {   
         
		return "login";
    }
    
    @GetMapping("/session")
	public String Dashboard(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) session.getAttribute("username");
		
		  String s1 = "Priyanka";
	      String username = s1;
	      
	      
		if (messages == null) {
			messages = new ArrayList<>();
		}
		model.addAttribute("username", messages);
		System.out.println(messages + "No Session");

		return "Dashboard";
	}
     
    @PostMapping("/login")
    public String login(@ModelAttribute("user") LoginModel user ) {
    
     LoginModel oauthUser = userService.login(user.getUsername(), user.getPassword());
    
 
     System.out.print(oauthUser);
     if(Objects.nonNull(oauthUser))
     {
  
     return "redirect:/Dashboard";
    
     } else {
     return "redirect:/login";
    
     }
 
}
    @RequestMapping(value = {"/Dashboard"}, method = RequestMethod.POST)
    public String logout(HttpServletRequest request,HttpServletResponse response)
    {
        return "redirect:/login";
    }
    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request,HttpServletResponse response)
    {
        return "redirect:/login";
    }
    
    private static EntityManagerFactory entityManagerFactory =
	          Persistence.createEntityManagerFactory("automation");
	
	//RequestMethod in Login Controller
	@RequestMapping(value="LoginDataValue" , method= RequestMethod.GET)
	public List<LoginModel> getLogindata(Integer LocationId, Integer Departmentid, Integer BotId) {
		//return realtimedataRepos.getRealTime(1,1,1);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	      StoredProcedureQuery procedureQuery =
	              entityManager.createNamedStoredProcedureQuery(LoginModel.NamedQuery_LoginStoreProcedure);
	      procedureQuery.setParameter("pid", 1);
	      procedureQuery.setParameter("pusernamed", 1);
	      procedureQuery.setParameter("ppassword", 1);
	      procedureQuery.execute(); 
	      @SuppressWarnings("unchecked")
	      List<LoginModel> resultList = procedureQuery.getResultList();
		return resultList; 
	}
	
	
	//added by kiran
		@GetMapping("/UserRegister")    
	    public String showUserRegistrationForm(Model model) {
	       
	        return "UserRegister"; 
	    }
		//added by kiran
		@PostMapping("/register")
		public String register(@ModelAttribute LoginModel model, HttpSession session) {
			System.out.println(model);
			//LoginModel user = userService.registerService(model);
			session.setAttribute("message", "User Register Successfully");
			return "redirect:/";
		}
	
	
	
	
 
}