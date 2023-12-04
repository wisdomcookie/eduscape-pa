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
}
