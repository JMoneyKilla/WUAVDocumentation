package be.handlers;

public interface AlertBoxStrategy {

    //Strategy interface used for handling exceptions

    void showGenericAlert(Exception e);
    void showCustomAlert(String content);


}
