package be.devices;

public class Screen implements IDevice{
    private int projectId;
    private int userId;
    private int deviceId;
    private int deviceType;
    private String modelNumber;
    private String deviceName;
    private String screenSize;

    Screen(int projectId, int userId, int deviceId, int deviceType,
           String modelNumber, String deviceName, String screenSize) {
        this.projectId = projectId;
        this.userId = userId;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.modelNumber = modelNumber;
        this.deviceName = deviceName;
        this.screenSize = screenSize;
    }
}
