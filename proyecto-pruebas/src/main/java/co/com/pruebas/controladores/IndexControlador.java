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
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Random;
import java.util.UUID;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    private boolean nexus5x = true;
    private boolean galaxy5s;
    private String paginaTienda = "https://play.google.com/store/apps/category/FINANCE/collection/topselling_paid";
    private int numeroEventos = 3;
    private int semilla = 96423;
    private String paquete = "net.alaindonesia.silectric";
    private UploadedFile apk;
    private UploadedFile features;
    private int progresoMonkey;
    private int progresoCalabash;
    private int progresoScraper;
    private int progresoFireBase;
    //private Map<String, String> mapaDisp;
    private List<Dispositivo> listaDisp;
    private List<Boolean> listaSeleccion;
    public static final String NEXUS_5X_ID = "emulator-5554";
    public static final String GALAXY_5X_ID = "320490902d7e3171";

    public IndexControlador() {
        listaDisp = new ArrayList<>();
        listaDisp.add(new Dispositivo("A0001", "OnePlusOne", "PHYSICAL", "22"));
        listaDisp.add(new Dispositivo("D6503", "XperiaZ2", "PHYSICAL", "21"));
        listaDisp.add(new Dispositivo("D6603", "XperiaZ3", "PHYSICAL", "21"));
        listaDisp.add(new Dispositivo("E5803", "XperiaZ5Compact", "PHYSICAL", "22"));
        listaDisp.add(new Dispositivo("F5121", "SonyXperiaX", "PHYSICAL", "23"));
        listaDisp.add(new Dispositivo("G8142", "SonyXPERIAXZPremium", "PHYSICAL", "25"));
        listaDisp.add(new Dispositivo("HWMHA", "HuaweiMate9", "PHYSICAL", "24"));
        listaDisp.add(new Dispositivo("Nexus10", "Nexus10", "VIRTUAL", "19,21,22"));
        listaDisp.add(new Dispositivo("Nexus4", "Nexus4", "VIRTUAL", "19,21,22"));
        listaDisp.add(new Dispositivo("Nexus5", "Nexus5", "VIRTUAL", "19,21,22,23"));
        listaDisp.add(new Dispositivo("Nexus5X", "Nexus5X", "VIRTUAL", "23,24,25,26"));
        listaDisp.add(new Dispositivo("Nexus6", "Nexus6", "VIRTUAL", "21,22,23,24,25"));
        listaDisp.add(new Dispositivo("Nexus6P", "Nexus6P", "VIRTUAL", "23,24,25,26"));
        listaDisp.add(new Dispositivo("Nexus7", "Nexus7(2012)", "VIRTUAL", "19,21,22"));
        listaDisp.add(new Dispositivo("Nexus7_clone_16_9", "Nexus7clone,DVD16:9aspectratio", "VIRTUAL", "23,24,25,26"));
        listaDisp.add(new Dispositivo("Nexus9", "Nexus9", "VIRTUAL", "21,22,23,24,25"));
        listaDisp.add(new Dispositivo("NexusLowRes", "Low-resolutionMDPIphone", "VIRTUAL", "23,24,25,26"));
        listaDisp.add(new Dispositivo("SH-04H", "SH-04H", "PHYSICAL", "23"));
        listaDisp.add(new Dispositivo("athene", "MotoG4Plus", "PHYSICAL", "23"));
        listaDisp.add(new Dispositivo("athene_f", "MotoG4", "PHYSICAL", "23"));
        listaDisp.add(new Dispositivo("condor_umts", "MotoE", "PHYSICAL", "19"));
        listaDisp.add(new Dispositivo("falcon_umts", "MotoG(1stGen)", "PHYSICAL", "19"));
        listaDisp.add(new Dispositivo("flo", "Nexus7(2013)", "PHYSICAL", "19,21"));
        listaDisp.add(new Dispositivo("flounder", "Nexus9", "PHYSICAL", "21"));
        listaDisp.add(new Dispositivo("g3", "LGG3", "PHYSICAL", "19"));
        listaDisp.add(new Dispositivo("hammerhead", "Nexus5", "PHYSICAL", "19,21,22,23"));
        listaDisp.add(new Dispositivo("harpia", "MotoGPlay(4thGen)XT1607", "PHYSICAL", "23"));
        listaDisp.add(new Dispositivo("hero2lte", "GalaxyS7edge", "PHYSICAL", "23"));
        listaDisp.add(new Dispositivo("herolte", "GalaxyS7", "PHYSICAL", "23,24"));
        listaDisp.add(new Dispositivo("hlte", "GalaxyNote3Duos", "PHYSICAL", "19"));
        listaDisp.add(new Dispositivo("htc_m8", "HTCOne(M8)", "PHYSICAL", "19"));
        listaDisp.add(new Dispositivo("hwALE-H", "HuaweiP8lite", "PHYSICAL", "21"));
        listaDisp.add(new Dispositivo("j1acevelte", "GalaxyJ1aceSM-J111M", "PHYSICAL", "22"));
        listaDisp.add(new Dispositivo("j5lte", "GalaxyJ5", "PHYSICAL", "23"));
        listaDisp.add(new Dispositivo("j7xelte", "GalaxyJ7(SM-J710MN)", "PHYSICAL", "23"));
        listaDisp.add(new Dispositivo("lt02wifi", "GalaxyTab3", "PHYSICAL", "19"));
        listaDisp.add(new Dispositivo("lucye", "LGG6LGUS997", "PHYSICAL", "24"));
        listaDisp.add(new Dispositivo("m0", "SamsungGalaxyS3", "PHYSICAL", "18"));
        listaDisp.add(new Dispositivo("mako", "Nexus4", "PHYSICAL", "19,22"));
        listaDisp.add(new Dispositivo("osprey_umts", "MotoG(3rdGen)", "PHYSICAL", "22"));
        listaDisp.add(new Dispositivo("p1", "LGG4", "PHYSICAL", "22"));
        listaDisp.add(new Dispositivo("sailfish", "Pixel", "PHYSICAL", "25,26"));
        listaDisp.add(new Dispositivo("serranolte", "GalaxyS4mini", "PHYSICAL", "19"));
        listaDisp.add(new Dispositivo("shamu", "Nexus6", "PHYSICAL", "21,22,23"));
        listaDisp.add(new Dispositivo("t03g", "GalaxyNote2", "PHYSICAL", "19"));
        listaDisp.add(new Dispositivo("titan_umts", "MotoG(2ndGen)", "PHYSICAL", "19"));
        listaDisp.add(new Dispositivo("trelte", "GalaxyNote4", "PHYSICAL", "22"));
        listaDisp.add(new Dispositivo("victara", "MotoX", "PHYSICAL", "19"));
        listaDisp.add(new Dispositivo("zeroflte", "GalaxyS6", "PHYSICAL", "22"));
        listaDisp.add(new Dispositivo("zerolte", "GalaxyS6Edge", "PHYSICAL", "22"));
    }

    public void ejecutarPruebas() {

        boolean algoEnFireBase = false;
        for (Dispositivo tmp : listaDisp) {
            if (tmp.isSelected()) {
                algoEnFireBase = true;
                break;
            }
        }

        if (!algoEnFireBase) {
            String msn = "Debe seleccionar almenos un dispositivo firebase";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msn, msn));
        } else if (numeroEventos <= 0) {
            String msn = "El numero de eventos debe ser mayor a cero";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msn, msn));
        } else if (semilla <= 0) {
            String msn = "La semilla debe ser mayor a cero";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msn, msn));
        } else if (!nexus5x && !galaxy5s) {
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
            RequestContext.getCurrentInstance().execute("PF('pbAjax1').start();PF('pbAjax2').start();PF('pbAjax3').start();PF('pbAjax4').start();PF('startButton2').disable();");

            if (nexus5x && galaxy5s) {
                progresoMonkey = 0;
                progresoCalabash = 0;
                progresoScraper = 0;
                ejecutarMonkeyAsinc(NEXUS_5X_ID);

                progresoMonkey = 0;
                progresoCalabash = 0;
                progresoScraper = 0;
                ejecutarMonkeyAsinc(GALAXY_5X_ID);
            } else if (nexus5x) {
                progresoMonkey = 0;
                progresoCalabash = 0;
                progresoScraper = 0;
                ejecutarMonkeyAsinc(NEXUS_5X_ID);
            } else if (galaxy5s) {
                progresoMonkey = 0;
                progresoCalabash = 0;
                progresoScraper = 0;
                ejecutarMonkeyAsinc(GALAXY_5X_ID);
            }
            ejecutarFireBaseAsync();
            ejecutarScraperAsinc();
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
        FileWriter archivo = new FileWriter(resource.getPath());
        PrintWriter pw = new PrintWriter(archivo);
        pw.println("export ANDROID_HOME=" + android_home);
        pw.println("$ANDROID_HOME/emulator/emulator -netdelay none -netspeed full -avd " + emulador);
        archivo.close();
        pw.close();
        rt.exec("sh " + resource.getPath());
        Thread.sleep(10000);
        System.out.println("Abriendo emulador " + emulador);
    }

    public void ejecutarAdb(String orden, String device) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(adb_root + "adb -s " + device + " shell input " + orden);
        System.out.println("Orden adb: " + orden);
    }

    public void instalarApk(String apk, String paquete, String device) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(adb_root + "adb -s " + device + " install -r " + apk);
        System.out.println("Instaldo: " + apk);
        Thread.sleep(5000);
    }

    public void borrarLogCat(String device) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(adb_root + "adb -s " + device + " logcat -c");
        System.out.println("Borrando logcat: " + device);
        Thread.sleep(5000);
    }

    public void extraerLogCat(String nombre, String device) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        InputStream is = rt.exec(adb_root + "adb -s " + device + " logcat -d").getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        URL resource = this.getClass().getResource(File.separator + nombre);
        FileWriter archivo = new FileWriter(resource.getPath());
        PrintWriter pw = new PrintWriter(archivo);
        String line;
        while ((line = in.readLine()) != null) {
            pw.println(line);
        }
        pw.close();
        archivo.close();
        System.out.println("Extrayendo logcat: " + resource.getPath());
        Thread.sleep(5000);
    }

    public void abrirApk(String paquete, String device) throws IOException {
        Runtime rt = Runtime.getRuntime();
        InputStream is = rt.exec(adb_root + "adb -s " + device + " shell dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'").getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String salida = "";
        String line;
        while ((line = in.readLine()) != null) {
            salida += line;
        }
        if (!salida.contains(paquete)) {
            rt.exec(adb_root + "adb -s " + device + " shell monkey -p " + paquete + " 1");
            System.out.println("Abriendo: " + paquete);
        }
    }

    public void copiarArchivo(InputStream archivo, String rutaAbsolutaDestino) throws IOException {
        OutputStream out = null;
        try {
            out = new FileOutputStream(rutaAbsolutaDestino);

            byte[] buffer = new byte[1024];
            int len;

            while ((len = archivo.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            if (archivo != null) {
                archivo.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public void ejecutarMonkeyAsinc(final String device) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    borrarLogCat(device);
                    abrirEmulador("Nexus_5X_API_24");
                    String calabash = this.getClass().getResource("/calabash").getPath();
                    String apkDestino = calabash + File.separator + apk.getFileName();
                    copiarArchivo(apk.getInputstream(), apkDestino);
                    instalarApk(apkDestino, paquete, device);
                    abrirApk(paquete, device);
                    Random random = new Random(semilla);
                    int incremento = 100 / numeroEventos;
                    if (nexus5x && galaxy5s) {
                        incremento /= 2;
                    }
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
                        int cual = random.nextInt(7);

                        switch (cual) {
                            case 0:
                                abrirApk(paquete, device);
                                ejecutarAdb("tap " + x1 + " " + y1, device);
                                break;
                            case 1:
                                abrirApk(paquete, device);
                                ejecutarAdb("text " + UUID.randomUUID(), device);
                                break;
                            case 2:
                                abrirApk(paquete, device);
                                ejecutarAdb("swipe " + x1 + " " + y1 + " " + x2 + " " + y2, device);
                                break;
                            case 3:
                                abrirApk(paquete, device);
                                ejecutarAdb("keyevent " + key, device);
                                break;
                            case 4:
                                abrirApk(paquete, device);
                                ejecutarTelnet("rotate");
                                break;
                            case 5:
                                abrirApk(paquete, device);
                                ejecutarTelnet("network speed " + VELOCIDADES[vel]);
                                break;
                            case 6:
                                abrirApk(paquete, device);
                                ejecutarTelnet("sensor set acceleration " + x + ":" + y + ":" + z);
                                break;
                            default:
                                break;
                        }
                        progresoMonkey += incremento;
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    progresoMonkey = 100;
                    extraerLogCat("monkey_" + device + ".log", device);
                    Thread.sleep(5000);
                    ejecutarCalabashAsinc(device);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void ejecutarCalabashAsinc(final String device) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    progresoCalabash = 0;
                    borrarLogCat(device);
                    String calabash = this.getClass().getResource("/calabash").getPath();
                    String apkDestino = calabash + File.separator + apk.getFileName();
                    String featuresDestino = calabash + File.separator + "features" + File.separator + features.getFileName();
                    copiarArchivo(apk.getInputstream(), apkDestino);
                    copiarArchivo(features.getInputstream(), featuresDestino);
                    FileWriter archivo = new FileWriter(calabash + File.separator + "calabash.sh");
                    PrintWriter pw = new PrintWriter(archivo);
                    pw.println("cd " + calabash);
                    pw.println("export ANDROID_HOME=" + android_home);
                    pw.println("export ADB_DEVICE_ARG=" + device);
                    pw.println("unzip -u " + featuresDestino + " -d " + calabash + File.separator + "features");
                    pw.println("calabash-android resign " + apk.getFileName());
                    pw.println("calabash-android run " + apk.getFileName());
                    archivo.close();
                    pw.close();
                    Runtime rt = Runtime.getRuntime();
                    InputStream is = rt.exec("sh " + calabash + File.separator + "calabash.sh").getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(is));

                    URL resource2 = this.getClass().getResource(File.separator + "calabash_feature_" + device + ".log");
                    FileWriter archivo2 = new FileWriter(resource2.getPath());
                    PrintWriter pw2 = new PrintWriter(archivo2);
                    String line;
                    while ((line = in.readLine()) != null) {
                        pw2.println(line);
                        if (progresoCalabash < 100) {
                            progresoCalabash++;
                        } else {
                            progresoCalabash--;
                        }
                    }
                    pw2.close();
                    archivo2.close();
                    progresoCalabash = 100;
                    extraerLogCat("calabash_" + device + ".log", device);
                    Thread.sleep(5000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void ejecutarScraperAsinc() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Set<String> hrefs = new HashSet<>();
                    Document doc = Jsoup.connect(paginaTienda).timeout(0).get();
                    Elements anchors = doc.getElementsByClass("card-click-target");
                    for (Element e : anchors) {
                        String href = "https://play.google.com/" + e.attr("href");
                        hrefs.add(href);
                    }

                    URL resource = this.getClass().getResource("/scraper.log");
                    FileWriter archivo = new FileWriter(resource.getPath());
                    PrintWriter pw = new PrintWriter(archivo);

                    for (String url : hrefs) {
                        progresoScraper++;
                        Document paginaApp = Jsoup.connect(url).timeout(0).get();

                        //Descripcion
                        Elements claseTitulo = paginaApp.getElementsByClass("id-app-title");
                        pw.println("\nTitulo: " + (!claseTitulo.isEmpty() ? claseTitulo.get(0).text() : ""));

                        //Numero de raitings
                        Elements claseRaitings = paginaApp.getElementsByClass("rating-count");
                        pw.println("Numero de raitings: " + (!claseRaitings.isEmpty() ? claseRaitings.get(0).text() : ""));

                        //Score
                        Elements claseScore = paginaApp.getElementsByClass("score");
                        pw.println("Score: " + (!claseScore.isEmpty() ? claseScore.get(0).text() : ""));

                        //Descripcion
                        String descripcion = paginaApp.select("[itemprop='description']").text();
                        pw.println("Descripcion: " + descripcion);

                        //Cambios recientes
                        Elements claseCambiosRecientes = paginaApp.getElementsByClass("recent-change");
                        pw.println("Cambios recientes: " + (!claseCambiosRecientes.isEmpty() ? claseCambiosRecientes.get(0).text() : ""));

                        //Ratings con 5 estrellas
                        Elements claseCincoEstrellas = paginaApp.getElementsByClass("rating-bar-container five");
                        pw.println("Ratings con 5 estrellas: " + (!claseCincoEstrellas.isEmpty() ? claseCincoEstrellas.get(0).text() : ""));

                        //Ratings con 4 estrellas
                        Elements claseCuatroEstrellas = paginaApp.getElementsByClass("rating-bar-container four");
                        pw.println("Ratings con 4 estrellas: " + (!claseCuatroEstrellas.isEmpty() ? claseCuatroEstrellas.get(0).text() : ""));
                    }
                    pw.close();
                    archivo.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    progresoScraper = 100;
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void ejecutarFireBaseAsync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    progresoFireBase = 0;
                    Runtime rt = Runtime.getRuntime();
                    String calabash = this.getClass().getResource("/calabash").getPath();
                    String apkDestino = calabash + apk.getFileName();
                    String sh = "gcloud firebase test android run --type robo --app " + apkDestino;
                    for (Dispositivo tmp : listaDisp) {
                        if (tmp.isV18()) {
                            sh = sh + " --device model=" + tmp.getId() + ",version=18,locale=en,orientation=portrait ";
                        }
                        if (tmp.isV19()) {
                            sh = sh + " --device model=" + tmp.getId() + ",version=19,locale=en,orientation=portrait ";
                        }
                        if (tmp.isV20()) {
                            sh = sh + " --device model=" + tmp.getId() + ",version=20,locale=en,orientation=portrait ";
                        }
                        if (tmp.isV21()) {
                            sh = sh + " --device model=" + tmp.getId() + ",version=21,locale=en,orientation=portrait ";
                        }
                        if (tmp.isV22()) {
                            sh = sh + " --device model=" + tmp.getId() + ",version=22,locale=en,orientation=portrait ";
                        }
                        if (tmp.isV23()) {
                            sh = sh + " --device model=" + tmp.getId() + ",version=23,locale=en,orientation=portrait ";
                        }
                        if (tmp.isV24()) {
                            sh = sh + " --device model=" + tmp.getId() + ",version=24,locale=en,orientation=portrait ";
                        }
                        if (tmp.isV25()) {
                            sh = sh + " --device model=" + tmp.getId() + ",version=25,locale=en,orientation=portrait ";
                        }
                    }
                    sh += "--timeout 90s";
                    System.out.println("Ejecuntado firebase: " + sh);
                    //InputStream is = rt.exec("gcloud firebase test android models list").getInputStream();
                    InputStream is = rt.exec(sh).getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(is));
                    URL resource2 = this.getClass().getResource("/firebase.log");
                    FileWriter archivo2 = new FileWriter(resource2.getPath());
                    PrintWriter pw2 = new PrintWriter(archivo2);
                    String line;
                    while ((line = in.readLine()) != null) {
                        progresoFireBase++;
                        pw2.println(line);
                    }
                    pw2.close();
                    archivo2.close();
                    progresoFireBase = 100;
                    Thread.sleep(5000);
                    System.out.println("finaliza ejecucion firebase");
                } catch (Exception e) {
                    e.printStackTrace();
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

    public boolean isGalaxy5s() {
        return galaxy5s;
    }

    public void setGalaxy5s(boolean galaxy5s) {
        this.galaxy5s = galaxy5s;
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

    public int getSemilla() {
        return semilla;
    }

    public void setSemilla(int semilla) {
        this.semilla = semilla;
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

    public int getProgresoScraper() {
        return progresoScraper;
    }

    public void setProgresoScraper(int progresoScraper) {
        this.progresoScraper = progresoScraper;
    }

    public int getProgresoFireBase() {
        return progresoFireBase;
    }

    public void setProgresoFireBase(int progresoFireBase) {
        this.progresoFireBase = progresoFireBase;
    }

    public List<Dispositivo> getListaDisp() {
        return listaDisp;
    }

    public void setListaDisp(List<Dispositivo> listaDisp) {
        this.listaDisp = listaDisp;
    }

    public List<Boolean> getListaSeleccion() {
        return listaSeleccion;
    }

    public void setListaSeleccion(List<Boolean> listaSeleccion) {
        this.listaSeleccion = listaSeleccion;
    }
}
