package ivanmerkush.service.impl;

import ivanmerkush.model.User;
import ivanmerkush.service.FileService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {
    private final File file;

    private FileServiceImpl() {
        this.file = new File("./src/main/resources/users.txt");
    }

    private static class FileServiceHolder {
        private static final FileServiceImpl instance = new FileServiceImpl();

    }

    public static FileServiceImpl getInstance() {
        return FileServiceImpl.FileServiceHolder.instance;
    }

    @Override
    public void write(List<User> users) {
        ObjectOutputStream oos;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(users);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> read() {
        ObjectInputStream ois;

        List<User> users = new ArrayList<>();
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            users = (List<User>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
}
