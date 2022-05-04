package ViewModel.CommandAngajat;

import Model.PersistentaIncaltaminte;
import View.Bar;
import View.Pie;
import ViewModel.ICommand;

public class CmdChart implements ICommand {

    private PersistentaIncaltaminte persistentaIncaltaminte;

    public CmdChart(PersistentaIncaltaminte pe)
    {
        this.persistentaIncaltaminte=pe;
    }

    @Override
    public void Execute()
    {
        var pieChart = new Pie(persistentaIncaltaminte);
        pieChart.setVisible(true);

        var barChart = new Bar(persistentaIncaltaminte);
        barChart.setVisible(true);

    }
}
