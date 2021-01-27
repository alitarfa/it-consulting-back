package it.consulting.coding.demo.model;

import lombok.*;
import org.apache.tomcat.jni.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "users")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
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
        User user = (User) o;
        return experiences == user.experiences &&
                Objects.equals(id, user.id) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(function, user.function) &&
                Objects.equals(address, user.address) &&
                Objects.equals(salary, user.salary) &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, function, experiences, address, salary, birthday);
    }
}
