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

    }

    @Test
    void buildProjector() {
    }

    @Test
    void buildScreen() {
    }

    @Test
    void buildSpeaker() {
    }
}