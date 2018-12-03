package org.launchcode.controllers;

import org.launchcode.models.Job;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view
        Job job = jobData.findById(id);
        model.addAttribute("job", job);

        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model with an error message,
        if (errors.hasErrors()) {
            return "new-job";
        }
        // TODO... and if valid, create a new Job and add it to the jobData
        else {

            Job job = new Job(
            jobForm.getName(),
            jobData.getEmployers().findById(jobForm.getEmployerId()),
            jobData.getLocations().findById(jobForm.getLocationId()),
            jobData.getPositionTypes().findById(jobForm.getPositionTypeId()),
            jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId()));
            jobData.add(job);

            //TODO... and add it to the jobData data store.
            model.addAttribute("job", job);

            // TODO... Then redirect to the job detail view for the new Job.
            return "redirect:?id=" + job.getId();

            // redirect==>if name field has content ==> print to job-detail all the id's ==> /job?id=x(getId of new job).
            // redirect==> if name field is empty ==> redirects to form with an added "Name is empty" message.

        }
    }

}
//http://docs.sequelizejs.com/manual/tutorial/models-usage.html

/** JobController, will work to enable full creation of a Job object,
 * including all necessary fields.
 */
// Demo app: https://techjobs-oo.cfapps.io/
// Assignment page: https://education.launchcode.org/skills-back-end-java/assignments/techjobs-oo/
// https://docs.spring.io/autorepo/docs/spring-framework/3.0.0.M3/reference/html/ch16s11.html
// https://howtodoinjava.com/spring-boot2/resttemplate-post-json-example/
// https://docs.spring.io/spring-restdocs/docs/2.0.0.RELEASE/reference/html5/#configuration-default-preprocessors
