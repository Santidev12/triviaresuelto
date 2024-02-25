package user;


import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(String nombre, String password) {
        super(nombre, password);
    }

    @Override
    public boolean permisosAdmin() {
        return true;
    }
}
