package View;
import Model.Incaltaminte;
import Model.IncaltaminteMagazin;
import Model.PersistentaIncaltaminte;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
public class Bar extends JFrame {
    private PersistentaIncaltaminte persistentaIncaltaminte;

    public Bar(PersistentaIncaltaminte pe)
    {
        this.persistentaIncaltaminte=pe;
        initial();
    }


    private CategoryDataset createdata()
    {
        var data = new DefaultCategoryDataset();

        for(IncaltaminteMagazin i: persistentaIncaltaminte.getIncaltaminteMagazins())
        {
            for(Incaltaminte ii:i.getIncaltamintes())
            {
                data.setValue(ii.getPret(),ii.getProducator(),ii.getNume()+" / "+ i.getNumemagazin());
            }
        }

        return data;

    }

    private  JFreeChart createChart(CategoryDataset data)
    {
        JFreeChart barch = ChartFactory.createBarChart("Pret Incaltaminte"," ","Pret",data,PlotOrientation.VERTICAL,true,true,true);
        return  barch;
    }


    private void initial()
    {
        CategoryDataset data = createdata();

        JFreeChart ch = createChart(data);
        ChartPanel chr = new ChartPanel(ch);
        chr.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chr.setBackground(Color.white);
        add(chr);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);

    }




}
