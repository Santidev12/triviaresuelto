package Juego;

import Preguntas.Pregunta;
import files.GestionaFicheros;
import user.Partida;
import user.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TrivialJuego {
    private Player jugador;
    private ArrayList<Pregunta> preguntas;

    public TrivialJuego(Player jugador, ArrayList<Pregunta> preguntas) {
        this.jugador = jugador;
        this.preguntas = preguntas;
    }

    public void jugar() {

        Partida partida = new Partida(jugador);

        System.out.println("¡Bienvenido al Trivial, " + jugador.nombre + "!");
        System.out.println("Responde a las siguientes preguntas:");

        // Mezclar las preguntas
        Collections.shuffle(preguntas);

        // Mostrar 5 preguntas al azar
        for (int i = 0; i < 5 && i < preguntas.size(); i++) {
            Pregunta pregunta = preguntas.get(i);
            mostrarPregunta(pregunta);
            char respuesta = obtenerRespuesta();
            if (verificarRespuesta(pregunta, respuesta)) {
                System.out.println("¡Respuesta correcta!");
                partida.sumarPunto();
            } else {
                System.out.println("Respuesta incorrecta. La opción correcta es: " + pregunta.getOpcionCorrecta());
            }
        }

        // Mostrar puntuación final
        System.out.println("Tu puntuación final es: " + partida.getPuntuacion());

        GestionaFicheros.guardarPartida(partida);
    }

    private void mostrarPregunta(Pregunta pregunta) {
        System.out.println(pregunta.getPregunta());
        for (int i = 0; i < pregunta.getOpciones().length; i++) {
            System.out.println((char) ('A' + i) + ") " + pregunta.getOpciones()[i].getEnunciado());
        }
    }

    private char obtenerRespuesta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Elige tu respuesta (A, B, C o D): ");
        return Character.toUpperCase(scanner.nextLine().charAt(0));
    }

    private boolean verificarRespuesta(Pregunta pregunta, char respuesta) {
        int indiceRespuesta = respuesta - 'A';
        return pregunta.esCorrecta(indiceRespuesta);
    }
}
