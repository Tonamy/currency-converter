package com.example.CurrencyConverter;

import com.example.CurrencyConverter.models.ExchangeRateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyConverterTest {
    private CurrencyConverter converter;
    private ExchangeRateResponse rates;

    // Este método se ejecuta antes de cada prueba para configurar el entorno de prueba.
    @BeforeEach
    public void setUp() {
        converter = new CurrencyConverter();  // Inicializa el objeto CurrencyConverter.
        rates = new ExchangeRateResponse();   // Inicializa el objeto ExchangeRateResponse.
        rates.conversion_rates = new HashMap<>();  // Crea un nuevo HashMap para las tasas de cambio.
        rates.conversion_rates.put("ARS", 99.0);  // Establece la tasa de cambio para ARS.
        rates.conversion_rates.put("BRL", 5.5);   // Establece la tasa de cambio para BRL.
        rates.conversion_rates.put("CLP", 750.0); // Establece la tasa de cambio para CLP.
        rates.conversion_rates.put("MXN", 20.0);  // Establece la tasa de cambio para MXN.
        rates.conversion_rates.put("USD", 1.0);   // Establece la tasa de cambio para USD.
    }

    // Prueba para convertir 10 USD a ARS.
    @Test
    public void testConvertUSDtoARS() {
        double result = converter.convertCurrency(10, rates.conversion_rates.get("ARS"));  // Realiza la conversión.
        assertEquals(990, result);  // Verifica que el resultado sea el esperado.
    }

    // Prueba para convertir 990 ARS a USD.
    @Test
    public void testConvertARStoUSD() {
        double result = converter.convertCurrencyToUSD(990, rates.conversion_rates.get("ARS"));  // Realiza la conversión.
        assertEquals(10, result);  // Verifica que el resultado sea el esperado.
    }

    // Agrega más pruebas según sea necesario.
}
