package one.digitalinnovation.PersonAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long id;

    private String firstName;

    private String lastName;

    private String cpf;

    private LocalDateTime birthDate;

    private List<Phone> phones;
}
