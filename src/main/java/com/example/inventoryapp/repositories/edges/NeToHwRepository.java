package com.example.inventoryapp.repositories.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.edges.NeToHw;

public interface NeToHwRepository extends ArangoRepository<NeToHw, String> {
}
