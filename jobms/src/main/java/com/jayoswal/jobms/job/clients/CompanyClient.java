package com.jayoswal.jobms.job.clients;

import com.jayoswal.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-MICROSERVICE", url = "${company-microservice.url}")
public interface CompanyClient {

    // TODO - What is Ribbon and load balanced?

    @GetMapping("/companies/{companyId}")
    Company getCompany(@PathVariable int companyId);
}
