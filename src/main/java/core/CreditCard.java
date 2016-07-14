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

import io.dropwizard.jackson.JsonSnakeCase;

/**
 * Represents a CreditCard class with its id, userId, creditCardType, cardNumber,
 * cardHolder,cvv,expirationYear, expirationMonth and billingAddress
 *
 * @author LiYang
 *
 */

@Entity
@Table(name = "credit_card")
@NamedQueries({

    @NamedQuery(name = "core.creditcard.findAll", query = "SELECT c FROM CreditCard c"),

    @NamedQuery(name = "core.creditcard.findByUserId",
        query = "SELECT c FROM CreditCard c WHERE c.userId = :userId")})
@JsonSnakeCase
public class CreditCard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  @JsonProperty
  private Long userId;

  @Column(name = "credit_card_type", nullable = false)
  @JsonProperty
  private String creditCardType;

  @Column(name = "card_number", nullable = false)
  @JsonProperty
  private Long cardNumber;

  @Column(name = "card_holder", nullable = false)
  @JsonProperty
  private String cardHolder;

  @Column(name = "cvv", nullable = false)
  @JsonProperty
  private int cvv;

  @Column(name = "expiration_year", nullable = false)
  @JsonProperty
  private String expirationYear;

  @Column(name = "expiration_month", nullable = false)
  @JsonProperty
  private String expirationMonth;

  @Column(name = "billing_address", nullable = false)
  @JsonProperty
  private String billingAddress;


  /**
   * Creates an instance of CreditCard class given its userId, creditCardType, cardNumber,
   * cardHolder,cvv,expirationYear, expirationMonth and billingAddress
   * 
   * @param userId The userId of the CreditCard object
   * @param creditCardType The creditCardType of the CreditCard object
   * @param cardNumber The cardNumber of the CreditCard object
   * @param cardHolder The cardHolder of the CreditCard object
   * @param cvv The cvv of the CreditCard object
   * @param expirationYear The expirationYear of the CreditCard object
   * @param expirationMonth The expirationMonth of the CreditCard object
   * @param billingAddress The billingAddress of the CreditCard object
   */
  public CreditCard(@JsonProperty("user_id") Long userId,
      @JsonProperty("credit_card_type") String creditCardType,
      @JsonProperty("card_number") Long cardNumber, @JsonProperty("card_holder") String cardHolder,
      @JsonProperty("cvv") int cvv, @JsonProperty("expiration_year") String expirationYear,
      @JsonProperty("expiration_month") String expirationMonth,
      @JsonProperty("billing_address") String billingAddress) {
    this.userId = userId;
    this.creditCardType = creditCardType;
    this.cardNumber = cardNumber;
    this.cardHolder = cardHolder;
    this.cvv = cvv;
    this.expirationYear = expirationYear;
    this.expirationMonth = expirationMonth;
    this.billingAddress = billingAddress;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return this.id;
  }

  /**
   * @return the userId
   */
  public Long getUserId() {
    return this.userId;
  }

  /**
   * @return the creditCardType
   */
  public String getCreditCardType() {
    return this.creditCardType;
  }

  /**
   * @return the cardNumber
   */
  public Long getCardNumber() {
    return this.cardNumber;
  }

  /**
   * @return the cardHolder
   */
  public String getCardHolder() {
    return this.cardHolder;
  }

  /**
   * @return the cvv
   */
  public int getCvv() {
    return this.cvv;
  }

  /**
   * @return the expirationYear
   */
  public String getExpirationYear() {
    return this.expirationYear;
  }

  /**
   * @return the expirationMonth
   */
  public String getExpirationMonth() {
    return this.expirationMonth;
  }

  /**
   * @return the billingAddress
   */
  public String getBillingAddress() {
    return this.billingAddress;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * @param creditCardType the creditCardType to set
   */
  public void setCreditCardType(String creditCardType) {
    this.creditCardType = creditCardType;
  }

  /**
   * @param cardNumber the cardNumber to set
   */
  public void setCardNumber(Long cardNumber) {
    this.cardNumber = cardNumber;
  }

  /**
   * @param cardHolder the cardHolder to set
   */
  public void setCardHolder(String cardHolder) {
    this.cardHolder = cardHolder;
  }

  /**
   * @param cvv the cvv to set
   */
  public void setCvv(int cvv) {
    this.cvv = cvv;
  }

  /**
   * @param expirationYear the expirationYear to set
   */
  public void setExpirationYear(String expirationYear) {
    this.expirationYear = expirationYear;
  }

  /**
   * @param expirationMonth the expirationMonth to set
   */
  public void setExpirationMonth(String expirationMonth) {
    this.expirationMonth = expirationMonth;
  }

  /**
   * @param billingAddress the billingAddress to set
   */
  public void setBillingAddress(String billingAddress) {
    this.billingAddress = billingAddress;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result =
        (prime * result) + ((this.billingAddress == null) ? 0 : this.billingAddress.hashCode());
    result = (prime * result) + ((this.cardHolder == null) ? 0 : this.cardHolder.hashCode());
    result = (prime * result) + ((this.cardNumber == null) ? 0 : this.cardNumber.hashCode());
    result =
        (prime * result) + ((this.creditCardType == null) ? 0 : this.creditCardType.hashCode());
    result = (prime * result) + this.cvv;
    result =
        (prime * result) + ((this.expirationMonth == null) ? 0 : this.expirationMonth.hashCode());
    result =
        (prime * result) + ((this.expirationYear == null) ? 0 : this.expirationYear.hashCode());
    result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
    result = (prime * result) + ((this.userId == null) ? 0 : this.userId.hashCode());
    return result;
  }

  /**
   * {@inheritDoc}
   */
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
    CreditCard other = (CreditCard) obj;
    if (this.billingAddress == null) {
      if (other.billingAddress != null) {
        return false;
      }
    } else if (!this.billingAddress.equals(other.billingAddress)) {
      return false;
    }
    if (this.cardHolder == null) {
      if (other.cardHolder != null) {
        return false;
      }
    } else if (!this.cardHolder.equals(other.cardHolder)) {
      return false;
    }
    if (this.cardNumber == null) {
      if (other.cardNumber != null) {
        return false;
      }
    } else if (!this.cardNumber.equals(other.cardNumber)) {
      return false;
    }
    if (this.creditCardType == null) {
      if (other.creditCardType != null) {
        return false;
      }
    } else if (!this.creditCardType.equals(other.creditCardType)) {
      return false;
    }
    if (this.cvv != other.cvv) {
      return false;
    }
    if (this.expirationMonth == null) {
      if (other.expirationMonth != null) {
        return false;
      }
    } else if (!this.expirationMonth.equals(other.expirationMonth)) {
      return false;
    }
    if (this.expirationYear == null) {
      if (other.expirationYear != null) {
        return false;
      }
    } else if (!this.expirationYear.equals(other.expirationYear)) {
      return false;
    }
    if (this.id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!this.id.equals(other.id)) {
      return false;
    }
    if (this.userId == null) {
      if (other.userId != null) {
        return false;
      }
    } else if (!this.userId.equals(other.userId)) {
      return false;
    }
    return true;
  }


}


