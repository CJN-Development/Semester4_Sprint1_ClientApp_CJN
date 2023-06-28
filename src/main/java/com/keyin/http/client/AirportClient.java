package com.keyin.http.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.Passenger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AirportClient {

    public List<Airport> getAllAirports() {
        List<Airport> airport = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/airport")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
                System.out.println("***** AIRPORT LIST ***** " + response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            airport = mapper.readValue(response.body(), new TypeReference<List<Airport>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return airport ;
    }

    public List<String> getAirportActions(){
        List<String> listOfActions = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/airport/getAirportActions";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                int actionNum = 1;
                String responseBody = response.body();
                listOfActions = Arrays.asList(responseBody.split(","));
                for (String actions : listOfActions){
                    System.out.println("Action" + " " + actionNum + ":" + actions);
                    actionNum++;
                }
            }else {
                System.out.println("Failed to get Airport actions. Error Status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        return listOfActions;
    }

    public boolean undoAirportAction(){
        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/airport/undo";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                System.out.println("Undo action successful");
                return true;
            } else {
                System.out.println("Undo action failed. Error status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean redoAirportAction(){
        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/airport/redo";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                System.out.println("Redo action successful");
                return true;
            } else {
                System.out.println("Redo action failed. Error status Code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Passenger> getAllAirportsPassengersHaveUsed(Long id){
        List<Passenger> passengers = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://localhost:8080/airport/getPassengers/%s",id);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200) {
                System.out.println("***** Passengers Used Airport With ID"+ id +"*****" + response.body());
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            passengers = mapper.readValue(response.body(), new TypeReference<List<Passenger>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return passengers;
    }


    public List<Airport> searchAirportBasedOnSearchTerm(String searchTerm) {
        List<Airport> airports = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://localhost:8080/airport/airportsearch?searchTerm=%s",searchTerm);
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
            airports = mapper.readValue(response.body(), new TypeReference<List<Airport>>(){});


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return airports ;
    }
}
