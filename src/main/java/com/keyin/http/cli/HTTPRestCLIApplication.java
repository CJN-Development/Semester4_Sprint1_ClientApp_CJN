package com.keyin.http.cli;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.http.client.AircraftClient;
import com.keyin.http.client.CityClient;
import com.keyin.http.client.RESTClient;

import java.util.List;
import java.util.Objects;

public class HTTPRestCLIApplication {

    private RESTClient restClient;

    private AircraftClient aircraftClient;
    private CityClient cityClient;

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

    /**   City client functions/methods ~ Author: Devin Augot    */
    public String generateCityReport() {
        List<City> cities = getCityClient().getAllCities();

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


    /**City client functions/methods END HERE ~ Author: Devin Augot */

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

    public AircraftClient getAircraftClient(){
        if(aircraftClient == null){
            aircraftClient = new AircraftClient();
        }
        return  aircraftClient;
    }

    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    public void setAircraftClient(AircraftClient aircraftClient) {
        this.aircraftClient = aircraftClient;
    }

    public void setCityClient(CityClient cityClient){this.cityClient = cityClient;}

    public static void main(String[] args) {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();

        cliApp.setRestClient(new RESTClient());
        cliApp.setAircraftClient(new AircraftClient());
        cliApp.setCityClient(new CityClient());

        if (args[0].equalsIgnoreCase("Genall")) {
            cliApp.generateAirportReport();
            cliApp.generateAircraftReport();
            cliApp.generateCityReport();

        } else if (args[0].equalsIgnoreCase("Generate") && args[1].equalsIgnoreCase("Allowed")) {
            Long id = Long.parseLong(args[2]);
            cliApp.generateAllowedAirportsBasedOnId(id);

        } else if (args[0].equalsIgnoreCase("searchAircraft")) {
            String searchTerm = args[1];
            cliApp.generateListOfAirportsFromSearch(searchTerm);

        } else if (args[0].equalsIgnoreCase("searchCities")) {
            String searchTerm = args[1];
            cliApp.generateListOfCitiesFromSearch(searchTerm);

        } else if (args[0].equalsIgnoreCase("GenCity")) {
            cliApp.generateCityReport();

        } else if (args[0].equalsIgnoreCase("GenAirportsInCity")) {
            Long id = Long.parseLong(args[1]);
            cliApp.getAllAirportsForCities(id);
        }



//        cliApp.generateAirportReport();

//        cliApp.generateAircraftReport();


    }
}
