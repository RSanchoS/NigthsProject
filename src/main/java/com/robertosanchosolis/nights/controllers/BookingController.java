package com.robertosanchosolis.nights.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.robertosanchosolis.nights.exceptions.RestCustomException;
import com.robertosanchosolis.nights.models.requestModels.BookingRequest;
import com.robertosanchosolis.nights.models.responseModels.MaximizeResponse;
import com.robertosanchosolis.nights.models.responseModels.StatsResponse;

public interface BookingController {

    ResponseEntity<StatsResponse> getBookingsStats(List<BookingRequest> bookingRequests) throws RestCustomException;

    ResponseEntity<MaximizeResponse> getMaximize(List<BookingRequest> bookingRequests) throws RestCustomException;
    
}
