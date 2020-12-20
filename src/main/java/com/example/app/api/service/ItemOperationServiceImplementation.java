package com.example.app.api.service;

import com.example.app.api.model.Item;
import com.example.app.api.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemOperationServiceImplementation implements ItemOperationService {

    @Resource
    private final ItemRepository itemService;

    @Autowired
    public ItemOperationServiceImplementation(ItemRepository itemService) {
        this.itemService = itemService;
    }

    @Override
    public Item getItem(int itemId) {
        Optional<Item> item=this.itemService.findById(itemId);
        if(item.isPresent()){
            return item.get();
        }
        throw new RuntimeException("The item don't exist");

    }

    @Override
    public void deleteItem(int itemId) {

        this.itemService.deleteById(itemId);
    }

    @Override
    public List<Item> getAllItems() {
        return StreamSupport.stream(this.itemService.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public String addItem(Item item) {
        if(item == null){
            throw new RuntimeException("Item don't exist");
        }
        this.itemService.save(item);
        return "The item was added";
    }

    @Override
    public String depositItem(int itemId, int amount) {
        Item item=this.getItem(itemId);

        if(item!= null){
            if(amount < 0){
                return "The amount must be positive";
            }
            item.setAmount(item.getAmount()+amount);
            this.itemService.save(item);
            return "The amount of item was updated to"+item.getAmount();
        }
        return "The item don't exist";
    }
}
