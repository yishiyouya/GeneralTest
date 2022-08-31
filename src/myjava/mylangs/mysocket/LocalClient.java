package myjava.mylangs.mysocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class LocalClient {
    
    private static String host = "127.0.0.1";
    private static int port = 9999;
    private static String endFlag = "bye";
    
    
    public static void main(String[] args){
        LocalClient localClient = new LocalClient();
        localClient.startClient();
    }
    
    public void startClient(){
        Socket socket = null;
        BufferedReader inputReader = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        String inContent = "";
        try {
            socket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            inputReader = new BufferedReader(new InputStreamReader(System.in));
            writer.write("Ω”»Î" + "\n");
            while(!(inContent = inputReader.readLine()).equals(endFlag)){
                writer.write(inContent + "\n");
                writer.flush();
                String response = reader.readLine();
                System.out.println(response);
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
                inputReader.close();
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}



























