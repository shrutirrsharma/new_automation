package com.example.automation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.*;

import javax.servlet.http.HttpSession;



@Controller
public class DashboardController {
	
	@Autowired(required=false)
	//private DashboardService dash;
	
	@GetMapping("/Upload")
	public ModelAndView invokeme1()  {
		ModelAndView mav = new ModelAndView("Upload");
		//mav.addObject("std", new DashboardDomain().Returnlist());
	
		return mav;
	}
	
	@GetMapping("/Dashboard")
	public ModelAndView invokeme()  {
		ModelAndView mav = new ModelAndView("Dashboard");
		//mav.addObject("std", new DashboardDomain().Returnlist());
	
		return mav;
	}
	
	@GetMapping("/Admin_Login")
	public ModelAndView invokeAdmin()  {
		ModelAndView mav = new ModelAndView("Admin_Login");
		//mav.addObject("std", new DashboardDomain().Returnlist());
	
		return mav;
	}
	
	@GetMapping("/Admin_Login_Grid")
	public ModelAndView invokeAdmingrid()  {
		ModelAndView mav = new ModelAndView("Admin_Login_Grid");
		//mav.addObject("std", new DashboardDomain().Returnlist());
	
		return mav;
	}
	
@GetMapping("/DropDown")
public ModelAndView invoke() {
	ModelAndView mav = new ModelAndView("DropDown");
	//mav.addObject("std", new Details());
	return mav;
}

@GetMapping("/dataGridT")
public ModelAndView invokedatagrid() {
ModelAndView mav = new ModelAndView("datatableT");
return mav;
}


@GetMapping("/BotMaster")
public ModelAndView invokeb() {
ModelAndView mav = new ModelAndView("BotMaster");
//mav.addObject("std", new Details());
return mav;
}

@GetMapping("/DepartmentMaster")
 public ModelAndView invoke1() {
ModelAndView mav = new ModelAndView("DepartmentMaster");
//mav.addObject("std", new Details());
return mav;
}


@GetMapping("/LocationList")
public ModelAndView invokeme3() {
	ModelAndView mav = new ModelAndView("LocationList");
	//mav.addObject("std", new DashboardDomain().Returnlist());
	return mav;
}

@GetMapping("/LocationMaster")
public ModelAndView invoke2() {
ModelAndView mav = new ModelAndView("LocationMaster");
//mav.addObject("std", new Details());
return mav;
}


@GetMapping("/BotList")
public ModelAndView invoke4() {
	ModelAndView mav = new ModelAndView("BotList");
	//mav.addObject("std", new DashboardDomain().Returnlist());
	return mav;
}

@GetMapping("/DepartmentList")
public ModelAndView invoke5() {
	ModelAndView mav = new ModelAndView("DepartmentList");
	//mav.addObject("std", new DashboardDomain().Returnlist());
	return mav;
}

@GetMapping("/Dashboard1")
public String process(Model model, HttpSession session) {
	@SuppressWarnings("unchecked")
	List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

	if (messages == null) {
		messages = new ArrayList<>();
	}
	model.addAttribute("sessionMessages", "Priyanka");

	return "Dashboard";
}
}

