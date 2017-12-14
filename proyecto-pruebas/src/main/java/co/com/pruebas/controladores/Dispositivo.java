package co.com.pruebas.controladores;

/**
 *
 * @author juan
 */
public class Dispositivo {
    private String id;
    private String nombre;
    private boolean v18;
    private boolean v19;
    private boolean v20;
    private boolean v21;
    private boolean v22;
    private boolean v23;
    private boolean v24;
    private boolean v25;

    public Dispositivo(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public boolean isSelected() {
        return v18 || v19 || v20 || v21 || v22 || v23 || v24 || v25;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isV18() {
        return v18;
    }

    public void setV18(boolean v18) {
        this.v18 = v18;
    }

    public boolean isV19() {
        return v19;
    }

    public void setV19(boolean v19) {
        this.v19 = v19;
    }

    public boolean isV20() {
        return v20;
    }

    public void setV20(boolean v20) {
        this.v20 = v20;
    }

    public boolean isV21() {
        return v21;
    }

    public void setV21(boolean v21) {
        this.v21 = v21;
    }

    public boolean isV22() {
        return v22;
    }

    public void setV22(boolean v22) {
        this.v22 = v22;
    }

    public boolean isV23() {
        return v23;
    }

    public void setV23(boolean v23) {
        this.v23 = v23;
    }

    public boolean isV24() {
        return v24;
    }

    public void setV24(boolean v24) {
        this.v24 = v24;
    }

    public boolean isV25() {
        return v25;
    }

    public void setV25(boolean v25) {
        this.v25 = v25;
    }    
}
