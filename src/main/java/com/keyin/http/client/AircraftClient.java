package com.keyin.http.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AircraftClient {

    public List<Aircraft> getAllAircraft() {
        List<Aircraft> aircraft = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/aircraft")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
//                System.out.println("***** AIRCRAFT LIST ***** " + response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            aircraft = mapper.readValue(response.body(), new TypeReference<List<Aircraft>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return aircraft ;
    }

    public List<Airport> getAllAllowedAirportsForAircraftBasedOnId(Long id) {
        List<Airport> airports = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://localhost:8080/aircraft/getAllowed/%s",id);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
                System.out.println("***** Allowed Airports For Plane With ID"+ id +"*****" + response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            airports = mapper.readValue(response.body(), new TypeReference<List<Airport>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return airports ;
    }
    public List<Aircraft> searchAircraftBasedOnSearchTerm(String searchTerm) {
        List<Aircraft> aircrafts = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://localhost:8080/aircraft/aircraftsearch?searchTerm=%s",searchTerm);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
                System.out.println("***** Airports Matching Search Term "+ searchTerm +"*****");
                System.out.println(response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            aircrafts = mapper.readValue(response.body(), new TypeReference<List<Aircraft>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return aircrafts ;
    }

/** Adding the ability to fetch the list of Aircraft Actions*/

    public List<String> getAircraftActions(){


        List<String> listOfActions = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/aircraft/getAircraftActions";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                int actionNum = 1;
                String responseBody = response.body();
                listOfActions = Arrays.asList(responseBody.split(","));
                for(String actions: listOfActions){
                    System.out.println("Action"+ " " + actionNum + ":"  + actions);
                    actionNum++;
                }


//                System.out.println(listOfActions);
            } else {
                System.out.println("Failed to get Aircraft actions. Error Status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return listOfActions;
    }

    public boolean undoAircraftAction() {
        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/aircraft/undo";
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
        String url = "http://localhost:8080/aircraft/redo";
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

