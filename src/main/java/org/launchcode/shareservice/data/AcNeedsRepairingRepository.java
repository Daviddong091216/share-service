package org.launchcode.shareservice.data;

import org.launchcode.shareservice.models.AcNeedsRepairing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcNeedsRepairingRepository extends CrudRepository<AcNeedsRepairing, Integer> {
}
