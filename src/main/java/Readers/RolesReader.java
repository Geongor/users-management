package Readers;

import entity.Role;
import entity.RoleList;
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
 * Реализует чтение возможных ролей из xml файла
 *
 * */

public class RolesReader {

    private final String filePath = "roles.xml";
    private File file;
    private JAXBContext context;
    private Unmarshaller unmarshaller;

    public RolesReader() {

        this.file = new File(filePath);


        try {
            this.context = JAXBContext.newInstance(RoleList.class);
            this.unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e){
            System.out.println("Что-то не так с XML");
        }
    }

    public RoleList loadRoles(){

        RoleList roleList = new RoleList();
        try {
            if (file.exists()) {
                roleList = (RoleList) unmarshaller.unmarshal(new InputStreamReader(
                        new FileInputStream(file), Charset.forName("UTF-8")));

            }
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        } catch (JAXBException e) {
            System.out.println("Что-то не так с XML");
        }

        return roleList;

    }
}
