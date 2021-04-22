package eu.eutampieri.catacombs.tests;

import eu.eutampieri.catacombs.model.map.TileMapFactoryImpl;
import eu.eutampieri.catacombs.model.map.TileMap;
import eu.eutampieri.catacombs.model.GameObject;
import eu.eutampieri.catacombs.model.Projectile;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class ProjectileTest {
    @Test
    public void testProjectile() {
        final TileMap map = new TileMapFactoryImpl().def();
        final Projectile projectile = new Projectile(10,10,1,0,0,map);
        projectile.update(0,new ArrayList<GameObject>());
    }
}
