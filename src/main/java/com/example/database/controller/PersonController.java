package com.example.database.controller;

import com.example.database.data.model.Person;
import com.example.database.data.vo.PersonVO;
import com.example.database.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PersonVO person) {
        PersonVO entity = personService.create(person);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonVO> findById(@PathVariable("id") Long id){
        PersonVO person = personService.findById(id);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<List<PersonVO>> findAll(){
        List<PersonVO> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PersonVO person) {
        PersonVO entity = personService.update(person);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }


}
