package com.vytautasdev.api.domain.dao;

import com.vytautasdev.api.domain.entity.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
