package Readers;

import entity.UserList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Реализует чтение списка пользователей из xml файла
 *
 * */

public class UserReader {

    private String filePath = "file.xml";
    private File file;
    private JAXBContext context;
    private Unmarshaller unmarshaller;

    public UserReader(String filePath){
        this.filePath = filePath;

        this.file = new File(filePath);

        try {
            this.context = JAXBContext.newInstance(UserList.class);
            this.unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e){
            System.out.println("Что-то не так с XML");
        }

    }

    public UserList loadUsers(){

        UserList userList = new UserList();
        try {
            if (file.exists()) {
                userList = (UserList) unmarshaller.unmarshal(new InputStreamReader(
                        new FileInputStream(file), Charset.forName("UTF-8")));

            }
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        } catch (JAXBException e) {
            System.out.println("Что-то не так с XML");
        }

        return userList;

    }
}
