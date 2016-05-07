package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Candidate;
import com.example.entity.Employer;
import com.example.entity.JPUser;
import com.example.repository.CandidateRepository;
import com.example.repository.EmployerRepository;
import com.example.repository.JPUserRepository;
import com.example.request.UserRegistrationRequest;
import com.example.util.Role;
import com.example.util.URLMapper;
import com.example.util.ValidateScripting;
import com.example.util.ViewMapper;

@Controller
@RequestMapping(URLMapper.REGISTRATION)
public class RegistrationController {

	public static final String REGISTRATION_MODEL = "registration";

	String univ;
	
	@Autowired
	JPUserRepository jpUserRepository;

	@Autowired
	EmployerRepository employerRepository;

	@Autowired
	CandidateRepository candidateRepository;


	@RequestMapping(method = RequestMethod.GET)
	public String loadRegistrationPage(Model model) {
		model.addAttribute(REGISTRATION_MODEL, new UserRegistrationRequest());
		model.addAttribute("registration_url", URLMapper.REGISTRATION);
		return ViewMapper.REGISTRATION;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String userRegistration(
			@Valid @ModelAttribute(REGISTRATION_MODEL) UserRegistrationRequest userRegistrationRequest,
			BindingResult result) {
		
		String name = userRegistrationRequest.getName();
		String email = userRegistrationRequest.getEmail();
		String linkedinURL = userRegistrationRequest.getLinkedInUrl();
		String username = userRegistrationRequest.getUsername();
		
		name = ValidateScripting.validate(name);
		email = ValidateScripting.validate(email);
		linkedinURL = ValidateScripting.validate(linkedinURL);
		username = ValidateScripting.validate(username);

		if (result.hasErrors()) {
			return ViewMapper.REGISTRATION;
		}

		if(Role.employer.name().equalsIgnoreCase(userRegistrationRequest.getRole())){

			System.out.println(username);
			
			employerRepository.save(new Employer(name, email, linkedinURL,username));
		}

		if(Role.candidate.name().equalsIgnoreCase(userRegistrationRequest.getRole())){

			univ = userRegistrationRequest.getUnivName();
			
			name = ValidateScripting.validate(name);
			email = ValidateScripting.validate(email);
			linkedinURL = ValidateScripting.validate(linkedinURL);
			username = ValidateScripting.validate(username);
			univ = ValidateScripting.validate(univ);
			
			candidateRepository.save(
					new Candidate(name,
							univ,
							email,
							linkedinURL,
							userRegistrationRequest.getGpa(), username));
		}

		jpUserRepository.save(
				new JPUser(username,
						userRegistrationRequest.getPassword(),
						userRegistrationRequest.getRole().toLowerCase(),
						true
						));


		return ViewMapper.LOGIN;
	}
}