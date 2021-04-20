package eu.eutampieri.catacombs.model;

import java.util.List;

import eu.eutampieri.catacombs.model.map.TileMap;

public class Projectile extends GameObject implements HealthModifier {
	private final TileMap map;
	private final int strength;
	private boolean toErase;
	private final static int BOX_SIZE = 8;

	public Projectile(final int x, final int y, final int sx, final int sy, final int strength, final TileMap map){
		super(x,y,GameObjectType.BULLET);
		this.speedX=sx;
		this.speedY=sy;
		this.strength=strength;
		this.map=map;
	}

	@Override
	public void update(final long delta, final List<GameObject> others) {
		posX+=this.speedX;
		posY+=this.speedY;
		this.hitBox.move(this.speedX,this.speedY);
		for(final var o: others){
			if(o instanceof LivingCharacter && o.getHitBox().overlaps(this.getHitBox())) {
				this.useOn((LivingCharacter)o);
				this.toErase=true;
				break;
			}
		}
		if(!map.at(this.posX/16, this.posY/16).isWalkable()){
			this.toErase=true;
		}
	}

	@Override
	public boolean isMarkedForDeletion() {
		return this.toErase;
	}

	@Override
	public int getHealthDelta() {
		return -strength;
	}
	@Override
	public String getName() {
		return "bullet";
	}

	@Override
	public void initializeHitBox() {
		this.hitBox = new CollisionBox(this.posX, this.posY, BOX_SIZE, BOX_SIZE);
	}
}
