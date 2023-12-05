package com.cricket.cricket.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="Team")
public class Team {
    @MongoId
    private int  teamId;
    private String teamName;
    private String coachId;
    private  List<Player> players;
    private  List<Player> extras;
    private Inning inning;

}
