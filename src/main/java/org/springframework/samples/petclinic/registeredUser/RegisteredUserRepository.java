package org.springframework.samples.petclinic.registeredUser;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends CrudRepository<RegisteredUser, Integer> {


    // @Query("SELECT DISTINCT ru FROM RegisteredUser ru WHERE ru.user.username = :username")
    // public RegisteredUser findByUsername(String username);
    
}
