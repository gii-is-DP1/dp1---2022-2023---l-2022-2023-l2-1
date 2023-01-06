package org.springframework.samples.petclinic.registeredUser;



import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends CrudRepository<RegisteredUser, Integer> {


    @Query("SELECT ru FROM RegisteredUser ru WHERE ru.user = :user")
    public RegisteredUser findByUsername(@Param("user") User user);
 
    Collection<RegisteredUser> findAll();

    @Query("SELECT ru FROM RegisteredUser ru WHERE ru.name LIKE :name%")
    public Collection<RegisteredUser> findByName(@Param("name") String name);

    Page<RegisteredUser> findAllpageablePage(Pageable pageable);
    
    Iterable<RegisteredUser> findAllIterable(Sort sort);


}
