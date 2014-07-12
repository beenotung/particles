import java.awt.geom.Point2D;

public class Particle {
	private Point2D location, speed, acceleration;
	private double time;
	double dx, dy;

	public Particle(Point2D location, Point2D speed, Point2D acceleration) {
		this.location = location;
		this.speed = speed;
		this.acceleration = acceleration;
	}

	public void calc(double t) {
		time = t;
		dx = calcS(speed.getX(), acceleration.getX(), time);
		dy = calcS(speed.getY(), acceleration.getY(), time);
	};

	public void move() {
		location.setLocation(location.getX() + dx, location.getY() + dy);
		speed.setLocation(dx, dy);
	};

	public double calcS(double u, double a, double t) {
		return u * t + 0.5 * a * t * t;
	}

}
