package files;

import Preguntas.Opcion;
import Preguntas.Pregunta;
import user.Partida;
import user.User;
import java.io.*;
import java.util.ArrayList;
public class GestionaFicheros {

    public static final String filePreguntas = "src/files/preguntas.txt";
    public static final String fileUser = "src/files/user.bin";
    public static final String filePartidas = "src/files/partidas.txt";



    public static void guardaUsuarios(ArrayList<User> usuarios) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileUser))) {
            out.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> cargaUsuarios() {
        ArrayList<User> usuarios = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileUser))) {
            usuarios = (ArrayList<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }


    public static ArrayList<Pregunta> cargaPreguntas() {
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filePreguntas))) {
            String enunciado;
            String linea;
            while ((enunciado = in.readLine()) != null) {
                // Lee la pregunta
                enunciado = enunciado.trim(); // Elimina espacios en blanco al inicio y al final

                // Lee las opciones para esta pregunta
                ArrayList<Opcion> opciones = new ArrayList<>();
                 // Inicializamos el índice de la opción correcta
                while ((linea = in.readLine()) != null && !linea.isEmpty())                     { // Lee líneas hasta encontrar una línea en blanco
                    boolean esCorrecta = linea.endsWith("*");
                    String enunciadoOpcion = linea.replace("*", "").trim();
                    Opcion opcion = new Opcion(enunciadoOpcion, esCorrecta);
                    opciones.add(opcion);

                    // Verifica si esta opción es correcta y almacena su índice si lo es

                }

                // Crea la pregunta y la agrega a la lista
                Pregunta nuevaPregunta = new Pregunta(enunciado, opciones.toArray(new Opcion[0])); // Convierte el ArrayList de opciones a un array
                preguntas.add(nuevaPregunta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return preguntas;
    }

    public static void guardarPartida(Partida partida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePartidas, true))) {
            // Formateamos la información de la partida de manera legible
            writer.write("Jugador: " + partida.getPlayer().nombre + ", Puntaje: " + partida.getPuntuacion() + ", Fecha:" + partida.getDate());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Método para leer todas las partidas desde el fichero de texto
        public static ArrayList<String> leePartidas() {
            ArrayList<String> partidasInfo = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filePartidas))) {
                String line;
                while ((line = br.readLine()) != null) {
                    partidasInfo.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return partidasInfo;
        }
}

