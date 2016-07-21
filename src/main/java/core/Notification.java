package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "Notification")
@NamedQueries({
    @NamedQuery(name = "core.Notification.findAll", query = "SELECT i FROM Notification i"),
    @NamedQuery(name = "core.user.findNotificationByUserID",
        query = "SELECT u FROM Notification u WHERE u.user_id = :user_id"),
    @NamedQuery(name = "core.Notification.findNotificationByTransactionID",
        query = "SELECT u FROM Notification u WHERE u.transaction_id = :transaction_id"),})

public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "user_id", nullable = false)
  @JsonProperty
  private long user_id;

  @Column(name = "transaction_id", nullable = false)
  @JsonProperty
  private long transaction_id;

  @Column(name = "content", nullable = false)
  @JsonProperty
  private String content;

  public Notification(@JsonProperty("user_id") long l, @JsonProperty("transaction_id") long m,
      @JsonProperty("content") String content) {
    this.user_id = l;
    this.transaction_id = m;
    this.content = content;
  }



  public long getId() {
    return this.id;
  }

  public long getUser_id() {
    return this.user_id;
  }

  public long getTransaction_id() {
    return this.transaction_id;
  }

  public String getContent() {
    return this.content;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setUser_id(long user_id) {
    this.user_id = user_id;
  }

  public void setTransaction_id(long transaction_id) {
    this.transaction_id = transaction_id;
  }

  public void setContent(String content) {
    this.content = content;
  }



}
