package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

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
  private long id;

  @Column(name = "item_id", nullable = false)
  @JsonProperty
  private long item_id;

  @Column(name = "shipping_address_id", nullable = false)
  @JsonProperty
  private long shipping_address_id;

  @Column(name = "billing_address_id", nullable = false)
  @JsonProperty
  private long billing_address_id;

  @Column(name = "user_id", nullable = false)
  @JsonProperty
  private long user_id;

  @Column(name = "feedback", nullable = false)
  @JsonProperty
  private String feedback;

  @Column(name = "transaction_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private Date date;

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getItem_id() {
    return this.item_id;
  }

  public void setItem_id(long item_id) {
    this.item_id = item_id;
  }

  public long getShipping_address_id() {
    return this.shipping_address_id;
  }

  public void setShipping_address_id(long shipping_address_id) {
    this.shipping_address_id = shipping_address_id;
  }

  public long getBilling_address_id() {
    return this.billing_address_id;
  }

  public void setBilling_address_id(long billing_address_id) {
    this.billing_address_id = billing_address_id;
  }

  public long getUser_id() {
    return this.user_id;
  }

  public void setUser_id(long user_id) {
    this.user_id = user_id;
  }

  public String getFeedback() {
    return this.feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + (int) (this.billing_address_id ^ (this.billing_address_id >>> 32));
    result = (prime * result) + ((this.date == null) ? 0 : this.date.hashCode());
    result = (prime * result) + ((this.feedback == null) ? 0 : this.feedback.hashCode());
    result = (prime * result) + (int) (this.id ^ (this.id >>> 32));
    result = (prime * result) + (int) (this.item_id ^ (this.item_id >>> 32));
    result =
        (prime * result) + (int) (this.shipping_address_id ^ (this.shipping_address_id >>> 32));
    result = (prime * result) + (int) (this.user_id ^ (this.user_id >>> 32));
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
    Transaction other = (Transaction) obj;
    if (this.billing_address_id != other.billing_address_id) {
      return false;
    }
    if (this.date == null) {
      if (other.date != null) {
        return false;
      }
    } else if (!this.date.equals(other.date)) {
      return false;
    }
    if (this.feedback == null) {
      if (other.feedback != null) {
        return false;
      }
    } else if (!this.feedback.equals(other.feedback)) {
      return false;
    }
    if (this.id != other.id) {
      return false;
    }
    if (this.item_id != other.item_id) {
      return false;
    }
    if (this.shipping_address_id != other.shipping_address_id) {
      return false;
    }
    if (this.user_id != other.user_id) {
      return false;
    }
    return true;
  }
}
