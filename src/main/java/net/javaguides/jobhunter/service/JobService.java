package net.javaguides.jobhunter.service;

import net.javaguides.jobhunter.domain.Job;
import net.javaguides.jobhunter.domain.response.ResultPaginationDTO;
import net.javaguides.jobhunter.domain.response.job.ResCreateJobDTO;
import net.javaguides.jobhunter.domain.response.job.ResUpdateJobDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface JobService {
    Optional<Job> fetchJobById(long id);
    ResCreateJobDTO create(Job j);
    ResUpdateJobDTO update(Job j, Job jobInDB);
    void delete(long id);
    ResultPaginationDTO fetchAll(Specification<Job> spec, Pageable pageable);
}
