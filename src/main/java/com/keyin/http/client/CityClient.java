package com.keyin.http.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.City;
import com.keyin.domain.Airport;
import com.keyin.domain.City;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
public class CityClient {

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/cities")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
//                System.out.println("***** CITY LIST ***** " + response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            cities = mapper.readValue(response.body(), new TypeReference<List<City>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return cities;
    }

    /** This should get all airports based on ID
     * not sure if this needs to use airportsInCity ArrayList or not, or will cities display along with correlating
     * airports */

    public List<Airport> getAllAirportsForCitiesBasedOnId(Long id) {
        List<Airport> airportInCity = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://localhost:8080/cities/getAirport/%s",id);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
                System.out.println("***** Airports In Cities with id : "+ id +"*****" + response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            airportInCity = mapper.readValue(response.body(), new TypeReference<List<Airport>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return airportInCity ;
    }
    public List<City> searchCitiesBasedOnSearchTerm(String searchInput) {
        List<City> cities = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://localhost:8080/cities/citysearch?searchInput=%s",searchInput);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
                System.out.println("***** Cities Matching Search Term "+ searchInput +"*****");
                System.out.println(response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            cities = mapper.readValue(response.body(), new TypeReference<List<City>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return cities ;
    }
}