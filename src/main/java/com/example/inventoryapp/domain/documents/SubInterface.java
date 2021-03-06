package com.example.inventoryapp.domain.documents;

import static com.example.inventoryapp.domain.Collections.SUB_INTERFACE;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Document(SUB_INTERFACE)
@Data
public class SubInterface implements DocumentEntity {

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
