package entity;

import Validators.MainValidator;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "lastName", "email", "phoneNumbers", "roles"})
public class User {

    private String name;
    private String lastName;
    private String email;

    @XmlElementWrapper
    @XmlElement(name = "phoneNumber")
    private List<String> phoneNumbers;
    @XmlElementWrapper
    @XmlElement(name = "role")
    private List<Role> roles;

    public User(String name, String lastName, String email, List<String> phoneNumbers, List<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
        this.roles = roles;
    }

    public User() {
        roles = new ArrayList<Role>();
        phoneNumbers = new ArrayList<String>();
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public List<Role> checkRoles(List<Role> roles){

        List<Role> validRoles = new ArrayList<Role>();
        List<Integer> missingLevels = getMissingLevels();
        if (!missingLevels.contains(3)) return validRoles;
        for (Role role: roles) {

            if (missingLevels.contains(role.getAuthorityLevel())){
                validRoles.add(role);
            }
        }

        return validRoles;
    }

    private List<Integer> getMissingLevels(){

        List<Integer> missingLevels = new ArrayList<Integer>();
        missingLevels.add(1);
        missingLevels.add(2);
        missingLevels.add(3);

        for (Role srole: roles) {

            missingLevels.remove((Integer) srole.getAuthorityLevel());
        }

        return  missingLevels;
    }

    public User inputUserWithConsole(List<Role> roleList){

        setNameWithConsole();
        setLastNameWithConsole();
        setEmailWithConsole();
        setPhoneNumbersWithConsole();
        chooseRoles(roleList);

        return this;
    }

    public void chooseRoles(List<Role> roles){

        boolean check = true;
        while (check) {
            roles = checkRoles(roles);
            System.out.println("Выберите роль:");
            int i = 1;
            for (Role role : roles) {

                System.out.println("  " + i + ") " + role.toString() + "\n");
                i++;
            }
            Scanner in = new Scanner(System.in);
            String temp = in.nextLine();

            if (MainValidator.checkString(temp)) {

                if (roles.get(Integer.parseInt(temp)-1).getAuthorityLevel() == 3){
                    clearRoles();
                    check = false;
                }
                addRole(roles.get(Integer.parseInt(temp)-1));
            } else {
                System.out.println("Введено не число");
                continue;
            }

            System.out.println("Желаете выбрать еще роль? (Д/Н)");
            temp = in.nextLine().toUpperCase();
            if (temp.equals("Д")){
                continue;
            } else if (temp.equals("Н")){
                check = false;
            } else {
                System.out.println("Некорректный ввод");
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String setNameWithConsole(){

        Scanner in = new Scanner(System.in);
        boolean check = true;
        String temp = "";
        while (check) {

            System.out.println("Введите имя:");
            temp = in.nextLine();
            if (MainValidator.checkName(temp)) {
                setName(temp);
                check = false;
            } else {
                System.out.println("Некорректный ввод! Попробуйте снова (имя начинается с большой буквы и не содержит посторонние символы)");
            }
        }
        return  this.name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String setLastNameWithConsole(){

        Scanner in = new Scanner(System.in);
        boolean check = true;
        String temp = "";
        while (check) {

            System.out.println("Введите Фамилию:");
            temp = in.nextLine();
            if (MainValidator.checkName(temp)) {
                setLastName(temp);
                check = false;
            } else {
                System.out.println("Некорректный ввод! Попробуйте снова (фамилия начинается с большой буквы и не содержит посторонние символы)");
            }
        }
        return  this.name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String setEmailWithConsole(){

        Scanner in = new Scanner(System.in);
        boolean check = true;
        String temp = "";
        while (check) {

            System.out.println("Введите эл. адрес:");
            temp = in.nextLine();
            if (MainValidator.checkEmail(temp)) {
                setEmail(temp);
                check = false;
            } else {
                System.out.println("Некорректный ввод! Попробуйте снова (эл. адрес должен быть формы ****@****.***)");
            }
        }
        return  this.name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> setPhoneNumbersWithConsole(){
        List<String> numbers = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        String temp;
        int count = 1;
        boolean check = true;
        while (check) {
            System.out.println("Введите количество номеров:");
            temp = in.nextLine();
            if (MainValidator.checkString(temp)) {
                count = Integer.parseInt(temp);
                if (!(count < 1 || count > 3)){
                    check = false;
                } else {
                    System.out.println("Количесвто номер минимум 1 и максимум 3");
                }
            } else {
                System.out.println("Введено не число");
            }
        }

        for (int i = 0; i<count; i++){
            check = true;
            while (check) {

                System.out.println("Введите номер телефона:");
                temp = in.nextLine();
                if (MainValidator.checkPhoneNumber(temp)) {
                    numbers.add(temp);
                    check = false;
                } else {
                    System.out.println("Некорректный ввод! Попробуйте снова (номер должен быть вида 375*******)");
                }
            }
        }
        setPhoneNumbers(numbers);

        return numbers;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void clearRoles(){
        this.roles.removeAll(roles);
    }

    @Override
    public String toString(){

        String numbers = "";
        for (String number: phoneNumbers) {

            numbers += "\n   >" + number;
        }

        String sroles = "";
        for (Role role: roles) {
            sroles += "\n   >" + role.toString();
        }

        return ("Name: " + this.name + "\n" + "Last name: " + this.lastName +
                "\n" + "Email: " + this.email + "\n" + "Phone numbers: " + numbers +
                "\n" + "Roles: " + sroles);
    }
}
