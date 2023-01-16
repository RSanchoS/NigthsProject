package com.robertosanchosolis.nights.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.robertosanchosolis.nights.controllers.BookingController;
import com.robertosanchosolis.nights.exceptions.RestCustomException;
import com.robertosanchosolis.nights.models.requestModels.BookingRequest;
import com.robertosanchosolis.nights.models.responseModels.MaximizeResponse;
import com.robertosanchosolis.nights.models.responseModels.StatsResponse;
import com.robertosanchosolis.nights.services.BookingService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookingControllerImpl implements BookingController {

    @Autowired
    BookingService bookingService;

    @Override
    @PostMapping("/stats")
    public ResponseEntity<StatsResponse> getBookingsStats(@RequestBody @Valid List<BookingRequest> bookingRequests) throws RestCustomException {

        try{
            log.info("Post - /stats");

            StatsResponse statsResponse = bookingService.getBookingsStats(bookingRequests);

            return statsResponse != null ? new ResponseEntity<>(statsResponse, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){

            throw new RestCustomException("Post - /stats - " + e.getMessage());
        }

    }

    @Override
    @PostMapping("/maximize")
    public ResponseEntity<MaximizeResponse> getMaximize(@RequestBody @Valid List<BookingRequest> bookingRequests) throws RestCustomException {
    
        try{
            log.info("Post - /maximize");

            MaximizeResponse maximizeResponse = bookingService.getMaximize(bookingRequests);

            return maximizeResponse != null ? new ResponseEntity<>(maximizeResponse, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){

            throw new RestCustomException("Post - /maximize - " + e.getMessage());
        }
    }
    
}
