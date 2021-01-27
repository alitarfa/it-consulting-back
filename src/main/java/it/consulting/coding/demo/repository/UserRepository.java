package it.consulting.coding.demo.repository;

import it.consulting.coding.demo.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    List<User> findAll();

    @Query("{ ?0 : ?1 }")
    List<User> findUsers(String filter, String value);
}
