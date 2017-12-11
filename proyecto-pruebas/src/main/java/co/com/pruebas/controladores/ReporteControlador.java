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
            
            URL r1 = this.getClass().getResource("/monkey.log");
            File a1 = new File(r1.getPath());
            FileReader fr1 = new FileReader(a1);
            BufferedReader br1 = new BufferedReader(fr1);
            String linea1;
            Integer er1 = 0;
            Integer cr1 = 0;
            Integer ex1 = 0;
            while ((linea1 = br1.readLine()) != null) {
                if(linea1.toLowerCase().contains("error")){
                    er1++;
                }
                
                if(linea1.toLowerCase().contains("crash")){
                    cr1++;
                }
                
                if(linea1.toLowerCase().contains("exception")){
                    ex1++;
                }
            }
            fr1.close();
            br1.close();
            
            ChartSeries n5xMonkeySerie = new ChartSeries();
            n5xMonkeySerie.setLabel("Nexus 5x");
            n5xMonkeySerie.set("Errores", er1);
            n5xMonkeySerie.set("Crashes", cr1);
            n5xMonkeySerie.set("Excepciones", ex1);

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

            URL r2 = this.getClass().getResource("/monkey.log");
            File a2 = new File(r2.getPath());
            FileReader fr2 = new FileReader(a2);
            BufferedReader br2 = new BufferedReader(fr2);
            String linea2;
            Integer er2 = 0;
            Integer cr2 = 0;
            Integer ex2 = 0;
            while ((linea2 = br2.readLine()) != null) {
                if(linea2.toLowerCase().contains("error")){
                    er2++;
                }
                
                if(linea2.toLowerCase().contains("crash")){
                    cr2++;
                }
                
                if(linea2.toLowerCase().contains("exception")){
                    ex2++;
                }
            }
            fr2.close();
            br2.close();
            
            ChartSeries n5xCalabashSerie = new ChartSeries();
            n5xCalabashSerie.setLabel("Nexus 5x");
            n5xCalabashSerie.set("Errores", er2);
            n5xCalabashSerie.set("Crashes", cr2);
            n5xCalabashSerie.set("Excepciones", ex2);

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

            URL r3 = this.getClass().getResource("/ripper.log");
            File a3 = new File(r3.getPath());
            FileReader fr3 = new FileReader(a3);
            BufferedReader br3 = new BufferedReader(fr3);
            String linea3;
            ripperLog = "";
            while ((linea3 = br3.readLine()) != null) {
                ripperLog = ripperLog + linea3 + "\n";
            }
            fr3.close();
            br3.close();
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
