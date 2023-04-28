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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }
}
