package Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class IncaltaminteXml {


    public static Document getincaltamintefromxml()
    {
        Document document=null;
        try{
            File file= new File("src/main/java/Incaltaminte.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document= (Document) db.parse(file);

        }catch (Exception e)
        {
            e.printStackTrace();
            document=null;
        }

        return document;
    }

    public  static List<IncaltainteMagazin> readfromxml()
    {
        Document document=getincaltamintefromxml();
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("incaltaminte");
        List<IncaltainteMagazin> incaltainteMagazins = new ArrayList<>();

        for(int i=0;i<nodeList.getLength();i++)
        {
            Node node= nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                String numemagazin=element.getElementsByTagName("numemagazin").item(0).getTextContent();
                String nume=element.getElementsByTagName("nume").item(0).getTextContent();
                String producator=element.getElementsByTagName("producator").item(0).getTextContent();
                String pret=element.getElementsByTagName("pret").item(0).getTextContent();
                String numarexemplareinitiale=element.getElementsByTagName("numarexemplareinitiale").item(0).getTextContent();
                String numarexemplarevandute=element.getElementsByTagName("numarexemplarevandute").item(0).getTextContent();
                String marime=element.getElementsByTagName("marime").item(0).getTextContent();

                IncaltaminteDisponibila ids= new IncaltaminteDisponibila(Integer.parseInt(marime),Integer.parseInt(numarexemplareinitiale),Integer.parseInt(numarexemplarevandute));
                Incaltaminte incaltaminte = new Incaltaminte(nume,producator,Integer.parseInt(pret),ids);
                IncaltaminteDisponibila id = new IncaltaminteDisponibila(Integer.parseInt(marime),Integer.parseInt(numarexemplareinitiale),Integer.parseInt(numarexemplarevandute));

                boolean found= false;

                int index= -1;
                for (IncaltainteMagazin im:incaltainteMagazins)
                {
                    if(im.getNumemagazin().equals(numemagazin))
                    {
                        found=true;
                        index=incaltainteMagazins.indexOf(im);
                    }
                }
                if(!found)
                {
                    IncaltainteMagazin incaltainteMagazin= new IncaltainteMagazin(numemagazin);
                    incaltainteMagazin.addincaltaminteinmagazin(incaltaminte);
                    incaltainteMagazins.add(incaltainteMagazin);
                }else
                {
                    incaltainteMagazins.get(index).addincaltaminteinmagazin(incaltaminte);
                }

            }

        }

        return incaltainteMagazins;
    }

    public static void saveincaltamintexml(Document document)
    {
        String path ="src/main/java/Incaltaminte.xml";

        try {
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "yes");
            DOMSource ds = new DOMSource(document);
            StreamResult sr = new StreamResult(path);
            tf.transform(ds, sr);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static boolean addincaltamintetodoc(IncaltainteMagazin mag)
    {
        Document document = getincaltamintefromxml();
        Element inncals= document.getDocumentElement();
        Element inca = document.createElement("incaltaminte");

        Element numemagazin=document.createElement("numemagazin");
        numemagazin.appendChild(document.createTextNode(mag.getNumemagazin()));
        inca.appendChild(numemagazin);

        List<Incaltaminte> incaltamintes = mag.getIncaltamintes();
        //for(Incaltaminte in:incaltamintes)
        //{
            Element nume = document.createElement("nume");
            nume.appendChild(document.createTextNode(mag.getIncaltamintes().get(0).getNume()));
            inca.appendChild(nume);

            Element producator = document.createElement("producator");
            producator.appendChild(document.createTextNode(mag.getIncaltamintes().get(0).getProducator()));
            inca.appendChild(producator);

            Element pret = document.createElement("pret");
            pret.appendChild(document.createTextNode(mag.getIncaltamintes().get(0).getPret().toString()));
            inca.appendChild(pret);

            Element numarexemplareinitiale = document.createElement("numarexemplareinitiale");
            numarexemplareinitiale.appendChild(document.createTextNode(mag.getIncaltamintes().get(0).getIncaltaminteDisponibila().getNumarexemplareinitiale().toString()));
            inca.appendChild(numarexemplareinitiale);

            Element numarexemplarevandute = document.createElement("numarexemplarevandute");
            numarexemplarevandute.appendChild(document.createTextNode(mag.getIncaltamintes().get(0).getIncaltaminteDisponibila().getGetNumarexemplarevandute().toString()));
            inca.appendChild(numarexemplarevandute);

            Element marime = document.createElement("marime");
            marime.appendChild(document.createTextNode(mag.getIncaltamintes().get(0).getIncaltaminteDisponibila().getMarime().toString()));
            inca.appendChild(marime);
        //}
         inncals.appendChild(inca);
         saveincaltamintexml(document);

        return false;
    }

    public static void deleteincaltamintexml(IncaltainteMagazin mag)
    {
        Document document= getincaltamintefromxml();
        NodeList nodeList = document.getElementsByTagName("incaltaminte");


        for(int i=0;i<nodeList.getLength();i++)
        {
            Element element= (Element) nodeList.item(i);


            if(element.getElementsByTagName("numemagazin").item(0).getTextContent().equals(mag.getNumemagazin())
                        && element.getElementsByTagName("nume").item(0).getTextContent().equals(mag.getIncaltamintes().get(0).getNume())&&
                        element.getElementsByTagName("producator").item(0).getTextContent().equals(mag.getIncaltamintes().get(0).getProducator())&&
                        element.getElementsByTagName("marime").item(0).getTextContent().equals(mag.getIncaltamintes().get(0).getIncaltaminteDisponibila().getMarime().toString()))
            {
                    element.getParentNode().removeChild(element);
                }



        }

        saveincaltamintexml(document);
    }

}
