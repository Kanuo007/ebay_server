package db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

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
    return list(namedQuery("core.Item.findItemByName").setParameter("name", name));
  }

  public List<Item> findItemByNameColorSize(String name, String color, int size) {
    return list(namedQuery("core.Item.findItemByNameColorSize").setParameter("name", name)
        .setParameter("color", color).setParameter("size", size));
  }

  public List<Item> findItemByCatagory(String catagory) {
    return list(namedQuery("core.Item.findItemByCatagory").setParameter("catagory", catagory));
  }

  public List<Item> findAllItem() {
    return list(namedQuery(""));
  }
}
