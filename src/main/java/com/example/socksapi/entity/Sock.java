package com.example.socksapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sock")
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String color;
    @Min(0)
    @Max(value = 100, message = "Cotton value can not be more than 100")
    private int cottonPart;
    @Min(1)
    private int quantity;
}
