package com.eduscape;

import com.eduscape.district.District;
import com.eduscape.district.DistrictRepository;
import com.eduscape.school.School;
import com.eduscape.school.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Controller
@RequestMapping(path="/insert")
public class SchoolManager {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @RequestMapping("/all")
    private ResponseEntity<String> generate() {
        try {
            File file = new File("data", "district.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine(); // Ignore the first line
            String[] arr;
            while((line = br.readLine()) != null) {
                arr = line.split(",");

                int aun = Integer.parseInt(arr[0]);
                District district = new District(aun, arr[1], arr[2]);

                districtRepository.save(district);
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

                School school = new School(
                        id,
                        aun,
                        arr[2],
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0);

                schoolRepository.save(school);
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

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

                School school = new School(
                        id,
                        aun,
                        arr[2],
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0);

                schoolRepository.save(school);
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return new ResponseEntity<>("Done inserting\n", HttpStatus.OK);
    }

}