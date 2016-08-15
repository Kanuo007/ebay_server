package com.betterebay.core;

import com.fasterxml.jackson.annotation.JsonCreator;
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
@Table(name = "notification")
@NamedQueries({
    @NamedQuery(name = "com.betterebay.core.notification.findAll", query = "SELECT i FROM Notification i"),
    @NamedQuery(name = "com.betterebay.core.notification.findNotificationByUserID",
        query = "SELECT u FROM Notification u WHERE u.user_id = :user_id"),
    @NamedQuery(name = "com.betterebay.core.notification.findNotificationByTransactionID",
        query = "SELECT u FROM Notification u WHERE u.transaction_id = :transaction_id"),})


@JsonSnakeCase
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  @JsonProperty
  private Long user_id;

  @Column(name = "transaction_id", nullable = false)
  @JsonProperty
  private Long transaction_id;

  @Column(name = "content", nullable = false)
  @JsonProperty
  private String content;

  public Notification() {}

  @JsonCreator
  public Notification(@JsonProperty("user_id") Long l, @JsonProperty("transaction_id") Long m,
      @JsonProperty("content") String content) {
    this.user_id = l;
    this.transaction_id = m;
    this.content = content;
  }

  public Long getId() {
    return this.id;
  }

  public Long getUser_id() {
    return this.user_id;
  }

  public Long getTransaction_id() {
    return this.transaction_id;
  }

  public String getContent() {
    return this.content;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public void setTransaction_id(Long transaction_id) {
    this.transaction_id = transaction_id;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Notification that = (Notification) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (user_id != null ? !user_id.equals(that.user_id) : that.user_id != null) return false;
    if (transaction_id != null ? !transaction_id.equals(that.transaction_id) : that.transaction_id != null)
      return false;
    return content != null ? content.equals(that.content) : that.content == null;

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
    result = 31 * result + (transaction_id != null ? transaction_id.hashCode() : 0);
    result = 31 * result + (content != null ? content.hashCode() : 0);
    return result;
  }

}
