package domain.interfaces;

import domain.entities.User;

public interface IReportService {
    public void generateReportInParallel(User user);
}
