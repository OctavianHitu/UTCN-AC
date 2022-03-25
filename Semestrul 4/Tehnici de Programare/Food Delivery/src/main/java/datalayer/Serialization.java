package datalayer;

import java.io.*;

public class Serialization {

    public void serializate(String file,Object object) throws IOException {
        FileOutputStream fileout = new FileOutputStream(file);
        ObjectOutputStream objout = new ObjectOutputStream(fileout);

        try
        {
            objout.writeObject(object);
            objout.flush();
            objout.close();
            fileout.close();

        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }


    public Object deserializate(String file) throws IOException {
        FileInputStream filein = new FileInputStream(file);
        ObjectInputStream obj = new ObjectInputStream(filein);
        Object object1=null;
        try
        {
             object1 = obj.readObject();
            filein.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object1;

    }
}
