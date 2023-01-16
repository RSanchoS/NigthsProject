package com.robertosanchosolis.nights.models.responseModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsResponse{
    
    private Float avg_night;
    private Float min_night;
    private Float max_night;
}
