package org.learning.resources.rest;

import org.learning.domain.model.Speciality;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "specialities", path = "specialities")
public interface SpecialityController extends PagingAndSortingRepository<Speciality, Long> {
}
