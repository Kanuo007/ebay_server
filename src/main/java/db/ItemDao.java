package db;

import java.util.ArrayList;
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

  public Optional<Item> findItemByName(String name) {
    return Optional.ofNullable(
        (Item) (namedQuery("core.Item.findItemByName").setParameter("name", name).uniqueResult()));
  }

  public List<Item> findItemByNameColorSize(String name, String color, int size) {
    List<Item> result = new ArrayList<Item>();
    result.add((Item) (namedQuery("core.Item.findItemByNameColorSize").setParameter("name", name)
        .setParameter("color", color).setParameter("size", size)));
    return result;
  }

  public List<Item> findItemByCatagory(String catagory) {
    List<Item> result = new ArrayList<Item>();
    result.add((Item) (namedQuery("core.Item.findItemByCatagory").setParameter("catagory", catagory)
        .uniqueResult()));
    return result;
  }

  public List<Item> findAllItem() {
    return list(namedQuery(""));
  }
}
