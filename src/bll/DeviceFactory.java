package bll;

import be.devices.IDevice;

public abstract class DeviceFactory{

    public IDevice makeDevice(){
        IDevice device = createDevice();
        return device;
    }

    public abstract IDevice createDevice();
}


