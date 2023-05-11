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
/*
ALGORITMO PARA REALIZAR EL METODO:
PRIMERO QUE TODO SE DEBE CREAR UNA CLASE DE TIPO THREAD , SE NECESITA UN THREAD YA QUE ESTE PERMITE EJECUTAR
SIMULTANEAMENTE LO QUE SE NECESITE.
ESTA CLASE THREAD SE LE IMPLEMENTA RUNNABLE YA QUE NECESITO QUE ME CORRA ESTE HILO POR SEPARADO DE LAS OTRAS FUNCIONES
DEL PROYECTO. EN ESTA CLASE SE IMPORTA LA LISTA PARA TENERLA DENTRO DEL METODO Y SE LE HACE UN CONSTRUCTOR PARA SEPARARLE
MEMORIA.
AHORA SI , EL METODO RUN: ES UN METODO DE UN HILO QUE ME VA A PERMITIR CORRERLO CON LAS FUNCIONES DE LOS THREADS , EN ESTE
CASO NECESITO EL SLEEP PARA DARLE UNA SEPARACION CADA QUE SE APAGA O PRENDE UN LED , ESA FUNCION ES SLEEP.
CON TODO LO ANTERIOR PUEDO EMPEZAR A DESCRIBIR DETALLADAMENTE COMO FUNCIONA MI CODIGO:
-------------------------------------------------------------------------------------------------------------------------
Primero se pregunta si hay datos , si los hay se procede , si no , se salta y se para el codigo.
 digamos que se procede, entonces creamos dos ayudantes , uno para el next y otro para el previous.
 ahora vamos a hacer la primera validación, si cuando el tamaño de la lista se divide entre 2 da como residuo 0 entonces
 es una lista par , de otra manera es una lista impar.
 ----------------------------------------------------------------------------------------------------------------------
 si es una lista par, divido la lista entre 2 para tener asi una variable de tipo entero que tenga ese dato de la mitad,
 pero hay un detalle,el acumulador inicia en 0, como el acumulador inicia en 0 se va a iterar por ejemplo si la pos es
 2 , se llega a el dato 3 , entonces lo que se hace es que se deja el ayudante empt en el 3 y el temp se pasa para el
 previo. asi voy a tener una ayudante en el dato 3 y uno en el dato 2 para por ejemplo una lista de 4 leds.
 ahora ya que los tengo ahi, debo decir que mientras hayan datos a lado y lado , que pregunte por cada uno si es nulo ,
 si no son nulos los ayudantes , les digo que me prendan los bombillos y me digan la hora en la que lo hicieron , despues
 cuando hagan eso, les digo que esperen en el bombillo en el que están parados. deben esperar lo que yo les diga en
 este caso les dije que esperen 10000milisegundos para que sea mas rapido , sin embargo  , puedo ponerle el temporizador que
 yo desee.
 Ahora despues de que esperen , cumplido el tiempo les digo que si siguen siendo nulos, que cada uno apague el bombillo y me
 diga a que hora lo hizo , deberia ser en este caso mil milisegundos despues de que se encendieran. una aclaracion, todos
 los ifs y las verificaciones son por seguiridad , ya que no deseo tener problemas con nullpointer que ya tuve en la creación del metodo.
 finalmente despues de realizada la actualizacion de la hora de apagado , le digo a temp que se me pase a el previous, y al
 empt que  se me pase al next y hagan lo mismo. cuando ya sean vacios los dos entonces les digo que se salgan y retornen el
 mensaje del controller.
 -------------------------------------------------------------------------------------------------------------------------
 ahora en el otro caso , si es un tamaño impar , lo que se debe hacer es coger el tamaño y restarlo en una unidad, asi se divide entre 2
 y se tiene la mitad , sin embargo como ya lo dije , en una lista con por ejemplo tamaño 5, lo que pasa es que se le resta 1 , entonces queda en
 4, y se divide entre 2 por lo tanto queda en 2, pero en el ciclo for va a quedar en la posicion 3 que es exactamente donde necesito que esté ya que
 es exactamente la mitad , parados en ese 3 nos sobran 2 datos a cada lado.
 En este caso decidi que no iba a hacer la actualización de la hora
 de apagado y de encendido justo despues de tener al ayudante parado en el centro, ya que asi ahorro lineas de codigo y no me molesta tampoco
 ver repetido el mismo bombillo ya que el resultado es igual , sin embargo , bastaria con que al terminar el for y estar parado en el 3
 , decirle al ayudante temp que realice el apagado y encendido y retorne el mensaje. que cuando termine se pase para el previo y que el
 empt se pase para el next.
    ahora , como lo hice de una manera mas rapida evitando tener mas codigo, la verificacion la hace tanto el temp como el empt ,
    y despues de eso realizan el mismo proceso de arriba, se pasan al tiempo, apagan y prenden al tiempo , mandan el mensaje por consola
    , tienen su sleep y realmente todo es igual exceptuando el posicionamiento inicial.
--------------------------------------------------------------------------------------------------------------------------
 */
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

            }

        }

    }
}
