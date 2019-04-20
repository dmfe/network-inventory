package com.example.inventoryapp.domain.documents;

import static com.example.inventoryapp.domain.Collections.INTERFACE;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Relations;
import com.example.inventoryapp.domain.edges.IntToSub;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

@Document(INTERFACE)
@Data
public class Interface implements DocumentEntity {

    @Id
    private String id;

    private String name;

    private String description;

    @Field("mac-address")
    @JsonProperty("mac-address")
    private String macAddress;

    @Field("ip-address")
    @JsonProperty("ip-address")
    private String ipAddress;

    private int mtu;

    private String speed;

    private String duplex;

    @Field("admin-status")
    @JsonProperty("admin-status")
    private String adminStatus;

    @Field("port-mode")
    @JsonProperty("port-mode")
    private String portMode;

    private Set<String> vlans;

    @Relations(edges = IntToSub.class, direction = Relations.Direction.OUTBOUND)
    @ApiModelProperty(hidden = true)
    @JsonProperty("sub-interface")
    private List<SubInterface> subInterfaces;
}
