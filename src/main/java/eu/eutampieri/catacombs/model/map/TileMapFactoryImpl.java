package eu.eutampieri.catacombs.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TileMapFactoryImpl implements TileMapFactory {

	/**
	 * prng used to generate maps
	 */
	private Random rand = new Random();

	/**
	 * A useful helper class for map generation
	 */
	private class Point {
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
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		final int x;
		final int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int dist(Point o) {
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
	private void makeCorridor(final Point a, final Point b, Tile[][] res) {
		int y = a.y;
		int x = a.x;
		while (y != b.y && x != b.x) {
			if (rand.nextBoolean()) {
				if (y < b.y)
					y++;
				else
					y--;
			} else {
				if (x < b.x)
					x++;
				else
					x--;
			}
			res[y][x] = Tile.FLOOR;
		}
		while (y != b.y) {
			if (y < b.y)
				y++;
			else
				y--;
			res[y][x] = Tile.FLOOR;
		}
		while (x != b.x) {
			if (x < b.x)
				x++;
			else
				x--;
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
	private TileMap normal(int nRooms, int minRoomSide, int maxRoomSide, int minRoomDist, int maxRoomDist) {
		if (nRooms <= 0)
			throw new IllegalArgumentException();
		if (minRoomSide <= 0)
			throw new IllegalArgumentException();
		if (maxRoomSide < minRoomSide)
			throw new IllegalArgumentException();
		if (minRoomDist < 0)
			throw new IllegalArgumentException();
		if (maxRoomDist < minRoomDist)
			throw new IllegalArgumentException();
		var pool = new ArrayList<Point>(); // pool of all points at acceptable distances frome the already selected
											// rooms
		var centers = new ArrayList<Point>(); // selected rooms' centers
		var dist = new HashMap<Point, Integer>(); // distance to closest selected center for all points
		pool.add(new Point(0, 0)); // starting point does not matter to the final structure
		dist.put(pool.get(0), 0);
		for (int room = 0; room < nRooms; room++) {
			final Point p = pool.get(rand.nextInt(pool.size())); // get a random point from the pool
			centers.add(p);
			for (int dy = -maxRoomDist; dy <= maxRoomDist; dy++) { // recalculate distances of all points in range
				for (int dx = Math.abs(dy) - maxRoomDist; dx <= maxRoomDist - Math.abs(dy); dx++) {
					final Point cp = new Point(p.x + dx, p.y + dy);
					int formerDist = dist.getOrDefault(cp, maxRoomDist + 1);
					int currentDist = cp.dist(p);
					if (currentDist < formerDist) {
						dist.put(cp, currentDist);
						if (formerDist > maxRoomDist)
							pool.add(cp); // add points that were too far before
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
		for (Point p : centers) {
			if (p.y < minY)
				minY = p.y;
			if (p.x < minX)
				minX = p.x;
			if (p.y > maxY)
				maxY = p.y;
			if (p.x > maxX)
				maxX = p.x;
		}
		int w = maxX - minX + maxRoomSide + 4; // this way a room should not touch the edges
		int h = maxY - minY + maxRoomSide + 4;
		int dx = 2 + (maxRoomSide + 1) / 2 - minX;
		int dy = 2 + (maxRoomSide + 1) / 2 - minY;
		for (int i = 0; i < centers.size(); i++) {
			var p = centers.get(i);
			centers.set(i, new Point(p.x + dx, p.y + dy));
		}
		var res = new Tile[h][w]; // new tile map initially filled with wall
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				res[y][x] = Tile.WALL;
			}
		}
		for (Point p : centers) { // add the rooms with random sizes
			final int roomH = rand.nextInt(maxRoomSide - minRoomSide + 1) + minRoomSide;
			final int roomW = rand.nextInt(maxRoomSide - minRoomSide + 1) + minRoomSide;
			for (int y = p.y - roomH / 2; y <= p.y + (roomH + 1) / 2; y++) {
				for (int x = p.x - roomW / 2; x <= p.x + (roomW + 1) / 2; x++) {
					res[y][x] = Tile.FLOOR;
				}
			}
		}
		for (int i = 1; i < centers.size(); i++) {
			var p = centers.get(i);
			var p0 = centers.get(0);
			for (int j = 1; j < i; j++) { // get closest room already connected to the tree
				if (p.dist(centers.get(j)) < p.dist(p0)) {
					p0 = centers.get(j);
				}
			}
			makeCorridor(p0, p, res); // add the corridor to the tree
			if (rand.nextInt(4) == 0) { // choose if to add a random corridor (dead end or cycle) to this room too
				var randomPoint = new Point(rand.nextInt(w), rand.nextInt(h));
				if (p.dist(randomPoint) <= 2 * maxRoomDist) { // if the corridor would be too long, don't add it
					makeCorridor(p, randomPoint, res);
				}
			}
		}
		return new TileMapImpl(res);
	}

	/**
	 * @return a TileMap with the default settings and a random seed based on time
	 */
	@Override
	public TileMap def() {
		rand.setSeed(System.currentTimeMillis()); // seed the prng based on time
		return normal(16, 8, 16, 32, 42); // and call the normal builder with default parameters
	}

}
