package resource;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import core.Item;
import db.ItemDao;
import io.dropwizard.testing.junit.ResourceTestRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class ItemResourceTest {

    private static final ItemDao itemDao = mock(ItemDao.class);
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ItemResource(itemDao))
            .build();

    private Item item1;
    private Item item2;
    @Before
    public void setUp() throws Exception {
        SimpleDateFormat ft =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        item1 = new Item(new Long(1), "first item", new Double(10.0), true,
                ft.parse("2016-11-21 23:00:00"), ft.parse("2016-11-30 23:00:00"), "shoe",
                new Integer(1), "green", new Integer(4), "This is the first shoe");
        item1.setId(new Long(1));
        item2 = new Item(new Long(2), "second item", new Double(10.0), false,
                ft.parse("2016-12-21 23:00:00"), ft.parse("2016-12-30 23:00:00"), "book",
                new Integer(1), "", new Integer(1), "This is the first book");
        item2.setId(new Long(2));
        when(itemDao.findAllItem()).thenReturn(Arrays.asList(item1, item2));
        when(itemDao.findItemByID(new Long(1))).thenReturn(Optional.ofNullable(item1));
        when(itemDao.findItemByID(new Long(2))).thenReturn(Optional.ofNullable(item2));
        when(itemDao.findItemByName("first item")).thenReturn(Arrays.asList(item1));
        when(itemDao.findItemByName("second item")).thenReturn(Arrays.asList(item2));
        when(itemDao.findItemByAvailability()).thenReturn(Arrays.asList(item1));
        when(itemDao.findItemByNameColorSize("first item", "green", new Integer(1))).thenReturn(Arrays.asList(item1));
        when(itemDao.findItemByNameColorSize("second item", "", new Integer(1))).thenReturn(Arrays.asList(item2));
        when(itemDao.createItem(item1)).thenReturn(item1);
        when(itemDao.createItem(item2)).thenReturn(item2);
    }

    @After
    public void tearDown() throws Exception {
        reset(itemDao);
    }

    @Test
    public void searchAll() throws Exception {
        assertThat(resources.client().target("/item/search_all").request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Item>>(){}))
                .isEqualTo(Arrays.asList(item1, item2));
        verify(itemDao).findAllItem();
    }

    @Test
    public void search() throws Exception {
        assertThat(resources.client().target("/item/search_item_name/first item").request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Item>>(){}))
                .isEqualTo(Arrays.asList(item1));
        verify(itemDao).findItemByName("first item");
    }

    @Test
    public void findAllAvailableItems() throws Exception {
        assertThat(resources.client().target("/item/search_item_onBid").request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Item>>(){}))
                .isEqualTo(Arrays.asList(item1));
        verify(itemDao).findItemByAvailability();
    }

    @Test
    public void findIDItems() throws Exception {
        assertThat(resources.client().target("/item/search_item_id/2").request(MediaType.APPLICATION_JSON).get().readEntity(Item.class))
                .isEqualTo(item2);
        verify(itemDao).findItemByID(new Long(2));
    }

    @Test
    public void sell() throws Exception {
        assertThat(resources.client().target("/item/create_item").request(MediaType.APPLICATION_JSON).post(Entity.entity(item1, MediaType.APPLICATION_JSON), Item.class)).isEqualTo(item1);
        verify(itemDao).createItem(item1);
    }

}