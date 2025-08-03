package org.asynchronousemailnotificationsystem.asynchronousemailnotificationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsynchronousEmailNotificationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsynchronousEmailNotificationSystemApplication.class, args);
    }

}
