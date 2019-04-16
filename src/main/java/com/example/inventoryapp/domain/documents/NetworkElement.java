package com.example.inventoryapp.domain.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Relations;
import com.example.inventoryapp.domain.edges.NeToHw;
import com.example.inventoryapp.domain.edges.NeToInt;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document("network-element")
@Data
public class NetworkElement {

    @Id
    private String id;

    private String name;

    private String ip;

    @Field("os-version")
    @JsonProperty("os-version")
    private String osVersion;

    private String type;

    private String vendor;

    @Field("returned-to-rom")
    @JsonProperty("returned-to-rom")
    private String returnedToRom;

    @Field("system-booted-time")
    @JsonProperty("system-booted-time")
    private String systemBootedTime;

    @Field("last-sync")
    @JsonProperty("last-sync")
    private String lastSync;

    @Relations(edges = NeToHw.class, direction = Relations.Direction.OUTBOUND)
    @ApiModelProperty(hidden = true)
    @JsonProperty("hw-component")
    private HardwareComponent hardwareComponent;

    @Relations(edges = NeToInt.class, direction = Relations.Direction.OUTBOUND)
    @ApiModelProperty(hidden = true)
    @JsonProperty("interfaces")
    private List<Interface> interfaces;
}
