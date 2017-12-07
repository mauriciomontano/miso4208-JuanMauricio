package co.com.pruebas.controladores;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author juan
 */
@SessionScoped
@Named("indexControlador")
public class IndexControlador implements Serializable {

    private static final long serialVersionUID = 1L;

    private String[] VELOCIDADES = {"gsm", "hscsd", "gprs", "edge", "umts", "hsdpa", "lte", "evdo", "full"};
    private String android_home = "/home/juan/Android/Sdk";
    private String adb_root = "/home/juan/Android/Sdk/platform-tools/";
    private String telnet_token = "ZhOfLHqRnR2sPche";
    private String emulator_port = "5554";
    private boolean nexus5x;
    private boolean nexus6x;
    private String paginaTienda;
    private int numeroEventos;
    private String paquete;
    private UploadedFile apk;
    private UploadedFile features;
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

    public void ejecutarTelnet(String orden) throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process telnet = rt.exec("telnet localhost " + emulator_port);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(telnet.getOutputStream()));
        out.write("auth " + telnet_token + "\n");
        out.write(orden + "\n");
        out.write("quit\n");
        out.flush();
        System.out.println("Orden telnet: " + orden);
    }

    public void abrirEmulador(String emulador) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        URL resource = this.getClass().getResource("/abrirEmulador.sh");
        FileWriter fichero = new FileWriter(resource.getPath());
        PrintWriter pw = new PrintWriter(fichero);
        pw.println("export ANDROID_HOME=" + android_home);
        pw.println("$ANDROID_HOME/emulator/emulator -netdelay none -netspeed full -avd " + emulador);
        fichero.close();
        pw.close();
        rt.exec("sh " + resource.getPath());
        Thread.sleep(5000);
        System.out.println("Abriendo emulador " + emulador);
    }

    public void ejecutarAdb(String orden) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(adb_root + "adb shell input " + orden);
        System.out.println("Orden adb: " + orden);
    }

    public void instalarApk(String apk, String paquete) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(adb_root + "adb install -r " + apk);
        System.out.println("Instaldo: " + apk);
        Thread.sleep(3000);
    }

    public void abrirApk(String paquete) throws IOException {
        Runtime rt = Runtime.getRuntime();
        InputStream is = rt.exec(adb_root + "adb shell dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'").getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String salida = "";
        String line;
        while ((line = in.readLine()) != null) {
            salida += line;
        }
        if (!salida.contains(paquete)) {
            rt.exec(adb_root + "adb shell monkey -p " + paquete + " 1");
            System.out.println("Abriendo: " + paquete);
        }
    }

    public boolean getPermisoEjecutar(String probabilidad) {
        Random random = new Random();
        return probabilidad != null && random.nextInt(101) < (Double.valueOf(probabilidad) * 100);
    }

    private void ejecutarMonkeyAsinc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    progresoMonkey = 0;
                    abrirEmulador("Nexus_5X_API_24");
                    instalarApk(apk.getFileName(), paquete);
                    abrirApk(paquete);
                    Random random = new Random(12345);
                    for (int i = 0; i < numeroEventos; i++) {
                        int x1 = random.nextInt(1080);
                        int y1 = random.nextInt(1920);
                        int x2 = random.nextInt(1080);
                        int y2 = random.nextInt(1920);
                        int key = random.nextInt(283);
                        int vel = random.nextInt(8 + 1);
                        int x = random.nextInt(99);
                        int y = random.nextInt(99);
                        int z = random.nextInt(99);

                        abrirApk(paquete);
                        ejecutarAdb("tap " + x1 + " " + y1);

                        abrirApk(paquete);
                        ejecutarAdb("text " + UUID.randomUUID());

                        abrirApk(paquete);
                        ejecutarAdb("swipe " + x1 + " " + y1 + " " + x2 + " " + y2);

                        abrirApk(paquete);
                        ejecutarAdb("keyevent " + key);

                        abrirApk(paquete);
                        ejecutarTelnet("rotate");

                        abrirApk(paquete);
                        ejecutarTelnet("network speed " + VELOCIDADES[vel]);

                        abrirApk(paquete);
                        ejecutarTelnet("sensor set acceleration " + x + ":" + y + ":" + z);

                        progresoMonkey++;
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public String[] getVELOCIDADES() {
        return VELOCIDADES;
    }

    public void setVELOCIDADES(String[] VELOCIDADES) {
        this.VELOCIDADES = VELOCIDADES;
    }

    public String getAndroid_home() {
        return android_home;
    }

    public void setAndroid_home(String android_home) {
        this.android_home = android_home;
    }

    public String getAdb_root() {
        return adb_root;
    }

    public void setAdb_root(String adb_root) {
        this.adb_root = adb_root;
    }

    public String getTelnet_token() {
        return telnet_token;
    }

    public void setTelnet_token(String telnet_token) {
        this.telnet_token = telnet_token;
    }

    public String getEmulator_port() {
        return emulator_port;
    }

    public void setEmulator_port(String emulator_port) {
        this.emulator_port = emulator_port;
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

    public String getPaginaTienda() {
        return paginaTienda;
    }

    public void setPaginaTienda(String paginaTienda) {
        this.paginaTienda = paginaTienda;
    }

    public int getNumeroEventos() {
        return numeroEventos;
    }

    public void setNumeroEventos(int numeroEventos) {
        this.numeroEventos = numeroEventos;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
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
