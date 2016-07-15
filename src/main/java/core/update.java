package core;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import db.BidHistoryDao;
import db.ItemDao;
import db.NotificationDao;
import db.TransactionDao;


public class Update {
  NotificationDao notificationDao;
  TransactionDao transactionDao;
  BidHistoryDao bidHistoryDao;
  ItemDao itemDao;


  public Update(NotificationDao notificationDao, TransactionDao transactionDao,
      BidHistoryDao bidHistoryDao, ItemDao itemDao) {
    this.notificationDao = notificationDao;
    this.transactionDao = transactionDao;
    this.bidHistoryDao = bidHistoryDao;
    this.itemDao = itemDao;
  }

  // update everyTable per second.
  // The logic is: Each second,
  // 1. we check each item in item table, if current time equals item_start_time, update item status
  // to be true, which means is available to bid;
  // 2. if current time equals item_end_time:
  // (1) update item status to be false, which means is not available to bid;
  // (2) check Bid_history table to find the highest price bid, recording its bidder_id
  // (3) End the bid: add an new transaction in transaction Table
  // (4) Send notification to bidder and seller.


  public void updateEverything() {
    Timer t = new Timer();
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        List<Item> Allitem = Update.this.itemDao.findAllItem();
        Date currentTime = new Date();

        for (int i = 0; i < Allitem.size(); i++) {
          Item curItem = Allitem.get(i);

          if (currentTime.equals(curItem.getBid_start_time())) {
            curItem.setStatus(true);
          }

          if (currentTime.equals(curItem.getBid_end_time())) {
            curItem.setStatus(false);
            Optional<BidHistory> WinBid =
                Update.this.bidHistoryDao.findByHighestPriceByItemId(curItem.getId());
            Transaction newTransaction = new Transaction(curItem.getId(),
                WinBid.get().getBidderId(), curItem.getBid_end_time());
            Update.this.transactionDao.createTransaction(newTransaction);

            String content = "Auction has end.";
            Notification notification_1 =
                new Notification(WinBid.get().getBidderId(), newTransaction.getId(), content);
            Notification notification_2 =
                new Notification(curItem.getUserID(), newTransaction.getId(), content);
            Update.this.notificationDao.createNotification(notification_1);
            Update.this.notificationDao.createNotification(notification_2);
          }
        }
      }
    };
    t.schedule(task, 0, 1000);
  }

}


