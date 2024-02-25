package user;

import java.io.Serializable;

public abstract class User implements Comparable<User>, Serializable {
    public String nombre;
    protected String password;

    public User(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public boolean cambiarPassword(String nuevaPassword) {
        if (nuevaPassword.length() >= 8) {
            this.password = nuevaPassword;
            return true;
        } else {
            return false;
        }
    }

    public boolean compruebaPass(String password) {
        return this.password.equals(password);
    }

    public abstract boolean permisosAdmin();

    @Override
    public int compareTo(User otroUsuario) {
        return this.nombre.compareTo(otroUsuario.nombre);
    }


}
