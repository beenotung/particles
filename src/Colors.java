public class Colors {

	public static int getColorCode(int r, int g, int b) {
		return r << 16 + g << 8 + b;
	}

}
