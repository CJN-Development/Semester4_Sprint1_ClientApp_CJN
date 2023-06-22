package com.keyin;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.http.cli.HTTPRestCLIApplication;
import com.keyin.http.client.AircraftClient;
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

public class AircraftClientTest {
    @Mock
    private AircraftClient mockAircraftClient;


    @Test
    @DisplayName("Getting All Airports Test")
    public void testGenerateListOfAllAircraftReport(){
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Aircraft aircraft1 = new Aircraft();
        aircraft1.setId(1L);
        aircraft1.setModel("747");
        aircraft1.setTailNumber("XYZ-227");


        Aircraft aircraft2 = new Aircraft();
        aircraft2.setId(2L);
        aircraft2.setModel("002");
        aircraft2.setTailNumber("222-Num");

        List<Aircraft> aircraftList = new ArrayList<Aircraft>();

        aircraftList.add(aircraft1);
        aircraftList.add(aircraft2);

        httpRestCLIApplicationUnderTest.setAircraftClientClient(mockAircraftClient);

//        Mockito.when(mockAircraftClient.getAllAircraft()).thenReturn(aircraftList);



//        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAircraftReport().contains("XYZ-227"));



    }

    @Test
    @DisplayName("Getting Allowed Airports Based On ID")
    public void getAllowedAirportsForAircraftBasedOnId(){
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Airport airport1 = new Airport();
        airport1.setName("Pearson International");
        airport1.setCode("YYT");
        airport1.setId(1L);

        List<Airport> allowedAirport = new ArrayList<Airport>();

        allowedAirport.add(airport1);

//        httpRestCLIApplicationUnderTest.setAircraftClientClient(mockAircraftClient);
        Mockito.when(mockAircraftClient.getAllAllowedAirportsForAircraftBasedOnId(1L)).thenReturn(allowedAirport);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAllowedAirportsBasedOnId(1L).contains("Pearson"));


    }


}
