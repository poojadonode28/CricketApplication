package com.cricket.cricket.Entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="inningInfo")
public class InningInfo {
     private int overs;
     private String battingTeam;
     private String fieldingTeam;
     private Inning inning;
     private int runs;
     private int wickets;
     private int teamId;
     private List<ScoreInfo> scoreInfo;
}
