package com.jayoswal.companyms.company;

import com.jayoswal.companyms.company.dto.ReviewMessageDTO;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    Company updateCompany(int id, Company company);
    Company createCompany(Company company);
    Company getComapnyById(int id);
    boolean deleteCompany(int id);
    void updateCompanyRating(ReviewMessageDTO reviewMessageDTO);
}
