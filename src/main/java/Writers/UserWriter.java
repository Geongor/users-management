package Writers;

import entity.User;
import entity.UserList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class UserWriter {

    private String filePath = "file.xml";
    private UserList users;
    private File file;
    private JAXBContext context;
    private Marshaller marshaller;

    public UserWriter(String filePath, UserList users){
        this.filePath = filePath;
        this.users = users;

        this.file = new File(filePath);

        try {
            this.context = JAXBContext.newInstance(UserList.class);
            this.marshaller = context.createMarshaller();
            this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e){
            System.out.println("Что-то не так с XML");
        }

    }

    public void saveUsers(){

        try {
            if (file.exists()) {
                marshaller.marshal(users, file);
            }
        } catch (JAXBException e){
            System.out.println("Что-то не так с XML");
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public UserList getUsers() {
        return users;
    }

    public void setUsers(UserList users) {
        this.users = users;
    }
}
