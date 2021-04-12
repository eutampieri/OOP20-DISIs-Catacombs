package eu.eutampieri.catacombs.model.map;

public enum Tile {
    /**
     * A void tile, which contains nothing.
     */
    VOID,
    /**
     * A wall tile, which forms a wall.
     */
    WALL,
    /**
     * A floor tile, which makes floors.
     */
    FLOOR,
    /**
     * A stair tile, which will form flight of stairs.
     */
    STAIRS;

    public boolean isWalkable() {
        return this == Tile.FLOOR || this == Tile.STAIRS;
    }
}
