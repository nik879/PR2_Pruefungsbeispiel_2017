package org.campus02.ecom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BasketServerST {
    static int counter=1;
    public static void main(String[] args) {



        try (ServerSocket ss = new ServerSocket(1111);) {

            ArrayList<EcommerceLogic> ecommerceLogics = new ArrayList<>();

            while (true) {
                Socket s = ss.accept();

                System.out.println("Client " + counter + " is connected");
                EcommerceLogic el = new EcommerceLogic(s);

                ecommerceLogics.add(el);
                counter++;
                Thread t1 = new Thread(el);
                t1.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
