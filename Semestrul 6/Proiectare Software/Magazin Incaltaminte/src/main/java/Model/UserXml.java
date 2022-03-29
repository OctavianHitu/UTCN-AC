package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public abstract class UserXml {

    public static Document getusersfromxml() {
        Document document = null;
        try {
            File docfile = new File("src/main/java/users.xml");
            DocumentBuilderFactory d = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = d.newDocumentBuilder();
            document =  db.parse(docfile);

        } catch (Exception ex) {
            ex.printStackTrace();
            document = null;

        }
        return document;
    }

    public static void saveusersxml(Document document)  {
        String path = "src/main/java/users.xml";


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

    public static List<User> readfromxml() {
        Document doc = getusersfromxml();
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("user");
        List<User> users = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node x = nodeList.item(i);
            if (x.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) x;
                String username = element.getElementsByTagName("username").item(0).getTextContent();
                String password = element.getElementsByTagName("password").item(0).getTextContent();
                String rol = element.getElementsByTagName("rol").item(0).getTextContent();
                User usr = new User(username, password, rol);
                users.add(usr);
            }
        }


        return users;
    }


    public static void addusertodoc(User user)  {

        Document document=getusersfromxml();
        Element users= document.getDocumentElement();
        Element usr= document.createElement("user");

        Element username=document.createElement("username");
        username.appendChild(document.createTextNode(user.getUsername()));
        usr.appendChild(username);

        Element password=document.createElement("password");
        password.appendChild(document.createTextNode(user.getPassword()));
        usr.appendChild(password);

        Element rol=document.createElement("rol");
        rol.appendChild(document.createTextNode(user.getRol()));
        usr.appendChild(rol);

        users.appendChild(usr);
        saveusersxml(document);
    }

    public static void deleteuserxml(User user)  {
        Document document= getusersfromxml();
        NodeList nodeList = document.getElementsByTagName("user");

        for (int i=0;i<nodeList.getLength();i++)
        {
            Element element= (Element) nodeList.item(i);
            if(element.getElementsByTagName("username").item(0).getTextContent().equals(user.getUsername()))
            {
                element.getParentNode().removeChild(element);
            }
        }
        saveusersxml(document);
    }
}