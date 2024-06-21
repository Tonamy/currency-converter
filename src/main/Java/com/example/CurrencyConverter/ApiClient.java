package com.example.CurrencyConverter;

import com.google.gson.Gson;
import com.example.CurrencyConverter.models.ExchangeRateResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApiClient {
    // URL de la API con la clave de API
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/006b792d0478d5dd9567b996/latest/USD";

    // Método para obtener las tasas de cambio desde la API
    public ExchangeRateResponse getExchangeRates() throws IOException {
        // Crear un cliente HTTP
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Crear una solicitud GET
        HttpGet request = new HttpGet(API_URL);

        // Ejecutar la solicitud
        HttpResponse response = httpClient.execute(request);

        // Verificar si la respuesta es exitosa (código 200)
        if (response.getStatusLine().getStatusCode() == 200) {
            // Obtener el cuerpo de la respuesta
            HttpEntity entity = response.getEntity();
            String responseBody = EntityUtils.toString(entity);

            // Parsear el cuerpo de la respuesta JSON a un objeto Java usando Gson
            Gson gson = new Gson();
            return gson.fromJson(responseBody, ExchangeRateResponse.class);
        } else {
            // Lanza una excepción si la solicitud falla
            throw new IOException("Failed to get data from API: " + response.getStatusLine().getStatusCode());
        }
    }
}
