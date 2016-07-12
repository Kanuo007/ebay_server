package db;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import core.BidHistory;
import io.dropwizard.hibernate.AbstractDAO;

public class BidHistoryDao extends AbstractDAO<BidHistory> {
  public BidHistoryDao(SessionFactory factory) {
    super(factory);
  }

  public BidHistory findBidHistoryByID(Long id) {
    return get(id);
  }

  public BidHistory createBidHistory(BidHistory bidHistory) {
    return persist(bidHistory);
  }

  public List<BidHistory> findAllBidHistory() {
    return list(namedQuery("core.bidhistory.findAll"));
  }

  public List<BidHistory> findByBidderId() {
    return list(namedQuery("core.bidhistory.findByBidderId"));
  }

  public List<BidHistory> findByItemId() {
    return list(namedQuery("core.bidhistory.findByItemId"));
  }
  public Optional<BidHistory> findByTopPriceById(Long id) {
    return Optional.ofNullable((BidHistory) namedQuery("core.bidhistory.findTopPriceById")
            .setParameter("id", id)
            .setMaxResults(1));
  }

  public List<BidHistory> findByBidTime() {
    return list(namedQuery("core.bidhistory.findByBidTime"));
  }
}


