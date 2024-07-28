package com.tek.acs.repository;

import com.tek.acs.data.models.entity.Slot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SlotRepository extends MongoRepository<Slot,String> {
}
