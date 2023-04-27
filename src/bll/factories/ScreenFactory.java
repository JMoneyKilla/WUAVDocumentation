package bll.factories;

import bll.DeviceFactory;
import be.devices.IDevice;
import be.devices.Screen;

public class ScreenFactory extends DeviceFactory {
    @Override
    public IDevice createDevice() {
        return new Screen();
    }
}
