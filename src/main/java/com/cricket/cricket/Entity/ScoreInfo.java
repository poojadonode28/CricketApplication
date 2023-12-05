package com.cricket.cricket.Entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScoreInfo {
        /*private List<InningInfo> inning;
        private Player  batsmanInfo;
        private Player bowlerInfo;
        private int totalRuns;
        private boolean isOut;
        private int totalWickets;
        private int overNo;
        private int  ballNo;*/
        @MongoId
        private int currrentBall;
        private int currentOver;
        private int currentScore;
        private int runScored;
        private int wicket;
    }

