package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Job;

public interface JobService {

    public List<Job> getAllJob();

    public Job createJob(Job job);

    public Job updateJob(Job job);

    public void deleteJob(Long id);
}
