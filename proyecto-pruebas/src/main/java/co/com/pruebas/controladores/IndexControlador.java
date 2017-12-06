package co.com.pruebas.controladores;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author juan
 */
@SessionScoped
@Named("indexControlador")
public class IndexControlador implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private int numeroEventos;
    private UploadedFile apk;
    private UploadedFile features;
    private String paginaTienda;
    private boolean nexus5x;
    private boolean nexus6x;
    private int progresoMonkey;
    private int progresoCalabash;
    private int progresoRipper;

    public void ejecutarPruebas() {
        if (numeroEventos <= 0) {
            String msn = "El numero de eventos debe ser mayor a cero";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msn, msn));
        } else if (!nexus5x && !nexus6x) {
            String msn = "Debe seleccionar al menos un dispositivo";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msn, msn));
        } else if (apk.getFileName().trim().isEmpty()) {
            String msn = "Debe adjuntar un archivo APK";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msn, msn));
        } else if (features.getFileName().trim().isEmpty()) {
            String msn = "Debe adjuntar un archivo ZIP con features calabash";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msn, msn));
        } else if (paginaTienda.trim().isEmpty()) {
            String msn = "Debe indicar una URL de la tienda de aplicaciones";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msn, msn));
        } else {
            RequestContext.getCurrentInstance().execute("PF('pbAjax1').start();PF('pbAjax2').start();PF('pbAjax3').start();PF('startButton2').disable();");
            ejecutarMonkeyAsinc();
            ejecutarCalabashAsinc();
            ejecutarRipperAsinc();
        }
    }

    private void ejecutarMonkeyAsinc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                progresoMonkey = 0;
                for (int i = 0; i < 100; i++) {
                    try {
                        progresoMonkey++;
                        Thread.sleep(250);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    progresoMonkey = 100;
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private void ejecutarCalabashAsinc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                progresoCalabash = 0;
                for (int i = 0; i < 100; i++) {
                    try {
                        progresoCalabash++;
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    progresoCalabash = 100;
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private void ejecutarRipperAsinc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                progresoRipper = 0;
                for (int i = 0; i < 100; i++) {
                    try {
                        progresoRipper++;
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    progresoRipper = 100;
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public int getNumeroEventos() {
        return numeroEventos;
    }

    public void setNumeroEventos(int numeroEventos) {
        this.numeroEventos = numeroEventos;
    }

    public UploadedFile getApk() {
        return apk;
    }

    public void setApk(UploadedFile apk) {
        this.apk = apk;
    }

    public UploadedFile getFeatures() {
        return features;
    }

    public void setFeatures(UploadedFile features) {
        this.features = features;
    }

    public String getPaginaTienda() {
        return paginaTienda;
    }

    public void setPaginaTienda(String paginaTienda) {
        this.paginaTienda = paginaTienda;
    }

    public boolean isNexus5x() {
        return nexus5x;
    }

    public void setNexus5x(boolean nexus5x) {
        this.nexus5x = nexus5x;
    }

    public boolean isNexus6x() {
        return nexus6x;
    }

    public void setNexus6x(boolean nexus6x) {
        this.nexus6x = nexus6x;
    }

    public int getProgresoMonkey() {
        return progresoMonkey;
    }

    public void setProgresoMonkey(int progresoMonkey) {
        this.progresoMonkey = progresoMonkey;
    }

    public int getProgresoCalabash() {
        return progresoCalabash;
    }

    public void setProgresoCalabash(int progresoCalabash) {
        this.progresoCalabash = progresoCalabash;
    }

    public int getProgresoRipper() {
        return progresoRipper;
    }

    public void setProgresoRipper(int progresoRipper) {
        this.progresoRipper = progresoRipper;
    }    
}