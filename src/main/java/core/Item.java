package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "item")
@NamedQueries({@NamedQuery(name = "core.item.findAll", query = "SELECT i FROM Item i")})

public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty
  private long id;

  @Column(name = "user_id", nullable = false)
  @JsonProperty
  private long userID;

  @Column(name = "name", nullable = false)
  @JsonProperty
  private String name;

  @Column(name = "color")
  @JsonProperty
  private String color;

  @Column(name = "size")
  @JsonProperty
  private int size;

  @Column(name = "status", nullable = false)
  @JsonProperty
  private boolean status;

  @Column(name = "bid_start_time", nullable = false)
  @JsonProperty
  private Data bid_start_time;

  @Column(name = "bid_end_time", nullable = false)
  @JsonProperty
  private Data bid_end_time;

  @Column(name = "basePrice", nullable = false)
  @JsonProperty
  private double basePrice;

  @Column(name = "deliverFee")
  @JsonProperty
  private int deliveryFee;

  @Column(name = "description")
  @JsonProperty
  private String description;

  public Item(@JsonProperty("user_id") Integer user_id, @JsonProperty("name") String item_name) {
    this.userID = user_id;
    this.name = item_name;
  }

  public long getId() {
    return this.id;
  }

  public long getUserID() {
    return this.userID;
  }

  public String getName() {
    return this.name;
  }

  public String getColor() {
    return this.color;
  }

  public int getSize() {
    return this.size;
  }

  public boolean isStatus() {
    return this.status;
  }

  public Data getBid_start_time() {
    return this.bid_start_time;
  }

  public Data getBid_end_time() {
    return this.bid_end_time;
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

  public void setStatus(boolean status) {
    this.status = status;
  }

  public void setBid_start_time(Data bid_start_time) {
    this.bid_start_time = bid_start_time;
  }

  public void setBid_end_time(Data bid_end_time) {
    this.bid_end_time = bid_end_time;
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
    result = (prime * result) + ((this.bid_end_time == null) ? 0 : this.bid_end_time.hashCode());
    result =
        (prime * result) + ((this.bid_start_time == null) ? 0 : this.bid_start_time.hashCode());
    result = (prime * result) + ((this.color == null) ? 0 : this.color.hashCode());
    result = (prime * result) + this.deliveryFee;
    result = (prime * result) + ((this.description == null) ? 0 : this.description.hashCode());
    result = (prime * result) + (int) (this.id ^ (this.id >>> 32));
    result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
    result = (prime * result) + this.size;
    result = (prime * result) + (this.status ? 1231 : 1237);
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
    if (this.bid_end_time == null) {
      if (other.bid_end_time != null) {
        return false;
      }
    } else if (!this.bid_end_time.equals(other.bid_end_time)) {
      return false;
    }
    if (this.bid_start_time == null) {
      if (other.bid_start_time != null) {
        return false;
      }
    } else if (!this.bid_start_time.equals(other.bid_start_time)) {
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
    if (this.status != other.status) {
      return false;
    }
    if (this.userID != other.userID) {
      return false;
    }
    return true;
  }

}
