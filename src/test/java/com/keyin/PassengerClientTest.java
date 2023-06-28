package com.keyin;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Passenger;
import com.keyin.http.cli.HTTPRestCLIApplication;
import com.keyin.http.client.PassengerClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PassengerClientTest {
    @Mock
    private PassengerClient mockPassengerClient;

    @Test
    @DisplayName("Getting All Passengers Test")
    public void testGenerateListOfAllPassengerReport(){
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Passenger passenger = new Passenger();
        passenger.setId(1L);
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setPhoNum(1231234);


//        Passenger passenger2 = new Passenger();
//        passenger2.setId(2L);
//        passenger2.setFirstName("Steph");
//        passenger2.setLastName("Short");
//        passenger2.setPhoNum(5675678);

        List<Passenger> passengerList = new ArrayList<Passenger>();



        passengerList.add(passenger);
//        passengerList.add(passenger2);
        httpRestCLIApplicationUnderTest.setPassengerClient(mockPassengerClient);
        Mockito.when(mockPassengerClient.getAllPassenger()).thenReturn(passengerList);
        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generatePassengerReport().contains("John"));

    }
    @Test
    @DisplayName("Getting Passenger List on Aircraft Based on ID")
    public void getPassengerOnAircraftBasedOnID(){
        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();

        Aircraft aircraft1 = new Aircraft();
        aircraft1.setId(1L);
        aircraft1.setModel("747");
        aircraft1.setTailNumber("XYZ-227");

        List<Aircraft> aircraftsList = new ArrayList<>();
        aircraftsList.add(aircraft1);
        httpRestCLIApplicationUnderTest.setPassengerClient(mockPassengerClient);
        Mockito.when(mockPassengerClient.getPassengersOnAircraftBasedOnId(1L)).thenReturn(aircraftsList);
        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAllowedAircraftsBasedOnId(1L).contains("XYZ-227"));



    }

    @Test
    @DisplayName("Get All City Action")
    public void testGetAllPassengerActions(){
        List<String> passengerActions = new ArrayList<>();
        passengerActions.add("Action 1");
        passengerActions.add("Action 2");
        passengerActions.add("Action 3");

        when(mockPassengerClient.getPassengerActions()).thenReturn(passengerActions);
        List<String> result = mockPassengerClient.getPassengerActions();
        Assertions.assertEquals(passengerActions,result,"Returned Passenger actions should match the expected list");

        System.out.println("Passenger Action");
        for(String action: result){
            System.out.println("- "+ action);
        }
        verify(mockPassengerClient,times(1)).getPassengerActions();
    }
}
