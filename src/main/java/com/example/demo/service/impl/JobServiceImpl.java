package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Job;
import com.example.demo.service.ContactService;
import com.example.demo.service.JobService;
import com.example.demo.service.repository.JobRepository;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    ContactService contactService;

    @Override
    public List<Job> getAllJob() {

        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) {
        return jobRepository.findById(job.getId_Job())
                .map(jobUpdate -> {
                    jobUpdate.setName(job.getName());
                    return jobRepository.save(jobUpdate);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Job not exist with id:"
                        + job.getId_Job()));
    }

    @Override
    public void deleteJob(Long id) {

        jobRepository.deleteById(id);
    }
}
