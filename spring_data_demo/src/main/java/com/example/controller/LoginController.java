package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	ModelAndView mv;

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	 * protected ModelAndView handleRequestInternal(HttpServletRequest request,
	 * HttpServletResponse response) throws Exception { String username =
	 * request.getParameter("username"); String password =
	 * request.getParameter("password"); HttpSession session =
	 * request.getSession();
	 * 
	 * 
	 * 
	 * session.setAttribute("user", user);
	 * 
	 * // write code wen user not found if (user != null &&
	 * user.getPassword().equals(password) ) {
	 * 
	 * if (user.getRole().equalsIgnoreCase("employer")) {
	 * 
	 * // employer login mv = new ModelAndView("employerDashboard", "employer",
	 * user);
	 * 
	 * } else if (user.getRole().equalsIgnoreCase("candidate")) {
	 * 
	 * // candidate login mv = new ModelAndView("candidateDashboard",
	 * "candidate", user);
	 * 
	 * } else {
	 * 
	 * // admin login mv = new ModelAndView("adminDashboard", "admin", user);
	 * 
	 * } } else { mv = new ModelAndView("loginpage", "status", true); } return
	 * mv;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/login.htm", method = RequestMethod.GET) public
	 * String initializeForm(@ModelAttribute("user") User user, BindingResult
	 * result) {
	 * 
	 * return "loginpage"; }
	 * 
	 * @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	 * protected ModelAndView handleRequestInternalLogout(HttpServletRequest
	 * request, HttpServletResponse response) throws Exception {
	 * 
	 * HttpSession session = request.getSession(); session.invalidate();
	 * 
	 * mv = new ModelAndView("loginpage");
	 * 
	 * return mv;
	 * 
	 * }
	 */

}
