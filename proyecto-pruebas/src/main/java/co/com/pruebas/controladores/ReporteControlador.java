package co.com.pruebas.controladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.net.URL;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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

    private BarChartModel barModelMonkey;
    private BarChartModel barModelCalabash;
    private String ripperLog;

    @PostConstruct
    public void postConstruct() {
        generarReporte();
    }

    private void generarReporte() {
        try {

            barModelMonkey = new BarChartModel();

            ChartSeries n5xMonkeySerie = new ChartSeries();
            n5xMonkeySerie.setLabel("Nexus 5x");
            n5xMonkeySerie.set("Errores", 120);
            n5xMonkeySerie.set("Crashes", 100);
            n5xMonkeySerie.set("Excepciones", 44);

            ChartSeries n6xMonkeySerie = new ChartSeries();
            n6xMonkeySerie.setLabel("Nexus 6x");
            n6xMonkeySerie.set("Errores", 52);
            n6xMonkeySerie.set("Crashes", 60);
            n6xMonkeySerie.set("Excepciones", 110);

            barModelMonkey.addSeries(n5xMonkeySerie);
            barModelMonkey.addSeries(n6xMonkeySerie);

            barModelMonkey.setTitle("Reporte random testing");
            barModelMonkey.setLegendPosition("ne");

            Axis xAxisMonkey = barModelMonkey.getAxis(AxisType.X);
            xAxisMonkey.setLabel("");

            Axis yAxisMonkey = barModelMonkey.getAxis(AxisType.Y);
            yAxisMonkey.setLabel("Porcentaje");
            yAxisMonkey.setMin(0);
            yAxisMonkey.setMax(200);

            barModelCalabash = new BarChartModel();

            ChartSeries n5xCalabashSerie = new ChartSeries();
            n5xCalabashSerie.setLabel("Nexus 5x");
            n5xCalabashSerie.set("Errores", 120);
            n5xCalabashSerie.set("Crashes", 100);
            n5xCalabashSerie.set("Excepciones", 44);

            ChartSeries n6xCalabashSerie = new ChartSeries();
            n6xCalabashSerie.setLabel("Nexus 6x");
            n6xCalabashSerie.set("Errores", 52);
            n6xCalabashSerie.set("Crashes", 60);
            n6xCalabashSerie.set("Excepciones", 110);

            barModelCalabash.addSeries(n5xCalabashSerie);
            barModelCalabash.addSeries(n6xCalabashSerie);

            barModelCalabash.setTitle("Reporte BDT testing");
            barModelCalabash.setLegendPosition("ne");

            Axis xAxisCalabash = barModelCalabash.getAxis(AxisType.X);
            xAxisCalabash.setLabel("");

            Axis yAxisCalabash = barModelCalabash.getAxis(AxisType.Y);
            yAxisCalabash.setLabel("Porcentaje");
            yAxisCalabash.setMin(0);
            yAxisCalabash.setMax(200);

            URL resource = this.getClass().getResource("/ripper.log");
            File archivo = new File(resource.getPath());
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            ripperLog = "";
            while ((linea = br.readLine()) != null) {
                ripperLog = ripperLog + linea + "\n";
            }
            fr.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public String getRipperLog() {
        return ripperLog;
    }

    public void setRipperLog(String ripperLog) {
        this.ripperLog = ripperLog;
    }
}
