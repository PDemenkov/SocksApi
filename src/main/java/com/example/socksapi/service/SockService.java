package com.example.socksapi.service;

import com.example.socksapi.component.Option;
import com.example.socksapi.component.Verify;
import com.example.socksapi.entity.Sock;
import com.example.socksapi.exception.SocksNotFoundException;
import com.example.socksapi.repository.SockRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SockService {

    private final SockRepo sockRepo;

    private final Verify verify;

    public SockService(SockRepo sockRepo, Verify verify) {
        this.sockRepo = sockRepo;
        this.verify = verify;
    }

    private final Logger logger = LoggerFactory.getLogger(SockService.class);

    /**
     *
     * @param sock
     * @return
     */
    public Sock addSock(Sock sock) {
        logger.info("Was invoked method add sock");
        verify.inOutVerify(sock);
        Optional<Integer> quantity = Optional.ofNullable(sockRepo.
                findQuantityOfSocksByColorAndCottonEquals(sock.getColor(), sock.getCottonPart()));
        if (!quantity.isPresent()) {
            return sockRepo.save(sock);
        } else {
            sockRepo.incomeSocksThatAlreadyExist(sock.getColor(), sock.getCottonPart(), sock.getQuantity());
            return null;
        }
    }

    public void outcomeSock(Sock sock) {
        logger.info("Was invoked method outcomeSock sock");
        verify.inOutVerify(sock);
        Optional<Integer> quantity = Optional.ofNullable(sockRepo.
                findQuantityOfSocksByColorAndCottonEquals(sock.getColor(), sock.getCottonPart()));
        verify.outcomeValidator(quantity, sock.getQuantity());
        sockRepo.outcomeSocks(sock.getColor(), sock.getCottonPart(), sock.getQuantity());
    }

    public int getQuantityOfSocksByColorAndCottonLessThanCurrent(String color, int cottonPart) {
        Optional<Integer> quantity = Optional.ofNullable
                (sockRepo.findQuantityOfSocksByColorAndCottonLessThan(color, cottonPart));
        return quantity.orElse(0);
    }

    public int getQuantityOfSocksByColorAndCottonGreaterThanCurrent(String color, int cottonPart) {
        Optional<Integer> quantity = Optional.ofNullable
                (sockRepo.findQuantityOfSocksByColorAndCottonGreaterThan(color, cottonPart));
        return quantity.orElse(0);
    }

    public int getQuantityOfSocksByColorAndCottonEqualsToCurrent(String color, int cottonPart) {
        Optional<Integer> quantity = Optional.ofNullable
                (sockRepo.findQuantityOfSocksByColorAndCottonEquals(color, cottonPart));
        return quantity.orElse(0);
    }

    public int getAllSocksByOperationAndCurrentParameters(String color, int cottonPart, String operation) {
        verify.getSocksVerify(color, cottonPart);

        if (operation.equals(Option.moreThan.name())) {
            return getQuantityOfSocksByColorAndCottonGreaterThanCurrent(color, cottonPart);
        }
        if (operation.equals(Option.lessThan.name())) {
            return getQuantityOfSocksByColorAndCottonLessThanCurrent(color, cottonPart);
        }
        if (operation.equals(Option.equals.name())) {
            return getQuantityOfSocksByColorAndCottonEqualsToCurrent(color, cottonPart);
        }
        throw new SocksNotFoundException();
    }
}
