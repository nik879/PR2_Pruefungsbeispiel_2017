package org.campus02.ecom;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EcommerceLogic implements Runnable {
    private Socket socket;

    public EcommerceLogic(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            BasketAnalyzer ba = null;
            String incommincmd;
            double avgsum=0;
            double counter=0;
            HashMap<String, ArrayList<Double>> analyzer = new HashMap<>();
            ArrayList<Double> avg;
            ArrayList<BasketData> data = new ArrayList<>();
            while ((incommincmd = br.readLine()) != null) {

                String[] parts = incommincmd.split(" ");
                if (parts[0].equals("OpenFile")) {
                    ba = new BasketAnalyzer(BasketDataLoader.load(parts[1]));
                    bw.write("basket data loaded with" +ba.getBaskets().size());
                    bw.newLine();
                    bw.flush();
                }
                if (parts[0].equals("getEveryNthBasket")) {
                    data = ba.getEveryNthBasket(Integer.valueOf(parts[1]));
                    for (BasketData datum : data) {
                        bw.write(String.valueOf(datum));
                    }

                    bw.newLine();
                    bw.flush();
                }
                if (parts[0].equals("GetStats")) {
                    analyzer= ba.groupByProductCategory();
                    for (Map.Entry<String, ArrayList<Double>> stringArrayListEntry : analyzer.entrySet()) {
                        avg = stringArrayListEntry.getValue();
                        for (Double aDouble : avg) {
                            avgsum += aDouble;
                        }
                        counter = (double) avg.size();
                        bw.write("groupProduct by Category" + stringArrayListEntry.getKey() + "-" + avgsum / counter);
                        bw.newLine();
                        bw.flush();
                    }
                }
                if (parts[0].equals("exit")) {
                    throw new DataFileException("Connection refused");
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFileException e) {
            e.printStackTrace();
        }

    }
}
