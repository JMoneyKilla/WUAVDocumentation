package be.handlers;

public interface AlertBoxStrategy {
    void showGenericAlert(Exception e);
    void showCustomAlert(String content);


}
