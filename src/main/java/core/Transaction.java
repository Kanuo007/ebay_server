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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.jackson.JsonSnakeCase;

@Entity
@Table(name = "transaction")
@NamedQueries({
    @NamedQuery(name = "core.transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "core.transaction.findTransactionByUserId",
        query = "SELECT t FROM Transaction t WHERE t.user_id = :user_id"),
    @NamedQuery(name = "core.transaction.findTransactionByItemId",
        query = "SELECT t FROM Transaction t WHERE t.item_id = :item_id"),})
@JsonSnakeCase
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty
  private Long id;

  @Column(name = "item_id", nullable = false)
  @JsonProperty
  private Long item_id;

  @Column(name = "user_id", nullable = false)
  @JsonProperty
  private Long user_id;

  @Column(name = "transaction_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date date;

  public Transaction() {}

  public Transaction(@JsonProperty("item_id") Long item_id, @JsonProperty("user_id") Long user_id,
      @JsonProperty("transaction_date") @JsonFormat(shape = JsonFormat.Shape.STRING,
          pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
    this.item_id = item_id;
    this.user_id = user_id;
    this.date = date;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getItem_id() {
    return this.item_id;
  }

  public void setItem_id(Long item_id) {
    this.item_id = item_id;
  }

  public Long getUser_id() {
    return this.user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }

    Transaction that = (Transaction) o;

    if (this.id != null ? !this.id.equals(that.id) : that.id != null) {
      return false;
    }
    if (this.item_id != null ? !this.item_id.equals(that.item_id) : that.item_id != null) {
      return false;
    }
    if (this.user_id != null ? !this.user_id.equals(that.user_id) : that.user_id != null) {
      return false;
    }
    return this.date != null ? this.date.equals(that.date) : that.date == null;

  }

  @Override
  public int hashCode() {
    int result = this.id != null ? this.id.hashCode() : 0;
    result = (31 * result) + (this.item_id != null ? this.item_id.hashCode() : 0);
    result = (31 * result) + (this.user_id != null ? this.user_id.hashCode() : 0);
    result = (31 * result) + (this.date != null ? this.date.hashCode() : 0);
    return result;
  }
}
