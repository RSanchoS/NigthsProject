package com.robertosanchosolis.nights.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.robertosanchosolis.nights.controllers.impl.BookingControllerImpl;
import com.robertosanchosolis.nights.models.requestModels.BookingRequest;
import com.robertosanchosolis.nights.models.responseModels.MaximizeResponse;
import com.robertosanchosolis.nights.models.responseModels.StatsResponse;
import com.robertosanchosolis.nights.services.BookingService;

@ExtendWith(MockitoExtension.class)
public class BookingControllerUnitTest {

    @InjectMocks
    BookingControllerImpl bookingController;

    @Mock
    BookingService bookingService;

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
        Mockito.when(bookingService.getBookingsStats(anyList())).thenReturn(StatsResponse.builder()
            .avg_night(8.29f)
            .min_night(8.0f)
            .max_night(8.58f)
            .build());
        ResponseEntity<StatsResponse> statsResponse = bookingController.getBookingsStats(bookingRequests);

        //then
        assertNotNull(statsResponse);
        assertEquals(HttpStatus.OK, statsResponse.getStatusCode());
        assertEquals(Float.valueOf(8.29f), statsResponse.getBody().getAvg_night());
        assertEquals(Float.valueOf(8.0f), statsResponse.getBody().getMin_night());
        assertEquals(Float.valueOf(8.58f), statsResponse.getBody().getMax_night());
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
            Mockito.when(bookingService.getMaximize(anyList())).thenReturn(MaximizeResponse.builder()
                .request_ids(List.of(bookingRequestA.getRequest_id(), bookingRequestD.getRequest_id()))
                .avg_night(10.0f)
                .min_night(8.0f)
                .max_night(12.0f)
                .total_profit(88)
                .build());

            ResponseEntity<MaximizeResponse> maximizeResponseEntity = bookingController.getMaximize(bookingRequests);
    
            //then
            assert(maximizeResponseEntity.getBody().getRequest_ids().contains(bookingRequestA.getRequest_id()));
            assert(maximizeResponseEntity.getBody().getRequest_ids().contains(bookingRequestD.getRequest_id()));
            assertEquals(Float.valueOf(10.0f), maximizeResponseEntity.getBody().getAvg_night());
            assertEquals(Float.valueOf(8.0f), maximizeResponseEntity.getBody().getMin_night());
            assertEquals(Float.valueOf(12.0f), maximizeResponseEntity.getBody().getMax_night());
            assertEquals(88, maximizeResponseEntity.getBody().getTotal_profit());
    }
    
}
