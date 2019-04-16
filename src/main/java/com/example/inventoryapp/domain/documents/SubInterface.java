package com.example.inventoryapp.domain.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Document("sub-interface")
@Data
public class SubInterface {

    @Id
    private String id;

    private String name;

    private String description;

    private String encapsulation;

    private String vlan;

    @Field("ip-address")
    @JsonProperty("ip-address")
    private String ipAddress;

    @Field("subnet-mask")
    @JsonProperty("subnet-mask")
    private String subNetMask;
}
