package com.example.inventoryapp.repositories.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.documents.Interface;

public interface InterfaceRepository extends ArangoRepository<Interface, String> {
}
