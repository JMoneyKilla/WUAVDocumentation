package be.devices;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceBuilderTest {

    @Test
    void buildCabel() {
        DeviceBuilder deviceBuilder = new DeviceBuilder();
        deviceBuilder.projectId(1);
        deviceBuilder.userId(2);
        deviceBuilder.deviceId(3);
        deviceBuilder.deviceType(1);
        deviceBuilder.modelNumber("12mn4n39s");
        deviceBuilder.deviceName("Cabel");
        deviceBuilder.cabelLength("6 meters");
        Cabel cabel = (Cabel) deviceBuilder.buildCabel();
        assertEquals(1, cabel.getProjectId());
        assertEquals(2, cabel.getUserId());
        assertEquals(3, cabel.getDeviceId());
        assertEquals(1, cabel.getDeviceType());
        assertEquals("12mn4n39s", cabel.getModelNumber());
        assertEquals("Cabel", cabel.getDeviceName());
        assertEquals("6 meters", cabel.getCabelLength());
    }

    @Test
    void buildControlCenter() {
        DeviceBuilder deviceBuilder = new DeviceBuilder();
        deviceBuilder.projectId(1);
        deviceBuilder.userId(2);
        deviceBuilder.deviceId(3);
        deviceBuilder.deviceType(2);
        deviceBuilder.modelNumber("12mjkl395v");
        deviceBuilder.deviceName("Control Center");
        ControlCenter controlCenter = (ControlCenter) deviceBuilder.buildControlCenter();
        assertEquals(1, controlCenter.getProjectId());
        assertEquals(2, controlCenter.getUserId());
        assertEquals(3, controlCenter.getDeviceId());
        assertEquals(2, controlCenter.getDeviceType());
        assertEquals("12mjkl395v", controlCenter.getModelNumber());
        assertEquals("Control Center", controlCenter.getDeviceName());


    }

    @Test
    void buildProjector() {
        DeviceBuilder deviceBuilder = new DeviceBuilder();
        deviceBuilder.projectId(1);
        deviceBuilder.userId(2);
        deviceBuilder.deviceId(3);
        deviceBuilder.deviceType(3);
        deviceBuilder.modelNumber("47fjm294nf");
        deviceBuilder.deviceName("Projector");
        Projector projector = (Projector) deviceBuilder.buildProjector();
        assertEquals(1, projector.getProjectId());
        assertEquals(2, projector.getUserId());
        assertEquals(3, projector.getDeviceId());
        assertEquals(3, projector.getDeviceType());
        assertEquals("47fjm294nf", projector.getModelNumber());
        assertEquals("Projector", projector.getDeviceName());
    }

    @Test
    void buildScreen() {
        DeviceBuilder deviceBuilder = new DeviceBuilder();
        deviceBuilder.projectId(1);
        deviceBuilder.userId(2);
        deviceBuilder.deviceId(3);
        deviceBuilder.deviceType(4);
        deviceBuilder.modelNumber("192849nfnfnf");
        deviceBuilder.deviceName("Screen");
        deviceBuilder.screenSize("28 inches");
        Screen screen = (Screen) deviceBuilder.buildScreen();
        assertEquals(1, screen.getProjectId());
        assertEquals(2, screen.getUserId());
        assertEquals(3, screen.getDeviceId());
        assertEquals(4, screen.getDeviceType());
        assertEquals("192849nfnfnf", screen.getModelNumber());
        assertEquals("Screen", screen.getDeviceName());
        assertEquals("28 inches", screen.getScreenSize());
    }

    @Test
    void buildSpeaker() {
        DeviceBuilder deviceBuilder = new DeviceBuilder();
        deviceBuilder.projectId(1);
        deviceBuilder.userId(2);
        deviceBuilder.deviceId(3);
        deviceBuilder.deviceType(5);
        deviceBuilder.modelNumber("m09sn3nb");
        deviceBuilder.deviceName("Speaker");
        Speaker speaker = (Speaker) deviceBuilder.buildSpeaker();
        assertEquals(1, speaker.getProjectId());
        assertEquals(2, speaker.getUserId());
        assertEquals(3, speaker.getDeviceId());
        assertEquals(5, speaker.getDeviceType());
        assertEquals("m09sn3nb", speaker.getModelNumber());
        assertEquals("Speaker", speaker.getDeviceName());
    }
}