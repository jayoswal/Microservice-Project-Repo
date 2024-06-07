package com.jayoswal.jobms.job.mapper;

import com.jayoswal.jobms.job.Job;
import com.jayoswal.jobms.job.dto.JobDTO;
import com.jayoswal.jobms.job.external.Company;
import com.jayoswal.jobms.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobDto(
            Job job,
            Company company,
            List<Review> reviews
    ) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setTitle(job.getTitle());

        return jobDTO;
    }
}
