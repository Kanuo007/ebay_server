package core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.Principal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "item")
@NamedQueries({@NamedQuery(name = "core.item.findAll", query = "SELECT i FROM item i")})

public class Item implements Principal {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq_name")
  @SequenceGenerator(name = "item_id_seq_name", sequenceName = "item_id_seq", allocationSize = 1)
  @JsonProperty
  private long id;

  @Column(name = "userID", nullable = false)
  @JsonProperty
  private long userID;

  @Column(name = "name", nullable = false)
  @JsonProperty
  private String name;

  @Column(name="color“)
	@JsonProperty
	private String color;

  @Column(name = "size")
  @JsonProperty
  private int size;

  @Column(name = "stockAmount", nullable = false)
  @JsonProperty
  private int stockAmount;

  @Column(name = "basePrice", nullable = false)
  @JsonProperty
  private double basePrice;

  @Column(name = "deliveryFee")
  @JsonProperty
  private int deliveryFee；

  @Column(name = "description")
  @JsonProperty
  private String description;

  public long getId() {
    return this.id;
  }

  public long getUserID() {
    return this.userID;
  }

  @Override
  public String getName() {
    return this.name;
  }

  public String getColor() {
    return this.color;
  }

  public int getSize() {
    return this.size;
  }

  public int getStockAmount() {
    return this.stockAmount;
  }

  public double getBasePrice() {
    return this.basePrice;
  }

  public int getDeliveryFee() {
    return this.deliveryFee;
  }

  public String getDescription() {
    return this.description;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setUserID(long userID) {
    this.userID = userID;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public void setStockAmount(int stockAmount) {
    this.stockAmount = stockAmount;
  }

  public void setBasePrice(double basePrice) {
    this.basePrice = basePrice;
  }

  public void setDeliveryFee(int deliveryFee) {
    this.deliveryFee = deliveryFee;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(this.basePrice);
    result = (prime * result) + (int) (temp ^ (temp >>> 32));
    result = (prime * result) + ((this.color == null) ? 0 : this.color.hashCode());
    result = (prime * result) + this.deliveryFee;
    result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
    result = (prime * result) + (int) (this.id ^ (this.id >>> 32));
    result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
    result = (prime * result) + this.size;
    result = (prime * result) + this.stockAmount;
    result = (prime * result) + (int) (this.userID ^ (this.userID >>> 32));
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
    Item other = (Item) obj;
    if (Double.doubleToLongBits(this.basePrice) != Double.doubleToLongBits(other.basePrice)) {
      return false;
    }
    if (this.color == null) {
      if (other.color != null) {
        return false;
      }
    } else if (!this.color.equals(other.color)) {
      return false;
    }
    if (this.deliveryFee != other.deliveryFee) {
      return false;
    }
    if (this.description == null) {
      if (other.description != null) {
        return false;
      }
    } else if (!this.description.equals(other.description)) {
      return false;
    }
    if (this.id != other.id) {
      return false;
    }
    if (this.name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!this.name.equals(other.name)) {
      return false;
    }
    if (this.size != other.size) {
      return false;
    }
    if (this.stockAmount != other.stockAmount) {
      return false;
    }
    if (this.userID != other.userID) {
      return false;
    }
    return true;
}


