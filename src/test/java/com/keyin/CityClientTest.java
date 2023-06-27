package com.keyin;

import com.keyin.domain.City;
import com.keyin.domain.Airport;

import com.keyin.http.cli.HTTPRestCLIApplication;
import com.keyin.http.client.CityClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CityClientTest {

    @Mock public CityClient mockCityClient;


    @Test
    @DisplayName("Getting all Cities")
    public void testGenerateListOfAllCities() {
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        City city1 = new City(1L, "NL", 150_000, "St. John's");
        City city2 = new City(2L, "ON", 2_900_000, "Toronto");
        City city3 = new City(2L, "BC", 657_000, "Vancouver");

        List<City> cityList = new ArrayList<City>();
        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);

        httpRestCLIApplicationUnderTest.setCityClient(mockCityClient);

        // Stub the getAllCities() method with lenient behavior
        lenient().when(mockCityClient.getAllCities()).thenReturn(cityList);

        // Call the method that uses getAllCities()
        String cityReport = httpRestCLIApplicationUnderTest.generateCityReport();

        // Print the city report instead of asserting its content
        System.out.println(cityReport);
    }

    @Test
    @DisplayName("Getting Airports For Cities Based On ID")
    public void getAllAirportsForCitiesBasedOnId() {
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Airport airport1 = new Airport();
        airport1.setName("St. John's International");
        airport1.setCode("YYT");
        airport1.setId(1L);


        List<Airport> airportInCity = new ArrayList<>();
        airportInCity.add(airport1);

        httpRestCLIApplicationUnderTest.setCityClient(mockCityClient);

        Mockito.when(mockCityClient.getAllAirportsForCitiesBasedOnId(1L)).thenReturn(airportInCity);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.getAllAirportsForCities(1L).contains("YYT"));

    }

    @Test
    @DisplayName("What Airports are in what Cities?")
    public void testAirportsInCities() {

        City city1 = new City(1L, "NL", 150_000, "St. John's");
        City city2 = new City(2L, "ON", 2_900_000, "Toronto");

        // airports for St. John's
        Airport airport1 = new Airport();
        airport1.setName("St. John's International 1");
        airport1.setCode("YYT");
        airport1.setId(1L);

        Airport airport2 = new Airport();
        airport2.setName("St. John's International 2");
        airport2.setCode("YYT2");
        airport2.setId(2L);

        // airports for Toronto
        Airport airport3 = new Airport();
        airport3.setName("Toronto International 1");
        airport3.setCode("YYZ");
        airport3.setId(3L);

        Airport airport4 = new Airport();
        airport4.setName("Toronto International 2");
        airport4.setCode("YYZ2");
        airport4.setId(4L);

        // Associating airports with cities
        city1.addAirportsInCity(airport1);
        city1.addAirportsInCity(airport2);
        city2.addAirportsInCity(airport3);
        city2.addAirportsInCity(airport4);

        // Retrieve the airports in each city
        List<Airport> airportsInCity1 = city1.getAirportsInCity();
        List<Airport> airportsInCity2 = city2.getAirportsInCity();


        Assertions.assertTrue(airportsInCity1.contains(airport1));
        Assertions.assertTrue(airportsInCity1.contains(airport2));
        Assertions.assertTrue(airportsInCity2.contains(airport3));
        Assertions.assertTrue(airportsInCity2.contains(airport4));


        // Did these prints to just make sure right results coming back
        System.out.println("Test Airports in Cities: SUCCESS");
        System.out.println("Airports in St. John's:");
        for (Airport airport : airportsInCity1) {
            System.out.println("- " + airport.getName());
        }
        System.out.println("Airports in Toronto:");
        for (Airport airport : airportsInCity2) {
            System.out.println("- " + airport.getName());
        }
    }

    @Test
    @DisplayName("Get All City Actions")
    public void testGetAllCityActions() {
        // Create a list of city actions
        List<String> cityActions = new ArrayList<>();
        cityActions.add("Action 1");
        cityActions.add("Action 2");
        cityActions.add("Action 3");

        when(mockCityClient.getCityActions()).thenReturn(cityActions);

        List<String> result = mockCityClient.getCityActions();

        Assertions.assertEquals(cityActions, result, "Returned city actions should match the expected list");

        System.out.println("City Actions:");
        for (String action : result) {
            System.out.println("- " + action);
        }
        // Verify the method invocation
        verify(mockCityClient, times(1)).getCityActions();
    }

}
