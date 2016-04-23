package ru.tikhoa.pft.soap;


import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIP() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("188.227.78.59");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

    @Test
    public void testInvalidIP() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("188.227.78.xxx");
        assertEquals(geoIP.getReturnCodeDetails(), "Invalid IP address");
    }
}
