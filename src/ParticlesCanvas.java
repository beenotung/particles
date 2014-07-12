import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.sun.org.apache.xml.internal.serializer.utils.Utils;

public class ParticlesCanvas extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final String VERSION = "Particles 0.0 -by Beeno Tung";
	private boolean running;
	private Thread t;

	private final int WIDTH;
	private final int HEIGHT;
	private final int SCALE;

	private JFrame frame;

	private BufferedImage image;
	private int[] pixels;

	private final int NPARTICLE;
	private Particle[] particles;

	private int tickCount = 0;

	ParticlesCanvas(int width, int height, int scale, int n) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.SCALE = scale;
		this.NPARTICLE = n;

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		setMaximumSize(new Dimension(WIDTH * SCALE * 2, HEIGHT * SCALE * 2));
		setMinimumSize(new Dimension(WIDTH * SCALE / 2, HEIGHT * SCALE / 2));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(VERSION);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		particles = new Particle[NPARTICLE];
	}

	@Override
	public void run() {
		init();
		int ticks = 0;
		int renders = 0;
		double nsPerTick = 1000000000D / 60D;
		double nsPerRender = 1000000000D / 60D;
		long lastTime = System.nanoTime();
		long currentTime;
		double deltaTick = 0;
		double deltaRender = 0;
		long lastDebug = System.currentTimeMillis();
		while (running) {
			currentTime = System.nanoTime();
			deltaTick += (currentTime - lastTime) / nsPerTick;
			deltaRender += (currentTime - lastTime) / nsPerRender;
			lastTime = currentTime;
			if (deltaTick > 0) {
				ticks++;
				tick();
				deltaTick--;
			}
			if (deltaRender > 0) {
				renders++;
				render();
				deltaRender--;
			}

			if (System.currentTimeMillis() - lastDebug >= 1000) {
				lastDebug += 1000;
				System.out.println(ticks + " TPS, " + renders + " FPS");
				ticks = 0;
				renders = 0;
			}
		}
	}

	private void tick() {
		tickCount++;
	}

	private void render() {
	}

	private void init() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = Colors.getColorCode(0, 0, 0);

		Point2D location=new Point2D.Double(0,0);
		Point2D speed=new Point2D.Double(1,1);
		Point2D acceleration=new Point2D.Double(0,0);
		for (int i = 0; i < particles.length; i++){
			location.setLocation();

			particles[i]=new Particle(location, speed, acceleration);
		}
	}

	public synchronized void start() {
		if (t == null)
			t = new Thread(this);
		t.start();
		running = true;
	}

	public void stop() {
		running = false;
	}

	public boolean isRunning() {
		return running;
	}
}
