package db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import core.Address;
import io.dropwizard.hibernate.AbstractDAO;

public class AddressDao extends AbstractDAO<Address> {
  public AddressDao(SessionFactory factory) {
    super(factory);
  }

  public List<Address> findAll() {
    return list(namedQuery("core.transaction.findAll"));
  }

  public Optional<Address> findAddressByUserId(long user_id) {
    return Optional.ofNullable(get(user_id));
  }

  public Optional<Address> findAddressByCity(String city) {
    return Optional.ofNullable(get(city));
  }

  public Optional<Address> findAddressByCountry(String country) {
    return Optional.ofNullable(get(country));
  }

  public Optional<Address> findAddressByZipcode(long zipcode_id) {
    return Optional.ofNullable(get(zipcode_id));
  }
}
