package com.keyin.http.cli;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;

import com.keyin.http.client.*;

import com.keyin.http.client.AircraftClient;
import com.keyin.http.client.AirportClient;
import com.keyin.http.client.CityClient;
import com.keyin.http.client.RESTClient;


import java.util.List;
import java.util.Objects;

public class HTTPRestCLIApplication {

    private RESTClient restClient;

    private AircraftClient aircraftClient;
    private CityClient cityClient;
    private AirportClient airportClient;

    private PassengerClient passengerClient;

    public String generateAirportReport() {
        List<Airport> airports = getAirportClient().getAllAirports();

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

    public String getAllPassengersWhoHaveUsedAirport(Long id) {
        List<Passenger> airportsPassengersHaveUsed = getAirportClient().getAllAirportsPassengersHaveUsed(1L);

        StringBuffer report = new StringBuffer();

        for (Passenger passenger : airportsPassengersHaveUsed) {
            report.append(passenger.getFirstName());
            report.append("-");
            report.append(passenger.getLastName());


            if (airportsPassengersHaveUsed.indexOf(passenger) != (airportsPassengersHaveUsed.size() - 1)) {
                report.append(", ");
            }
        }

//        System.out.println(report.toString());

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

    /**   City client functions/methods ~ Author: Devin Augot    */
    public String generateCityReport() {
        List<City> cities = getRestClient().getAllCities();

        StringBuffer report = new StringBuffer();

        for (City city : cities) {
            // not sure how this looks right now in terminal
            report.append(city.getName());
            report.append(",");
            report.append(city.getState());
            report.append(" "+"{Population : ");
            report.append(city.getPopulation()).append("}");



            if (cities.indexOf(city) != (cities.size() - 1)) {
                report.append(", ");
            }
        }
        System.out.println("****LIST OF ALL CITIES****");

        System.out.println(report);

        return report.toString();
    }


    public String getAllAirportsForCities(Long id) {
        List<Airport> airportsInCity = getCityClient().getAllAirportsForCitiesBasedOnId(1L);

        StringBuffer report = new StringBuffer();

        for (Airport airport : airportsInCity) {
            report.append(airport.getName());
            report.append("-");
            report.append(airport.getCode());


            if (airportsInCity.indexOf(airport) != (airportsInCity.size() - 1)) {
                report.append(", ");
            }
        }

//        System.out.println(report.toString());

        return report.toString();
    }

    public String generateListOfCitiesFromSearch(String searchInput) {
        List<City> cities = getCityClient().searchCitiesBasedOnSearchTerm(searchInput);

        StringBuffer report = new StringBuffer();

        for (City city : cities) {
            report.append(city.getName());
            report.append("-");
            report.append(city.getState());


            if (cities.indexOf(city) != (cities.size() - 1)) {
                report.append(", ");
            }
        }

//        System.out.println(report.toString());

        return report.toString();
    }
    public void undoCityAction() {
        CityClient cityClient = new CityClient();
        boolean success = cityClient.undoCityAction();
        if (success) {
            System.out.println("Undo city action successful.");
        } else {
            System.out.println("Undo city action failed.");
        }
    }

    public void redoCityAction() {
        CityClient cityClient = new CityClient();
        boolean success = cityClient.redoCityAction();
        if (success) {
            System.out.println("Redo city action successful.");
        } else {
            System.out.println("Redo city action failed.");
        }
    }

    public List<String> getCityActions() {
        CityClient cityClient = new CityClient();
        return cityClient.getCityActions();
    }

    /**City client functions/methods END HERE ~ Author: Devin Augot */

    /** PASSENGER CLIENT FUNCTIONS START*/
    public String generatePassengerReport() {
        List<Passenger> passengers = getPassengerClient().getAllPassenger();

        StringBuffer report = new StringBuffer();

        for (Passenger passenger : passengers) {
            report.append(passenger.getFirstName());
            report.append("-");
            report.append(passenger.getPhoNum());


            if (passengers.indexOf(passenger) != (passengers.size() - 1)) {
                report.append(", ");
            }
        }
        System.out.println("****LIST OF ALL PASSENGERS****");
        System.out.println(report.toString());

        return report.toString();
    }

public String generateAllowedAircraftsBasedOnId(Long id) {
    List<Aircraft> aircrafts = getPassengerClient().getPassengersOnAircraftBasedOnId(id);

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

    public String generateListOfPassengersFromSearch(String searchTerm) {
        List<Passenger> passengers = getPassengerClient().searchPassengerBasedOnSearchTerm(searchTerm);

        StringBuffer report = new StringBuffer();

        for (Passenger passenger : passengers) {
            report.append(passenger.getFirstName());
            report.append("-");
            report.append(passenger.getPhoNum());


            if (passengers.indexOf(passenger) != (passengers.size() - 1)) {
                report.append(", ");
            }
        }

//        System.out.println(report.toString());

        return report.toString();
    }
    /** PASSENGER CLIENT FUNCTIONS END*/

    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }

        return restClient;
    }
    public CityClient getCityClient(){
        if(cityClient == null){
            cityClient = new CityClient();
        }
        return cityClient;
    }

    public AirportClient getAirportClient(){
        if (airportClient == null){
            airportClient = new AirportClient();
        }
        return airportClient;
    }

    public AircraftClient getAircraftClient(){
        if(aircraftClient == null){
            aircraftClient = new AircraftClient();
        }
        return  aircraftClient;
    }
        public PassengerClient getPassengerClient(){
        if(passengerClient == null){
            passengerClient = new PassengerClient();
        }
        return  passengerClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    public void setAircraftClient(AircraftClient aircraftClient) {
        this.aircraftClient = aircraftClient;
    }

    public void setCityClient(CityClient cityClient){this.cityClient = cityClient;}


    public void setPassengerClient(PassengerClient passengerClient){this.passengerClient = passengerClient;}

    public void setAirportClient(AirportClient airportClient){this.airportClient = airportClient;}

    public void setPassengerClient(PassengerClient passengerClient){this.passengerClient = passengerClient;}


    public static void main(String[] args) {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();

        cliApp.setRestClient(new RESTClient());
        cliApp.setAircraftClient(new AircraftClient());
        cliApp.setCityClient(new CityClient());

        if (args[0].equalsIgnoreCase("Genall")) {
            // Generate reports for airports, aircraft, and cities
            cliApp.generateAirportReport();
            cliApp.generateAircraftReport();
            cliApp.generateCityReport();
        } else if (args[0].equalsIgnoreCase("Generate") && args[1].equalsIgnoreCase("Allowed")) {
            // Generate allowed airports for an aircraft based on ID
            Long id = Long.parseLong(args[2]);
            cliApp.generateAllowedAirportsBasedOnId(id);
        } else if (args[0].equalsIgnoreCase("searchAircraft")) {
            // Search for aircraft based on a search term
            String searchTerm = args[1];
            cliApp.generateListOfAirportsFromSearch(searchTerm);
        } else if (args[0].equalsIgnoreCase("searchCities")) {
            // Search for cities based on a search term
            String searchTerm = args[1];
            cliApp.generateListOfCitiesFromSearch(searchTerm);
        } else if (args[0].equalsIgnoreCase("GenCity")) {
            // Generate a report for all cities
            cliApp.generateCityReport();
        } else if (args[0].equalsIgnoreCase("GenAirportsInCity")) {
            // Get all airports for a city based on ID
            Long id = Long.parseLong(args[1]);
            cliApp.getAllAirportsForCities(id);

        } else if (args[0].equalsIgnoreCase("CityUndo")) {
            // Undo the last city action
            cliApp.undoCityAction();
        } else if (args[0].equalsIgnoreCase("CityRedo")) {
            // Redo the last city action
            cliApp.redoCityAction();
        } else if (args[0].equalsIgnoreCase("getCityActions")) {
            // Get the list of city actions
            List<String> cityActions = cliApp.getCityActions();
            System.out.println("City Actions:");
            for (String action : cityActions) {
                System.out.println(action);
            }
        } else {
            System.out.println("Invalid action");
        }
       } else if (args[0].equalsIgnoreCase("searchPassengers")) {
               String searchTerm = args[1];
               cliApp.generateListOfPassengersFromSearch(searchTerm);
       } else if (args[0].equalsIgnoreCase("GenPassenger")) {
               cliApp.generatePassengerReport();
       } else if (args[0].equalsIgnoreCase("GenPassengersOnAircrafts")) {
           Long id = Long.parseLong(args[1]);
           cliApp.generateAllowedAircraftsBasedOnId(id);


        } else if (args[0].equalsIgnoreCase("searchPassengers")) {
                String searchTerm = args[1];
                cliApp.generateListOfPassengersFromSearch(searchTerm);
        } else if (args[0].equalsIgnoreCase("GenPassenger")) {
                cliApp.generatePassengerReport();
        } else if (args[0].equalsIgnoreCase("GenPassengersOnAircrafts")) {
            Long id = Long.parseLong(args[1]);
            cliApp.generateAllowedAircraftsBasedOnId(id);



        cliApp.generateAirportReport();

        cliApp.generateAircraftReport();


    }
}}
