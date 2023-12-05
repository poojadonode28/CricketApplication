package com.cricket.cricket.Entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="match")
public class Match {
    @MongoId
    private int matchId;
    //private List<ScoreInfo> Score;
    private List<InningInfo> inningInfo;
    private List<Team> teams;
    private String winningTeam;
    private int totalRuns;

}
