package ViewModel;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ICommand {
    void Execute() throws IOException, ParserConfigurationException, TransformerException, SAXException;
}
