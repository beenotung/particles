public class Particle {
	public Coor2D location, speed, acceleration;
	public double dx, dy;

	Particle(Coor2D location, Coor2D speed, Coor2D acceleration) {
		this.location = (Coor2D) location.clone();
		this.speed = (Coor2D) speed.clone();
		this.acceleration = (Coor2D) acceleration.clone();
	}

	public void move() {
		location.x+=dx;
		location.y+=dy;
		speed.set(dx, dy);
	}

	public void bound(double xMin, double yMin, double xMax, double yMax) {
		double x = location.x;
		double y = location.y;

		if (x < xMin)
			x = xMin + (xMin - x);
		if (x > xMax)
			x = xMax - (x - xMax);
		if (y < yMin)
			y = yMin + (yMin - y);
		if (y > yMax)
			y = yMax - (y - yMax);

		if ((x != location.x) || (y != location.y)) {
			if (x != location.x)
				speed.x *= -1;
			if (x != location.x)
				speed.y *= -1;
		}

		location.x = x;
		location.y = y;
	};
}
