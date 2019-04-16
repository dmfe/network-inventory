package com.example.inventoryapp.repositories.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.documents.NetworkElement;

public interface NetworkElementRepository extends ArangoRepository<NetworkElement, String> {
}
