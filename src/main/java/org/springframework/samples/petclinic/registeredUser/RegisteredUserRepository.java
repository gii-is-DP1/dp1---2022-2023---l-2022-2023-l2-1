package org.springframework.samples.petclinic.registeredUser;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends CrudRepository<RegisteredUser, Integer> {


    @Query("SELECT ru FROM RegisteredUser ru WHERE ru.user = :user")
    public RegisteredUser findByUsername(@Param("user") User user);
 
 

    //Coger una id por nombre 


}
