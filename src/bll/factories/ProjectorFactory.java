package bll.factories;

import bll.DeviceFactory;
import be.devices.IDevice;
import be.devices.Projector;

public class ProjectorFactory extends DeviceFactory {
    @Override
    public IDevice createDevice() {
        return new Projector();
    }
}
