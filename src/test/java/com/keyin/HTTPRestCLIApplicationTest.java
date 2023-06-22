//package com.keyin;
//
//import com.keyin.domain.Airport;
//import com.keyin.domain.City;
//import com.keyin.http.cli.HTTPRestCLIApplication;
//import com.keyin.http.client.RESTClient;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@ExtendWith(MockitoExtension.class)
//public class HTTPRestCLIApplicationTest {
//    @Mock
//    private RESTClient mockRESTClient;
//
//    @Test
//    public void testGenerateAirportReport() {
//        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();
//
//        Airport stJohnsAirport = new Airport();
//        stJohnsAirport.setCode("YYT");
//        stJohnsAirport.setName("St. John's Airport");
//        stJohnsAirport.setId(1);
//
//        List<Airport> airportList = new ArrayList<Airport>();
//        airportList.add(stJohnsAirport);
//
////        Mockito.when(mockRESTClient.getAllAirports()).thenReturn(airportList);
//
//        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);
//
//        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAirportReport().contains("YYT"));
//    }
//
//    // works fine
//    @Test
//    public void testGenerateCityReport() {
//        HTTPRestCLIApplication httpRestCLIApplicationUnderTest = new HTTPRestCLIApplication();
//
//        City city1 = new City(1L,"NL",150_000,"St. John's");
//        City city2 = new City(2L,"ON",2_900_000,"Toronto");
//        City city3 = new City(2L,"BC",657_000,"Vancouver");
//
//        List<City> cityList = new ArrayList<City>();
//        cityList.add(city1);
//        cityList.add(city2);
//        cityList.add(city3);
//
//        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);
//
//        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateCityReport().contains("St. John's"));
//
//
//    }
//
//
//}
