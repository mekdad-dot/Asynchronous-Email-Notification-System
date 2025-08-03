package controllers;

import domain.entities.User;
import domain.interfaces.IEmailService;
import domain.interfaces.IReportService;
import domain.services.EmailService;
import domain.services.helpers.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/upload")

public class UploadController {

    private final IEmailService emailService;
    private final IReportService reportService;

    public UploadController(IEmailService emailService, IReportService reportService) {
        this.emailService = emailService;
        this.reportService = reportService;
    }

    @PostMapping("/file")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".csv")) {
            return ResponseEntity.badRequest().body("Invalid file");
        }

        try {
            List<User> users = CSVHelper.parseCSV(file.getInputStream());

            for (User user : users) {
                emailService.sendEmailAsync(user);
                reportService.generateReportInParallel(user);
            }

            return ResponseEntity.ok("File processed. Async tasks started.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to process file: " + e.getMessage());
        }
    }
}
