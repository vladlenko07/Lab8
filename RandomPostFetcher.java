package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import org.json.JSONObject;

public class RandomPostFetcher {

    public static void main(String[] args) {
        try {
            // Генеруємо випадкове ID від 1 до 100
            Random rand = new Random();
            int id = rand.nextInt(100) + 1;

            // Формуємо URL
            String apiUrl = "https://jsonplaceholder.typicode.com/posts/" + id;

            // Відправляємо GET-запит
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Зчитуємо відповідь
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Парсимо JSON-об'єкт
            JSONObject json = new JSONObject(response.toString());

            // Виводимо ID, title і body
            System.out.println("ID: " + json.getInt("id"));
            System.out.println("Title: " + json.getString("title"));
            System.out.println("Body: " + json.getString("body"));

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
