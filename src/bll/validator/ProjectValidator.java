package bll.validator;

public class ProjectValidator {

    public boolean isProjectValid(String name, String customerName, String companyAddress){
        if(name.isBlank() || name.isEmpty())
            return false;
        if(customerName.isEmpty() || customerName.isBlank())
            return false;
        if(companyAddress.isBlank() || companyAddress.isEmpty())
            return false;
        else return true;
    }
}
