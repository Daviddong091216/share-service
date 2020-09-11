package org.launchcode.shareservice.data;

import org.launchcode.shareservice.models.ZipCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends CrudRepository<ZipCode, Integer> {
}
