package domain.services;

import domain.entities.User;
import domain.interfaces.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Async("sendEmailAsyncExecutor")
    @Override
    public void sendEmailAsync(User user) {
        try {
            log.info("Sending email to {} in thread {}", user.getEmail(), Thread.currentThread().getName());

            Thread.sleep(3000);

            log.info("Email sent to {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", user.getEmail(), e.getMessage());
        }
    }
}
