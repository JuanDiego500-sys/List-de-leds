package co.edu.umanizales.controller.dto;

import lombok.Data;

import java.time.LocalTime;
@Data
public class LedDTO {
    private int id;
    private boolean state;
    private LocalTime dateOn;
    private LocalTime dateOff;
}
