package one.digitalinnovation.PersonAPI.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.PersonAPI.dto.mapper.PersonMapper;
import one.digitalinnovation.PersonAPI.dto.request.PersonDTO;
import one.digitalinnovation.PersonAPI.dto.response.MessageResponseDTO;
import one.digitalinnovation.PersonAPI.entity.Person;
import one.digitalinnovation.PersonAPI.exception.PersonNotFoundException;
import one.digitalinnovation.PersonAPI.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO create(PersonDTO personDTO){
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException(id);
        }
        return personMapper.toDTO(optionalPerson.get());
    }
}