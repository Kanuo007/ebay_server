package db;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import core.Item;
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
    return list(namedQuery("core.item.findItemByName").setParameter("name", name));
  }

  public List<Item> findItemByNameColorSize(String name, String color, int size) {
    return list(namedQuery("core.item.findItemByNameColorSize").setParameter("name", name)
        .setParameter("color", color).setParameter("item_size", size));
  }

  public List<Item> findAllItem() {
    return list(namedQuery("core.item.findAll"));
  }

  // find available items.
  public List<Item> findItemByAvailability() {
    return list(namedQuery("core.item.findItemByAvailability").setBoolean("status", Boolean.TRUE));
  }

  public void updateCurrentPrice(Double newPrice){
    namedQuery("core.item.updateCurrentPrice").setParameter("newPrice", newPrice);
  }
}
