package com.example.inventoryapp.repositories.edges;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.edges.Link;
import org.springframework.data.repository.query.Param;

public interface LinkRepository extends ArangoRepository<Link, String> {

    @Query("FOR link IN #collection " +
            "FILTER link.`a-ne` == @aNe && " +
            "       link.`z-ne` == @zNe && " +
            "       link.`a-interface` == @aInt && " +
            "       link.`z-interface` == @zInt " +
            "RETURN link")
    ArangoCursor<Link> findLinkByEndpoints(@Param("aNe") String aNe,
                                           @Param("zNe") String zNe,
                                           @Param("aInt") String aInt,
                                           @Param("zInt") String zInt);
}
