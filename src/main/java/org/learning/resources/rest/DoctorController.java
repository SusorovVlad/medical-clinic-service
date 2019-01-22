package org.learning.resources.rest;

import org.learning.domain.model.Speciality;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "doctors", path = "doctors")
public interface DoctorController extends PagingAndSortingRepository<Speciality, Long> {
}