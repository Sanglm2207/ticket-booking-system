package net.javaguides.jobhunter.controller;

import net.javaguides.jobhunter.service.EmailService;
import net.javaguides.jobhunter.service.SubscriberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.jobhunter.service.Impl.EmailServiceImpl;
import net.javaguides.jobhunter.service.Impl.SubscriberServiceImpl;
import net.javaguides.jobhunter.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

    private final EmailService emailService;
    private final SubscriberService subscriberService;

    public EmailController(EmailService emailService,
                           SubscriberService subscriberService) {
        this.emailService = emailService;
        this.subscriberService = subscriberService;
    }

    @GetMapping("/email")
    @ApiMessage("Send simple email")
    // @Scheduled(cron = "*/30 * * * * *")
    // @Transactional
    public String sendSimpleEmail() {
        // this.emailService.sendSimpleEmail();
        // this.emailService.sendEmailSync("kaidev99.pro@icloud.com", "test send email",
        // "<h1> <b> hello </b> </h1>", false,
        // true);
        // this.emailService.sendEmailFromTemplateSync("kaidev99.pro@icloud.com", "test
        // send email", "job");
        this.subscriberService.sendSubscribersEmailJobs();
        return "ok";
    }
}
