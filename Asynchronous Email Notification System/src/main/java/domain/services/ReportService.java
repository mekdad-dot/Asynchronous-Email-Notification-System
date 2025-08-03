package domain.services;

import domain.entities.User;
import domain.interfaces.IReportService;
import domain.services.helpers.PdfGeneratorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportService implements IReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    @Override
    public void generateReportInParallel(User user) {
        CompletableFuture.runAsync(() -> {
            try {
                log.info("Generating report for {} on {}", user.getFirstName() + " " + user.getLastName(), Thread.currentThread().getName());

                byte[] pdf = PdfGeneratorHelper.generateUserReport(user);

                log.info("Report for {} generated successfully ({} bytes)", user.getFirstName() + " " + user.getLastName(), pdf.length);

            } catch (Exception e) {
                log.error("Failed to generate report for {}: {}", user.getFirstName() + " " + user.getLastName(), e.getMessage());
            }
        });
    }

    public void generateAllReportsParallel(List<User> users) {
        users.parallelStream().forEach(this::generateReportInParallel);
    }
}
