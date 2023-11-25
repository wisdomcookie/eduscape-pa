package com.eduscape.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/school_data")
public class SchoolDataController {

    @Autowired
    private SchoolDataRepository schoolDataRepository;

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<SchoolData>> getAll() {
        return new ResponseEntity<>(schoolDataRepository.findAll(), HttpStatus.OK);
    }
}
