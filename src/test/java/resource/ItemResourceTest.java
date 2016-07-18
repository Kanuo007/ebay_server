package resource;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import db.ItemDao;
import io.dropwizard.testing.junit.ResourceTestRule;

import static org.mockito.Mockito.mock;

public class ItemResourceTest {

    private static final ItemDao itemDao = mock(ItemDao.class);
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ItemResource(itemDao))
            .build();
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void searchAll() throws Exception {

    }

    @Test
    public void search() throws Exception {

    }

    @Test
    public void findAllAvailableItems() throws Exception {

    }

    @Test
    public void findIDItems() throws Exception {

    }

    @Test
    public void sell() throws Exception {

    }

}