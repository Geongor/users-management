package entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы со список пользователей
 * и необходимы для записи/чтения из XML файла
 *
 * */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserList {

    @XmlElement(name = "user")
    private List<User> users;

    public UserList(List<User> users) {
        this.users = users;
    }

    public UserList() {
        users = new ArrayList<User>();
    }

    public void printUsersList(){

        if (users.isEmpty()){
            System.out.println("Список пуст");
            return;
        }
        for (User user: users) {

            System.out.println(user.toString());
            System.out.println("\n-------------------\n");
        }
    }

    public User findUserByEmail(String email){

        for (User user: users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    public void addNewUser(List<Role> roles){

        User user = new User();
        List<Role> validRoles = user.checkRoles(roles);
        user.inputUserWithConsole(validRoles);
        users.add(user);
    }

    public boolean removeUser(User user){
        return users.remove(user);
    }

    public boolean isExists(){
        return !this.users.isEmpty();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
