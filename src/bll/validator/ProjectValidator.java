package bll.validator;

public class ProjectValidator {

    /*
    Checks if all given information of project is valid
     */
    public boolean isProjectValid(String name, String customerName, String companyAddress, String zipcode, String phoneNumber, String email){
        if(name.isBlank() || name.isEmpty())
            return false;
        if(customerName.isEmpty() || customerName.isBlank())
            return false;
        if(companyAddress.isBlank() || companyAddress.isEmpty())
            return false;
        if(zipcode.isBlank() || zipcode.isEmpty())
            return false;
        if(phoneNumber.isBlank() ||phoneNumber.isEmpty())
            return false;

        if(email.isBlank() || email.isEmpty())
            return false;
        else return true;
    }

    public boolean isEmailValid(String email){
            String email_regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if(email!=null){
            if(email.matches(email_regex))
                return true;
            } return false;
    }

    public boolean isPhoneNumberValid(String number){
        if(number!=null){
        if(number.matches("[0-9]+") && (number.length() == 8 || number.length() == 10))
            return true;
        }
        return false;
    }

    public boolean isZipCodeValid(String number){
        if(number!=null){
            if(number.matches("[0-9]+"))
                return true;
        }
        return false;
    }
}
