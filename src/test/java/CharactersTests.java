import org.junit.jupiter.api.Test;
import eu.eutampieri.catacombs.model.*;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CharactersTests {

    @Test
    void testName() {
        final String name = "John Appleseed";
        Player p = new Player(0, 0, name);
        assertEquals(p.getName(), name);
    }
}
