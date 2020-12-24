package Validators;

public class MainValidator {

    public static boolean checkPhoneNumber(String phoneNumber){

        return phoneNumber.matches("(\\+*)(375)\\d{9}");
    }

    public static boolean checkEmail(String email){

        return email.matches("^([A-z0-9_-]+\\.)*[A-z0-9_-]+@[A-z0-9_-]+(\\.[a-z]{2,6})+$");
    }

    public static boolean checkName(String name){

        return name.matches("^[A-ZА-Я][a-zа-я]+$");
    }

    public static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
