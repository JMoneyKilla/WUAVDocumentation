package bll.validator;

public class DocumentValidator {

    public boolean isTextDocValid(String name, String description){
        if(name.isBlank() || name.isEmpty())
            return false;
        if(description.isBlank() || description.isEmpty())
            return false;
        else return true;
    }

    public boolean isDiagramOrPictureDocValid(String name, String description, String abseloutePath){
        if(name.isBlank() || name.isEmpty())
            return false;
        if(description.isBlank() || description.isEmpty())
            return false;
        if(abseloutePath.isBlank() || abseloutePath.isEmpty())
            return false;
        else return true;
    }
}
