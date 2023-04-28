package be.devices;

public class DeviceBuilder {
    private int projectId;
    private int userId;
    private int deviceId;
    private int deviceType;
    private String modelNumber;
    private String deviceName;
    private String screenSize;
    private String cabelLength;

    public DeviceBuilder projectId(int projectId){
        this.projectId = projectId;
        return this;
    }
    public DeviceBuilder userId(int userId){
        this.userId = userId;
        return this;
    }
    public DeviceBuilder deviceType(int deviceType){
        this.deviceType = deviceType;
        return this;
    }
    public DeviceBuilder deviceId(int deviceId){
        this.deviceId = deviceId;
        return this;
    }
    public DeviceBuilder modelNumber(String modelNumber){
        this.modelNumber = modelNumber;
        return this;
    }
    public DeviceBuilder deviceName(String deviceName){
        this.deviceName = deviceName;
        return this;
    }
    public DeviceBuilder screenSize(String screenSize){
        this.screenSize = screenSize;
        return this;
    }
    public DeviceBuilder cabelLength(String cabelLength){
        this.cabelLength = cabelLength;
        return this;
    }

    public IDevice buildCabel(){
        return new Cabel(projectId, userId, deviceId, deviceType, modelNumber, deviceName, cabelLength);
    }
    public IDevice buildControlCenter(){
        return new ControlCenter(projectId, userId, deviceId, deviceType, modelNumber, deviceName);
    }
    public IDevice buildProjector(){
        return new Projector(projectId, userId, deviceId, deviceType, modelNumber, deviceName);
    }
    public IDevice buildScreen(){
        return new Screen(projectId, userId, deviceId, deviceType, modelNumber, deviceName, screenSize);
    }
    public IDevice buildSpeaker(){
        return new Speaker(projectId, userId, deviceId, deviceType, modelNumber, deviceName);
    }
}
