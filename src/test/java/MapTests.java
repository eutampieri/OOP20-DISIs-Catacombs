import org.junit.jupiter.api.Test;

import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class MapTests {
    @Test
    void testGameLaunch() {
        TileMapFactoryImpl mf = new TileMapFactoryImpl();
        mf.def();
    }
}
