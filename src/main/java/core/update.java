package core;

import db.BidHistoryDao;
import db.ItemDao;
import db.NotificationDao;
import db.TransactionDao;

public class update {
  NotificationDao notificationDao;
  TransactionDao transactionDao;
  BidHistoryDao bidHistoryDao;
  ItemDao itemDao;

  public update(NotificationDao notificationDao, TransactionDao transactionDao,
      BidHistoryDao bidHistoryDao, ItemDao itemDao) {
    this.notificationDao = notificationDao;
    this.transactionDao = transactionDao;
    this.bidHistoryDao = bidHistoryDao;
    this.itemDao = itemDao;
  }
}
//
//
// Timer t = new Timer();
// t.schedule(new TimerTask(){
//
// public void run(){
//
// List<Item> item = this.itemDao.findAllItem();
// for(int i = 0; i < item.size(); i++){
// Date currentTime = new Date();
// if (currentTime.after(item.get(i).getBid_end_time())) {
// item.get(i).setStatus(false);
// bidHistoryDao.findByItemId(item.get(i).getId());
// Transaction newTransaction = new Transaction();
// String content = "Auction has end.";
// Notification notification_1 = new Notification(newTransaction.getUser_id(),
// newTransaction.getId(), content);
// Notification notification_2 = new Notification(item.get(i).getUserID(), newTransaction.getId(),
// content);
// TransactionDao.createTransaction(newTransaction);
// NotificationDao.createNotification(notification_1);
// NotificationDao.createNotification(notification_2);
// }
// }
//
// }
// },0,1);
//
//
// }
