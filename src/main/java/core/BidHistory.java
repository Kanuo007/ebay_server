package core;

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

import io.dropwizard.jackson.JsonSnakeCase;

/**
 * Represents a BidHistory class
 *
 * @author LiYang
 *
 */

@Entity
@Table(name = "bid_history")
@NamedQueries({
    @NamedQuery(name = "core.bidhistory.findAll", query = "SELECT b FROM BidHistory b"),
    @NamedQuery(name = "core.bidhistory.findByBidderId",
        query = "SELECT b FROM BidHistory b WHERE b.bidderId = :bidderId"),
    @NamedQuery(name = "core.bidhistory.findByItemId",
        query = "SELECT b FROM BidHistory b WHERE b.itemId = :itemId"),
    @NamedQuery(name = "core.bidhistory.findByBidTime",
        query = "SELECT b FROM BidHistory b WHERE b.bidTime = :bidTime"),
@NamedQuery(name = "core.bidhistory.findTopPriceById",
        query = "SELECT b FROM BidHistory b WHERE b.itemId = :itemId ORDER BY b.bidPrice DESC")})
@JsonSnakeCase
public class BidHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty
  private Long id;

  @Column(name = "item_id", nullable = false)
  @JsonProperty
  private Long itemId;

  @Column(name = "bidder_id", nullable = false)
  @JsonProperty
  private Long bidderId;

  @Column(name = "bid_time", nullable = false)
  @JsonProperty
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private Date bidTime;

  @Column(name = "bid_price", nullable = false)
  @JsonProperty
  private double bidPrice;

  public BidHistory(@JsonProperty("item_id") Long itemId,
                    @JsonProperty("bidder_id") Long bidderId,
                    @JsonProperty("bid_time") Date bidTime,
                    @JsonProperty("bid_price") double bidPrice) {
    this.itemId = itemId;
    this.bidderId = bidderId;
    this.bidTime = bidTime;
    this.bidPrice = bidPrice;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return this.id;
  }

  /**
   * @return the itemId
   */
  public Long getItemId() {
    return this.itemId;
  }

  /**
   * @return the bidderId
   */
  public Long getBidderId() {
    return this.bidderId;
  }

  /**
   * @return the bidTime
   */
  public Date getBidTime() {
    return this.bidTime;
  }

  /**
   * @return the bidPrice
   */
  public double getBidPrice() {
    return this.bidPrice;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @param itemId the itemId to set
   */
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  /**
   * @param bidderId the bidderId to set
   */
  public void setBidderId(Long bidderId) {
    this.bidderId = bidderId;
  }

  /**
   * @param bidTime the bidTime to set
   */
  public void setBidTime(Date bidTime) {
    this.bidTime = bidTime;
  }

  /**
   * @param bidPrice the bidPrice to set
   */
  public void setBidPrice(double bidPrice) {
    this.bidPrice = bidPrice;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(this.bidPrice);
    result = (prime * result) + (int) (temp ^ (temp >>> 32));
    result = (prime * result) + ((this.bidTime == null) ? 0 : this.bidTime.hashCode());
    result = (prime * result) + ((this.bidderId == null) ? 0 : this.bidderId.hashCode());
    result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
    result = (prime * result) + ((this.itemId == null) ? 0 : this.itemId.hashCode());
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
    BidHistory other = (BidHistory) obj;
    if (Double.doubleToLongBits(this.bidPrice) != Double.doubleToLongBits(other.bidPrice)) {
      return false;
    }
    if (this.bidTime == null) {
      if (other.bidTime != null) {
        return false;
      }
    } else if (!this.bidTime.equals(other.bidTime)) {
      return false;
    }
    if (this.bidderId == null) {
      if (other.bidderId != null) {
        return false;
      }
    } else if (!this.bidderId.equals(other.bidderId)) {
      return false;
    }
    if (this.id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!this.id.equals(other.id)) {
      return false;
    }
    if (this.itemId == null) {
      if (other.itemId != null) {
        return false;
      }
    } else if (!this.itemId.equals(other.itemId)) {
      return false;
    }
    return true;
  }



}
