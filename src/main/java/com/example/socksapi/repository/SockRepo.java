package com.example.socksapi.repository;

import com.example.socksapi.entity.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SockRepo extends JpaRepository<Sock,Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(s.quantity) FROM Sock s WHERE s.color = :color and s.cotton_part < :cottonPart")
    Integer findQuantityOfSocksByColorAndCottonLessThan(
            @Param("color") String color,
            @Param("cottonPart") Integer cottonPart
    );

    @Query(nativeQuery = true, value = "SELECT SUM(s.quantity) FROM Sock s WHERE s.color = :color and s.cotton_part > :cottonPart")
    Integer findQuantityOfSocksByColorAndCottonGreaterThan(
            @Param("color") String color,
            @Param("cottonPart") Integer cottonPart
    );

    @Query(nativeQuery = true, value = "SELECT SUM(s.quantity) FROM Sock s WHERE s.color = :color and s.cotton_part = :cottonPart")
    Integer findQuantityOfSocksByColorAndCottonEquals(
            @Param("color") String color,
            @Param("cottonPart") Integer cottonPart
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE Sock s SET s.quantity = s.quantity + :quantity WHERE s.color = :color and s.cottonPart = :cottonPart")
    void incomeSocksThatAlreadyExist(
            @Param("color") String color,
            @Param("cottonPart") Integer cottonPart,
            @Param("quantity") Integer quantity
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE Sock s SET s.quantity = s.quantity - :quantity WHERE s.color = :color and s.cottonPart = :cottonPart")
    void outcomeSocks(
            @Param("color") String color,
            @Param("cottonPart") Integer cottonPart,
            @Param("quantity") Integer quantity
    );

}
