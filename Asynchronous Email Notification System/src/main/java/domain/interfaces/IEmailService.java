package domain.interfaces;

import domain.entities.User;

import java.util.List;

public interface IEmailService {
    void sendEmailAsync(User user);
}
