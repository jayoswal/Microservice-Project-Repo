package com.jayoswal.jobms.job.impl;

import com.jayoswal.jobms.job.Job;
import com.jayoswal.jobms.job.JobRepository;
import com.jayoswal.jobms.job.JobService;
import com.jayoswal.jobms.job.clients.CompanyClient;
import com.jayoswal.jobms.job.clients.ReviewClient;
import com.jayoswal.jobms.job.dto.JobDTO;
import com.jayoswal.jobms.job.external.Company;
import com.jayoswal.jobms.job.external.Review;
import com.jayoswal.jobms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    // provide at runtime (as already menitoned as bean
//    @Autowired
//    RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    private JobDTO convertToDTO(Job job) {
        System.out.println("HEREEEEE");

//        Company company = restTemplate.getForObject("http://COMPANYMS/companies/" + job.getCompanyId(), Company.class);
        Company company = companyClient.getCompany(job.getCompanyId());
        System.out.println(company.toString());
        System.out.println("HEREEEEE2");

//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEWMS/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>(){});
//        List<Review> reviews = reviewResponse.getBody();
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDTO jobDTO = JobMapper.mapToJobDto(job, company, reviews);
        return jobDTO;
    }

    int attemptRetry = 0;

    @Override
//    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
        System.out.println("Attempt: " + ++attemptRetry);
        List<Job> jobs = jobRepository.findAll();

        // TODO - used this instead of for loop
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<String> companyBreakerFallback(Exception e) {
        List<String> list = new ArrayList<>();
        list.add("There is some problem.");
        list.add("Please try again.");
        list.add("From companyBreaker CircuitBreaker");

        return list;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(int id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            JobDTO jobWithCompanyDTO = convertToDTO(job);
            return jobWithCompanyDTO;
        }
        else {
            return null;
        }
    }

    @Override
    public Boolean deleteJobById(int id) {
        try {
            // TODO - error if no job found
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateJob(int id, Job updatedJob) {
        // TODO - error if no job found

        Job job = jobRepository.findById(id).orElse(null);
        if(job != null) {
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setTitle(updatedJob.getTitle());
            jobRepository.save(job);
            return true;
        }
        else {
            return false;
        }
    }
}
