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

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import java.sql.Time;



@Entity
@Table(name = "item")
@NamedQueries({
  @NamedQuery(
          name = "core.feedback.findAll",
          query = "SELECT u FROM Feedback u"),
  @NamedQuery(
          name = "core.user.findFeedbackByBuyerID",
          query = "SELECT u FROM Feedback u WHERE u.buyer_id = :buyerID"),
  @NamedQuery(
          name = "core.user.findFeedbackByTransactionID",
          query = "SELECT u FROM Feedback u WHERE u.transaction_id = :transactionID"),

})


public class Feedback {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "buyer_id", nullable = false)
  @JsonProperty
  private long buyer_id;

  @Column(name = "transaction_id", nullable = false)
  @JsonProperty
  private long transaction_id;

  @Column(name = "content", nullable = false)
  @JsonProperty
  private String content;


  @Column(name = "datetime", nullable = false)
  @JsonProperty
  @Temporal(TemporalType.TIMESTAMP)
  private Date datetime;

  public Feedback(@JsonProperty("buyer_id") long buyer_id,
                  @JsonProperty("transaction_id") long transaction_id,
                  @JsonProperty("content") String content, @JsonProperty("date") Date datetime) {
    this.buyer_id = buyer_id;
    this.transaction_id = transaction_id;
    this.content = content;
    this.datetime = datetime;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getBuyer_id() {
    return buyer_id;
  }

  public void setBuyer_id(long buyer_id) {
    this.buyer_id = buyer_id;
  }

  public long getTransaction_id() {
    return transaction_id;
  }

  public void setTransaction_id(long transaction_id) {
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
}
