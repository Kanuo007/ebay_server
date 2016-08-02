package core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

public class FeedbackTest {
   private Feedback feedback1;
   private Feedback feedback2;
   private Feedback feedback3;
   private Feedback feedback4;

   @Before
   public void setUp() throws Exception {
   SimpleDateFormat ft =
   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   feedback1 = new Feedback(new Long(123), new Long(456), "first feedback", ft.parse("2014-11-21 23:00:00"));
   feedback2 = new Feedback(new Long(789), new Long(221), "second feedback", ft.parse("2016-02-21 10:40:42"));
   feedback3 = new Feedback(new Long(123), new Long(456), "first feedback", ft.parse("2014-11-21 23:00:00"));
   feedback4 = new Feedback(new Long(123), new Long(456), "first feedback", ft.parse("2014-11-21 23:00:00"));
   }

   @After
   public void tearDown() throws Exception {

   }

   // 1. reflexive: x.equal(x) return true
   // 2. symmetric: x.equals(y) return true iff y.equals(x)
   // 3. transitive:
   // 1. x.equals(y) returns true
   // 2. y.equals(z) returns true
   // 3. x.equals(z) must return true
   // 4. consistent:Repeated calls to equals consistently return true or false.
   // 5. false for null: x.equal(null) return false
   // 6. false for wrong type: x.equal(WrongType) return false
   @Test
   public void testEqualsObject() {

   Assert.assertTrue(this.feedback1.equals(this.feedback1));
   Assert.assertTrue(this.feedback2.equals(this.feedback2));
   Assert.assertTrue(this.feedback3.equals(this.feedback3));

   Assert.assertTrue(this.feedback1.equals(this.feedback3));
   Assert.assertTrue(this.feedback3.equals(this.feedback1));

   Assert.assertTrue(this.feedback1.equals(this.feedback3));
   Assert.assertTrue(this.feedback3.equals(this.feedback4));
   Assert.assertTrue(this.feedback1.equals(this.feedback4));

   Assert.assertTrue(this.feedback1.equals(this.feedback3));
   Assert.assertTrue(this.feedback1.equals(this.feedback3));
   Assert.assertFalse(this.feedback1.equals(this.feedback2));
   Assert.assertFalse(this.feedback1.equals(this.feedback2));

   Assert.assertFalse(this.feedback1.equals(null));
   Assert.assertFalse(this.feedback2.equals(null));

   Assert.assertFalse(this.feedback1.equals(new Integer(1)));
   Assert.assertFalse(this.feedback2.equals(new Character('a')));
   }

   // 1. Repeated calls to hashcode should consistently return the same integer.
   // 2. Objects that are equal using the equals method should return the same integer.
   // 3. If the objects are unequal different integers are produced.
   @Test
   public void testHashCode() throws Exception {
   int init_1 = this.feedback1.hashCode();
   int init_2 = this.feedback2.hashCode();
   int init_3 = this.feedback3.hashCode();

   Assert.assertEquals(init_1, this.feedback1.hashCode());
   Assert.assertEquals(init_1, this.feedback1.hashCode());
   Assert.assertEquals(init_2, this.feedback2.hashCode());
   Assert.assertEquals(init_2, this.feedback2.hashCode());

   Assert.assertEquals(init_1, init_3);

   Assert.assertTrue(init_1 != init_2);
   Assert.assertTrue(init_3 != init_2);
   }

}
