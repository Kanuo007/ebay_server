package com.betterebay.db;

import com.betterebay.core.Address;

import org.hibernate.SessionFactory;

import java.util.List;

import io.dropwizard.hibernate.AbstractDAO;

public class AddressDao extends AbstractDAO<Address> {
  public AddressDao(SessionFactory factory) {super(factory);}

  public List<Address> findAll() {
    return list(namedQuery("com.betterebay.core.address.findAll"));
  }

  public List<Address> findAddressByUserId(long user_id) {
    return list(namedQuery("com.betterebay.core.address.findAddressByUserId").setParameter("user_id", user_id));
  }

  public List<Address> findAddressByCity(String city) {
    return list(namedQuery("com.betterebay.core.address.findAddressByCity").setParameter("city", city));
  }

  public List<Address> findAddressByCountry(String country) {
    return list(namedQuery("com.betterebay.core.address.findAddressByCountry").setParameter("country", country));
  }

  public List<Address> findAddressByZipcode(long zipcode_id) {
    return list(namedQuery("com.betterebay.core.address.findAddressByZipcode").setParameter("zipcode", zipcode_id));
  }
}
