package com.example.enjoytripfinal.domain.place.entity;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UlidCreator.getMonotonicUlid().toUuid();
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String roadAddress;
    private String address;
    private Double latitude;
    private Double longitude;

    private Long like;

    public Place(String name,Category category,String roadAddress,String address,Double latitude,Double longitude) {
        this.name = name;
        this.category = category;
        this.roadAddress = roadAddress;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void updateLike(boolean already) {
        if(already) {
            this.like--;
        }else{
            this.like++;
        }
    }
}
