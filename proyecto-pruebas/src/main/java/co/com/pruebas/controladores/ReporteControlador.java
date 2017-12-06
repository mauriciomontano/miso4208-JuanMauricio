package co.com.pruebas.controladores;

import java.io.Serializable;
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
public class ReporteControlador implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private BarChartModel barModel;
    
    @PostConstruct
    public void postConstruct() {
        generarReporte();
    }
    
    private void generarReporte() {
        if (true) {
            barModel = new BarChartModel();

            ChartSeries boys = new ChartSeries();
            boys.setLabel("Pruebas exitosas");
            boys.set("Monkey 1", 120);
            boys.set("Monkey 2", 100);
            boys.set("Monkey 3", 44);
            boys.set("Calabash 1", 150);
            boys.set("Calabash 2", 25);

            ChartSeries girls = new ChartSeries();
            girls.setLabel("Pruebas fallidas");
            girls.set("Monkey 1", 52);
            girls.set("Monkey 2", 60);
            girls.set("Monkey 3", 110);
            girls.set("Calabash 1", 135);
            girls.set("Calabash 2", 120);

            barModel.addSeries(boys);
            barModel.addSeries(girls);

            barModel.setTitle("Reporte de pruebas");
            barModel.setLegendPosition("ne");

            Axis xAxis = barModel.getAxis(AxisType.X);
            xAxis.setLabel("Prueba realizada");

            Axis yAxis = barModel.getAxis(AxisType.Y);
            yAxis.setLabel("Porcentaje");
            yAxis.setMin(0);
            yAxis.setMax(200);
        }
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
}
