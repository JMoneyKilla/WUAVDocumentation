package bll.validator;

public class ProjectValidator {

    public boolean isProjectValid(String name, String customerName, String companyAddress, String zipcode){
        if(name.isBlank() || name.isEmpty())
            return false;
        if(customerName.isEmpty() || customerName.isBlank())
            return false;
        if(companyAddress.isBlank() || companyAddress.isEmpty())
            return false;
        if(zipcode.isBlank() || zipcode.isEmpty())
            return false;
        if(!zipcode.matches("[0-9]+"))
            return false;
        else return true;
    }
}
