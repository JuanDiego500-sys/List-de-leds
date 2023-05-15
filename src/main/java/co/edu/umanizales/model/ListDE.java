package co.edu.umanizales.model;

import co.edu.umanizales.exception.ListDEException;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public void addLed(Led led) throws ListDEException {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                if (temp.getData().getId() == led.getId()) {
                    throw new ListDEException("400", "Ya existe un led con ese codigo");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getId() == led.getId()) {
                throw new ListDEException("400", "Ya existe un led con ese codigo");
            }
            NodeDE newNode = new NodeDE(led);
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        } else {
            this.head = new NodeDE(led);
        }
        size++;

    }

    public void addLedToBeginning(Led led) {
        NodeDE newNode = new NodeDE(led);
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                if (temp.getData().getId() == led.getId()) {
                    throw new ListDEException("400", "Ya existe un led con ese código");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getId() == led.getId()) {
                throw new ListDEException("400", "Ya existe un led con ese código");
            }
            this.head.setPrevious(newNode);
            newNode.setNext(this.head);
        }
        this.head = newNode;

        size++;
    }

    public ArrayList<Led> showList() {
        ArrayList<Led> leds = new ArrayList<>();
        if (this.head != null) {
            NodeDE temp = this.head;

            do {
                leds.add(temp.getData());
                temp = temp.getNext();
            } while (temp != null);
        }
        return leds;

    }

    public void restartLeds() throws ListDEException {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp != null) {
                temp.getData().setState(false);
                temp = temp.getNext();
            }
            return;
        }
        throw new ListDEException("404", "No se han encontrado datos");
    }


}//end of the listDE---------------------------------
