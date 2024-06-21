package com.example.CurrencyConverter;

import com.example.CurrencyConverter.models.ExchangeRateResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private ApiClient apiClient;
    private Map<String, String> currencyNames;

    // Constructor que inicializa el cliente de la API y el mapa de nombres de monedas
    public CurrencyConverter() {
        this.apiClient = new ApiClient();
        initializeCurrencyNames();
    }

    // Inicializa el mapa de nombres de monedas
    private void initializeCurrencyNames() {
        currencyNames = new HashMap<>();
        currencyNames.put("ARS", "Peso Argentino");
        currencyNames.put("BRL", "Real Brasileño");
        currencyNames.put("CLP", "Peso Chileno");
        currencyNames.put("MXN", "Peso Mexicano");
        currencyNames.put("USD", "Dólar Estadounidense");
    }

    // Método principal para iniciar el proceso de conversión
    public void start() {
        try {
            // Obtener las tasas de cambio desde la API
            ExchangeRateResponse rates = apiClient.getExchangeRates();

            // Crear un escáner para la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Mostrar menú de opciones al usuario
                displayMenu();
                int option = scanner.nextInt();

                if (option == 9) {
                    // Salir del programa si elige la opción 9
                    break;
                }

                System.out.print("Ingresa la cantidad: ");
                double amount = scanner.nextDouble();

                // Convertir según la opción seleccionada
                double convertedAmount = handleConversion(option, amount, rates);

                // Mostrar el resultado de la conversión
                if (convertedAmount != -1) {
                    displayResult(option, amount, convertedAmount);
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Muestra el menú de opciones al usuario
    private void displayMenu() {
        System.out.println("Conversor de Monedas:");
        System.out.println("1. Convertir USD a ARS (Peso Argentino)");
        System.out.println("2. Convertir USD a BRL (Real Brasileño)");
        System.out.println("3. Convertir USD a CLP (Peso Chileno)");
        System.out.println("4. Convertir USD a MXN (Peso Mexicano)");
        System.out.println("5. Convertir ARS (Peso Argentino) a USD");
        System.out.println("6. Convertir BRL (Real Brasileño) a USD");
        System.out.println("7. Convertir CLP (Peso Chileno) a USD");
        System.out.println("8. Convertir MXN (Peso Mexicano) a USD");
        System.out.println("9. Salir");
        System.out.print("Selecciona una opción: ");
    }

    // Maneja la conversión basada en la opción seleccionada por el usuario
    private double handleConversion(int option, double amount, ExchangeRateResponse rates) {
        String fromCurrency = "";
        String toCurrency = "USD"; // Por defecto se convierte a USD
        double convertedAmount = -1;

        switch (option) {
            case 1:
                fromCurrency = "USD";
                toCurrency = "ARS";
                convertedAmount = convertCurrency(amount, rates.conversion_rates.get(toCurrency));
                break;
            case 2:
                fromCurrency = "USD";
                toCurrency = "BRL";
                convertedAmount = convertCurrency(amount, rates.conversion_rates.get(toCurrency));
                break;
            case 3:
                fromCurrency = "USD";
                toCurrency = "CLP";
                convertedAmount = convertCurrency(amount, rates.conversion_rates.get(toCurrency));
                break;
            case 4:
                fromCurrency = "USD";
                toCurrency = "MXN";
                convertedAmount = convertCurrency(amount, rates.conversion_rates.get(toCurrency));
                break;
            case 5:
                fromCurrency = "ARS";
                convertedAmount = convertCurrencyToUSD(amount, rates.conversion_rates.get(fromCurrency));
                break;
            case 6:
                fromCurrency = "BRL";
                convertedAmount = convertCurrencyToUSD(amount, rates.conversion_rates.get(fromCurrency));
                break;
            case 7:
                fromCurrency = "CLP";
                convertedAmount = convertCurrencyToUSD(amount, rates.conversion_rates.get(fromCurrency));
                break;
            case 8:
                fromCurrency = "MXN";
                convertedAmount = convertCurrencyToUSD(amount, rates.conversion_rates.get(fromCurrency));
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

        return convertedAmount;
    }

    // Muestra el resultado de la conversión al usuario
    private void displayResult(int option, double amount, double convertedAmount) {
        String fromCurrency = "";
        String toCurrency = "";

        switch (option) {
            case 1:
                fromCurrency = "USD";
                toCurrency = "ARS";
                break;
            case 2:
                fromCurrency = "USD";
                toCurrency = "BRL";
                break;
            case 3:
                fromCurrency = "USD";
                toCurrency = "CLP";
                break;
            case 4:
                fromCurrency = "USD";
                toCurrency = "MXN";
                break;
            case 5:
                fromCurrency = "ARS";
                toCurrency = "USD";
                break;
            case 6:
                fromCurrency = "BRL";
                toCurrency = "USD";
                break;
            case 7:
                fromCurrency = "CLP";
                toCurrency = "USD";
                break;
            case 8:
                fromCurrency = "MXN";
                toCurrency = "USD";
                break;
        }

        System.out.println(amount + " " + currencyNames.get(fromCurrency) + " (" + fromCurrency + ") = "
                + convertedAmount + " " + currencyNames.get(toCurrency) + " (" + toCurrency + ")");
    }

    // Método para convertir una cantidad de una moneda a otra usando la tasa de cambio
    private double convertCurrency(double amount, double rate) {
        return amount * rate;
    }

    // Método para convertir una cantidad de una moneda a dólares (USD) usando la tasa de cambio
    private double convertCurrencyToUSD(double amount, double rate) {
        return amount / rate;
    }
}