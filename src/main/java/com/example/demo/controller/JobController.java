package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Job;
import com.example.demo.service.ContactService;
import com.example.demo.service.JobService;

@RestController
@RequestMapping("job")
public class JobController {
    @Autowired
    JobService jobService;

    @Autowired
    ContactService contactService;

    @GetMapping("/")
    public @ResponseBody List<Job> getAllJob() {
        return jobService.getAllJob();
    }

    @PostMapping("/create-job")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        try {
            if (contactService.findById(job.getContact().getId()).getId() == null) {
                return new ResponseEntity<>("Not Found Contact with id!!" + job.getContact().getId(),
                        HttpStatus.NOT_FOUND);
            }
            jobService.createJob(job);
        } catch (Exception e) {

            return new ResponseEntity<>("Something went wrong!!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Create Job successfully!!", HttpStatus.OK);
    }

    @PutMapping("/update-job")
    public ResponseEntity<?> updateJob(@RequestBody Job job) {
        if (contactService.findById(job.getContact().getId()).getId() == null) {
            return new ResponseEntity<>("Not Found Contact with id!!" + job.getContact().getId(),
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(jobService.updateJob(job));
    }

    @DeleteMapping("/delete-job-{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        try {
            jobService.deleteJob(id);
        } catch (Exception e) {

            return new ResponseEntity<>("Not Found Job!!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Delete Job Successfully", HttpStatus.OK);
    }
}
