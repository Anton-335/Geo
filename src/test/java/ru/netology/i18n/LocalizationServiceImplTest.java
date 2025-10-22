package ru.netology.i18n;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.netology.entity.Country;

public class LocalizationServiceImplTest {

    private final LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    void shouldReturnRussianForRussia() {
        String message = localizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", message);
    }

    @Test
    void shouldReturnEnglishForUSA() {
        String message = localizationService.locale(Country.USA);
        assertEquals("Welcome", message);
    }

    @Test
    void shouldReturnEnglishForOtherCountries() {
        String message = localizationService.locale(Country.BRAZIL);
        assertEquals("Welcome", message);
    }
}