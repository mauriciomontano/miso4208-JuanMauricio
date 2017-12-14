package co.com.pruebas.controladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author juan
 */
@ViewScoped
@Named("reporteControlador")
public class ReporteControlador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private IndexControlador ic;

    private BarChartModel barModelMonkey;
    private BarChartModel barModelCalabash;
    private StreamedContent fileMonkeyNexus5x;
    private StreamedContent fileMonkeyGalaxy5s;
    private StreamedContent fileCalabashNexus5x;
    private StreamedContent fileCalabashGalaxy5s;
    private StreamedContent fileCalabashFeaturesNexus5x;
    private StreamedContent fileCalabashFeaturesGalaxy5s;
    private StreamedContent fileScraper;
    private StreamedContent fileFireBase;
    private String scraperLog;
    private String firebaseLog;

    @PostConstruct
    public void postConstruct() {
        generarReporte();
    }

    private void generarReporte() {
        try {
            barModelMonkey = new BarChartModel();

            Integer er1 = 0;
            Integer cr1 = 0;
            Integer ex1 = 0;
            Integer er2 = 0;
            Integer cr2 = 0;
            Integer ex2 = 0;

            URL r1 = this.getClass().getResource("/monkey_" + IndexControlador.NEXUS_5X_ID + ".log");
            if (r1 != null) {
                File a1 = new File(r1.getPath());
                FileReader fr1 = new FileReader(a1);
                BufferedReader br1 = new BufferedReader(fr1);
                String linea1;
                while ((linea1 = br1.readLine()) != null) {
                    if (linea1.toLowerCase().contains("error")) {
                        er1++;
                    }

                    if (linea1.contains("Force removing") && linea1.toLowerCase().contains(ic.getPaquete()) && linea1.toLowerCase().contains("app died")) {
                        cr1++;
                    }

                    if (linea1.toLowerCase().contains("exception")) {
                        ex1++;
                    }
                }
                fr1.close();
                br1.close();

                InputStream stream = new FileInputStream(r1.getPath());
                fileMonkeyNexus5x = new DefaultStreamedContent(stream, null, "fileMonkeyNexus5x.log");
            }

            URL r2 = this.getClass().getResource("/monkey_" + IndexControlador.GALAXY_5X_ID + ".log");
            if (r2 != null) {
                File a2 = new File(r2.getPath());
                FileReader fr2 = new FileReader(a2);
                BufferedReader br2 = new BufferedReader(fr2);
                String linea2;
                while ((linea2 = br2.readLine()) != null) {
                    if (linea2.toLowerCase().contains("error")) {
                        er2++;
                    }

                    if (linea2.contains("Force removing") && linea2.toLowerCase().contains(ic.getPaquete()) && linea2.toLowerCase().contains("app died")) {
                        cr2++;
                    }

                    if (linea2.toLowerCase().contains("exception")) {
                        ex2++;
                    }
                }
                fr2.close();
                br2.close();

                InputStream stream = new FileInputStream(r2.getPath());
                fileMonkeyGalaxy5s = new DefaultStreamedContent(stream, null, "fileMonkeyGalaxy5s.log");
            }

            ChartSeries n5xMonkeySerie = new ChartSeries();
            n5xMonkeySerie.setLabel("Nexus 5x");
            n5xMonkeySerie.set("Errores generales", er1);
            n5xMonkeySerie.set("Crashes del apk", cr1);
            n5xMonkeySerie.set("Excepciones generales", ex1);

            ChartSeries n6xMonkeySerie = new ChartSeries();
            n6xMonkeySerie.setLabel("Galaxy 5s");
            n6xMonkeySerie.set("Errores generales", er2);
            n6xMonkeySerie.set("Crashes del apk", cr2);
            n6xMonkeySerie.set("Excepciones generales", ex2);

            barModelMonkey.addSeries(n5xMonkeySerie);
            barModelMonkey.addSeries(n6xMonkeySerie);

            barModelMonkey.setTitle("Reporte random testing");
            barModelMonkey.setLegendPosition("ne");

            Axis xAxisMonkey = barModelMonkey.getAxis(AxisType.X);
            xAxisMonkey.setLabel("");

            Axis yAxisMonkey = barModelMonkey.getAxis(AxisType.Y);
            yAxisMonkey.setLabel("Porcentaje");
            yAxisMonkey.setMin(0);
            yAxisMonkey.setMax(30);

            barModelCalabash = new BarChartModel();

            Integer er3 = 0;
            Integer cr3 = 0;
            Integer ex3 = 0;
            Integer er4 = 0;
            Integer cr4 = 0;
            Integer ex4 = 0;

            URL r3 = this.getClass().getResource("/calabash_" + IndexControlador.NEXUS_5X_ID + ".log");
            if (r3 != null) {
                File a3 = new File(r3.getPath());
                FileReader fr3 = new FileReader(a3);
                BufferedReader br3 = new BufferedReader(fr3);
                String linea3;
                while ((linea3 = br3.readLine()) != null) {
                    if (linea3.toLowerCase().contains("error")) {
                        er3++;
                    }

                    if (linea3.contains("Force removing") && linea3.toLowerCase().contains(ic.getPaquete()) && linea3.toLowerCase().contains("app died")) {
                        cr3++;
                    }

                    if (linea3.toLowerCase().contains("exception")) {
                        ex3++;
                    }
                }
                fr3.close();
                br3.close();

                InputStream stream = new FileInputStream(r3.getPath());
                fileCalabashNexus5x = new DefaultStreamedContent(stream, null, "fileCalabashNexus5x.log");
            }

            URL r4 = this.getClass().getResource("/calabash_" + IndexControlador.GALAXY_5X_ID + ".log");
            if (r4 != null) {
                File a4 = new File(r4.getPath());
                FileReader fr4 = new FileReader(a4);
                BufferedReader br4 = new BufferedReader(fr4);
                String linea4;
                while ((linea4 = br4.readLine()) != null) {
                    if (linea4.toLowerCase().contains("error")) {
                        er4++;
                    }

                    if (linea4.contains("Force removing") && linea4.toLowerCase().contains(ic.getPaquete()) && linea4.toLowerCase().contains("app died")) {
                        cr4++;
                    }

                    if (linea4.toLowerCase().contains("exception")) {
                        ex4++;
                    }
                }
                fr4.close();
                br4.close();

                InputStream stream = new FileInputStream(r4.getPath());
                fileCalabashGalaxy5s = new DefaultStreamedContent(stream, null, "fileCalabashGalaxy5s.log");
            }

            URL r5 = this.getClass().getResource("/calabash_feature_" + IndexControlador.NEXUS_5X_ID + ".log");
            if (r5 != null) {
                InputStream stream = new FileInputStream(r5.getPath());
                fileCalabashFeaturesNexus5x = new DefaultStreamedContent(stream, null, "fileCalabashFeaturesNexus5x.log");
            }

            URL r6 = this.getClass().getResource("/calabash_feature_" + IndexControlador.GALAXY_5X_ID + ".log");
            if (r6 != null) {
                InputStream stream = new FileInputStream(r6.getPath());
                fileCalabashFeaturesGalaxy5s = new DefaultStreamedContent(stream, null, "fileCalabashFeaturesGalaxy5s.log");
            }

            ChartSeries n5xCalabashSerie = new ChartSeries();
            n5xCalabashSerie.setLabel("Nexus 5x");
            n5xCalabashSerie.set("Errores generales", er3);
            n5xCalabashSerie.set("Crashes del apk", cr3);
            n5xCalabashSerie.set("Excepciones generales", ex3);

            ChartSeries n6xCalabashSerie = new ChartSeries();
            n6xCalabashSerie.setLabel("Galaxy 5s");
            n6xCalabashSerie.set("Errores generales", er4);
            n6xCalabashSerie.set("Crashes del apk", cr4);
            n6xCalabashSerie.set("Excepciones generales", ex4);

            barModelCalabash.addSeries(n5xCalabashSerie);
            barModelCalabash.addSeries(n6xCalabashSerie);

            barModelCalabash.setTitle("Reporte BDT testing");
            barModelCalabash.setLegendPosition("ne");

            Axis xAxisCalabash = barModelCalabash.getAxis(AxisType.X);
            xAxisCalabash.setLabel("");

            Axis yAxisCalabash = barModelCalabash.getAxis(AxisType.Y);
            yAxisCalabash.setLabel("Porcentaje");
            yAxisCalabash.setMin(0);
            yAxisCalabash.setMax(30);

            URL r7 = this.getClass().getResource("/scraper.log");
            File a7 = new File(r7.getPath());
            FileReader fr7 = new FileReader(a7);
            BufferedReader br7 = new BufferedReader(fr7);
            String linea7;
            scraperLog = "";
            while ((linea7 = br7.readLine()) != null) {
                scraperLog = scraperLog + linea7 + "\n";
            }
            fr7.close();
            br7.close();
            InputStream stream7 = new FileInputStream(r7.getPath());
            fileScraper = new DefaultStreamedContent(stream7, null, "fileScraper.log");

            URL r8 = this.getClass().getResource("/firebase.log");
            File a8 = new File(r8.getPath());
            FileReader fr8 = new FileReader(a8);
            BufferedReader br8 = new BufferedReader(fr8);
            String linea8;
            firebaseLog = "";
            while ((linea8 = br8.readLine()) != null) {
                firebaseLog = firebaseLog + linea8 + "\n";
            }
            fr8.close();
            br8.close();
            InputStream stream8 = new FileInputStream(r8.getPath());
            fileFireBase = new DefaultStreamedContent(stream8, null, "fileFireBase.log");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IndexControlador getIc() {
        return ic;
    }

    public void setIc(IndexControlador ic) {
        this.ic = ic;
    }

    public BarChartModel getBarModelMonkey() {
        return barModelMonkey;
    }

    public void setBarModelMonkey(BarChartModel barModelMonkey) {
        this.barModelMonkey = barModelMonkey;
    }

    public BarChartModel getBarModelCalabash() {
        return barModelCalabash;
    }

    public void setBarModelCalabash(BarChartModel barModelCalabash) {
        this.barModelCalabash = barModelCalabash;
    }

    public StreamedContent getFileMonkeyNexus5x() {
        return fileMonkeyNexus5x;
    }

    public void setFileMonkeyNexus5x(StreamedContent fileMonkeyNexus5x) {
        this.fileMonkeyNexus5x = fileMonkeyNexus5x;
    }

    public StreamedContent getFileMonkeyGalaxy5s() {
        return fileMonkeyGalaxy5s;
    }

    public void setFileMonkeyGalaxy5s(StreamedContent fileMonkeyGalaxy5s) {
        this.fileMonkeyGalaxy5s = fileMonkeyGalaxy5s;
    }

    public StreamedContent getFileCalabashNexus5x() {
        return fileCalabashNexus5x;
    }

    public void setFileCalabashNexus5x(StreamedContent fileCalabashNexus5x) {
        this.fileCalabashNexus5x = fileCalabashNexus5x;
    }

    public StreamedContent getFileCalabashGalaxy5s() {
        return fileCalabashGalaxy5s;
    }

    public void setFileCalabashGalaxy5s(StreamedContent fileCalabashGalaxy5s) {
        this.fileCalabashGalaxy5s = fileCalabashGalaxy5s;
    }

    public StreamedContent getFileCalabashFeaturesNexus5x() {
        return fileCalabashFeaturesNexus5x;
    }

    public void setFileCalabashFeaturesNexus5x(StreamedContent fileCalabashFeaturesNexus5x) {
        this.fileCalabashFeaturesNexus5x = fileCalabashFeaturesNexus5x;
    }

    public StreamedContent getFileCalabashFeaturesGalaxy5s() {
        return fileCalabashFeaturesGalaxy5s;
    }

    public void setFileCalabashFeaturesGalaxy5s(StreamedContent fileCalabashFeaturesGalaxy5s) {
        this.fileCalabashFeaturesGalaxy5s = fileCalabashFeaturesGalaxy5s;
    }

    public StreamedContent getFileScraper() {
        return fileScraper;
    }

    public void setFileScraper(StreamedContent fileScraper) {
        this.fileScraper = fileScraper;
    }

    public StreamedContent getFileFireBase() {
        return fileFireBase;
    }

    public void setFileFireBase(StreamedContent fileFireBase) {
        this.fileFireBase = fileFireBase;
    }

    public String getScraperLog() {
        return scraperLog;
    }

    public void setScraperLog(String scraperLog) {
        this.scraperLog = scraperLog;
    }

    public String getFirebaseLog() {
        return firebaseLog;
    }

    public void setFirebaseLog(String firebaseLog) {
        this.firebaseLog = firebaseLog;
    }   
}