package com.keyin.http.cli;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.http.client.AircraftClient;
import com.keyin.http.client.RESTClient;

import java.util.List;
import java.util.Objects;

public class HTTPRestCLIApplication {

    private RESTClient restClient;

    private AircraftClient aircraftClient;

    public String generateAirportReport() {
        List<Airport> airports = getRestClient().getAllAirports();

        StringBuffer report = new StringBuffer();

        for (Airport airport : airports) {
            report.append(airport.getName());
            report.append("-");
            report.append(airport.getCode());

            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append(", ");
            }
        }
        System.out.println("****LIST OF ALL AIRPORTS****");

        System.out.println(report.toString());

        return report.toString();
    }

    public String generateAircraftReport() {
        List<Aircraft> aircrafts = getAircraftClient().getAllAircraft();

        StringBuffer report = new StringBuffer();

        for (Aircraft aircraft : aircrafts) {
            report.append(aircraft.getModel());
            report.append("-");
            report.append(aircraft.getTailNumber());


            if (aircrafts.indexOf(aircraft) != (aircrafts.size() - 1)) {
                report.append(", ");
            }
        }
        System.out.println("****LIST OF ALL AIRCRAFT****");
        System.out.println(report.toString());

        return report.toString();
    }

    public String generateAllowedAirportsBasedOnId(Long id) {
        List<Airport> airports = getAircraftClient().getAllAllowedAirportsForAircraftBasedOnId(id);

        StringBuffer report = new StringBuffer();

        for (Airport airport : airports) {
            report.append(airport.getName());
            report.append("-");
            report.append(airport.getCode());


            if (airports.indexOf(airport) != (airports.size() - 1)) {
                report.append(", ");
            }
        }

//        System.out.println(report.toString());

        return report.toString();
    }

    public String generateListOfAirportsFromSearch(String searchTerm) {
        List<Aircraft> aircrafts = getAircraftClient().searchAircraftBasedOnSearchTerm(searchTerm);

        StringBuffer report = new StringBuffer();

        for (Aircraft aircraft : aircrafts) {
            report.append(aircraft.getModel());
            report.append("-");
            report.append(aircraft.getTailNumber());


            if (aircrafts.indexOf(aircraft) != (aircrafts.size() - 1)) {
                report.append(", ");
            }
        }

//        System.out.println(report.toString());

        return report.toString();
    }

    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }

        return restClient;
    }

    public AircraftClient getAircraftClient(){
        if(aircraftClient == null){
            aircraftClient = new AircraftClient();
        }
        return  aircraftClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    public void setAircraftClientClient(AircraftClient aircraftClient) {
        this.aircraftClient = aircraftClient;
    }

    public static void main(String[] args) {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();

        cliApp.setRestClient(new RESTClient());
        cliApp.setAircraftClientClient(new AircraftClient());

        if((args[0].equalsIgnoreCase("Genall"))){
            cliApp.generateAirportReport();

            cliApp.generateAircraftReport();

        } else if (args[0].equalsIgnoreCase("Generate") && args[1].equalsIgnoreCase("Allowed")){
            Long id = Long.parseLong(args[2]);
            cliApp.generateAllowedAirportsBasedOnId(id);

        } else if (args[0].equalsIgnoreCase("searchAircraft")) {
            String searchTerm =args[1];
            cliApp.generateListOfAirportsFromSearch(searchTerm);

        }


//        cliApp.generateAirportReport();

//        cliApp.generateAircraftReport();


    }
}
