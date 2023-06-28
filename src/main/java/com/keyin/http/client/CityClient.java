package com.keyin.http.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.City;
import com.keyin.domain.Airport;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CityClient {

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/cities")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
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

    /**Implementing Action Stack list for City*/
    public List<String> getCityActions() {
        List<String> actionList = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/cities/getCityActions";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body();
                // Parse the response body to extract the list of actions
                actionList = Arrays.asList(responseBody.split(","));
                for (String action : actionList) {
                    System.out.println(action);
                }
            } else {
                System.out.println("Failed to get city actions. Error Status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if (actionList.isEmpty()) {
            throw new IllegalStateException("Action list is empty");
            // System.out.println("Action list is empty.");
        }

        return actionList;
    }

    /**Implementing Undo redo HTTP calls*/

    public boolean undoCityAction() {
        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/cities/undo";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Undo action successful");
                return true;
            } else {
                System.out.println("Undo action failed. Error Status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean redoCityAction() {
        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/cities/redo";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Redo action successful");
                return true;
            } else {
                System.out.println("Redo action failed. Error Status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

}
