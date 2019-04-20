package com.example.inventoryapp.repositories.documents;

import static com.example.inventoryapp.domain.Collections.NE_TO_HW;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.documents.HardwareComponent;
import org.springframework.data.repository.query.Param;

public interface HardwareComponentRepository extends ArangoRepository<HardwareComponent, String> {

    @Query("FOR hw IN 1..1 OUTBOUND @neId `" + NE_TO_HW + "` RETURN hw")
    ArangoCursor<HardwareComponent> getHwComponentByNeId(@Param("neId") String neId);
}
