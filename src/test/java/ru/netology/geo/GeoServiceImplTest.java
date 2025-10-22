package ru.netology.geo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.netology.entity.Country;
import ru.netology.entity.Location;


public class GeoServiceImplTest {

    private final GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    void shouldReturnRussiaForLocalIp() {
        Location location = geoService.byIp("172.0.0.1");
        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    void shouldReturnUSAForUsIp() {
        Location location = geoService.byIp("96.0.0.1");
        assertEquals(Country.USA, location.getCountry());
    }

    @Test
    void shouldThrowExceptionForUnknownIp() {
        assertThrows(IllegalArgumentException.class, () -> {
            geoService.byIp("1.2.3.4");
        });
    }
}