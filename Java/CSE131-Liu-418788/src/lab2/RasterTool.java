package lab2;

import java.awt.Color;

import nip.*;

/**
 * Name: Ruiqi,Liu Lab Section: A Date: 06/02/2012 Email: ruiqi.liu@ wustl.edu
 * RasterTool.java CSE 131 Lab 2
 */

public class RasterTool extends Tool {

	// Your methods go here.

	public void flipHoriz(Image source, Image target) {
		for (int x = 0; x < source.getWidth(); x++) {
			for (int y = 0; y < source.getHeight(); y++) {
				target.setPixel(x, y,source.getPixel(source.getWidth() - 1 - x, y));
			}
		}
	}

	public String[] getEventNames() {
		String[] s = { "Flip Horizontally", 
				"Flip Vertically",
				"Flip Left Half Horizontally", 
				"Flip Bottom Half Vertically",
				"Color Gradient", 
		"Edge Detection" };
		return s;
	}

	// Don't forget to tell your tool here how it should respond when a menu
	// item is clicked!
	public void actionNameCalled(String name) {
		// horizontally
		if (name.equals("Flip Horizontally"))
			flipHoriz(nip.getPrimarySourceImage(), nip.getTargetImage());
		// vertically
		if (name.equals("Flip Vertically"))
			flipVert(nip.getPrimarySourceImage(), nip.getTargetImage());
		// left half horizontally
		if (name.equals("Flip Left Half Horizontally"))
			flipHorizLeftHalf(nip.getPrimarySourceImage(), nip.getTargetImage());
		// bottom half vertically
		if (name.equals("Flip Bottom Half Vertically"))
			flipVertBotHalf(nip.getPrimarySourceImage(), nip.getTargetImage());
		// Edge Detection
		if (name.equals("Edge Detection"))
			edgeDetect(nip.getPrimarySourceImage(),nip.getTargetImage());
		if (name.equals("Color Gradient"))
			gradient(nip.getTargetImage());
	}

	public void flipVert(Image source, Image target) {
		for (int x = 0; x < source.getWidth(); x++) {
			for (int y = 0; y < source.getHeight(); y++) {
				target.setPixel(x, y,source.getPixel(x, source.getHeight()-1-y));
			}
		}
	}

	public void flipHorizLeftHalf(Image source, Image target) {
		for (int x = 0; x <source.getWidth(); x++) {
			for (int y = 0; y < source.getHeight(); y++) {
				if(x <= 0.5*source.getWidth()) 
					target.setPixel(x, y,source.getPixel(x, y));
				else    target.setPixel(x,y,source.getPixel(source.getWidth()-1-x,y));
			}
		}
	}

	public void flipVertBotHalf(Image source, Image target) {
		for (int x = 0; x <source.getWidth(); x++) {
			for (int y = 0; y < source.getHeight(); y++) {
				if(y >= 0.5*source.getHeight()) 
					target.setPixel(x, y,source.getPixel(x, y));
				else    target.setPixel(x, y,source.getPixel(x, source.getHeight()-1-y));
			}
		}
	}

	public boolean edgeHelper(Image source, int x, int y) {
		Color sourceImage = source.getPixelColor(x, y);
		Color image1 = source.getPixelColor(x-1, y);
		Color image2 = source.getPixelColor(x+1, y);
		Color image3 = source.getPixelColor(x, y-1);
		Color image4 = source.getPixelColor(x, y+1);
		int red = sourceImage.getRed();int green = sourceImage.getGreen();int blue = sourceImage.getBlue();
		int red1 = image1.getRed();int green1 = image1.getGreen();int blue1 = image1.getBlue();
		int red2 = image2.getRed();int green2 = image2.getGreen();int blue2 = image2.getBlue();
		int red3 = image3.getRed();int green3 = image3.getGreen();int blue3 = image3.getBlue();
		int red4 = image4.getRed();int green4 = image4.getGreen();int blue4 = image4.getBlue();
		int m1 = Math.abs(red-red1);int m2 = Math.abs(red-red2);int m3 = Math.abs(red-red3);int m4 = Math.abs(red-red4);
		int n1 = Math.abs(blue-blue1);int n2 = Math.abs(blue-blue2);int n3 = Math.abs(blue-blue3);int n4 = Math.abs(blue-blue4);
		int p1 = Math.abs(green-green1);int p2 = Math.abs(green-green2);int p3 = Math.abs(green-green3);int p4 = Math.abs(green-green4);
		if ((m1 > 50 && n1 > 50 && p1 > 50)||(m2 > 50 && n2 > 50 && p2 > 50)||(m3 > 50 && n3 > 50 && p3 > 50)||(m4 > 50 && n4 > 50 && p4 > 50))
			return true;
		else 
			return false;
	}
	public void edgeDetect(Image source, Image target) {
		for (int x = 1; x < source.getWidth() - 1; x++){
			for (int y = 1; y < source.getHeight() - 1; y++){
				if (edgeHelper(source, x, y) == true)
					target.setPixel(x, y, new Color(0, 0, 0));
				else 
					target.setPixel(x, y, new Color(255, 255, 255));
			}
		}
	}

	
	public void gradient (Image target) {
		double green = 0.0;
		for (int y = 0; y < target.getHeight(); y++) {
			green = green + 255.0/target.getHeight();
		double red = 0.0;
		for (int x = 0; x < target.getWidth(); x++) {
			red = red + 255.0/target.getWidth();
		target.setPixel(x, y, new Color((int)red,(int)green, 128));
			}
		}
	} 





	public String toString() {
		return "raster";
	}

	public static void main(String args[]) {
		new NIP(new RasterTool(), 200, 300, 3, "", "brookings.jpg",
				"two-bears.jpg");
	}

}
