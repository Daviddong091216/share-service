package org.launchcode.shareservice.data;

import org.launchcode.shareservice.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer>  {
    User findByUsername(String username);
}
