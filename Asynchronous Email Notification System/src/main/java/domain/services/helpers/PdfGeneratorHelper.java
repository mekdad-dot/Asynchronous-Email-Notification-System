package domain.services.helpers;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.*;

import domain.entities.User;

import java.io.ByteArrayOutputStream;

public class PdfGeneratorHelper {
    public static byte[] generateUserReport(User user) throws Exception {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDFont font = PDType1Font.HELVETICA_BOLD;

            contentStream.beginText();
            contentStream.setFont(font, 18);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("User Report");
            contentStream.newLineAtOffset(0, -30);
            contentStream.setFont(PDType1Font.HELVETICA, 14);
            contentStream.showText("Name: " + user.getFirstName() + " " + user.getLastName());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Email: " + user.getEmail());
            contentStream.endText();

            contentStream.close();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            return out.toByteArray();
        }
    }
}
