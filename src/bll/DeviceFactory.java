package bll;

import be.devices.IDevice;

public abstract class DeviceFactory{

    public IDevice makeDevice(){
        return createDevice();
    }

    public abstract IDevice createDevice();
}


