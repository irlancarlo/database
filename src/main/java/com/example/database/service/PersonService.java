package com.example.database.service;

import com.example.database.adapter.DozerAdapter;
import com.example.database.data.model.Person;
import com.example.database.data.vo.PersonVO;
import com.example.database.exception.ResourceNotFoundException;
import com.example.database.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonVO create(PersonVO personVO) {
        Person person = DozerAdapter.parseObject(personVO, Person.class);
        PersonVO vo = DozerAdapter.parseObject(personRepository.save(person), PersonVO.class);
        return vo;
    }

    public PersonVO update(PersonVO personVO) {
        Person person = DozerAdapter.parseObject(personVO, Person.class);

        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No register for ID."));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = DozerAdapter.parseObject(personRepository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVO findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No register for ID."));
        return DozerAdapter.parseObject(person, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        List<Person> all = personRepository.findAll();
        return DozerAdapter.parseListObject(all, PersonVO.class);
    }

    public void delete(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No register for ID"));
        personRepository.delete(person);
    }
}
