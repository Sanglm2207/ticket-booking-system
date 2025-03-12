package net.javaguides.jobhunter.service;

import net.javaguides.jobhunter.domain.Job;
import net.javaguides.jobhunter.domain.Subscriber;
import net.javaguides.jobhunter.domain.response.email.ResEmailJob;

public interface SubscriberService {
    boolean isExistsByEmail(String email);
    Subscriber create(Subscriber subs);
    Subscriber update(Subscriber subsDB, Subscriber subsRequest);
    Subscriber findById(long id);
    ResEmailJob convertJobToSendEmail(Job job);
    void sendSubscribersEmailJobs();
    Subscriber findByEmail(String email);
}
