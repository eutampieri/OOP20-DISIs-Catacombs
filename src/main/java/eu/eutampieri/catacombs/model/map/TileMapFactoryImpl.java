package eu.eutampieri.catacombs.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public final class TileMapFactoryImpl implements TileMapFactory {

    private static final int NORMAL_N_ROOMS = 16;
    private static final int NORMAL_MIN_ROOM_SIDE = 8;
    private static final int NORMAL_MAX_ROOM_SIDE = 16;
    private static final int NORMAL_MIN_ROOM_DIST = 32;
    private static final int NORMAL_MAX_ROOM_DIST = 42;

    /**
     * prng used to generate maps.
     */
    private final transient Random rand = new Random();

    /**
     * A useful helper class for map generation.
     */
    private class Point {
        private final int x;
        private final int y;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int dist(final Point o) {
            return Math.abs(x - o.x) + Math.abs(y - o.y);
        }

        private TileMapFactoryImpl getEnclosingInstance() {
            return TileMapFactoryImpl.this;
        }
    }

    /**
     * @param a   a point to connect
     * @param b   the other point to connect
     * @param res the map in which to connect them
     */
    private void makeCorridor(final Point a, final Point b, final Tile[][] res) {
        int y = a.y;
        int x = a.x;
        while (y != b.y && x != b.x) {
            if (rand.nextBoolean()) {
                if (y < b.y) {
                    y++;
                } else {
                    y--;
                }
            } else {
                if (x < b.x) {
                    x++;
                } else {
                    x--;
                }
            }
            res[y][x] = Tile.FLOOR;
        }
        while (y != b.y) {
            if (y < b.y) {
                y++;
            } else {
                y--;
            }
            res[y][x] = Tile.FLOOR;
        }
        while (x != b.x) {
            if (x < b.x) {
                x++;
            } else {
                x--;
            }
            res[y][x] = Tile.FLOOR;
        }
    }

    /**
     * @param nRooms      number of rooms to generate
     * @param minRoomSide minimum side length of a room
     * @param maxRoomSide maximum side length of a room
     * @param minRoomDist minimum distance between two rooms' centers
     * @param maxRoomDist maximum distance with the closest room's center for each
     *                    room center
     * @return A Tilemap with nRooms square rooms connected by corridors in a tree,
     *         plus some random corridors minRoomDist > maxRoomSide is recommended
     */
    private TileMap normal(final int nRooms, final int minRoomSide, final int maxRoomSide, final int minRoomDist,
            final int maxRoomDist) {
        if (nRooms <= 0) {
            throw new IllegalArgumentException();
        }
        if (minRoomSide <= 0) {
            throw new IllegalArgumentException();
        }
        if (maxRoomSide < minRoomSide) {
            throw new IllegalArgumentException();
        }
        if (minRoomDist < 0) {
            throw new IllegalArgumentException();
        }
        if (maxRoomDist < minRoomDist) {
            throw new IllegalArgumentException();
        }
        final ArrayList<Point> pool = new ArrayList<>(); // pool of all points at acceptable distances from the already
                                                         // selected
        // rooms
        final var centers = new ArrayList<Point>(); // selected rooms' centers
        final var dist = new HashMap<Point, Integer>(); // distance to closest selected center for all points
        pool.add(new Point(0, 0)); // starting point does not matter to the final structure
        dist.put(pool.get(0), 0);
        for (int room = 0; room < nRooms; room++) {
            final Point p = pool.get(rand.nextInt(pool.size())); // get a random point from the pool
            centers.add(p);
            for (int dy = -maxRoomDist; dy <= maxRoomDist; dy++) { // recalculate distances of all points in range
                for (int dx = Math.abs(dy) - maxRoomDist; dx <= maxRoomDist - Math.abs(dy); dx++) {
                    final Point cp = new Point(p.x + dx, p.y + dy);
                    final int formerDist = dist.getOrDefault(cp, maxRoomDist + 1);
                    final int currentDist = cp.dist(p);
                    if (currentDist < formerDist) {
                        dist.put(cp, currentDist);
                        if (formerDist > maxRoomDist) {
                            pool.add(cp); // add points that were too far before
                        }
                    }
                }
            }
            for (int i = 0; i < pool.size(); i++) {
                if (dist.get(pool.get(i)) < minRoomDist) { // remove points that are now too close
                    pool.set(i, pool.get(pool.size() - 1));
                    pool.remove(pool.size() - 1);
                    i--;
                }
            }
        }
        int minY = 0, minX = 0, maxY = 0, maxX = 0; // get map boundaries to shift coordinates and calculate map size
        for (final Point p : centers) {
            if (p.y < minY) {
                minY = p.y;
            }
            if (p.x < minX) {
                minX = p.x;
            }
            if (p.y > maxY) {
                maxY = p.y;
            }
            if (p.x > maxX) {
                maxX = p.x;
            }
        }
        final int w = maxX - minX + maxRoomSide + 4; // this way a room should not touch the edges
        final int h = maxY - minY + maxRoomSide + 4;
        final int dx = 2 + (maxRoomSide + 1) / 2 - minX;
        final int dy = 2 + (maxRoomSide + 1) / 2 - minY;
        for (int i = 0; i < centers.size(); i++) {
            final var p = centers.get(i);
            centers.set(i, new Point(p.x + dx, p.y + dy));
        }
        final var res = new Tile[h][w]; // new tile map initially filled with wall
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                res[y][x] = Tile.WALL;
            }
        }
        for (final Point p : centers) { // add the rooms with random sizes
            final int roomH = rand.nextInt(maxRoomSide - minRoomSide + 1) + minRoomSide;
            final int roomW = rand.nextInt(maxRoomSide - minRoomSide + 1) + minRoomSide;
            for (int y = p.y - roomH / 2; y <= p.y + (roomH + 1) / 2; y++) {
                for (int x = p.x - roomW / 2; x <= p.x + (roomW + 1) / 2; x++) {
                    res[y][x] = Tile.FLOOR;
                }
            }
        }
        for (int i = 1; i < centers.size(); i++) {
            final var p = centers.get(i);
            var p0 = centers.get(0);
            for (int j = 1; j < i; j++) { // get closest room already connected to the tree
                if (p.dist(centers.get(j)) < p.dist(p0)) {
                    p0 = centers.get(j);
                }
            }
            makeCorridor(p0, p, res); // add the corridor to the tree
            if (rand.nextInt(4) == 0) { // choose if to add a random corridor (dead end or cycle) to this room too
                final var randomPoint = new Point(rand.nextInt(w - 2) + 1, rand.nextInt(h - 2) + 1);
                if (p.dist(randomPoint) <= 2 * maxRoomDist) { // if the corridor would be too long, don't add it
                    makeCorridor(p, randomPoint, res);
                }
            }
        }
        return new TileMapImpl(res);
    }

    /**
     * @return a TileMap with the default settings using a given seed
     */
    @Override
    public TileMap seededDef(final long seed) {
        rand.setSeed(seed);
        return normal(NORMAL_N_ROOMS, NORMAL_MIN_ROOM_SIDE, NORMAL_MAX_ROOM_SIDE, NORMAL_MIN_ROOM_DIST,
                NORMAL_MAX_ROOM_DIST); // and call the normal builder with default parameters
    }

    /**
     * @return a TileMap with the default settings using a seed based on time
     */
    @Override
    public TileMap def() {
        return seededDef(System.currentTimeMillis());
    }

    /**
     * @return an hxw TileMap with walls on the borders and floor inside
     */
    @Override
    public TileMap empty(final int h, final int w) {
        if (h < 1 || w < 1) {
            throw new IllegalArgumentException();
        }
        final var res = new Tile[h][w];
        for (int y = 0; y < h; y++) {
            res[y][0] = Tile.WALL;
            res[y][w - 1] = Tile.WALL;
        }
        for (int x = 0; x < w; x++) {
            res[0][x] = Tile.WALL;
            res[h - 1][x] = Tile.WALL;
        }
        for (int y = 1; y < h - 1; y++) {
            for (int x = 1; x < w - 1; x++) {
                res[y][x] = Tile.FLOOR;
            }
        }
        return new TileMapImpl(res);
    }

}
