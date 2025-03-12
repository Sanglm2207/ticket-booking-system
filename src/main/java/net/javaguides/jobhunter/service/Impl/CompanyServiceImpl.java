package net.javaguides.jobhunter.service.Impl;

import java.util.List;
import java.util.Optional;

import net.javaguides.jobhunter.domain.Company;
import net.javaguides.jobhunter.domain.User;
import net.javaguides.jobhunter.domain.response.ResultPaginationDTO;
import net.javaguides.jobhunter.repository.CompanyRepository;
import net.javaguides.jobhunter.repository.UserRepository;
import net.javaguides.jobhunter.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Company handleCreateCompany(Company c) {
        return this.companyRepository.save(c);
    }

    @Override
    public ResultPaginationDTO handleGetCompany(Specification<Company> spec, Pageable pageable) {
        Page<Company> pCompany = this.companyRepository.findAll(spec, pageable);
        ResultPaginationDTO rs = new ResultPaginationDTO();
        ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();

        mt.setPage(pageable.getPageNumber() + 1);
        mt.setPageSize(pageable.getPageSize());

        mt.setPages(pCompany.getTotalPages());
        mt.setTotal(pCompany.getTotalElements());

        rs.setMeta(mt);
        rs.setResult(pCompany.getContent());
        return rs;
    }

    @Override
    public Company handleUpdateCompany(Company c) {
        Optional<Company> companyOptional = this.companyRepository.findById(c.getId());
        if (companyOptional.isPresent()) {
            Company currentCompany = companyOptional.get();
            currentCompany.setLogo(c.getLogo());
            currentCompany.setName(c.getName());
            currentCompany.setDescription(c.getDescription());
            currentCompany.setAddress(c.getAddress());
            return this.companyRepository.save(currentCompany);
        }
        return null;
    }

    @Override
    public void handleDeleteCompany(long id) {
        Optional<Company> comOptional = this.companyRepository.findById(id);
        if (comOptional.isPresent()) {
            Company com = comOptional.get();
            // fetch all user belong to this company
            List<User> users = this.userRepository.findByCompany(com);
            this.userRepository.deleteAll(users);
        }

        this.companyRepository.deleteById(id);
    }

    @Override
    public Optional<Company> findById(long id) {
        return this.companyRepository.findById(id);
    }
}
