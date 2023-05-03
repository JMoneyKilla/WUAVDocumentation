package bll.validator;

import dal.UserDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    UserDAO userDAO = new UserDAO();

    public boolean isUserValid(String name){
        if(name.isEmpty() || name.isBlank())
            return false;
        else return true;
    }

    public boolean isEmailAvailable(String email) throws SQLException {
        List<String> emails = userDAO.getAllEmails();
        for(String s : emails){
            if(s.equalsIgnoreCase(email))
                return false;
        }
        return true;
    }

    public boolean isEmailValid(String email){
        //checks if String email has email format
        String email_regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(email.matches(email_regex))
                return true;
        else return false;
    }

    public boolean isPasswordValid(String password){
        //checks if password contains special letters and is more than 8 and less than 20 characters
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(password);
        if(password.length()<8 || password.length()>20)
            return false;
        if(hasSpecial.find())
            return false;
        else return true;
    }
}
