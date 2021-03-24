import org.junit.jupiter.api.Test;

import eu.eutampieri.catacombs.model.map.Tile;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MapTests {
    private void checkMap(TileMap m){
        for(int y=0; y<m.height(); y++){
            for(int x=0; x<m.width(); x++){
                assertTrue(m.at(x,y)==Tile.FLOOR || m.at(x,y)==Tile.WALL);
                if(y==0||x==0||y==m.height()-1||x==m.width()-1){
                    assertTrue(m.at(x,y)!=Tile.FLOOR);
                }
            }
        }
    }
    @Test
    public void testGameLaunch() {
        TileMapFactoryImpl mf = new TileMapFactoryImpl();
        for(int i=0; i<32; i++){
            checkMap(mf.def());
        }
    }
}
