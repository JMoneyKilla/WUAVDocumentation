package be.devices;

public class Screen implements IDevice{
    private String modelNumber;
    private String deviceName;
    public void setData(String modelNumber, String deviceName){
        this.modelNumber = modelNumber;
        this.deviceName = deviceName;
    }
}
