package db;

import org.hibernate.SessionFactory;

import java.util.List;

import core.Address;
import io.dropwizard.hibernate.AbstractDAO;

public class AddressDao extends AbstractDAO<Address> {
  public AddressDao(SessionFactory factory) {super(factory);}

  public List<Address> findAll() {
    return list(namedQuery("core.address.findAll"));
  }

  public List<Address> findAddressByUserId(long user_id) {
    return list(namedQuery("core.address.findAddressByUserId").setParameter("user_id", user_id));
  }

  public List<Address> findAddressByCity(String city) {
    return list(namedQuery("core.address.findAddressByCity").setParameter("city", city));
  }

  public List<Address> findAddressByCountry(String country) {
    return list(namedQuery("core.address.findAddressByCountry").setParameter("country", country));
  }

  public List<Address> findAddressByZipcode(long zipcode_id) {
    return list(namedQuery("core.address.findAddressByZipcode").setParameter("zipcode", zipcode_id));
  }
}
