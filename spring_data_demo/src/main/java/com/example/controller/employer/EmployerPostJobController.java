package com.example.controller.employer;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Employer;
import com.example.entity.Job;
import com.example.repository.EmployerRepository;
import com.example.repository.JobRepository;
import com.example.util.URLMapper;
import com.example.util.ValidateScripting;
import com.example.util.ViewMapper;

@Controller
@RequestMapping(URLMapper.EMPLOYER_POST_JOB_URL)
public class EmployerPostJobController {

	static final String POST_JOB_MODEL = "postJob";

	@Autowired
	JobRepository jobRepository;

	@Autowired
	EmployerRepository employerRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String loadPostJobPage(Model model) {
		model.addAttribute(POST_JOB_MODEL, new Job());
		model.addAttribute("employer_post_job_url", URLMapper.EMPLOYER_POST_JOB_URL);

		return ViewMapper.EMPLOYER_POST_JOB;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postJob(@Valid @ModelAttribute(POST_JOB_MODEL) Job job, BindingResult result, Model model,
			HttpServletRequest request,Authentication auth) {

		// validate the job entry
		if (result.hasErrors()) {
			// the errors will automatically get mapped to form:errors path = ""
			model.addAttribute("employer_post_job_url", URLMapper.EMPLOYER_POST_JOB_URL);
			return ViewMapper.EMPLOYER_POST_JOB;
		}
		
		
		Employer employer = employerRepository.findByUsername(auth.getName());
//		System.out.println(auth.getName()+"iii");
		//model.addAttribute("jobs", jobRepository.findByEmployerId(employer.getId()));
		 String title =  ValidateScripting.validate(job.getTitle());
		String description = ValidateScripting.validate(job.getDescription());
		String location = ValidateScripting.validate(job.getLocation());
		
		// Hardcoded right now. Change Later. based on login
		job.setEmployer(employerRepository.findOne(employer.getId()));
		job.setPostedOn(new Date());
		job.setDeleted(false);
		job.setTitle(title);
		job.setDescription(description);
		job.setStatus("active");
		job.setLocation(location);
		String type = request.getParameter("jobType");
		// System.out.println(type);
		
		job.setType(type);

		// saving job
		jobRepository.save(job);

		return "redirect:" + URLMapper.EMPLOYER_JOB_LISTING_URL;
	}
}