package datalayer;

import model.Baseproduct;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFromFile {

    private String file= "src//products.csv";


    public HashSet<Baseproduct> readfile() throws IOException {
        HashSet<Baseproduct> baseproducts = new HashSet<>();
        Pattern atr = Pattern.compile("[,]");
        File f= new File(file);

       List<String> listofpro = (List<String>) Files.lines(Path.of(file)).collect(Collectors.toList());

       listofpro.remove(0);
       for(String str : listofpro)
       {
           String[] separator = str.split(String.valueOf(atr));
          Baseproduct prod = new Baseproduct(separator[0],
                                               Double.parseDouble(separator[1]),
                                               Double.parseDouble(separator[2]),
                                               Double.parseDouble(separator[3]),
                                               Double.parseDouble(separator[4]),
                                               Double.parseDouble(separator[5]),
                                              Float.parseFloat(separator[6]));

           baseproducts.add(prod);

       }

        return baseproducts;


    }
}
