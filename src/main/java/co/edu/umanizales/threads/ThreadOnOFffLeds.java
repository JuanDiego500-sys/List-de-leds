package co.edu.umanizales.threads;

import co.edu.umanizales.exception.ListDEException;
import co.edu.umanizales.model.ListDE;
import co.edu.umanizales.model.NodeDE;

import java.time.LocalTime;

public class ThreadOnOFffLeds implements Runnable {
    private ListDE listDE;

    public ThreadOnOFffLeds(ListDE listDE) {
        this.listDE = listDE;
    }

    @Override
    public void run() {
        if (this.listDE.getHead() == null) {

        } else {

            NodeDE temp = this.listDE.getHead();
            NodeDE empt = this.listDE.getHead();
            if (this.listDE.getSize() % 2 == 0) {
                int pos = this.listDE.getSize() / 2;
                for (int i = 0; i < pos; i++) {
                    empt = empt.getNext();
                    temp = empt;
                }
                temp = temp.getPrevious();
                while (temp != null || empt != null) {
                    if (temp != null) {
                        temp.getData().setState(true);
                        temp.getData().setDateOn(LocalTime.now());
                        System.out.println("hora de encendido" + temp.getData().getId() +
                                " : " + temp.getData().getDateOn());
                    }
                    if (empt != null) {
                        empt.getData().setState(true);
                        empt.getData().setDateOn(LocalTime.now());
                        System.out.println("hora de encendido" + empt.getData().getId() +
                                " : " + empt.getData().getDateOn());


                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();

                    }if (temp != null) {
                        temp.getData().setState(false);
                        temp.getData().setDateOff(LocalTime.now());
                        System.out.println("Hora de apagado" + temp.getData().getId() +
                                " : " + temp.getData().getDateOff());
                        temp = temp.getPrevious();
                    }
                    if (empt !=null) {
                        empt.getData().setState(false);
                        empt.getData().setDateOff(LocalTime.now());
                        System.out.println("Hora de apagado" + empt.getData().getId() +
                                " : " + empt.getData().getDateOff());
                        empt = empt.getNext();
                    }
                }

            } else {
                int pos = this.listDE.getSize() - 1;
                pos = this.listDE.getSize() / 2;
                for (int i = 0; i < pos; i++) {
                    temp = temp.getNext();
                    empt = temp;
                }
                temp.getData().setState(true);
                temp.getData().setDateOn(LocalTime.now());
                System.out.println("hora de encendido" + temp.getData().getId() +
                        " : " + temp.getData().getDateOn());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                }
                temp.getData().setDateOff(LocalTime.now());
                System.out.println("Hora de apagado" + temp.getData().getId() +
                        " : " + temp.getData().getDateOff());
                while (temp != null || empt != null) {
                    if (temp != null) {
                        temp = temp.getPrevious();
                        temp.getData().setState(true);
                        temp.getData().setDateOn(LocalTime.now());
                        System.out.println("hora de encendido" + temp.getData().getId() +
                                " : " + temp.getData().getDateOn());

                    }
                    if (empt != null) {
                        empt = empt.getNext();
                        empt.getData().setState(true);
                        empt.getData().setDateOn(LocalTime.now());
                        System.out.println("hora de encendido" + empt.getData().getId() +
                                " : " + empt.getData().getDateOn());


                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();

                    }if (temp != null) {
                        temp.getData().setState(false);
                        temp.getData().setDateOff(LocalTime.now());
                        System.out.println("Hora de apagado" + temp.getData().getId() +
                                " : " + temp.getData().getDateOff());
                        temp = temp.getPrevious();
                    }
                    if (empt !=null) {
                        empt.getData().setState(false);
                        empt.getData().setDateOff(LocalTime.now());
                        System.out.println("Hora de apagado" + empt.getData().getId() +
                                " : " + empt.getData().getDateOff());
                        empt = empt.getNext();
                    }
                }

            }

        }

    }
}
