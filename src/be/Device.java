package be;

public class Device {
    private int deviceId;
    private int projectId;
    private int userId;
    private String deviceName;
    private String description;
    private String dateAdded;

    public Device(int deviceId, int projectId, int userId, String deviceName, String description, String dateAdded){
        this.deviceId = deviceId;
        this.projectId = projectId;
        this.userId = userId;
        this.deviceName = deviceName;
        this.description = description;
        this.dateAdded = dateAdded;
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

    public String getDeviceName() {
        return deviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAdded() {
        return dateAdded;
    }
}
