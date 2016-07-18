package db;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import core.Feedback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class FeedbackDaoTest {

    private FeedbackDao feedbackDao;
    private Feedback feedback1;
    private Feedback feedback2;

    @Before
    public void setUp() throws Exception {
        feedbackDao = mock(FeedbackDao.class);
        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        feedback1 = new Feedback(123, 456, "first feedback", ft.parse("2014-11-21 23:00:00"));
        feedback2 = new Feedback(789, 221, "second feedback", ft.parse("2016-02-21 10:40:42"));

        when(feedbackDao.createFeedback(feedback1)).thenReturn(feedback1);
        when(feedbackDao.createFeedback(feedback2)).thenReturn(feedback2);
        when(feedbackDao.findAllFeedback()).thenReturn(Arrays.asList(feedback1, feedback2));
        when(feedbackDao.findFeedbackByID(new Long(1))).thenReturn(Optional.ofNullable(feedback1));
        when(feedbackDao.findFeedbackByID(new Long(2))).thenReturn(Optional.ofNullable(feedback2));
        when(feedbackDao.findFeedbackByTransactionID(new Long(456))).thenReturn(Optional.ofNullable(feedback1));
        when(feedbackDao.findFeedbackByTransactionID(new Long(221))).thenReturn(Optional.ofNullable(feedback2));
        when(feedbackDao.findFeedbackByBuyerID(new Long(123))).thenReturn(Arrays.asList(feedback1));
        when(feedbackDao.findFeedbackByBuyerID(new Long(789))).thenReturn(Arrays.asList(feedback2));

    }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void findFeedbackByID() throws Exception {
        Optional<Feedback> feedback = feedbackDao.findFeedbackByID(new Long(1));
        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Assert.assertEquals(feedback.get().getBuyer_id(), 123);
        Assert.assertEquals(feedback.get().getTransaction_id(), 456);
        Assert.assertEquals(feedback.get().getContent(), "first feedback");
        Assert.assertEquals(feedback.get().getDatetime(), ft.parse("2014-11-21 23:00:00"));
    }

    @Test
    public void findFeedbackByTransactionID() throws Exception {
        Optional<Feedback> feedback = feedbackDao.findFeedbackByTransactionID(new Long(221));
        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Assert.assertEquals(feedback.get().getBuyer_id(), 789);
        Assert.assertEquals(feedback.get().getTransaction_id(), 221);
        Assert.assertEquals(feedback.get().getContent(), "second feedback");
        Assert.assertEquals(feedback.get().getDatetime(), ft.parse("2016-02-21 10:40:42"));
    }

    @Test
    public void findFeedbackByBuyerID() throws Exception {
        List<Feedback> feedbacks = feedbackDao.findFeedbackByBuyerID(new Long(123));
        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Assert.assertTrue(feedbacks.size() == 1);
        Assert.assertEquals(feedbacks.get(0).getBuyer_id(), 123);
        Assert.assertEquals(feedbacks.get(0).getTransaction_id(), 456);
        Assert.assertEquals(feedbacks.get(0).getContent(), "first feedback");
        Assert.assertEquals(feedbacks.get(0).getDatetime(), ft.parse("2014-11-21 23:00:00"));
    }

    @Test
    public void createFeedback() throws Exception {
        Assert.assertEquals(feedbackDao.createFeedback(feedback1), feedback1);
        Assert.assertEquals(feedbackDao.createFeedback(feedback2), feedback2);
    }

    @Test
    public void findAllFeedback() throws Exception {
        List<Feedback> feedbacks = feedbackDao.findAllFeedback();
        Assert.assertEquals(feedbacks.size(), 2);
        Assert.assertEquals(feedbacks.get(0), feedback1);
        Assert.assertEquals(feedbacks.get(1), feedback2);
    }

}