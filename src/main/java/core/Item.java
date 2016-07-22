package core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.jackson.JsonSnakeCase;

@Entity
@Table(name = "item")
@NamedQueries({@NamedQuery(name = "core.item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "core.item.findItemByName",
        query = "SELECT i FROM Item i where i.name = :name and i.status = true"),
    @NamedQuery(name = "core.item.findItemByNameColorSize",
        query = "SELECT i FROM Item i where i.name = :name and i.color = :color and i.size = :item_size"),
    @NamedQuery(name = "core.item.findItemByAvailability",
        query = "SELECT i from Item i where i.status = :status"),
    @NamedQuery(name = "core.item.updateCurrentPrice",
        query = "UPDATE Item i SET i.base_price = :newPrice where i.id = :itemId")})
@JsonSnakeCase
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date bid_start_time;

  @Column(name = "bid_end_time", nullable = false)
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date bid_end_time;

  @Column(name = "base_price", nullable = false)
  @JsonProperty
  private double base_price;

  @Column(name = "deliver_fee")
  @JsonProperty
  private int delivery_fee;

  @Column(name = "description")
  @JsonProperty
  private String description;

  public Item(@JsonProperty("user_id") Long user_id, @JsonProperty("name") String item_name) {
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

  public boolean getStatus() {
    return this.status;
  }

  public Date getBid_start_time() {
    return this.bid_start_time;
  }

  public Date getBid_end_time() {
    return this.bid_end_time;
  }

  public double getBase_price() {
    return this.base_price;
  }

  public int getDelivery_fee() {
    return this.delivery_fee;
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

  public void setBid_start_time(Date bid_start_time) {
    this.bid_start_time = bid_start_time;
  }

  public void setBid_end_time(Date bid_end_time) {
    this.bid_end_time = bid_end_time;
  }

  public void setBase_price(double base_price) {
    this.base_price = base_price;
  }

  public void setDelivery_fee(int delivery_fee) {
    this.delivery_fee = delivery_fee;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(this.base_price);
    result = (prime * result) + (int) (temp ^ (temp >>> 32));
    result = (prime * result) + ((this.bid_end_time == null) ? 0 : this.bid_end_time.hashCode());
    result =
        (prime * result) + ((this.bid_start_time == null) ? 0 : this.bid_start_time.hashCode());
    result = (prime * result) + ((this.color == null) ? 0 : this.color.hashCode());
    result = (prime * result) + this.delivery_fee;
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
    if (Double.doubleToLongBits(this.base_price) != Double.doubleToLongBits(other.base_price)) {
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
    if (this.delivery_fee != other.delivery_fee) {
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

  public Boolean checkEndTime(Item item) {
    Date current_time = new Date();
    return current_time.after(item.getBid_end_time());
  }
}
