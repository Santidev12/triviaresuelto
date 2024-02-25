package Juego;

import files.GestionaFicheros;
import user.User;

import java.util.ArrayList;
import java.util.Collections;

public class TrivialAdmin {

    public void administrar() {
        ArrayList<String> partidasRegistradas = GestionaFicheros.leePartidas();
        System.out.println("\n--- Partidas registradas ---");
        for (String partidaInfo : partidasRegistradas) {
            System.out.println(partidaInfo);
        }

        ArrayList<User> usuariosRegistrados = GestionaFicheros.cargaUsuarios();
        System.out.println("\n--- Usuarios registrados ---");

        // Ordenar usuarios alfabéticamente por nombre
        Collections.sort(usuariosRegistrados);
        for (User user : usuariosRegistrados) {
            System.out.println("Nombre: " + user.nombre);
        }
    }

}
