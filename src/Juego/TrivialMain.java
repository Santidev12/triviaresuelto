package Juego;

import Preguntas.Pregunta;
import files.GestionaFicheros;
import user.Admin;
import user.Partida;
import user.Player;
import user.User;

import java.util.ArrayList;
import java.util.Scanner;
public class TrivialMain {
    private static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Registro player");
            System.out.println("2. Registro admin");
            System.out.println("3. Inicio de sesión");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registroPlayer(scanner);
                    break;
                case 2:
                    registroAdmin(scanner);
                    break;
                case 3:
                    inicioSesion(scanner, users);
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void registroPlayer(Scanner scanner) {
        System.out.println("\nRegistro de jugador:");
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Confirme contraseña: ");
        String confirmacion = scanner.nextLine();

        if (password.equals(confirmacion)) {
            if (password.length() >= 8) {
                boolean existe = false;
                for (User user : users) {
                    if (user.nombre.equals(nombre)) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    Player player = new Player(nombre, password);
                    users.add(player);
                    System.out.println("Jugador registrado correctamente.");

                    GestionaFicheros.guardaUsuarios(users);
                } else {
                    System.out.println("El nombre de usuario ya existe.");
                }
            } else {
                System.out.println("La contraseña debe tener al menos 8 caracteres.");
            }
        } else {
            System.out.println("Las contraseñas no coinciden.");
        }
    }

    private static void registroAdmin(Scanner scanner) {
        System.out.println("\nRegistro de administrador:");
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Confirme contraseña: ");
        String confirmacion = scanner.nextLine();

        if (password.equals(confirmacion)) {
            if (password.length() >= 8) {
                boolean existe = false;
                for (User user : users) {
                    if (user.nombre.equals(nombre)) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    Admin admin = new Admin(nombre, password);
                    users.add(admin);

                    GestionaFicheros.guardaUsuarios(users);
                    System.out.println("Administrador registrado correctamente.");
                } else {
                    System.out.println("El nombre de usuario ya existe.");
                }
            } else {
                System.out.println("La contraseña debe tener al menos 8 caracteres.");
            }
        } else {
            System.out.println("Las contraseñas no coinciden.");
        }
    }


    private static void inicioSesion(Scanner scanner, ArrayList<User> users) {
        System.out.println("\nInicio de sesión:");
        System.out.print("Ingrese nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();

        User usuarioEncontrado = null;
        for (User user : users) {
            if (user.nombre.equals(nombre) && user.compruebaPass(password)) {
                usuarioEncontrado = user;
                break;
            }
        }

        if (usuarioEncontrado != null) {

                if (usuarioEncontrado.permisosAdmin()) {
                    System.out.println("Inicio de sesión exitoso como administrador.");
                    TrivialAdmin admin = new TrivialAdmin();
                    admin.administrar();
                } else if (!usuarioEncontrado.permisosAdmin()) {
                    System.out.println("Inicio de sesión exitoso como jugador.");

                    Player player = (Player) usuarioEncontrado;
                    Partida partida = new Partida(player);
                    System.out.println("Se ha iniciado una nueva partida para " + player.nombre +
                            " en la fecha " + partida.getDate());

                    // Cargo las preguntas desde el archivo
                    ArrayList<Pregunta> preguntas = GestionaFicheros.cargaPreguntas();

                    // Aquí creo una instancia de TrivialJuego con el jugador y las preguntas
                    TrivialJuego juego = new TrivialJuego(player, preguntas);

                    juego.jugar();
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }

    }

}
