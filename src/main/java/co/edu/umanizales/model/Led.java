package co.edu.umanizales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
@Data
@AllArgsConstructor
public class Led {

    private int id;
    private boolean state;
    private LocalTime dateOn;
    private LocalTime dateOff;
}
