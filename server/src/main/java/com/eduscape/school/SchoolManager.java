package com.eduscape.school;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SchoolManager {

    //private final PlayerData database;
    private final Map<Integer, District> districts;
    private final Map<Integer, School> schools;

    public SchoolManager() {
        //this.database = database;

        districts = new HashMap<>();
        schools = new HashMap<>();

        generate();
    }

    private void generate() {
        try {
            File file = new File("data", "district.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine(); // Ignore the first line
            String[] arr;
            while((line = br.readLine()) != null) {
                arr = line.split(",");

                int aun = Integer.parseInt(arr[0]);
                districts.put(aun, new District(aun, arr[1], arr[2]));
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            File file = new File("data", "school1.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine(); // Ignore the first line
            String[] arr;
            while((line = br.readLine()) != null) {
                arr = line.split(",");

                int aun = Integer.parseInt(arr[0]);
                int id = Integer.parseInt(arr[1]);
                schools.put(id, new School(id, aun, arr[2]));
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        System.out.println("Schools size: " + schools.size());

        try {
            File file = new File("data", "school.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine(); // Ignore the first line
            String[] arr;
            while((line = br.readLine()) != null) {
                arr = line.split(",");

                int aun = Integer.parseInt(arr[0]);
                int id = Integer.parseInt(arr[1]);
                schools.put(id, new School(id, aun, arr[2]));
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        System.out.println("Districts size: " + districts.size());
        System.out.println("Schools size: " + schools.size());
    }

}
