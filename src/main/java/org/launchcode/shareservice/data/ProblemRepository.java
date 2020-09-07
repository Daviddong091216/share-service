package org.launchcode.shareservice.data;

import org.launchcode.shareservice.models.Problem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends CrudRepository<Problem, Integer> {

}
