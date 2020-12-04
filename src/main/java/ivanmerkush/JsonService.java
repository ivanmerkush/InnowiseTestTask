package ivanmerkush;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class JsonService {
    private Gson gson;
    private File file;
    private Type users;

    public JsonService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.file = new File("./src/main/resources/users.json");
        this.users = new TypeToken<List<User>>(){}.getType();
    }
    public void write(List<User> users) {
        String jsonUsers = gson.toJson(users);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonUsers);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> read() {
        StringBuilder str = new StringBuilder("");
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            while (scanner.hasNextLine()) {
                str.append(scanner.nextLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return gson.fromJson(str.toString(), users);
    }
}
