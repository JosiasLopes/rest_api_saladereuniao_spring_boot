package com.crud.saladereuniao.saladereuniao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name ="meetinggroup")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "starthour", nullable=false)
    private String startHour;

    @Column(name = "endhour", nullable=false)
    private String endHour;

    @Column(name = "data", nullable=false)
    private String data;

    public Room(Long id, String name, String startHour, String endHour, String data) {
        this.id = id;
        this.name = name;
        this.startHour = startHour;
        this.endHour = endHour;
        this.data = data;
    }

    public Room(){};

    @Override
    public String toString() {
        return "Room[" +
                "id=" + id +
                ", name='" + name +
                ", startHour='" + startHour  +
                ", endHour='" + endHour +
                ", data='" + data +
                ']';
    }
}
