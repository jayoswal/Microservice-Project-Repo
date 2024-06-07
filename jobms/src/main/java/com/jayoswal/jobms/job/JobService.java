package com.jayoswal.jobms.job;

import com.jayoswal.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {

    List<JobDTO> findAll();

    void createJob(Job job);

    JobDTO getJobById(int id);

    Boolean deleteJobById(int id);

    boolean updateJob(int id, Job updatedJob);
}
