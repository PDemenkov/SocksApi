package com.example.socksapi.component;

import com.example.socksapi.entity.Sock;
import com.example.socksapi.exception.SocksNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Verify {

    public void inOutVerify(Sock sock)   {
        if (sock.getQuantity() <= 0 || sock.getCottonPart() < 0 || sock.getCottonPart() > 100 || sock.getColor().isEmpty()) {
            throw new SocksNotFoundException("Quantity and Cotton cannot be less than 0, Cotton mor than 100 and Color not empty");
        }
    }

    public void getSocksVerify (String color, int cottonPart) {
        if (color.isEmpty() || cottonPart < 0 || cottonPart > 100) {
            throw new SocksNotFoundException("Quantity and Cotton cannot be less than 0, Color not empty ");
        }
    }
    public void outcomeValidator(Optional<Integer> actualQuantity, Integer transferableQuantity) {
        if (actualQuantity.isEmpty() || transferableQuantity > actualQuantity.get()) {
            throw new SocksNotFoundException("Actual quantity is empty or Outcome quantity more than income");
        }
    }

}
