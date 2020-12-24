import Readers.RolesReader;
import Readers.UserReader;
import Validators.MainValidator;
import Writers.UserWriter;
import entity.RoleList;
import entity.User;
import entity.UserList;

import java.util.Scanner;

public class UserManager {

    private UserList userList;
    private UserWriter writer;
    private UserReader reader;
    private String filePath;

    private final RoleList roles;


    public UserManager(UserList userList, String filePath) {
        this.userList = userList;
        this.filePath = filePath;

        RolesReader reader = new RolesReader();
        roles = reader.loadRoles();
    }

    public UserManager(String filePath) {
        this.filePath = filePath;

        RolesReader reader = new RolesReader();
        roles = reader.loadRoles();

    }

    public void loadUsers(){

        reader = new UserReader(filePath);
        userList = reader.loadUsers();
    }

    public void  saveUsers(){
        writer = new UserWriter("test.xml", userList);
        writer.saveUsers();
    }

    public void launchUserManager(){

        Scanner in = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.println("Выберите действие (ввести число - номер пункта):\n" +
                    "1) Посмотреть список существующих пользователей\n" +
                    "2) Добавить пользователя\n" +
                    "3) Поиск пользователя в списке\n" +
                    "4) Выход");

            String temp = in.nextLine();

            if (MainValidator.checkString(temp)) {
                loadUsers();
                switch (Integer.parseInt(temp)) {
                    case 1: {

                        userList.printUsersList();

                        break;
                    }
                    case 2: {

                        userList.addNewUser(roles.getRoles());
                        saveUsers();

                        break;
                    }
                    case 3: {

                        User user = null;
                        boolean check2 = true;
                        String email;
                        while (check2) {

                            System.out.println("Введите эл. адрес:");
                            email = in.nextLine();
                            if (MainValidator.checkEmail(email)) {
                                user = userList.findUserByEmail(email);
                                check2 = false;
                            } else {
                                System.out.println("Некорректный ввод! Попробуйте снова (эл. адрес должен быть формы ****@****.***)");
                            }
                            if (user != null) {

                                System.out.println(user.toString());
                                boolean check3 = true;
                                while (check3) {
                                    System.out.println("Выберите действие:\n" +
                                                        "1) Редактировать пользователя\n" +
                                                        "2) Удалить пользователя");
                                    String choose = in.nextLine();
                                    if (MainValidator.checkString(choose)){
                                        switch (Integer.parseInt(choose)){
                                            case 1:{

                                                user.inputUserWithConsole(roles.getRoles());

                                                check3 = false;
                                                break;
                                            }
                                            case 2:{

                                                userList.removeUser(user);
                                                check3=false;
                                                break;
                                            }
                                        }
                                    } else {
                                        System.out.println("Не число!");
                                    }

                                    saveUsers();
                                }
                            } else {
                                System.out.println("Пользователь с таким эл. адресом не найден");
                            }
                        }

                        break;
                    }
                    case 4: {

                        check = false;
                        break;
                    }
                    default: {
                        System.out.println("Введено неверное значение");
                        break;
                    }
                }
            } else {
                System.out.println("Введено не число");
            }
        }
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public UserWriter getWriter() {
        return writer;
    }

    public void setWriter(UserWriter writer) {
        this.writer = writer;
    }

    public UserReader getReader() {
        return reader;
    }

    public void setReader(UserReader reader) {
        this.reader = reader;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
