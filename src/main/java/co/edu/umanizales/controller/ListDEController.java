package co.edu.umanizales.controller;

import co.edu.umanizales.controller.dto.LedDTO;
import co.edu.umanizales.controller.dto.ResponseDTO;
import co.edu.umanizales.exception.ListDEException;
import co.edu.umanizales.exception.RequestException;
import co.edu.umanizales.model.Led;
import co.edu.umanizales.service.ListDEService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/list_de")
public class ListDEController {
    private ListDEService listDEService;

    public ListDEController(ListDEService listDEService) {
        this.listDEService = listDEService;
    }

    @GetMapping(path = "/get_list")
    public ResponseEntity<ResponseDTO> getPets() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.LedsToString(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addLed(@RequestBody LedDTO ledDTO) {
        try {
            listDEService.addLedToEnd(new Led(ledDTO.getId(), ledDTO.isState(), ledDTO.getDateOn(), ledDTO.getDateOff()));
            return new ResponseEntity<>(new ResponseDTO(200, "led añadida a la lista", null), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(path = "/add_led_beginning")
    public ResponseEntity<ResponseDTO> addLedToBeginning(@RequestBody LedDTO ledDTO) {
        listDEService.addLedToBeginning(new Led(ledDTO.getId(), ledDTO.isState(), ledDTO.getDateOn(), ledDTO.getDateOff()));
        return new ResponseEntity<>(new ResponseDTO(200, "led agregada a la lista", null), HttpStatus.OK);
    }

    @PostMapping(path = "/restart_list")
    public ResponseEntity<ResponseDTO> restartLeds() {
        try {
            listDEService.restartLeds();
            return new ResponseEntity<>(new ResponseDTO(200, "leds reiniciadas y apagadas", null
            ), HttpStatus.OK);
        } catch (ListDEException e) {
            throw new RequestException(e.getCode(), e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/on_off_all_led")
    public ResponseEntity<ResponseDTO> onOffAllLeds() {
        if(listDEService.getLeds().getHead() !=null){
            listDEService.runThread();
            return new ResponseEntity<>(new ResponseDTO(200, "realizado", null), HttpStatus.OK);

        } else  {
            throw new RequestException("404","no hay datos en la lista led",HttpStatus.NOT_FOUND);
        }

    }


}//end of the listDE controller
