package Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public interface XmlSave {

    String pathfile="src/main/java/Incaltaminte.xml";

    private Document getdocelem() {
        Document doc = null;
        try {

            File file = new File(pathfile);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(file);

        } catch (Exception e) {
            e.printStackTrace();
            doc = null;
        }
        return doc;
    }

    private void savexml(Document document) throws TransformerException, TransformerException {
        String pathfile="src/main/java/Incaltaminte.xml";

        TransformerFactory tff = TransformerFactory.newInstance();
        Transformer tf = tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource ds = new DOMSource( document);
        StreamResult sr = new StreamResult(pathfile);
        tf.transform(ds, sr);
    }

    private void addtoxml(List<IncaltaminteMagazin> incaltaminteMagazins) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = getdocelem();
        Element incal = document.getDocumentElement();

        List<IncaltaminteMagazin> list= new ArrayList<>();

        for(IncaltaminteMagazin i: incaltaminteMagazins)
        {
            for(Incaltaminte ii: i.getIncaltamintes())
            {
                IncaltaminteMagazin mag= new IncaltaminteMagazin(i.getNumemagazin());
                mag.addincaltaminteinmagazin(ii);
                list.add(mag);
            }
        }

        for(IncaltaminteMagazin in: incaltaminteMagazins)
        {
            Element incaltaminte= document.createElement("incaltaminte");
            Element numemagazin = document.createElement("numemagazin");
            numemagazin.appendChild(document.createTextNode(in.getNumemagazin()));
            incaltaminte.appendChild(numemagazin);

            for(Incaltaminte inc: in.getIncaltamintes())
            {
               Element nume= document.createElement("nume");
               nume.appendChild(document.createTextNode(inc.getNume()));
               incaltaminte.appendChild(nume);

                Element producator= document.createElement("producator");
                nume.appendChild(document.createTextNode(inc.getProducator()));
                incaltaminte.appendChild(producator);

                Element pret= document.createElement("pret");
                nume.appendChild(document.createTextNode(inc.getPret().toString()));
                incaltaminte.appendChild(pret);

                Element marime= document.createElement("marime");
                nume.appendChild(document.createTextNode(inc.getIncaltaminteDisponibila().getMarime().toString()));
                incaltaminte.appendChild(marime);

                Element exinitiale= document.createElement("exinitiale");
                nume.appendChild(document.createTextNode(inc.getIncaltaminteDisponibila().getNumarexemplareinitiale().toString()));
                incaltaminte.appendChild(exinitiale);

                Element exvandute= document.createElement("exvandute");
                nume.appendChild(document.createTextNode(inc.getIncaltaminteDisponibila().getNumarexemplarevandute().toString()));
                incaltaminte.appendChild(exvandute);


            }

            incal.appendChild(incaltaminte);
        }

        savexml(document);

    }

    default void saveasxml(List<IncaltaminteMagazin>  incaltaminteMagazins) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        Document document= getdocelem();
        NodeList nodeList = document.getElementsByTagName("incaltaminte");

        for(int i=0;i<nodeList.getLength();i++)
        {
            Element element = (Element) nodeList.item(i);
            element.getParentNode().removeChild(element);
        }
        savexml(document);

        addtoxml(incaltaminteMagazins);
    }

}
