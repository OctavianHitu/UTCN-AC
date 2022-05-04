package View;

import Model.Incaltaminte;
import Model.IncaltaminteMagazin;
import Model.PersistentaIncaltaminte;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;


public class Pie extends JFrame {
    private PersistentaIncaltaminte persistentaIncaltaminte;


    public Pie(PersistentaIncaltaminte pe) {
        this.persistentaIncaltaminte = pe;
        initial();
    }


    public DefaultPieDataset creatdata() {
        var data = new DefaultPieDataset();
        for (IncaltaminteMagazin i : persistentaIncaltaminte.getIncaltaminteMagazins())
        {
            double exvandute = 0;
            double exinitiale=0;
            double disp=0;
            for (Incaltaminte ii : i.getIncaltamintes())
            {
                exvandute+=ii.getIncaltaminteDisponibila().getNumarexemplarevandute();
                exinitiale+= ii.getIncaltaminteDisponibila().getNumarexemplareinitiale();
                disp = exinitiale-exvandute;

            }
            data.setValue(i.getNumemagazin(),disp);

        }


        return  data;
    }


    private JFreeChart crearechr(DefaultPieDataset data)
    {
        JFreeChart pie = ChartFactory.createPieChart("Disponibilitate",data,true,true,true);
        return  pie;
    }

    private void  initial()
    {
        DefaultPieDataset data = creatdata();
        JFreeChart chr = crearechr(data);
        ChartPanel chpn = new ChartPanel(chr);
        chpn.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chpn.setBackground(Color.white);
        add(chpn);

        pack();
        setTitle("Disponibilitate");
        setLocationRelativeTo(null);

    }

}





