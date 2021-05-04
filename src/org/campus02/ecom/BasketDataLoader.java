package org.campus02.ecom;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;



public class BasketDataLoader {
    public static void main(String[] args) {
        try {
            System.out.println(load("C:\\Users\\nikik\\Downloads\\StarterKitUE05052021\\data\\buyings.json"));

        } catch (DataFileException e) {
            e.printStackTrace();
        }

    }
    public static ArrayList<BasketData> load(String path) throws DataFileException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            ArrayList<BasketData> Basket = new ArrayList<>();
            String line;
            while ((line=br.readLine()) != null) {
                BasketData bd = new Gson().fromJson(line, BasketData.class);
                Basket.add(bd);
            }
            return Basket;

        } catch (FileNotFoundException e) {
            throw new DataFileException(e);
        } catch (IOException e) {
            throw new DataFileException(e);
        }
    }
    public static ArrayList<BasketData> load(String path, Comparator BasketComparator) throws DataFileException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            ArrayList<BasketData> Basket = new ArrayList<>();
            String line;
            while ((line=br.readLine()) != null) {
                BasketData bd = new Gson().fromJson(line, BasketData.class);
                Basket.add(bd);
            }
            Basket.sort(BasketComparator);
            return Basket;

        } catch (FileNotFoundException e) {
            throw new DataFileException(e);
        } catch (IOException e) {
            throw new DataFileException(e);
        }
    }

}
