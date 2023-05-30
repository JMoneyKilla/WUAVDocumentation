package bll.validator;

public class DocumentValidator {

    //Makes sure paramerters for given document are not blank or null

    public boolean isTextDocValid(String name, String description){
        if(name == null || name.isBlank() || name.isEmpty())
            return false;
        if(description == null || description.isBlank() || description.isEmpty())
            return false;
        else return true;
    }

    public boolean isDiagramOrPictureDocValid(String name, String description, String abseloutePath){
        if(name == null || name.isBlank() || name.isEmpty())
            return false;
        if(description == null || description.isBlank() || description.isEmpty())
            return false;
        if(abseloutePath == null || abseloutePath.isBlank() || abseloutePath.isEmpty())
            return false;
        else return true;
    }
}
