// NOTE: Requires jlayer-1.0.1.jar from https://mvnrepository.com/artifact/javazoom/jlayer/1.0.1

import java.io.*;
import java.util.*;
import java.net.*;
import javazoom.jl.player.Player;

public class App {
    private static final String API_ENDPOINT = "https://api.elevenlabs.io/v1/text-to-speech/EXAVITQu4vr4xnSDxMaL/stream";
    private static final String API_KEY = "3619945f5d4974009414d8ad67601453";

    public static void main(String[] args) throws Exception {
        ArrayList<String> insultList = readFileIntoArrayList("insults.txt");
        System.out.println("Do you want to get insulted? We are not responsible for any emotional damage. (y/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if (answer.equals("y")) {
            while (true) {
                String insult = getRandomInsult(insultList);
                System.out.println(insult);
                talk(insult);
                System.out.println("\nDo you want to get insulted again? (y/n)");
                answer = scanner.nextLine();
                if (answer.equals("n")) {
                    break;
                }
            }
        }
        scanner.close();
    }

    public static String getRandomInsult(ArrayList<String> insultList) {
        Random random = new Random();
        int randomIndex = random.nextInt(insultList.size());
        String insult = insultList.get(randomIndex);
        return insult;
    }

    public static ArrayList<String> readFileIntoArrayList(String filename) throws Exception {
        Scanner scanner = new Scanner(new File(filename));
        ArrayList<String> arrayList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replaceAll("\"", "\\\"");
            arrayList.add(line);
        }

        scanner.close();
        return arrayList;
    }

    private static void talk(String text) throws Exception {
        URL url = new URL(API_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String payload = String.format("{\"text\": \"%s\", \"voice_settings\": {\"stability\": 0.8, \"similarity_boost\": 0.8}}", text); 
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("xi-api-key", API_KEY);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(payload);
        wr.flush();
        wr.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = connection.getInputStream();
            Player player = new Player(inputStream);
            player.play();
        } else {
            System.out.println("Failed to get the audio. Response code: " + responseCode);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String errorMessage = bufferedReader.readLine();
            System.out.println(errorMessage);
        }
    }
}