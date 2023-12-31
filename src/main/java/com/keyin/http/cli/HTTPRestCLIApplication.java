package com.keyin.http.cli;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.domain.Passenger;
import com.keyin.http.client.*;

import java.util.List;
import java.util.Objects;

public class HTTPRestCLIApplication {

    private RESTClient restClient;

    private AircraftClient aircraftClient;
    private CityClient cityClient;
    private AirportClient airportClient;
    private PassengerClient passengerClient;


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

    public void setAirportClient(AirportClient airportClient){this.airportClient = airportClient;}

    public void setPassengerClient(PassengerClient passengerClient){this.passengerClient = passengerClient;}

    /*
    * Airport Methods
    * */
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

    public String generateAllAirportsPassengersHaveUsed(Long id) {
        List<Passenger> passengers = getAirportClient().getAllAirportsPassengersHaveUsed(id);

        StringBuffer report = new StringBuffer();

        for (Passenger passenger : passengers) {
            report.append(passenger.getFirstName());
            report.append("-");
            report.append(passenger.getLastName());


            if (passengers.indexOf(passenger) != (passengers.size() - 1)) {
                report.append(", ");
            }
        }



        return report.toString();
    }

    public List<String> getAirportActions() {
        AirportClient airportClient = new AirportClient();
        return airportClient.getAirportActions();
    }

    public void undoAirportAction() {
        AirportClient airportClient = new AirportClient();
        boolean success = airportClient.undoAirportAction();
        if (success) {
            System.out.println("Undo airport action successful.");
        } else {
            System.out.println("Undo airport action failed.");
        }
    }

    public void redoAirportAction() {
        AirportClient airportClient = new AirportClient();
        boolean success = airportClient.redoAirportAction();
        if (success) {
            System.out.println("Redo Airport action successful.");
        } else {
            System.out.println("Redo Airport action failed.");
        }
    }

    /*
    * Passenger Methods
    * */

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

        return report.toString();
    }
/**
 * ***************Aircraft Methods Start Here ******************
 *
 * **/
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


        return report.toString();
    }

    public List<String> getAircraftActions() {
        AircraftClient aircraftClient = new AircraftClient();
        return aircraftClient.getAircraftActions();
    }



    public void undoAircraftAction() {
        CityClient cityClient = new CityClient();
        boolean success = aircraftClient.undoAircraftAction();
        if (success) {
            System.out.println("Undo city action successful.");
        } else {
            System.out.println("Undo city action failed.");
        }
    }

    public void redoAircraftAction() {
        CityClient cityClient = new CityClient();
        boolean success = aircraftClient.redoCityAction();
        if (success) {
            System.out.println("Redo Aircraft action successful.");
        } else {
            System.out.println("Redo Aircraft action failed.");
        }
    }

    /**
     * ***************Aircraft Methods End Here ******************
     *
     * **/

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



        return report.toString();
    }
    public  List<String> getPassengerActions(){
        PassengerClient passengerClient = new PassengerClient();
        return passengerClient.getPassengerActions();
    }
    public void undoPassengerAction(){
        PassengerClient passengerClient = new PassengerClient();
        boolean success = passengerClient.undoPassengerAction();
        if(success){
            System.out.println("Undo Passenger Action Successful");
        }else{
            System.out.println("Undo Passenger Action Failed");
        }
    }
    public void redoPassengerAction(){
        PassengerClient passengerClient = new PassengerClient();
        boolean success = passengerClient.redoPassengerAction();
        if(success){
            System.out.println("Redo Passenger Action Successful");
        }else{
            System.out.println("Redo Passenger Action Failed");
        }
    }
    /** PASSENGER CLIENT FUNCTIONS END*/



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
            cliApp.getCityActions();
        } else if (args[0].equalsIgnoreCase("GenPassengersOnAircrafts")) {
            Long id = Long.parseLong(args[1]);
            cliApp.generateAllowedAircraftsBasedOnId(id);
        }  else if (args[0].equalsIgnoreCase("getPassengerActions")) {
            cliApp.getPassengerActions();

        }  else if (args[0].equalsIgnoreCase("undoPassengerActions")) {
            cliApp.undoPassengerAction();

        }  else if (args[0].equalsIgnoreCase("redoPassengerActions")) {
            cliApp.redoPassengerAction();

        } else if (args[0].equalsIgnoreCase("GetAircraftActions")) {
            cliApp.getAircraftActions();

        } else if (args[0].equalsIgnoreCase("UndoAircraftAction")) {
            cliApp.undoAircraftAction();

        } else if (args[0].equalsIgnoreCase("RedoAirCraftAction")) {
            cliApp.redoAircraftAction();

        } else if (args[0].equalsIgnoreCase("GenPassengersInAirport")) {
        Long id = Long.parseLong(args[1]);
        cliApp.generateAllAirportsPassengersHaveUsed(id);

        } else if (args[0].equalsIgnoreCase("GetAirportActions")){
            cliApp.getAirportActions();

        } else if (args[0].equalsIgnoreCase("UndoAirportAction")){
            cliApp.undoAirportAction();

        } else if (args[0].equalsIgnoreCase("RedoAirportAction")){
            cliApp.redoAirportAction();
            
        } else {
            System.out.println("Invalid action");

        }
    }
}

