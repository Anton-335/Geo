package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {

    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    @InjectMocks
    private MessageSenderImpl messageSender;

    @Test
    void shouldSendRussianMessageForRussianIp() {
        String ip = "172.1.2.3";
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", ip); // или "X-Real-IP" — зависит от реализации
        String result = messageSender.send(headers);
        assertEquals("Добро пожаловать", result);
    }

    @Test
    void shouldSendEnglishMessageForAmericanIp() {
        String ip = "96.44.183.149";
        Location location = new Location("New York", Country.USA, "10th Avenue", 32);
        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", ip);
        String result = messageSender.send(headers);
        assertEquals("Welcome", result);
    }

    @Test
    void shouldSendEnglishMessageForUnknownIp() {
        String ip = "8.8.8.8";
        Location location = new Location(null, Country.USA, null, 0);
        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", ip);
        String result = messageSender.send(headers);
        assertEquals("Welcome", result);
    }
}