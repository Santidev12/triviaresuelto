package user;

import java.io.Serializable;

public class Player extends User implements Serializable {
    public Player(String nombre, String password) {
        super(nombre, password);
    }

    @Override
    public boolean permisosAdmin() {
        return false;
    }
}
