package net.javaguides.jobhunter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.javaguides.jobhunter.domain.Subscriber;
import net.javaguides.jobhunter.service.Impl.SubscriberServiceImpl;
import net.javaguides.jobhunter.util.SecurityUtil;
import net.javaguides.jobhunter.util.annotation.ApiMessage;
import net.javaguides.jobhunter.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class SubscriberController {
    private final SubscriberServiceImpl subscriberServiceImpl;

    public SubscriberController(SubscriberServiceImpl subscriberServiceImpl) {
        this.subscriberServiceImpl = subscriberServiceImpl;
    }

    @PostMapping("/subscribers")
    @ApiMessage("Create a subscriber")
    public ResponseEntity<Subscriber> create(@Valid @RequestBody Subscriber sub) throws IdInvalidException {
        // check email
        boolean isExist = this.subscriberServiceImpl.isExistsByEmail(sub.getEmail());
        if (isExist == true) {
            throw new IdInvalidException("Email " + sub.getEmail() + " đã tồn tại");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.subscriberServiceImpl.create(sub));
    }

    @PutMapping("/subscribers")
    @ApiMessage("Update a subscriber")
    public ResponseEntity<Subscriber> update(@RequestBody Subscriber subsRequest) throws IdInvalidException {
        // check id
        Subscriber subsDB = this.subscriberServiceImpl.findById(subsRequest.getId());
        if (subsDB == null) {
            throw new IdInvalidException("Id " + subsRequest.getId() + " không tồn tại");
        }
        return ResponseEntity.ok().body(this.subscriberServiceImpl.update(subsDB, subsRequest));
    }

    @PostMapping("/subscribers/skills")
    @ApiMessage("Get subscriber's skill")
    public ResponseEntity<Subscriber> getSubscribersSkill() throws IdInvalidException {
        String email = SecurityUtil.getCurrentUserLogin().isPresent() == true
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";

        return ResponseEntity.ok().body(this.subscriberServiceImpl.findByEmail(email));
    }
}
