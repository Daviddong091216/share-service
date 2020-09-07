package org.launchcode.shareservice.data;

import org.launchcode.shareservice.models.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {
}
