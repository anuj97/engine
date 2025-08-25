package com.montage.engine.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    private String id;
    private String name;
    private List<String> memberUserIds;
}
