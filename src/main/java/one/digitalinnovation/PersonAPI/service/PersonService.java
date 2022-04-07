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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO create(PersonDTO personDTO){
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person person = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(person);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return  personRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException(id));
    }
}
