
public class Helpers {
	
	public static void main(String[] args) {
		Picture pic = new Picture(FileChooser.pickAFile());
		explore(toArray(pic.getPixels2D()));
		int[][][] pix = toArray(pic.getPixels2D());
		int[][] greyPix = colorToGrey(toArray(pic.getPixels2D()));
		int[][] bw = new int[pix.length][pix[0].length];
		int count = 0;
		int sumg = 0;
		int sumb = 0;
		int sumr = 0;
		int[] cream = {244, 241, 226};
		int[] dark = {9, 25, 22};
		int[] label = {163, 202, 199};
		for(int i = 0; i < pix.length; i++) {
			for(int j = 0; j < pix[0].length; j++) {
//				sumg += distance(avg, pix[i][j]);
//				count++;
				//System.out.println(pix[i][j][1]);
				if(distance(cream, pix[i][j]) > 50 && distance(dark, pix[i][j]) > 50 && distance(label, pix[i][j]) > 50) {
					bw[i][j] = 255;
					sumg += pix[i][j][1];
					count++;
					sumb += pix[i][j][2];
					sumr += pix[i][j][0];
				}
			}
			
		}
		explore(greyToColor(bw));
		//write(colorToGrey(toArray(pic.getPixels2D())), "greyScaleBlueJuice.jpg");
		System.out.println(count + " " + count);
		System.out.println("green average:" + (sumg / count));
		System.out.println("blue average:" + (sumb / count));
		System.out.println("red average:" + (sumr / count));
	}
	//--------------------------------------------------------------------------------------------------
	/**
	 * Converts a Pixel array to a 3D array
	 * @param pix Pixel array to be converted
	 * @return 3D integer array representing a color image
	 */
	public static int[][][] toArray(Pixel[][] pix) {
		int[][][] img = new int[pix.length][pix[0].length][3];
		for(int i = 0; i < pix.length; i++) {
			for(int j = 0; j < pix[0].length; j++) {
				img[i][j][0] = pix[i][j].getRed();
				img[i][j][1] = pix[i][j].getGreen();
				img[i][j][2] = pix[i][j].getBlue();
			}
		}
		return img;
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Converts a 3D image array to a Picture array
	 * @param img 3D array to be converted
	 * @return Picture array representing the image array
	 */
	public static Picture toPicture(int[][][] img) {
		Picture pic = new Picture(img.length, img[0].length);
		Pixel[][] pix = pic.getPixels2D();
		for(int i = 0; i < img.length; i++) {
			for(int j = 0; j < img[0].length; j++) {
				pix[i][j].setRed(img[i][j][0]);
				pix[i][j].setGreen(img[i][j][1]);
				pix[i][j].setBlue(img[i][j][2]);
			}
		}
		return pic;
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Converts double array to and integer array
	 * @param doubleArr double array to be converted
	 * @return integer array representing the double array
	 */
	public static int[][][] toIntArray(double[][][] doubleArr) {
		int[][][] intArr = new int[doubleArr.length][doubleArr[0].length][doubleArr[0][0].length];
		for(int i = 0; i < doubleArr.length; i++) {
			for(int j = 0; j < doubleArr[0].length; j++) {
				for(int k = 0; k < doubleArr[0][0].length; k++) {
					intArr[i][j][k] = (int) doubleArr[i][j][k];
				}
			}
		}
		return intArr;
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Converts integer array to double array
	 * @param intArr integer array to be converted
	 * @return double array representing the integer array
	 */
	public static double[][][] toDoubleArray(int[][][] intArr) {
		double[][][] doubleArr = new double[intArr.length][intArr[0].length][intArr[0][0].length];
		for(int i = 0; i < intArr.length; i++) {
			for(int j = 0; j < intArr[0].length; j++) {
				for(int k = 0; k < intArr[0][0].length; k++) {
					doubleArr[i][j][k] = (int) intArr[i][j][k];
				}
			}
		}
		return doubleArr;
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * explores a greyscale image
	 * @param img the greyscale image
	 */
	public static void explore(int[][] img) {
		explore(greyToColor(img));
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Explores a color image
	 * @param img the color image
	 */
	public static void explore(int[][][] img) {
		Picture pic = toPicture(img);
		pic.explore();
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Shows a greyscale image
	 * @param img the greyscale image
	 */
	public static void show(int[][] img) {
		show(greyToColor(img));
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Shows a color image
	 * @param img the color image
	 */
	public static void show(int[][][] img) {
		Picture pic = toPicture(img);
		pic.show();
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Writes a greyscale image to a file
	 * @param img the greyscale image
	 */
	public static void write(int[][] img, String fileName) {
		Picture pic = toPicture(greyToColor(img));
		pic.write(fileName);
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Writes a color image to a file
	 * @param img the color image
	 */
	public static void write(int[][][] img, String fileName) {
		Picture pic = toPicture(img);
		pic.write(fileName);
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Scales a greyscale image
	 * @param img image to be scaled
	 * @param x x value to be scaled by
	 * @param y y value to be scaled by
	 * @return scaled greyscale image scaled
	 */
	public static int[][] scale(int[][] img, double x, double y) {
		int[][][] a = greyToColor(img);
		return colorToGrey(scale(a, x, y));
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Scales a color image
	 * @param img image to be scaled
	 * @param x x value to be scaled by
	 * @param y y value to be scaled by
	 */
	public static int[][][] scale(int[][][] img, double x, double y) {
		Picture pic = toPicture(img);
		pic.scale(x, y);
		int[][][] arr = toArray(pic.getPixels2D());
		return arr;
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Converts a color image to a grey image
	 * @param color color image
	 * @return greyscale image
	 */
	public static int[][] colorToGrey(int[][][] color) {
		int[][] grey = new int[color.length][color[0].length];
		for(int i = 0; i < color.length; i++) {
			for(int j = 0; j < color[0].length; j++) {
				grey[i][j] = (int) (.21 * color[i][j][0] + .72 * color[i][j][1] + .72 * color[i][j][2]);
			}
		}
		return grey;
	}
	//-------------------------------------------------------------------------------------------------------------
		/**
		 * Returns a bw image
		 * @param color image
		 * @return bw array
		 */
		public static int[][] bw(int[][][] color) {
			int n;
			int[][] bw = colorToGrey(color);
			for(int i = 0; i < color.length; i++) {
				for(int j = 0; j < color[0].length; j++) {
					if(bw[i][j] < 128) {
						bw[i][j] = 0;
					}
					else {
						bw[i][j] = 255;
					}
				}
			}
			return bw;
		}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Converts a grey image to a color image
	 * @param grey grey image
	 * @return color image
	 */
	public static int[][][] greyToColor(int[][] grey) {
		int[][][] color = new int[grey.length][grey[0].length][3];
		for(int i = 0; i < grey.length; i++) {
			for(int j = 0; j < grey[0].length; j++) {
				color[i][j][0] = grey[i][j];
				color[i][j][1] = grey[i][j];
				color[i][j][2] = grey[i][j];
			}
		}
		return color;
	}
	//-------------------------------------------------------------------------------------------------------------
	/**
	 * Finds the color distance between two pixels
	 * @param a First color channel
	 * @param b Second color channel
	 * @return color distance between the two channels
	 */
	public static int distance(int[] a, int[] b) {
		return (int) Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2) + Math.pow(a[2] - b[2], 2));
	}
	public static int[][][] goCrazy(int[][][] img) {
		int temp;
		for(int i = 0; i < img.length; i++) {
			for(int j = 0; j < img[0].length; j++) {
				temp = img[i][j][1];
				img[i][j][2] = 0;
				img[i][j][0] = 0;
				
			}
		}
		return img;
	}
}
