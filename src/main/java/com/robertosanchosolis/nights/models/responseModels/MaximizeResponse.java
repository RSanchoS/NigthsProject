package com.robertosanchosolis.nights.models.responseModels;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaximizeResponse {
    
    private List<String> request_ids;
    private Integer total_profit;
    private Float avg_night;
    private Float min_night;
    private Float max_night;
}
