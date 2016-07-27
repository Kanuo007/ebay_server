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
  private Long id;

  @Column(name = "user_id", nullable = false)
  @JsonProperty
  private Long userID;

  @Column(name = "name", nullable = false)
  @JsonProperty
  private String name;

  @Column(name = "color")
  @JsonProperty
  private String color;

  @Column(name = "size")
  @JsonProperty
  private Integer size;

  @Column(name = "catagory")
  @JsonProperty
  private String catagory;

  @Column(name = "status", nullable = false)
  @JsonProperty
  private Boolean status;

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
  private Double base_price;

  @Column(name = "deliver_fee")
  @JsonProperty
  private Integer deliver_fee;

  @Column(name = "description")
  @JsonProperty
  private String description;

  public Item() {}

  public Item(@JsonProperty("user_id") Long user_id, @JsonProperty("name") String item_name,
      @JsonProperty("base_price") Double base_price, @JsonProperty("status") Boolean status,
      @JsonProperty("bid_start_time") @JsonFormat(shape = JsonFormat.Shape.STRING,
          pattern = "yyyy-MM-dd HH:mm:ss") Date bid_start_time,
      @JsonProperty("bid_end_time") @JsonFormat(shape = JsonFormat.Shape.STRING,
          pattern = "yyyy-MM-dd HH:mm:ss") Date bid_end_time) {

    this.userID = user_id;
    this.name = item_name;
    this.status = status;
    this.base_price = base_price;
    this.bid_end_time = bid_end_time;
    this.bid_start_time = bid_start_time;
  }

  public Item(@JsonProperty("user_id") Long user_id, @JsonProperty("name") String item_name,

      @JsonProperty("base_price") Double base_price, @JsonProperty("status") Boolean status,
      @JsonProperty("bid_start_time") @JsonFormat(shape = JsonFormat.Shape.STRING,
          pattern = "yyyy-MM-dd HH:mm:ss") Date bid_start_time,
      @JsonProperty("bid_end_time") @JsonFormat(shape = JsonFormat.Shape.STRING,
          pattern = "yyyy-MM-dd HH:mm:ss") Date bid_end_time,
      @JsonProperty("catagory") String catagory, @JsonProperty("size") Integer size,
      @JsonProperty("color") String color, @JsonProperty("deliver_fee") Integer deliver_fee,
      @JsonProperty("description") String description) {
    this.userID = user_id;
    this.name = item_name;
    this.status = status;
    this.base_price = base_price;
    this.bid_end_time = bid_end_time;
    this.bid_start_time = bid_start_time;
    this.catagory = catagory;
    this.color = color;
    this.size = size;
    this.deliver_fee = deliver_fee;
    this.description = description;
  }

  public Long getId() {
    return this.id;
  }


  public Long getUserID() {
    return this.userID;
  }

  public String getName() {
    return this.name;
  }

  public String getColor() {
    return this.color;
  }

  public Integer getSize() {
    return this.size;
  }

  public String getCatagory() {
    return this.catagory;
  }

  public Boolean getStatus() {
    return this.status;
  }

  public Date getBid_start_time() {
    return this.bid_start_time;
  }

  public Date getBid_end_time() {
    return this.bid_end_time;
  }

  public Double getBase_price() {
    return this.base_price;
  }

  public Integer getDeliver_fee() {
    return this.deliver_fee;
  }

  public String getDescription() {
    return this.description;
  }

  public void setStatus(Boolean status) {
    this.status = status;
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
    if (this.catagory == null) {
      if (other.catagory != null) {
        return false;
      }
    } else if (!this.catagory.equals(other.catagory)) {
      return false;
    }
    if (this.color == null) {
      if (other.color != null) {
        return false;
      }
    } else if (!this.color.equals(other.color)) {
      return false;
    }
    if (this.deliver_fee != other.deliver_fee) {
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
