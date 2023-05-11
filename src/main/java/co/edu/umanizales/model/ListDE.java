package co.edu.umanizales.model;

import co.edu.umanizales.exception.ListDEException;
import lombok.Data;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.time.LocalTime;

@Data
public class ListDE{
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

        public void addLedToBeginning (Led led){
            NodeDE newNode = new NodeDE(led);
            if (this.head != null) {
                this.head.setPrevious(newNode);
            }
            this.head = newNode;
            size++;
        }

        public String toListString () {
            StringBuilder sb = new StringBuilder();
            NodeDE temp = this.head;
            sb.append("[");
            while (temp != null) {
                sb.append(temp.getData().toString());
                temp = temp.getNext();
                if (temp != null) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();

        }
        public void restartLeds () throws ListDEException {
            if (this.head == null) {
                NodeDE temp = this.head;
                while (temp != null) {
                    temp.getData().setState(false);
                    temp = temp.getNext();
                }
            }
            throw new ListDEException("404", "No se ha encontrado");
        }


    }//end of the listDE---------------------------------
