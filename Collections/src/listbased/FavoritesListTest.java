package listbased;

import ADT.Iterable;
import ADT.Iterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FavoritesListTest {

    @Test
    void access()
    {
        //FavoritesList.Item<String> dog = new FavoritesList.Item<>("dog");
        FavoritesList <String> fl = new FavoritesList<>();
        fl.list.addFirst(new FavoritesList.Item("Localized"));
        fl.list.addAfter(fl.list.last(), new FavoritesList.Item("Motorcycle"));
        fl.list.addAfter(fl.list.last(), new FavoritesList.Item("Envelop"));
        fl.list.addAfter(fl.list.last(), new FavoritesList.Item("Unavailable"));

        fl.access("Motorcycle");fl.access("Motorcycle");fl.access("Motorcycle");

        int actual = fl.count(fl.findPosition("Motorcycle"));
        int expected = 3;
        assertEquals(expected, actual);

    }

    @Test
    void remove()
    {
        FavoritesList <String> fl = new FavoritesList<>();
        fl.list.addFirst(new FavoritesList.Item("Localized"));
        fl.list.addAfter(fl.list.last(), new FavoritesList.Item("Motorcycle"));

        fl.remove("Localized");

        int actual = fl.size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void getFavorites()
    {
        FavoritesList <String> fl = new FavoritesList<>();
        fl.list.addFirst(new FavoritesList.Item("Localized"));
        fl.list.addAfter(fl.list.last(), new FavoritesList.Item("Motorcycle"));
        fl.list.addAfter(fl.list.last(), new FavoritesList.Item("Envelop"));
        fl.list.addAfter(fl.list.last(), new FavoritesList.Item("Unavailable"));
        fl.list.addAfter(fl.list.last(), new FavoritesList.Item("Block"));

        fl.access("Localized");fl.access("Localized");fl.access("Unavailable");
        fl.access("Unavailable");fl.access("Block");

        //Iterable<String> actual = fl.getFavorites(2);
        Iterator<String> actual = fl.getFavorites(2).iterator();
        int count = 0;
        String[] actualArray = new String[2];
        String[] expected = {"Localized", "Unavailable"};

        while (actual.hasNext())
            actualArray[count++] = actual.next();

        assertArrayEquals(expected, actualArray);
    }
}