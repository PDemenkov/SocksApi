package com.example.socksapi.controller;

import com.example.socksapi.entity.Sock;
import com.example.socksapi.service.SockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SockController {
    SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    private final Logger log = LoggerFactory.getLogger(SockController.class);


    @PostMapping("/income")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sock has been added"),
            @ApiResponse(responseCode = "400", description = "Parameters are nullable or incorrect format"),
            @ApiResponse(responseCode = "500", description = "Server or database are not available")
    })
    @Operation(summary = "add sock", tags = "socks")
    public ResponseEntity<Sock> addSock(@RequestBody Sock sock) {
        log.info("Was invoked method add sock for adding {} to storage", sock);
        sockService.addSock(sock);
        return new  ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/outcome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Outcome socks"),
            @ApiResponse(responseCode = "400", description = "Parameters are nullable or incorrect format"),
            @ApiResponse(responseCode = "500", description = "Server or database are not available")
    })
    @Operation(summary = "socks outcome", tags = "socks")
    public ResponseEntity<Sock> subSocks(@RequestBody Sock sock) {
        log.info("Was invoked method for outcome {} from storage", sock);
        sockService.outcomeSock(sock);
        return new  ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sock by parameters were found"),
            @ApiResponse(responseCode = "400", description = "Parameters are nullable or incorrect format"),
            @ApiResponse(responseCode = "500", description = "Server or database are not available")
    })
    @Operation(summary = "get socks by operation", tags = "socks")
    public ResponseEntity<String> getAllSocks(@RequestParam(value = "color",required = false) String color,
                                              @RequestParam("operation") String operation,
                                              @RequestParam(value ="cottonPart",required = false) int cottonPart) {
        log.info("Was invoked method for get all socks by color {} and {} cotton part , sorted by operation {} from storage",color,cottonPart,operation);
        Integer socks = sockService.getAllSocksByOperationAndCurrentParameters(color, cottonPart, operation);
        return new ResponseEntity<>(socks.toString(), HttpStatus.OK);
    }
}
