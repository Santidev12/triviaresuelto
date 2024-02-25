package Preguntas;

public class Pregunta {
    private String pregunta;
    private Opcion[] opciones;

    public Pregunta(String pregunta, Opcion[] opciones) {
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    public String getOpcionCorrecta() {
        for (Opcion opcion : opciones) {
            if (opcion.esCorrecta()) {
                return opcion.getEnunciado();
            }
        }
        return null;
    }

    public boolean esCorrecta(int indice) {
        return opciones[indice].esCorrecta();
    }

    public String getPregunta() {
        return pregunta;
    }

    public Opcion[] getOpciones() {
        return opciones;
    }
}

