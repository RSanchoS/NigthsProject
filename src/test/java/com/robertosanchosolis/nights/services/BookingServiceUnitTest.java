package com.robertosanchosolis.nights.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.robertosanchosolis.nights.models.Stats;
import com.robertosanchosolis.nights.models.requestModels.BookingRequest;
import com.robertosanchosolis.nights.models.responseModels.MaximizeResponse;
import com.robertosanchosolis.nights.models.responseModels.StatsResponse;
import com.robertosanchosolis.nights.services.impl.BookingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookingServiceUnitTest {

    @InjectMocks
    BookingServiceImpl bookingService;


    @Test
    void getBookingsStats() throws Exception{
        //given
        BookingRequest bookingRequestA = BookingRequest.builder()
            .request_id("bookata_XY123")
            .check_in(LocalDate.parse("2021-01-01"))
            .nights(5)
            .selling_rate(200)
            .margin(20)
            .build();

        BookingRequest bookingRequestB = BookingRequest.builder()
            .request_id("kayete_PP234")
            .check_in(LocalDate.parse("2021-01-04"))
            .nights(4)
            .selling_rate(156)
            .margin(22)
            .build();

        List<BookingRequest> bookingRequests = List.of(bookingRequestA, bookingRequestB);
        
        //when

        StatsResponse statsResponse = bookingService.getBookingsStats(bookingRequests);

        //then
        assertEquals(Float.valueOf(8.29f), statsResponse.getAvg_night());
        assertEquals(Float.valueOf(8.0f), statsResponse.getMin_night());
        assertEquals(Float.valueOf(8.58f), statsResponse.getMax_night());

    }

    @Test
    void getBookingsStatsEmpty() throws Exception{
        //given
        List<BookingRequest> bookingRequests = List.of();
        
        //when

        StatsResponse statsResponse = bookingService.getBookingsStats(bookingRequests);

        //then
        assertEquals(null, statsResponse);

    }

    @Test
    void getMaximize() throws Exception{
        //given
        BookingRequest bookingRequestA = BookingRequest.builder()
            .request_id("bookata_XY123")
            .check_in(LocalDate.parse("2021-01-01"))
            .nights(5)
            .selling_rate(200)
            .margin(20)
            .build();
        BookingRequest bookingRequestB = BookingRequest.builder()
            .request_id("kayete_PP234")
            .check_in(LocalDate.parse("2021-01-04"))
            .nights(5)
            .selling_rate(156)
            .margin(5)
            .build();
        BookingRequest bookingRequestC = BookingRequest.builder()
            .request_id("atropote_AA930")
            .check_in(LocalDate.parse("2021-01-04"))
            .nights(4)
            .selling_rate(150)
            .margin(6)
            .build();
        BookingRequest bookingRequestD = BookingRequest.builder()
            .request_id("acme_AAAAA")
            .check_in(LocalDate.parse("2021-01-10"))
            .nights(4)
            .selling_rate(160)
            .margin(30)
            .build();
        List<BookingRequest> bookingRequests = List.of(bookingRequestA, bookingRequestB, bookingRequestC, bookingRequestD);

        //when
        MaximizeResponse maximizeResponse = bookingService.getMaximize(bookingRequests);

        //then
        assert(maximizeResponse.getRequest_ids().contains(bookingRequestA.getRequest_id()));
        assert(maximizeResponse.getRequest_ids().contains(bookingRequestD.getRequest_id()));
        assertEquals(Float.valueOf(10.0f), maximizeResponse.getAvg_night());
        assertEquals(Float.valueOf(8.0f), maximizeResponse.getMin_night());
        assertEquals(Float.valueOf(12.0f), maximizeResponse.getMax_night());
        assertEquals(88, maximizeResponse.getTotal_profit());
    }

    @Test
    void getStatsOfBookingRequest() throws Exception{
        //given
        BookingRequest bookingRequestA = BookingRequest.builder()
            .request_id("bookata_XY123")
            .check_in(LocalDate.parse("2021-01-01"))
            .nights(5)
            .selling_rate(200)
            .margin(20)
            .build();

        BookingRequest bookingRequestB = BookingRequest.builder()
            .request_id("kayete_PP234")
            .check_in(LocalDate.parse("2021-01-04"))
            .nights(4)
            .selling_rate(156)
            .margin(22)
            .build();

        List<BookingRequest> bookingRequests = List.of(bookingRequestA, bookingRequestB);
        
        //when

        Stats stats = bookingService.getStatsOfBookingRequest(bookingRequests);

        //then
        assertEquals(Float.valueOf(8.29f), stats.getAvg_night());
        assertEquals(Float.valueOf(8.0f), stats.getMin_night());
        assertEquals(Float.valueOf(8.58f), stats.getMax_night());

    }
  
    
}
