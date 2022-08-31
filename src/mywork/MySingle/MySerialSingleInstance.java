package mywork.MySingle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * –Ú¡–ªØ¿‡
 *
 */
public class MySerialSingleInstance {
    
    public static void main(String[] args) {
        serialClassToFile();
    }
    
    public static void serialClassToFile() {
        SingleInstance instanceOne = SingleInstance.getInstance();
        
        SingleInstance instanceTwo = null;
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
            out.writeObject(instanceOne);
            out.close();
            
            ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
            instanceTwo = (SingleInstance) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("instanceOne hashCode="+instanceOne.hashCode());
        System.out.println("instanceTwo hashCode="+instanceTwo.hashCode());
        
    }
    
}
