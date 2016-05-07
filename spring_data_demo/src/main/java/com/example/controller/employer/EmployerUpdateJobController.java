package com.example.controller.employer;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Job;
import com.example.repository.EmployerRepository;
import com.example.repository.JobRepository;
import com.example.util.URLMapper;
import com.example.util.ValidateScripting;
import com.example.util.ViewMapper;

@Controller
public class EmployerUpdateJobController {

	static final String UPDATE_JOB_MODEL = "updateJob";

	@Autowired
	JobRepository jobRepository;

	@Autowired
	EmployerRepository employerRepository;

	@RequestMapping(value = URLMapper.EMPLOYER_UPDATE_JOB_URL, method = RequestMethod.GET)
	public String loadUpdateJobPage(Model model, @RequestParam(value = "jId") String jobId) {
		Job job = jobRepository.findOne(Long.parseLong(jobId));

		model.addAttribute(UPDATE_JOB_MODEL, job);
		model.addAttribute("employer_update_job_url", URLMapper.EMPLOYER_UPDATE_JOB_URL);

		return ViewMapper.EMPLOYER_UPDATE_JOB;
	}

	@RequestMapping(value = URLMapper.EMPLOYER_UPDATE_JOB_URL, method = RequestMethod.POST)
	public String postJob(@Valid @ModelAttribute(UPDATE_JOB_MODEL) Job job, BindingResult result, Model model,HttpServletRequest request) {

		// validate the job entry
		if (result.hasErrors()) {
			// the errors will automatically get mapped to form:errors path = ""
			model.addAttribute("employer_update_job_url", URLMapper.EMPLOYER_UPDATE_JOB_URL);
			return ViewMapper.EMPLOYER_UPDATE_JOB;
		}
		// Hardcoded right now. Change Later. based on login

		try {
			Job newJob = jobRepository.findOne(job.getId());
			if (null == newJob) {
				return ViewMapper.EMPLOYER_UPDATE_JOB;
			}
			String title =  ValidateScripting.validate(job.getTitle());
			String description = ValidateScripting.validate(job.getDescription());
			String location = ValidateScripting.validate(job.getLocation());
			

			newJob.setTitle(title);
			newJob.setDescription(description);
			newJob.setExperience(job.getExperience());
			newJob.setLocation(location);
			String type = request.getParameter("jobType");
			// System.out.println(type);
			newJob.setType(type);

			newJob.setUpdatedOn(new Date());

			// updating job
			jobRepository.save(newJob);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "redirect:" + URLMapper.EMPLOYER_JOB_LISTING_URL;
	}

	@RequestMapping(value = URLMapper.EMPLOYER_DELETE_JOB_URL, method = RequestMethod.GET)
	public String deleteJobPage(Model model, @RequestParam(value = "jId") String jobId) {
		Job job = jobRepository.findOne(Long.parseLong(jobId));

		job.setUpdatedOn(new Date());
		job.setStatus("deactive");
		job.setDeleted(true);

		jobRepository.save(job);

		return "redirect:" + URLMapper.EMPLOYER_JOB_LISTING_URL;
	}

}
