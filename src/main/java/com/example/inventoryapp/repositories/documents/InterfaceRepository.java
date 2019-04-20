package com.example.inventoryapp.repositories.documents;

import static com.example.inventoryapp.domain.Collections.NE_TO_INT;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.documents.Interface;
import org.springframework.data.repository.query.Param;

public interface InterfaceRepository extends ArangoRepository<Interface, String> {


    @Query("FOR int IN 1..1 OUTBOUND @neId `" + NE_TO_INT + "` FILTER int.name == @intName RETURN int")
    ArangoCursor<Interface> findByNeAndName(@Param("neId") String neId, @Param("intName") String intName);

    @Query("FOR int IN 1..1 OUTBOUND @neId `" + NE_TO_INT + "` RETURN int")
    ArangoCursor<Interface> getInterfacesByNeId(@Param("neId") String neId);
}
