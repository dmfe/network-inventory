package com.example.inventoryapp.repositories.documents;

import static com.example.inventoryapp.domain.Collections.INT_TO_SUB;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.documents.SubInterface;
import org.springframework.data.repository.query.Param;

public interface SubInterfaceRepository extends ArangoRepository<SubInterface, String> {

    @Query("FOR sub IN 1..1 OUTBOUND @intId `" + INT_TO_SUB + "` RETURN sub")
    ArangoCursor<SubInterface> getSubInterfacesByIntId(@Param("intId") String intId);
}
