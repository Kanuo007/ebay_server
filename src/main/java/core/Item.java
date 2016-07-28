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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonCreator;
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
  @Temporal(TemporalType.TIMESTAMP)
  private Date bid_start_time;

  @Column(name = "bid_end_time", nullable = false)
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
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

  @JsonCreator
  public Item(@JsonProperty("user_id") Long user_id, @JsonProperty("name") String item_name,
              @JsonProperty("base_price") Double base_price, @JsonProperty("status") Boolean status,
              @JsonProperty("bid_start_time")
              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") Date bid_start_time,
              @JsonProperty("bid_end_time")
              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") Date bid_end_time,
              @JsonProperty("catagory") String catagory,
              @JsonProperty("size") Integer size, @JsonProperty("color") String color,
              @JsonProperty("deliver_fee") Integer deliver_fee,
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

  @Deprecated
  public Item(){}

  @Deprecated
  public Item(@JsonProperty("user_id") Long user_id, @JsonProperty("name") String item_name,
              @JsonProperty("base_price") Double base_price, @JsonProperty("status") Boolean status,
              @JsonProperty("bid_start_time")
              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") Date bid_start_time,
              @JsonProperty("bid_end_time")
              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")Date bid_end_time) {
    this.userID = user_id;
    this.name = item_name;
    this.status = status;
    this.base_price = base_price;
    this.bid_end_time = bid_end_time;
    this.bid_start_time = bid_start_time;
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

  public void setId(Long id) {
    this.id = id;
  }

  public void setUserID(Long userID) {
    this.userID = userID;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public void setCatagory(String catagory) {
    this.catagory = catagory;
  }

  public void setBid_start_time(Date bid_start_time) {
    this.bid_start_time = bid_start_time;
  }

  public void setBid_end_time(Date bid_end_time) {
    this.bid_end_time = bid_end_time;
  }

  public void setBase_price(Double base_price) {
    this.base_price = base_price;
  }

  public void setDeliver_fee(Integer deliver_fee) {
    this.deliver_fee = deliver_fee;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Item{" +
            "id=" + id +
            ", userID=" + userID +
            ", name='" + name + '\'' +
            ", color='" + color + '\'' +
            ", size=" + size +
            ", catagory='" + catagory + '\'' +
            ", status=" + status +
            ", bid_start_time=" + bid_start_time +
            ", bid_end_time=" + bid_end_time +
            ", base_price=" + base_price +
            ", deliver_fee=" + deliver_fee +
            ", description='" + description + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Item item = (Item) o;

    if (id != null ? !id.equals(item.id) : item.id != null) return false;
    if (userID != null ? !userID.equals(item.userID) : item.userID != null) return false;
    if (name != null ? !name.equals(item.name) : item.name != null) return false;
    if (color != null ? !color.equals(item.color) : item.color != null) return false;
    if (size != null ? !size.equals(item.size) : item.size != null) return false;
    if (catagory != null ? !catagory.equals(item.catagory) : item.catagory != null) return false;
    if (status != null ? !status.equals(item.status) : item.status != null) return false;
    if (bid_start_time != null ? !bid_start_time.equals(item.bid_start_time) : item.bid_start_time != null)
      return false;
    if (bid_end_time != null ? !bid_end_time.equals(item.bid_end_time) : item.bid_end_time != null)
      return false;
    if (base_price != null ? !base_price.equals(item.base_price) : item.base_price != null)
      return false;
    if (deliver_fee != null ? !deliver_fee.equals(item.deliver_fee) : item.deliver_fee != null)
      return false;
    return description != null ? description.equals(item.description) : item.description == null;

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (userID != null ? userID.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (color != null ? color.hashCode() : 0);
    result = 31 * result + (size != null ? size.hashCode() : 0);
    result = 31 * result + (catagory != null ? catagory.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (bid_start_time != null ? bid_start_time.hashCode() : 0);
    result = 31 * result + (bid_end_time != null ? bid_end_time.hashCode() : 0);
    result = 31 * result + (base_price != null ? base_price.hashCode() : 0);
    result = 31 * result + (deliver_fee != null ? deliver_fee.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }

  public Boolean checkEndTime(Item item) {
    Date current_time = new Date();
    return current_time.after(item.getBid_end_time());
  }
}
