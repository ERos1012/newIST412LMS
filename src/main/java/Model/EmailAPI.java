package Model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EmailAPI {
    private static final String ACCESS_KEY = "990b7690-931a-47e6-84c7-a9d0aa2bc990";

    public static void sendEmail(String name, String email, String message) {
        try {
            URL url = new URL("https://api.web3forms.com/submit");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            String jsonPayload = String.format("{\"access_key\": \"%s\", \"name\": \"%s\", \"email\": \"%s\", \"message\": \"%s\"}",
                                               ACCESS_KEY, name, email, message);

            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(jsonPayload.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            System.out.println("HTTP Response Code: " + responseCode);

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    responseCode == 200 ? conn.getInputStream() : conn.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            System.out.println("Response from server: " + response.toString());
            if (responseCode != 200) {
                throw new RuntimeException("Failed to send email, server responded with: " + response.toString());
            }
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
            e.printStackTrace();  // More detailed error reporting
        }
    }
}
