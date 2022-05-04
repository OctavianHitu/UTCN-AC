package ViewModel;

import Model.PersistentaIncaltaminte;
import ViewModel.CommandAngajat.*;
import lombok.Getter;
import lombok.Setter;
import net.sds.mvvm.properties.Property;
import net.sds.mvvm.properties.PropertyFactory;
@Getter
@Setter
public class VMAngajat {

    private Property<String> numemagazin = PropertyFactory.createProperty("magazincrudtf", this, String.class);
    private Property<String> nume = PropertyFactory.createProperty("numecrudtf", this, String.class);
    private Property<String> producator = PropertyFactory.createProperty("producatorcrudtf", this, String.class);
    private Property<String> pret = PropertyFactory.createProperty("pretcrudtf", this, String.class);
    private Property<String> numarexemplarevandute = PropertyFactory.createProperty("exvandutecrudtf", this, String.class);
    private Property<String> numarexemplareinitiale = PropertyFactory.createProperty("exinitialecrudtf", this, String.class);
    private Property<String> marime = PropertyFactory.createProperty("marimecrudtf", this, String.class);


    private Property<String> numemagazinu = PropertyFactory.createProperty("magazinsrctf", this, String.class);
    private Property<String> numeu = PropertyFactory.createProperty("numesrctf", this, String.class);
    private Property<String> producatoru = PropertyFactory.createProperty("producatorsrctf", this, String.class);
    private Property<String> pretu = PropertyFactory.createProperty("pretsrctf", this, String.class);
    private Property<String> numarexemplarevanduteu = PropertyFactory.createProperty("exvandutesrctf", this, String.class);
    private Property<String> numarexemplareinitialeu = PropertyFactory.createProperty("exinitialesrctf", this, String.class);
    private Property<String> marimeu = PropertyFactory.createProperty("marimesrctf", this, String.class);

    private PersistentaIncaltaminte persistentaIncaltaminte;
    private String data=null;


    public ICommand afisarecommand;
    public ICommand addcommand;
    public ICommand deletecommand;
    public ICommand updatecommand;
    public ICommand magazincommand;
    public ICommand producatorcommand;
    public ICommand numecommand;
    public  ICommand disponibilitatecommand;
    public  ICommand savecommand;
    public ICommand pretcommand;
    public  ICommand chartcommand;
    public VMAngajat()
    {
        this.persistentaIncaltaminte= new PersistentaIncaltaminte();
        this.afisarecommand= new CmdAfisare(this,persistentaIncaltaminte);
        this.addcommand=  new CmdAdd(this,persistentaIncaltaminte);
        this.deletecommand= new CmdDelete(this,persistentaIncaltaminte);
        this.updatecommand = new CmdUpdate(this,persistentaIncaltaminte);
        this.magazincommand= new CmdMagazin(this,persistentaIncaltaminte);
        this.producatorcommand = new CmdProducator(this,persistentaIncaltaminte);
        this.numecommand = new CmdNume(this,persistentaIncaltaminte);
        this.disponibilitatecommand= new CmdDisponibilitate(this,persistentaIncaltaminte);
        this.savecommand= new CmdSaveFile(persistentaIncaltaminte);
        this.pretcommand = new CmdPret(this,persistentaIncaltaminte);
        this.chartcommand= new CmdChart(persistentaIncaltaminte);

    }

}
