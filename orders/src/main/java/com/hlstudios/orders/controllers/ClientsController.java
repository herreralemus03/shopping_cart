package com.hlstudios.orders.controllers;

import com.hlstudios.orders.dto.ClientDto;
import com.hlstudios.orders.services.web.client.ClientServiceWeb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/clients")
public class ClientsController {

    final ClientServiceWeb clientServiceWeb;

    public ClientsController(
            ClientServiceWeb clientServiceWeb
    ) {
        this.clientServiceWeb = clientServiceWeb;
    }

    @GetMapping("/get/paged")
    public ResponseEntity<?> getClientsPaged(
            @RequestParam(name = "page", required = false, defaultValue = "0") final Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "25") final Integer size
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", clientServiceWeb.pageClients(page, size));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping("/get/listed")
    public ResponseEntity<?> getClientsListed(){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", clientServiceWeb.listClients());
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getClient(
            @PathVariable("id") final String id
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", clientServiceWeb.findClientById(UUID.fromString(id)));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addClient(
            @RequestBody ClientDto clientDto
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", clientServiceWeb.addClient(clientDto));
            }});
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateClient(
            @RequestBody ClientDto clientDto
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", clientServiceWeb.updateClient(clientDto));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable String id
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", clientServiceWeb.deleteClient(UUID.fromString(id)));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }
}
