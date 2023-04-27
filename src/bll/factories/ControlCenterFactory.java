package bll.factories;

import bll.DeviceFactory;
import be.devices.ControlCenter;
import be.devices.IDevice;

public class ControlCenterFactory extends DeviceFactory {
    @Override
    public IDevice createDevice() {
        return new ControlCenter();
    }
}
