package com.eduscape;

import com.eduscape.district.District;
import com.eduscape.district.DistrictData;
import com.eduscape.district.DistrictDataRepository;
import com.eduscape.district.DistrictRepository;
import com.eduscape.keystone.Keystone;
import com.eduscape.keystone.KeystoneRepository;
import com.eduscape.keystone.KeystoneResults;
import com.eduscape.keystone.KeystoneResultsRepository;
import com.eduscape.school.School;
import com.eduscape.school.SchoolData;
import com.eduscape.school.SchoolDataRepository;
import com.eduscape.school.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/insert")
public class DataManager {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictDataRepository districtDataRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolDataRepository schoolDataRepository;

    @Autowired
    private KeystoneRepository keystoneRepository;

    @Autowired
    private KeystoneResultsRepository keystoneResultsRepository;

    @RequestMapping("/all")
    private ResponseEntity<String> populateTables() {
        loadInitialData();
        loadDistrictData();
        loadSchoolData();

        return new ResponseEntity<>("Done inserting\n", HttpStatus.OK);
    }

    private void loadInitialData() {
        try {
            File defaultFile = new File("src/main/resources/data", "district.csv");
            FileReader fr = new FileReader(defaultFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String[] arr;

            List<District> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                arr = line.split(",");

                int districtId = Integer.parseInt(arr[0]);
                String name = arr[1];
                String county = arr[2];

                list.add(new District(districtId, name, county, -1, -1, -1, -1, -1));
            }
            districtRepository.saveAll(list);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File defaultFile = new File("src/main/resources/data", "school.csv");
            FileReader fr = new FileReader(defaultFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String[] arr;

            List<School> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                arr = line.split(",");

                int schoolId = Integer.parseInt(arr[0]);
                int districtId = Integer.parseInt(arr[1]);
                String name = arr[2];

                list.add(new School(schoolId, districtId, name, -1, -1, -1));
            }
            schoolRepository.saveAll(list);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDistrictData() {
        try {
            File defaultFile = new File("src/main/resources/data", "district_data.csv");
            FileReader fr = new FileReader(defaultFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String[] arr;

            List<DistrictData> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                arr = line.split(",");

                int districtId = Integer.parseInt(arr[0]);
                short year = Short.parseShort(arr[1]);
                long expenditures = Long.parseLong(arr[2]);
                long revenue = Long.parseLong(arr[3]);
                int profPersonnel = Integer.parseInt(arr[4]);
                float experience = Float.parseFloat(arr[5]);
                int salary = Integer.parseInt(arr[6]);
                float degree = Float.parseFloat(arr[7]);
                int enrollment = Integer.parseInt(arr[8]);

                list.add(new DistrictData(
                        districtId,
                        year,
                        expenditures,
                        revenue,
                        profPersonnel,
                        experience,
                        salary,
                        degree,
                        enrollment,
                        -1, -1, -1, -1, -1));
            }
            districtDataRepository.saveAll(list);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSchoolData() {
        try {
            File defaultFile = new File("src/main/resources/data", "school_data.csv");
            FileReader fr = new FileReader(defaultFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String[] arr;

            List<SchoolData> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                arr = line.split(",");
                if (arr.length < 8) continue;

                int schoolId = Integer.parseInt(arr[0]);
                short year = Short.parseShort(arr[1]);
                int initialEnrollment = Integer.parseInt(arr[2]);
                int dropout_count = Integer.parseInt(arr[3]);
                int graduate_count = Integer.parseInt(arr[4]);
                int college_bound = Integer.parseInt(arr[5]);
                int total_enrollment = Integer.parseInt(arr[6]);
                int low_income_enrollment = Integer.parseInt(arr[7]);

                list.add(new SchoolData(
                        schoolId,
                        year,
                        initialEnrollment,
                        dropout_count,
                        graduate_count,
                        college_bound,
                        total_enrollment,
                        low_income_enrollment,
                        -1, -1, -1));
            }
            schoolDataRepository.saveAll(list);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File defaultFile = new File("src/main/resources/data", "keystone.csv");
            FileReader fr = new FileReader(defaultFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String[] arr;

            List<Keystone> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                arr = line.split(",");

                int schoolId = Integer.parseInt(arr[0]);
                short year = Short.parseShort(arr[1]);
                String subj = arr[2];
                String grp = arr[3];
                int total = Integer.parseInt(arr[4]);
                float i0 = Float.parseFloat(arr[5]);
                float i1 = Float.parseFloat(arr[6]);
                float i2 = Float.parseFloat(arr[7]);
                float i3 = Float.parseFloat(arr[8]);

                list.add(new Keystone(
                        schoolId,
                        year,
                        subj,
                        grp,
                        total,
                        i0,
                        i1,
                        i2,
                        i3
                ));
            }
            keystoneRepository.saveAll(list);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File defaultFile = new File("src/main/resources/data", "keystone_results.csv");
            FileReader fr = new FileReader(defaultFile);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String[] arr;

            List<KeystoneResults> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                arr = line.split(",");

                short year = Short.parseShort(arr[0]);
                String subj = arr[1];
                String grp = arr[2];
                int total = Integer.parseInt(arr[3]);
                float i0 = Float.parseFloat(arr[4]);
                float i1 = Float.parseFloat(arr[5]);
                float i2 = Float.parseFloat(arr[6]);
                float i3 = Float.parseFloat(arr[7]);

                list.add(new KeystoneResults(
                        year,
                        subj,
                        grp,
                        total,
                        i0,
                        i1,
                        i2,
                        i3
                ));
            }
            keystoneResultsRepository.saveAll(list);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/wai")
    private ResponseEntity<String> printLocation() {
        return new ResponseEntity<>(System.getProperty("user.dir") + "\n", HttpStatus.OK);
    }

}