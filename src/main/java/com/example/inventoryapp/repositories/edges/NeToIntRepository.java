package com.example.inventoryapp.repositories.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.edges.NeToInt;

public interface NeToIntRepository extends ArangoRepository<NeToInt, String> {
}
