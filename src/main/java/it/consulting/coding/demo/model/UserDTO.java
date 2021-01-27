package it.consulting.coding.demo.model;

import lombok.*;

import java.util.Objects;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String lastname;
    private String function;
    private int experiences;
    private String address;
    private Long salary;
    private String birthday;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return experiences == userDTO.experiences &&
                Objects.equals(id, userDTO.id) &&
                Objects.equals(lastname, userDTO.lastname) &&
                Objects.equals(function, userDTO.function) &&
                Objects.equals(address, userDTO.address) &&
                Objects.equals(salary, userDTO.salary) &&
                Objects.equals(birthday, userDTO.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, function, experiences, address, salary, birthday);
    }
}
