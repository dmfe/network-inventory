package com.example.inventoryapp.repositories.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.edges.IntToSub;

public interface IntToSubRepository extends ArangoRepository<IntToSub, String> {
}
