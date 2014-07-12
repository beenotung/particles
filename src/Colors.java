public class Colors {

	public static int getColorCode(int r, int g, int b) {
		return r << 16 + g << 8 + b;
	}

	public static int getColorCode(double r, double g, double b) {
		return (int) (r * 255) << 16 + (int) (g * 255) << 8 + (int) (b * 255);
	}

}
