package domain.services.helpers;

import com.opencsv.CSVReader;
import domain.entities.User;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    public static List<User> parseCSV(InputStream is) {
        List<User> users = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            String[] line;
            boolean isHeader = true;

            while ((line = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                if (line.length >= 2) {
                    User user = new User();
                    user.setFirstName(line[0].trim());
                    user.setLastName(line[1].trim());
                    user.setEmail(line[2].trim());
                    users.add(user);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
