package com.betterebay.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import io.dropwizard.jackson.JsonSnakeCase;


@Entity
@Table(name = "address")
@NamedQueries({@NamedQuery(name = "com.betterebay.core.address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "com.betterebay.core.address.findAddressByUserId",
        query = "SELECT a FROM Address a WHERE a.user_id = :user_id"),
    @NamedQuery(name = "com.betterebay.core.address.findAddressByCity",
        query = "SELECT a FROM Address a WHERE a.city = :city"),
    @NamedQuery(name = "com.betterebay.core.address.findAddressByCountry",
        query = "SELECT a FROM Address a WHERE a.country = :country"),
    @NamedQuery(name = "com.betterebay.core.address.findAddressByZipcode",
        query = "SELECT a FROM Address a WHERE a.zipcode = :zipcode"),})
@JsonSnakeCase
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty
  private Long id;

  @Column(name = "street", nullable = false)
  @JsonProperty
  private String street;

  @Column(name = "city", nullable = false)
  @JsonProperty
  private String city;

  @Column(name = "country", nullable = false)
  @JsonProperty
  private String country;

  @Column(name = "zipcode", nullable = false)
  @JsonProperty
  private Long zipcode;

  @Column(name = "user_id", nullable = false)
  @JsonProperty
  private Long user_id;

  public Address() {}

  public Address(@JsonProperty("street") String street, @JsonProperty("city") String city,
      @JsonProperty("country") String country, @JsonProperty("zipcode") Long zipcode,
      @JsonProperty("user_id") Long user_id) {
    this.street = street;
    this.city = city;
    this.country = country;
    this.zipcode = zipcode;
    this.user_id = user_id;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return this.street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Long getZipcode() {
    return this.zipcode;
  }

  public void setZipcode(Long zipcode) {
    this.zipcode = zipcode;
  }

  public Long getUser_id() {
    return this.user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.city == null) ? 0 : this.city.hashCode());
    result = (prime * result) + ((this.country == null) ? 0 : this.country.hashCode());
    result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
    result = (prime * result) + ((this.street == null) ? 0 : this.street.hashCode());
    result = (prime * result) + ((this.user_id == null) ? 0 : this.user_id.hashCode());
    result = (prime * result) + ((this.zipcode == null) ? 0 : this.zipcode.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Address other = (Address) obj;
    if (this.city == null) {
      if (other.city != null) {
        return false;
      }
    } else if (!this.city.equals(other.city)) {
      return false;
    }
    if (this.country == null) {
      if (other.country != null) {
        return false;
      }
    } else if (!this.country.equals(other.country)) {
      return false;
    }
    if (this.id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!this.id.equals(other.id)) {
      return false;
    }
    if (this.street == null) {
      if (other.street != null) {
        return false;
      }
    } else if (!this.street.equals(other.street)) {
      return false;
    }
    if (this.user_id == null) {
      if (other.user_id != null) {
        return false;
      }
    } else if (!this.user_id.equals(other.user_id)) {
      return false;
    }
    if (this.zipcode == null) {
      if (other.zipcode != null) {
        return false;
      }
    } else if (!this.zipcode.equals(other.zipcode)) {
      return false;
    }
    return true;
  }



}
