package com.betterebay.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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

import io.dropwizard.jackson.JsonSnakeCase;


@Entity
@Table(name = "feedback")
@NamedQueries({
        @NamedQuery(
                name = "com.betterebay.core.feedback.findAll",
                query = "SELECT u FROM Feedback u"),
        @NamedQuery(
                name = "com.betterebay.core.feedback.findFeedbackByBuyerID",
                query = "SELECT f FROM Feedback f WHERE f.buyer_id = :buyer_id"),
        @NamedQuery(
                name = "com.betterebay.core.feedback.findFeedbackByTransactionID",
                query = "SELECT f FROM Feedback f WHERE f.transaction_id = :transaction_id"),
})
@JsonSnakeCase
public class Feedback {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "buyer_id", nullable = false)
  @JsonProperty
  private Long buyer_id;

  @Column(name = "transaction_id", nullable = false)
  @JsonProperty
  private Long transaction_id;

  @Column(name = "feedback_content", nullable = false)
  @JsonProperty
  private String content;

  @Column(name = "date_time", nullable = false)
  @JsonProperty
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private Date datetime;

  public Feedback(){}

  @JsonCreator
  public Feedback(@JsonProperty("buyer_id") Long buyer_id,
                  @JsonProperty("transaction_id") Long transaction_id,
                  @JsonProperty("feedback_content") String content,
                  @JsonProperty("date")
                  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss") Date datetime) {
    this.buyer_id = buyer_id;
    this.transaction_id = transaction_id;
    this.content = content;
    this.datetime = datetime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBuyer_id() {
    return buyer_id;
  }

  public void setBuyer_id(Long buyer_id) {
    this.buyer_id = buyer_id;
  }

  public Long getTransaction_id() {
    return transaction_id;
  }

  public void setTransaction_id(Long transaction_id) {
    this.transaction_id = transaction_id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getDatetime() {
    return datetime;
  }

  public void setDatetime(Date datetime) {
    this.datetime = datetime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Feedback feedback = (Feedback) o;

    if (id != null ? !id.equals(feedback.id) : feedback.id != null) return false;
    if (buyer_id != null ? !buyer_id.equals(feedback.buyer_id) : feedback.buyer_id != null)
      return false;
    if (transaction_id != null ? !transaction_id.equals(feedback.transaction_id) : feedback.transaction_id != null)
      return false;
    if (content != null ? !content.equals(feedback.content) : feedback.content != null)
      return false;
    return datetime != null ? datetime.equals(feedback.datetime) : feedback.datetime == null;

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (buyer_id != null ? buyer_id.hashCode() : 0);
    result = 31 * result + (transaction_id != null ? transaction_id.hashCode() : 0);
    result = 31 * result + (content != null ? content.hashCode() : 0);
    result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
    return result;
  }
}
