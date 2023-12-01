package com.eduscape.district;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(path="/districts")
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictDataRepository districtDataRepository;

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<District>> getAll() {
        return new ResponseEntity<>(districtRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path="/allNames")
    public @ResponseBody ResponseEntity<Iterable<String>> getNames() {
        List<String> names = new ArrayList<>();
        for (District d : districtRepository.findAll()) {
            names.add(d.getName());
        }

        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @GetMapping(path="/csv")
    public @ResponseBody ResponseEntity<String> toCSV() throws IOException {
        HashMap<Pair<Integer, Short>, DistrictData> map = new HashMap<>();
        for (DistrictData districtData : districtDataRepository.findAll()) {
            map.put(new Pair<>(districtData.getDistrictID(), districtData.getYear()), districtData);
        }

        loadEnrollmentData(map);

        File file = new File("blah.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            ArrayList<DistrictData> arr = new ArrayList<>(map.values());
            Collections.sort(arr);

            for (DistrictData districtData : arr) {
                fileWriter.write(districtData.toCSV());
            }

            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Dumped CSV data to blah.txt", HttpStatus.OK);
    }

    private void loadEnrollmentData(HashMap<Pair<Integer, Short>, DistrictData> map) throws FileNotFoundException {
        File file = new File("src/main/resources/data", "district_enrollment_data.csv");
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] arr = input.split(",");
            int aun = Integer.parseInt(arr[0]);
            short year = Short.parseShort(arr[1]);
            int enrollment = Integer.parseInt(arr[2]);

            Pair<Integer, Short> pair = new Pair<>(aun, year);
            if (map.containsKey(pair)) {
                map.get(pair).setEnrollment(enrollment);
            }
        }

        sc.close();
    }
}
