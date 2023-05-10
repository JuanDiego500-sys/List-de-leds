package co.edu.umanizales.service;

import co.edu.umanizales.model.Led;
import co.edu.umanizales.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {
    ListDE leds ;

    public ListDEService() {
        leds = new ListDE();
    }

    public void addLedToEnd(Led led){ leds.addLed(led);}
    public void addLedToBeginning(Led led){leds.addLedToBeginning(led);}
    public void restartLeds(){leds.restartLeds();}
    public void LedsToString(){leds.toListString();}
}//end of the listDeService
