package user;

import java.util.Date;

public class Partida {
    private Date date;
    private int puntuacion;
    private Player player;

    public Partida(Player player) {
        this.date = new Date();
        this.puntuacion = 0;
        this.player = player;
    }

    public void sumarPunto() {
        this.puntuacion++;
    }


    public int getPuntuacion() {
        return puntuacion;
    }

    public Date getDate() {
        return date;
    }

    public Player getPlayer() {
        return player;
    }

}