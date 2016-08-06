package com.betterebay.db;

import com.betterebay.core.Item;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;


public class ItemDao extends AbstractDAO<Item> {
  public ItemDao(SessionFactory factory) {
    super(factory);
  }

  public Optional<Item> findItemByID(Long id) {
    return Optional.ofNullable(get(id));
  }

  public Item createItem(Item item) {
    return persist(item);
  }

  public List<Item> findItemByName(String name) {
    return list(namedQuery("com.betterebay.core.item.findItemByName").setParameter("name", name));
  }

  public List<Item> findItemByNameColorSize(String name, String color, int size) {
    return list(namedQuery("com.betterebay.core.item.findItemByNameColorSize").setParameter("name", name)
        .setParameter("color", color).setParameter("item_size", size));
  }

  public List<Item> findAllItem() {
    return list(namedQuery("com.betterebay.core.item.findAll"));
  }

  // find available items.
  public List<Item> findItemByAvailability() {
    return list(namedQuery("com.betterebay.core.item.findItemByAvailability").setBoolean("status", Boolean.TRUE));
  }

  public void updateStatus(Boolean status, Long itemId) {
    namedQuery("com.betterebay.core.item.updateStatus").setLong("itemId", itemId).setBoolean("status", status)
        .executeUpdate();
  }
}
