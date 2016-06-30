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
import java.sql.Date;
import java.sql.Time;



@Entity
@Table(name = "item")
@NamedQueries({
  @NamedQuery(
          name = "core.feedback.findAll",
          query = "SELECT u FROM Feedback u"),
  @NamedQuery(
          name = "core.user.findFeedbackByUserID",
          query = "SELECT u FROM Feedback u WHERE u.userID = :userID"),
  @NamedQuery(
          name = "core.user.findFeedbackByTransactionID",
          query = "SELECT u FROM Feedback u WHERE u.transactionID = :transactionID"),

})


public class Feedback {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_id_seq_name")
  @SequenceGenerator(name = "feedback_id_seq_name", sequenceName = "feedback_id_seq", allocationSize = 1)

  @JsonProperty
  private long id;

  @Column(name = "userID", nullable = false)
  @JsonProperty
  private long userID;

  @Column(name = "transactionID", nullable = false)
  @JsonProperty
  private long transactionID;

  @Column(name = "content", nullable = false)
  @JsonProperty
  private String content;

  @Column(name = "data", nullable = false)
  @JsonProperty
  private Date  data;

  @Column(name = "time", nullable = false)
  @JsonProperty
  private Time time;

}
