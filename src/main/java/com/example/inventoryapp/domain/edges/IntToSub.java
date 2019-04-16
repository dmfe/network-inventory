package com.example.inventoryapp.domain.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.example.inventoryapp.domain.documents.Interface;
import com.example.inventoryapp.domain.documents.SubInterface;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Edge("int-to-sub")
@Data
public class IntToSub {

    @Id
    private String id;

    @From
    private final Interface anInterface;

    @To
    private final SubInterface subInterface;
}
