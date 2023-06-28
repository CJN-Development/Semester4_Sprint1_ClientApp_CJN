package com.keyin.http.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.Aircraft;
import com.keyin.domain.Passenger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PassengerClient {
    public List<Passenger> getAllPassenger() {
        List<Passenger> passenger = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/passenger")).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("***** PASSENGER LIST *****");
            } else {
                System.out.println("Error Status Code: " + response.statusCode());
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            passenger = mapper.readValue(response.body(), new TypeReference<List<Passenger>>() {
            });

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        return passenger;
    }
    public List<Aircraft> getPassengersOnAircraftBasedOnId(Long id){
        List<Aircraft> aircraft = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://localhost:8080/passenger/getAircrafts/%s",id);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        try{
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                System.out.println("***** Pasengers on Aircraft with ID "+ id +" *****"+ response.body());
            }else{
                System.out.println("Error Status Code: " + response.statusCode());
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            aircraft = mapper.readValue(response.body(), new TypeReference<List<Aircraft>>() {});

        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
        return aircraft;
    }
    public List<Passenger> searchPassengerBasedOnSearchTerm(String searchTerm){
        List<Passenger> passenger = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        String url = String.format("http://localhost:8080/passenger/searchPassenger?searchTerm=%s", searchTerm);
        HttpRequest request =  HttpRequest.newBuilder().uri(URI.create(url)).build();

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                System.out.println("***** Passengers Matching Search Term " + searchTerm + " *****");
                System.out.println(response.body());
            }else{
                System.out.println("Error Status Code: " + response.statusCode());
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            passenger = mapper.readValue(response.body(), new TypeReference<List<Passenger>>() {});

        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
        return passenger;
    }
    public List<String> getPassengerActions(){
        List<String> actionList = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/passenger/getPassengerActions";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                String responseBody = response.body();
                actionList = Arrays.asList(responseBody.split(","));
                System.out.println(actionList);
            }else{
                System.out.println("Failed to get passenger actions. Error Status Code: "+ response.statusCode());
            }
        }catch(IOException |InterruptedException e){
            e.printStackTrace();
        }
        if(actionList.isEmpty()){
            throw new IllegalStateException("Action List is empty");
        }
        return actionList;

    }
}
