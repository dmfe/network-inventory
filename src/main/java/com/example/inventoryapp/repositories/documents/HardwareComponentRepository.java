package com.example.inventoryapp.repositories.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.documents.HardwareComponent;

public interface HardwareComponentRepository extends ArangoRepository<HardwareComponent, String> {
}
