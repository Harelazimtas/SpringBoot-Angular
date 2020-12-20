package com.example.app.api.service;

import com.example.app.api.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemOperationService {
    Item getItem(int itemId);
    void deleteItem(int itemId);
    List<Item> getAllItems();
    String addItem(Item item);
    String depositItem(int itemId,int amount);


}
