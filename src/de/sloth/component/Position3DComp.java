package de.sloth.component;

/**
 * Data for a 3 dimensional position
 * [SlothCore]
 * @author Slothking
 *
 */
public class Position3DComp extends Position2DComp {

	private int z;
	
	public Position3DComp() {
		super();
		z = 1;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Position3DComp [z=" + z + ", getX()=" + getX() + ", getY()=" + getY() + "]";
	}
	
	
}