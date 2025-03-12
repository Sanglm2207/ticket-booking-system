package net.javaguides.jobhunter.service;

import net.javaguides.jobhunter.domain.Resume;
import net.javaguides.jobhunter.domain.response.ResultPaginationDTO;
import net.javaguides.jobhunter.domain.response.resume.ResCreateResumeDTO;
import net.javaguides.jobhunter.domain.response.resume.ResFetchResumeDTO;
import net.javaguides.jobhunter.domain.response.resume.ResUpdateResumeDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface ResumeService {
    Optional<Resume> fetchById(long id);
    boolean checkResumeExistByUserAndJob(Resume resume);
    ResCreateResumeDTO create(Resume resume);
    ResUpdateResumeDTO update(Resume resume);
    void delete(long id);
    ResFetchResumeDTO getResume(Resume resume);
    ResultPaginationDTO fetchAllResume(Specification<Resume> spec, Pageable pageable);
    ResultPaginationDTO fetchResumeByUser(Pageable pageable);
}
