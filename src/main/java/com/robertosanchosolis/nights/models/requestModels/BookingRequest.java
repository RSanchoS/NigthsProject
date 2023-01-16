package com.robertosanchosolis.nights.models.requestModels;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @NotNull(message = "request_id is required")
    private String request_id;
    @NotNull(message = "check_in is required")
    private LocalDate check_in;
    @NotNull(message = "nights is required")
    private Integer nights;
    @NotNull(message = "selling_rate is required")
    private Integer selling_rate;
    @NotNull(message = "margin is required")
    private Integer margin;

    public Float getTotalProfit(){
        return (this.selling_rate * (this.margin / 100f));
    }

    public float getProfitPerNight(){
        return ((this.selling_rate * (this.margin / 100f))/ this.nights);
    }
}
