package com.hlstudios.orders.controllers;

import com.hlstudios.orders.services.web.store.StoreServiceWeb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api/v1/stores")
public class StoresController {

    final StoreServiceWeb storeServiceWeb;

    public StoresController(StoreServiceWeb storeServiceWeb) {
        this.storeServiceWeb = storeServiceWeb;
    }

    @GetMapping(path = "/get/paged")
    public ResponseEntity<?> getStoresPaged(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "25") Integer size
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("message", "ok");
                put("body", storeServiceWeb.pageStores(page, size));
            }});
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }
}
