package com.example.inventoryapp.domain.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Document("hw-component")
@Data
public class HardwareComponent {

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
