package bll.factories;

import bll.DeviceFactory;
import be.devices.IDevice;
import be.devices.Speaker;

public class SpeakerFactory extends DeviceFactory {
    @Override
    public IDevice createDevice() {
        return new Speaker();
    }
}
