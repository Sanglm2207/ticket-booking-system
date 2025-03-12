package net.javaguides.jobhunter.service;

import net.javaguides.jobhunter.domain.Company;
import net.javaguides.jobhunter.domain.response.ResultPaginationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface CompanyService {
    Company handleCreateCompany(Company c);
    ResultPaginationDTO handleGetCompany(Specification<Company> spec, Pageable pageable);
    Company handleUpdateCompany(Company c);
    void handleDeleteCompany(long id);
    Optional<Company> findById(long id);
}
