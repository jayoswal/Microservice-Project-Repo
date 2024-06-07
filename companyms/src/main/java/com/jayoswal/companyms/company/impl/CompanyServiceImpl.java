package com.jayoswal.companyms.company.impl;

import com.jayoswal.companyms.company.Company;
import com.jayoswal.companyms.company.CompanyRepository;
import com.jayoswal.companyms.company.CompanyService;
import com.jayoswal.companyms.company.clients.ReviewClient;
import com.jayoswal.companyms.company.dto.AverageReviewDTO;
import com.jayoswal.companyms.company.dto.ReviewMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ReviewClient reviewClient;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company updateCompany(int id, Company company) {
        Company updatedCompany = companyRepository.findById(id).orElse(null);
        if(updatedCompany != null){
            updatedCompany.setName(company.getName());
            updatedCompany.setDescription(company.getDescription());
            companyRepository.save(updatedCompany);
            return updatedCompany;
        }
        else {
            return null;
        }
    }

    @Override
    public Company createCompany(Company company) {
        Company savedCompany = companyRepository.save(company);
        return savedCompany;
    }

    @Override
    public Company getComapnyById(int id) {
        Company company = companyRepository.findById(id).orElse(null);
        return company;
    }

    @Override
    public boolean deleteCompany(int id) {
        try {
            if(companyRepository.existsById(id)) {
                // TODO - delete a company login with reviews
                companyRepository.deleteById(id);
                return true;
            }
        }
        catch (Exception e) {
            return false;
        }

        return false;
    }

    @Override
    public void updateCompanyRating(ReviewMessageDTO reviewMessageDTO) {
        System.out.println("Message from queue: " + reviewMessageDTO.toString());
        // with the companyId, call averageReviewEndpoint of Review Microservice
        // and get number of reviews and average rating.
        AverageReviewDTO averageReviewDTO = reviewClient.averageReview(reviewMessageDTO.getCompanyId());
        System.out.println("Average Review: " + averageReviewDTO.toString());

        // TODO - update in db
        Company company = companyRepository.findById(reviewMessageDTO.getCompanyId()).orElse(null);
        if(company != null) {
            company.setNumberOfReviews(averageReviewDTO.getNumberOfReviews());
            company.setAverageRating(averageReviewDTO.getAverageRating());
            Company updated = companyRepository.save(company);
            System.out.println("Company updated after rating added: " + updated.toString());
        }
        else {
            // TODO - reviews gets added for companyID not found ... need to check in Review
            // TODO - also rabbitmq is used only for new reviews creation and not when old review raring is updated.
        }
    }
}
