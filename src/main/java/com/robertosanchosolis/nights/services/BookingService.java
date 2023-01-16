package com.robertosanchosolis.nights.services;

import java.util.List;

import com.robertosanchosolis.nights.models.requestModels.BookingRequest;
import com.robertosanchosolis.nights.models.responseModels.MaximizeResponse;
import com.robertosanchosolis.nights.models.responseModels.StatsResponse;

public interface BookingService {
    
        public StatsResponse getBookingsStats(List<BookingRequest> bookingRequests) throws Exception;
    
        public MaximizeResponse getMaximize(List<BookingRequest> bookingRequests) throws Exception;
}
