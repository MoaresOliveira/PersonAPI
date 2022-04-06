package one.digitalinnovation.PersonAPI.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import one.digitalinnovation.PersonAPI.dto.request.PersonDTO;
import one.digitalinnovation.PersonAPI.dto.request.UpdatePersonDTO;
import one.digitalinnovation.PersonAPI.dto.response.MessageResponseDTO;
import one.digitalinnovation.PersonAPI.exception.PersonNotFoundException;
import one.digitalinnovation.PersonAPI.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/person")
@Tag(name = "People")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a person")
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.create(personDTO);
    }

    @GetMapping
    @Operation(summary = "List all people in the database")
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search for a person by ID")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a person")
    public MessageResponseDTO updateById(@PathVariable Long id,@RequestBody @Valid UpdatePersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a person by ID")
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
