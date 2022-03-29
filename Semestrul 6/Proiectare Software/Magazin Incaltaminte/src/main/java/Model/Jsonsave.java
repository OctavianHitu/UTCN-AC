package Model;

import java.io.FileWriter;
import  java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface Jsonsave {

    String pathfile="src/main/java/Incaltaminte.json";

    default void savejson(List<IncaltainteMagazin> incaltainteMagazins) throws IOException {
        FileWriter newfile = new FileWriter(pathfile);
        JSONObject object;

        for (IncaltainteMagazin inc :incaltainteMagazins)
        {
            object= new JSONObject();
            object.put("Nume Magazin",inc.getNumemagazin());
            for (Incaltaminte in: inc.getIncaltamintes())
            {
                JSONArray papuc= new JSONArray();
                papuc.add("Nume:" +in.getNume());
                papuc.add("Producator:" +in.getProducator());
                papuc.add("Pret:" +in.getPret());
                papuc.add("Exemplare Vandute:" +in.getIncaltaminteDisponibila().getGetNumarexemplarevandute());
                papuc.add("Exemplare Initiale:" +in.getIncaltaminteDisponibila().getNumarexemplareinitiale());
                papuc.add("Marime:" +in.getIncaltaminteDisponibila().getMarime());
                object.put("Atribute:", papuc);
                newfile.write(object.toJSONString());


            }
        }
        newfile.flush();
        newfile.close();
    }

}
