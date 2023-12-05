package com.cricket.cricket.Entity;

import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="player")
public class Player {
    @MongoId
    private int playerId;
    private String name;
    private int age;
    private Type playerType;     //BATSMAN,BOWLER,ALL_ROUNDER
    private String country;
    private boolean isCoach;
    private int teamId;


    public Player(int id, String name, int age, Type type, String country) {
    }
}
