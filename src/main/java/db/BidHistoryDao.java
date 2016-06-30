package db;

import java.util.List;

import org.hibernate.SessionFactory;

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
    return list(namedQuery(""));
  }
}
