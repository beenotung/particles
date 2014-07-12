public class Coor2D implements Cloneable {
	public double x, y;

	public Coor2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Coor2D clone() {
		return new Coor2D(x, y);
	}

}
