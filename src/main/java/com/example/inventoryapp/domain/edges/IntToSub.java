package com.example.inventoryapp.domain.edges;

import static com.example.inventoryapp.domain.Collections.INT_TO_SUB;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.example.inventoryapp.domain.documents.Interface;
import com.example.inventoryapp.domain.documents.SubInterface;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Edge(INT_TO_SUB)
@Data
public class IntToSub implements EdgeEntity {

    @Id
    private String id;

    @From
    private final Interface anInterface;

    @To
    private final SubInterface subInterface;
}
