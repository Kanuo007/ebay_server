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

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "transaction")
@NamedQueries({
    @NamedQuery(name = "core.transaction.findAll", query = "SELECT t FROM transaction t"),
    @NamedQuery(name = "core.transaction.findTransactionByUserId",
        query = "SELECT t FROM Transaction t WHERE t.user_id = :user_id"),
    @NamedQuery(name = "core.transaction.findTransactionByItemId",
        query = "SELECT t FROM Transaction t WHERE t.itemr_id = :item_id"),})
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq_name")
  @SequenceGenerator(name = "transaction_id_seq_name", sequenceName = "transaction_id_seq",
      allocationSize = 1)
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

  @Column(name = "date", nullable = false)
  @JsonProperty
  private String date;

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

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }



}
