package com.betterebay.db;

import com.betterebay.core.BidHistory;

import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;

/**
 * Represents a BidHistoryDao class which manages BidHistory
 *
 * @author LiYang
 *
 */
public class BidHistoryDao extends AbstractDAO<BidHistory> {
  /**
   * Creates an instance of BidHistoryDao given a session provider
   *
   * @param factory The session provider
   */
  public BidHistoryDao(SessionFactory factory) {
    super(factory);
  }

  /**
   * Return the BidHistory object based on its id
   *
   * @param id The bid_history_id of a BidHistory object
   * @return the BidHistory object based on its id
   */
  public BidHistory findBidHistoryByID(Long id) {
    return get(id);
  }

  /**
   * Save the bidHistory and return it
   *
   * @param bidHistory An instance of BidHistory which is to be saved
   * @return the bidHistory which is saved
   */
  public BidHistory createBidHistory(BidHistory bidHistory) {
    return persist(bidHistory);
  }

  /**
   *
   * @return the results of all bid histories
   */
  public List<BidHistory> findAllBidHistory() {
    return list(namedQuery("com.betterebay.core.bidhistory.findAll"));
  }


  /**
   *
   * @param bidderId The bidderId
   * @return the results of bid histories based on bidder_id
   */
  public List<BidHistory> findByBidderId(Long bidderId) {
    return list(namedQuery("com.betterebay.core.bidhistory.findByBidderId").setParameter("bidder_id", bidderId));
  }

  /**
   * @param itemId The itemId
   * @return the results of bid histories based on item_id
   */
  public List<BidHistory> findByItemId(Long itemId) {
    return list(namedQuery("com.betterebay.core.bidhistory.findByItemId").setParameter("item_id", itemId));
  }

  /**
   *
   * @param item_id The item_id for a certain item
   * @return an instance of Optional<BidHistory> with the highest price
   */
  public Optional<BidHistory> findByHighestPriceByItemId(Long item_id) {
    List<BidHistory> list = list(
        namedQuery("com.betterebay.core.bidhistory.findByHighestPriceByItemId").setParameter("itemId", item_id));
    if (!list.isEmpty()) {
      return Optional.ofNullable(list.get(0));
    }
    return Optional.empty();
  }

  /**
   *
   * @return the results of all bid histories based on bid_time
   */
  public List<BidHistory> findByBidTime(Date date) {
    return list(namedQuery("com.betterebay.core.bidhistory.findByBidTime").setParameter("bid_time", date));
  }
}


