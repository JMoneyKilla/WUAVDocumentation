package bll.factories;

import bll.DeviceFactory;
import be.devices.Cabel;
import be.devices.IDevice;

public class CabelFactory extends DeviceFactory {
    @Override
    public IDevice createDevice() {
        return new Cabel();
    }
}
