package com.robertosanchosolis.nights.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.robertosanchosolis.nights.models.requestModels.BookingRequest;

public class BookingRequestUnitTest {

    @Test
    void getTotalProfit(){
        //given
        BookingRequest bookingRequest = BookingRequest.builder()
            .request_id("request_id")
            .check_in(LocalDate.now())
            .nights(1)
            .selling_rate(1)
            .margin(1)
            .build();

        //when
        Float totalProfit = bookingRequest.getTotalProfit();

        //then
        assertEquals(0.01f, totalProfit);
        

    }

    @Test
    void getProfitPerNight(){
        
        //given
        BookingRequest bookingRequest = BookingRequest.builder()
            .request_id("request_id")
            .check_in(LocalDate.now())
            .nights(1)
            .selling_rate(1)
            .margin(1)
            .build();

        //when
        Float profitPerNight = bookingRequest.getProfitPerNight();

        //then
        assertEquals(0.01f, profitPerNight);

    }
    
}
