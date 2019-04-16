package com.example.inventoryapp.repositories.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.edges.Link;

public interface LinkRepository extends ArangoRepository<Link, String> {
}
