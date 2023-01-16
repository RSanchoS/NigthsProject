package com.robertosanchosolis.nights.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stats {

    private Float avg_night;
    private Float min_night;
    private Float max_night;
}
