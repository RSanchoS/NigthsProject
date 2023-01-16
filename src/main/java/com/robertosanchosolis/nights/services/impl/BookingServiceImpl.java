package com.robertosanchosolis.nights.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.robertosanchosolis.nights.models.Stats;
import com.robertosanchosolis.nights.models.requestModels.BookingRequest;
import com.robertosanchosolis.nights.models.responseModels.MaximizeResponse;
import com.robertosanchosolis.nights.models.responseModels.StatsResponse;
import com.robertosanchosolis.nights.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService{

    @Override
    public StatsResponse getBookingsStats(List<BookingRequest> bookingRequests) throws Exception {
        if (bookingRequests != null && !bookingRequests.isEmpty()) {
            
            Stats stats = this.getStatsOfBookingRequest(bookingRequests);

            return StatsResponse.builder()
            .avg_night(stats.getAvg_night())
            .min_night(stats.getMin_night())
            .max_night(stats.getMax_night())
            .build();
        }

        return null;
    }

    @Override
    public MaximizeResponse getMaximize(List<BookingRequest> bookingRequests) throws Exception {

        List<BookingRequest> selectedRequests = new ArrayList<>();

        for (BookingRequest request : bookingRequests) {
            boolean overlapping = false;
            for (BookingRequest selectedRequest : selectedRequests) {
                if (request.getCheck_in().isBefore(selectedRequest.getCheck_in().plusDays(selectedRequest.getNights())) &&
                        request.getCheck_in().plusDays(request.getNights()).isAfter(selectedRequest.getCheck_in())) {
                    overlapping = true;
                    break;
                }
            }
            if (!overlapping) {
                selectedRequests.add(request);
            }
        }

        Stats stats = this.getStatsOfBookingRequest(selectedRequests);

        List<String> requestIds = selectedRequests
            .stream()
            .map(BookingRequest::getRequest_id)
            .collect(Collectors.toList());

        float total_profit = selectedRequests.stream()
            .map(BookingRequest::getTotalProfit)
            .reduce(0f, Float::sum);

        return MaximizeResponse.builder()
            .request_ids(requestIds)
            .avg_night(stats.getAvg_night())
            .max_night(stats.getMax_night())
            .min_night(stats.getMin_night())
            .total_profit((int) total_profit)
            .build();
    }

    public Stats getStatsOfBookingRequest(List<BookingRequest> bookingRequests){
        
        if (bookingRequests != null && !bookingRequests.isEmpty()) {

            Float avgNight = bookingRequests.stream()
            .map(BookingRequest::getProfitPerNight)
            .reduce(0f, Float::sum)
            / bookingRequests.size();

            Float minNight = bookingRequests.stream().map(BookingRequest::getProfitPerNight)
            .min(Float::compare)
            .get();

            Float maxNight = bookingRequests.stream()
            .map(BookingRequest::getProfitPerNight)
            .max(Float::compare)
            .get();

            return Stats.builder()
            .avg_night(avgNight)
            .min_night(minNight)
            .max_night(maxNight)
            .build();
        }

        return null;
    }
    
}
