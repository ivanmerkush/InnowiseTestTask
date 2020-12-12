package ivanmerkush.services.impl;

import ivanmerkush.models.User;
import ivanmerkush.services.FileService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {
    private final File file;

    private FileServiceImpl() {
        this.file = new File(PATH_TO_FILE);
    }

    private static class FileServiceHolder {
        private static final FileServiceImpl instance = new FileServiceImpl();

    }

    public static FileServiceImpl getInstance() { return FileServiceHolder.instance; }

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
