package com.example.inventoryapp.domain.documents;

import static com.example.inventoryapp.domain.Collections.HARDWARE_COMPONENT;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Document(HARDWARE_COMPONENT)
@Data
public class HardwareComponent implements DocumentEntity {

    @Id
    private String id;

    private String name;

    private String description;

    @Field("hardware-rev")
    @JsonProperty("hardware-rev")
    private String hardwareRev;

    @Field("board-id")
    @JsonProperty("board-id")
    private String boardId;

    @Field("part-number")
    @JsonProperty("part-number")
    private String partNumber;
}
