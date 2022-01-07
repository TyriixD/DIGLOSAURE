package ca.ulaval.glo4002.vet.repositories;

import ca.ulaval.glo4002.vet.domain.Breeding;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface BreedingRepository extends Repository<Breeding, Integer> {
    @RestResource(exported = false)
    @Query(
            "select distinct b from Breeding b "
                    + "join fetch b.mother "
                    + "join fetch b.father "
                    + "where b.father.species = ?1 and b.mother.species = ?2"
    )
    Breeding findByParents(String fatherSpecies, String motherSpecies);
}
