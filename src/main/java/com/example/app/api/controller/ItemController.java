package com.example.app.api.controller;

import com.example.app.api.model.Item;
import com.example.app.api.service.ItemOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ItemController {

    private final ItemOperationService itemOperationService;

    @Autowired
    public ItemController(ItemOperationService itemOperationService) {
        this.itemOperationService = itemOperationService;
    }

    @DeleteMapping(value = "api/deleteItem/{itemId}")
    public void deleteItem(@PathVariable int itemId){
        this.itemOperationService.deleteItem(itemId);
    }

    @GetMapping(value = "/api/getItem/{itemId}")
    public Item getItem(@PathVariable int itemId){
        return this.itemOperationService.getItem(itemId);
    }

    @GetMapping(value = "api/getAllItem")
    public List<Item> getAllItem() {
        return this.itemOperationService.getAllItems();
    }

    @PostMapping(value = "/api/addItem")
    public String addItem(@RequestBody Item item){
        return this.itemOperationService.addItem(item);
    }

}
