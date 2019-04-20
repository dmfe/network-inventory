package com.example.inventoryapp.domain.edges;

import static com.example.inventoryapp.domain.Collections.NE_TO_INT;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.example.inventoryapp.domain.documents.Interface;
import com.example.inventoryapp.domain.documents.NetworkElement;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Edge(NE_TO_INT)
@Data
public class NeToInt implements EdgeEntity {

    @Id
    private String id;

    @From
    private final NetworkElement networkElement;

    @To
    private final Interface anInterface;
}
