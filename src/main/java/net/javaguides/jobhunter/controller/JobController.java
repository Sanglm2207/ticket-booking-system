package net.javaguides.jobhunter.controller;

import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import net.javaguides.jobhunter.domain.Job;
import net.javaguides.jobhunter.domain.response.ResultPaginationDTO;
import net.javaguides.jobhunter.domain.response.job.ResCreateJobDTO;
import net.javaguides.jobhunter.domain.response.job.ResUpdateJobDTO;
import net.javaguides.jobhunter.service.Impl.JobServiceImpl;
import net.javaguides.jobhunter.util.annotation.ApiMessage;
import net.javaguides.jobhunter.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class JobController {

    private final JobServiceImpl jobServiceImpl;

    public JobController(JobServiceImpl jobServiceImpl) {
        this.jobServiceImpl = jobServiceImpl;
    }

    @PostMapping("/jobs")
    @ApiMessage("Create a job")
    public ResponseEntity<ResCreateJobDTO> create(@Valid @RequestBody Job job) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.jobServiceImpl.create(job));
    }

    @PutMapping("/jobs")
    @ApiMessage("Update a job")
    public ResponseEntity<ResUpdateJobDTO> update(@Valid @RequestBody Job job) throws IdInvalidException {
        Optional<Job> currentJob = this.jobServiceImpl.fetchJobById(job.getId());
        if (!currentJob.isPresent()) {
            throw new IdInvalidException("Job not found");
        }

        return ResponseEntity.ok()
                .body(this.jobServiceImpl.update(job, currentJob.get()));
    }

    @DeleteMapping("/jobs/{id}")
    @ApiMessage("Delete a job by id")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) throws IdInvalidException {
        Optional<Job> currentJob = this.jobServiceImpl.fetchJobById(id);
        if (!currentJob.isPresent()) {
            throw new IdInvalidException("Job not found");
        }
        this.jobServiceImpl.delete(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/jobs/{id}")
    @ApiMessage("Get a job by id")
    public ResponseEntity<Job> getJob(@PathVariable("id") long id) throws IdInvalidException {
        Optional<Job> currentJob = this.jobServiceImpl.fetchJobById(id);
        if (!currentJob.isPresent()) {
            throw new IdInvalidException("Job not found");
        }

        return ResponseEntity.ok().body(currentJob.get());
    }

    @GetMapping("/jobs")
    @ApiMessage("Get job with pagination")
    public ResponseEntity<ResultPaginationDTO> getAllJob(
            @Filter Specification<Job> spec,
            Pageable pageable) {

        return ResponseEntity.ok().body(this.jobServiceImpl.fetchAll(spec, pageable));
    }
}
