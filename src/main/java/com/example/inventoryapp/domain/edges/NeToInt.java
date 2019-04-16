package com.example.inventoryapp.domain.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.example.inventoryapp.domain.documents.Interface;
import com.example.inventoryapp.domain.documents.NetworkElement;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Edge("ne-to-int")
@Data
public class NeToInt {

    @Id
    private String id;

    @From
    private final NetworkElement networkElement;

    @To
    private final Interface anInterface;
}
