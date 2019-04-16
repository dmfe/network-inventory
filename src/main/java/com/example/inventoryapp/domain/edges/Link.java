package com.example.inventoryapp.domain.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.example.inventoryapp.domain.documents.Interface;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Edge("link")
@Data
public class Link {

    @Id
    private String id;

    @From
    private final Interface aInterface;

    @To
    private final Interface zInterface;

    @Field("a-ne")
    @JsonProperty("a-ne")
    private String aNetworkElementName;

    @Field("z-ne")
    @JsonProperty("z-ne")
    private String zNetworkElementName;

    @Field("a-interface")
    @JsonProperty("a-interface")
    private String aInterfaceName;

    @Field("z-interface")
    @JsonProperty("z-interface")
    private String zInterfaceName;
}
