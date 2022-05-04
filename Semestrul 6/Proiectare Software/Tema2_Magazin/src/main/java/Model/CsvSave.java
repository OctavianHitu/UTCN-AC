package Model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public interface CsvSave {

    String pathfile="src/main/java/Incaltaminte.csv";
    default void  savecsv(List<IncaltaminteMagazin> Incaltamintemag) throws FileNotFoundException {
        PrintWriter printWriter= new PrintWriter(pathfile);
        StringBuilder stbd = new StringBuilder();


        for (IncaltaminteMagazin inc:Incaltamintemag)
        {
            stbd.append("Nume Magazin:");
            stbd.append(inc.getNumemagazin());
            stbd.append(",");
            for(Incaltaminte in: inc.getIncaltamintes())
            {
                stbd.append("Nume :");
                stbd.append(in.getNume());
                stbd.append(",");
                stbd.append("Producator:");
                stbd.append(in.getProducator());
                stbd.append(",");
                stbd.append("Pret:");
                stbd.append(in.getPret());
                stbd.append(",");
                stbd.append("Exemplare Vandute:");
                stbd.append(in.getIncaltaminteDisponibila().getNumarexemplarevandute());
                stbd.append(",");
                stbd.append("Exemplare Initiale:");
                stbd.append(in.getIncaltaminteDisponibila().getNumarexemplareinitiale());
                stbd.append(",");
                stbd.append("Marime:");
                stbd.append(in.getIncaltaminteDisponibila().getMarime());
                stbd.append(",");
            }
        }
        printWriter.write(stbd.toString());
        printWriter.close();
    }
}
