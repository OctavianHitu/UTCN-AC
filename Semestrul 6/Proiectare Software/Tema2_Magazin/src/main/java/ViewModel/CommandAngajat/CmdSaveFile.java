package ViewModel.CommandAngajat;

import Model.PersistentaIncaltaminte;
import ViewModel.ICommand;
import ViewModel.VMAngajat;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CmdSaveFile implements ICommand {


    private PersistentaIncaltaminte persistentaIncaltaminte;

    public CmdSaveFile( PersistentaIncaltaminte pe) {
        this.persistentaIncaltaminte = pe;

    }

    @Override
    public void  Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
            persistentaIncaltaminte.savejson(persistentaIncaltaminte.getIncaltaminteMagazins());
            persistentaIncaltaminte.savecsv(persistentaIncaltaminte.getIncaltaminteMagazins());
            persistentaIncaltaminte.saveasxml(persistentaIncaltaminte.getIncaltaminteMagazins());
    }

}
