package com.keyin;

import com.keyin.domain.City;
import com.keyin.domain.Airport;

import com.keyin.http.cli.HTTPRestCLIApplication;
import com.keyin.http.client.CityClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
public class CityClientTest {

    @Mock private CityClient mockCityClient;

    @Test
    @DisplayName("Getting all Cities")
    public void testGenerateListOfAllCities(){
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        City city1 = new City(1L,"NL",150_000,"St. John's");
        City city2 = new City(2L,"ON",2_900_000,"Toronto");
        City city3 = new City(2L,"BC",657_000,"Vancouver");

        List<City> cityList = new ArrayList<City>();
        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);

        httpRestCLIApplicationUnderTest.setCityClient(mockCityClient);

        Mockito.when(mockCityClient.getAllCities()).thenReturn(cityList);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateCityReport().contains("St. John's"));


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
}


