package ivanmerkush.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ivanmerkush.model.User;
import ivanmerkush.service.JsonService;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class JsonServiceImpl implements JsonService {
    private final Gson gson;
    private final File file;
    private final Type users;

    public JsonServiceImpl() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.file = new File("./src/main/resources/users.json");
        this.users = new TypeToken<List<User>>(){}.getType();
    }
    @Override
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
    @Override
    public List<User> read() {
        StringBuilder str = new StringBuilder();
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
