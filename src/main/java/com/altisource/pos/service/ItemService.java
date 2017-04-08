package com.altisource.pos.service;

import com.altisource.pos.domain.Item;
import com.altisource.pos.exception.ItemValidationException;
import com.altisource.pos.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getItems() {
        return itemRepository.findAll().stream().collect(Collectors.toList());
    }

    public void createItem(final Item item) throws ItemValidationException {
        if (itemRepository.exists(item.getId())) {
            throw new ItemValidationException("Item already exists");
        }
        itemRepository.save(item);
    }

    public void updateItem(final Item item) throws ItemValidationException {
        if (itemRepository.exists(item.getId())) {
            itemRepository.save(item);
        }
        throw new ItemValidationException("Item does not exist");
    }

    public void deleteItem(final long id) throws ItemValidationException {
        if (itemRepository.exists(id)) {
            itemRepository.delete(id);
        }
        throw new ItemValidationException("Item does not exist");
    }

    public Item getItem(final long id) {
        return itemRepository.findOne(id);
    }
}
