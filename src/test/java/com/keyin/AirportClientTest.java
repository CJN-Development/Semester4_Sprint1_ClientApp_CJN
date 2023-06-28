package com.keyin;

import com.keyin.domain.Airport;
import com.keyin.domain.Passenger;
import com.keyin.http.cli.HTTPRestCLIApplication;
import com.keyin.http.client.AirportClient;
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
public class AirportClientTest {

    @Mock
    private AirportClient mockAirportClient;

    @Test
    @DisplayName("Getting all Airports")
    public void testGenerateListOfAllAirports(){
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Airport airport1 = new Airport();
        airport1.setId(1L);
        airport1.setName("Vancouver International");
        airport1.setCode("YVR");

        List<Airport> airportList = new ArrayList<Airport>();
        airportList.add(airport1);

        httpRestCLIApplicationUnderTest.setAirportClient(mockAirportClient);

        Mockito.when(mockAirportClient.getAllAirports()).thenReturn(airportList);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAirportReport().contains("Vancouver International"));
    }

    @Test
    @DisplayName("Getting Airports Passengers have used based on ID")
    public void getAllAirportsPassengersHaveUsedBasedOnId() {
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Passenger passenger1 = new Passenger();
        passenger1.setFirstName("Cameron");
        passenger1.setLastName("DAmico");
        passenger1.setPhoNum(7278389);
        passenger1.setId(1L);


        List<Passenger> AirportsPassengersUsed = new ArrayList<>();
        AirportsPassengersUsed.add(passenger1);

        httpRestCLIApplicationUnderTest.setAirportClient(mockAirportClient);
        Mockito.when(mockAirportClient.getAllAirportsPassengersHaveUsed(1L)).thenReturn(AirportsPassengersUsed);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.getAllPassengersWhoHaveUsedAirport(1L).contains("Cameron"));
    }
}
