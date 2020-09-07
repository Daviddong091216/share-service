package org.launchcode.shareservice.data;

import org.launchcode.shareservice.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer>  {

//    To take a username and return the given user with that username.
    User findByName(String name);
}
