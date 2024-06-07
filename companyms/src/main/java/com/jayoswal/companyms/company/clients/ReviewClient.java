package com.jayoswal.companyms.company.clients;

import com.jayoswal.companyms.company.dto.AverageReviewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEW-MICROSERVICE", url = "${review-microservice.url}")
public interface ReviewClient {

    @GetMapping("/reviews/average")
    AverageReviewDTO averageReview(@RequestParam int companyId);
}
