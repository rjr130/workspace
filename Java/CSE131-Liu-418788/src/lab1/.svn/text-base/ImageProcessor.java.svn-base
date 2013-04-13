package lab1;
/**
 * Name: Ruiqi,Liu
 * Lab Section: A
 * Date:29/01/2012
 * ImageProcessor.java
 * CSE 131 Lab 1
 */

import java.awt.Color;

import nip.*;


public class ImageProcessor {
	// Some sample methods:

	// This method cuts each color component of a pixel in half to produce the new image.
	// USED IN: example_darker
	public static int darker(int pixelComponent) {
		return pixelComponent/2;
	}

	// This method sums the color components of two pixels to produce a third.
	// Note that when the total exceeds 255, there is a strange effect.
	// USED IN: example_combine
	public static int combine(int pixelAComponent, int pixelBComponent) {
		return pixelAComponent+pixelBComponent;
	}

	// This method takes the color of each pixel and creates a new color without any green.
	// USED IN: example_purplish
	public static Color purplish(Color c) {
		int red = c.getRed();
		int blue = c.getBlue();
		return new Color(red, 0, blue);
	}

	// Now that you've seen the examples, complete the following methods.
	// The headers have been completed for you.
	//
	// NB: The 'return 0' and 'return new Color(0,0,0)' lines are simply placeholders
	// to prevent the compiler from complaining.  They should be removed or modified when
	// you add your implementation.

	//This method copies the components of a pixel without changing it.
	// USED IN: copy
	public static int copy(int pixelComponent) {
		return pixelComponent;
	}
	//This method averages the color components of two pixels.
	// USED IN: composite
	public static int composite(int a, int b) {
		return a = a/2 + b/2;
	}

	//This method returns the negative of a pixel by inverting its color components.
	// USED IN: negative
	public static int negative(int a) {
		return a = 255-a;
	}

	//This method reduces the number of possible values for a given color component
	//from 256 to 2, by returning either 0 or 255 based on the original value.
	// USED IN: posterize
	public static int posterize(int a) {
		return (a/128)*255;
	}

	//This method returns a color that is brighter than the original color.
	// USED IN: brighter
	public static Color brighter(Color c) {
		return c.brighter();
	}

	//This method returns a color that is some shade of gray, by making a new
	//color having equal RGB components.
	// USED IN: grayscale
	public static Color grayscale(Color c) {
		int red = c.getRed();
		int green = c.getGreen();
		int blue = c.getBlue();
		return new Color((red+green+blue)/3,(red+green+blue)/3,(red+green+blue)/3);
	}

	//This method returns either black or white, based on the intensity of the
	//originally provided color.
	// USED IN: blackWhite
	public static Color blackAndWhite(Color c) {
		int red=c.getRed();
		int green=c.getGreen();
		int blue=c.getBlue();
		int newC = (red+green+blue)/3;
		if (newC<=128)
			return Color.BLACK;
		else
            return Color.WHITE;
    }

	//This method combines two images by choosing for each location the brighter 
	//pixel in the same location from the two source images.
	// USED IN: combineBrighter
	public static Color combineBrighter(Color c, Color d) {   
		int red1=c.getRed();
		int green1=c.getGreen();
		int blue1=c.getBlue();
		int Addtion1=(red1+green1+blue1);
		int red2=d.getRed();
		int green2=d.getGreen();
		int blue2=d.getBlue();
		int Addtion2=(red2+green2+blue2);
		if (Addtion1>Addtion2)
			return new Color(red1,green1,blue1);
		else
			return new Color(red2,green2,blue2);
	}
	
	//The following two methods are for the optional extension for this lab.
	
	//This method performs background subtraction by returning the color blue
	//if the two colors are close enough; otherwise, it returns the second color.
	public static Color bgSubtract(Color background, Color source) {
		int red1=background.getRed();
		int green1=background.getGreen();
		int blue1=background.getBlue();
		int red2=source.getRed();
		int green2=source.getGreen();
		int blue2=source.getBlue();
		int Ab1=Math.abs(red1-red2);
		int Ab2=Math.abs(green1-green2);		
		int Ab3=Math.abs(blue1-blue2);
		if (Ab1<=25 && Ab2<=25 &&Ab3<=25)
			return Color.BLUE;
		else
			return source;  
	}

	//This method performs background replacement by returning the color from the
	//second image if the color from the first image is blue; otherwise returns
	//the color from the first image.
	public static Color bgReplace(Color foreground, Color back) {
		if (foreground.equals(Color.BLUE))
			return back;
		else
			return foreground;
	}


	/**
	 * Returns the name of this image processing class.
	 */
	public String toString() {
		return "Image Processor";
	}

	/*
	 * Java always looks for a "public static void main" method as the starting point for an application.
	 * The parameter, which you will ignore, is used for applications that take parameters from the command line.
	 * 
	 * This method creates a Ye Olde Photo Shoppe (NIP) object and then creates a new instance of the 
	 * ProcessorTool class (which uses the methods you've defined here) as the tool to be used by NIP.
	 *
	 */
	public static void main(String[] args) {
		NIP nip = new NIP(200, 300, 3, "one-bear.jpg", "brookings.jpg", "wrighton.jpg");   // create a NIP window with 3 panels, each 200x300
		nip.setTool(new ProcessorTool());  // set the current NIP tool to an instance of your ImageProcessor

		// If you do the optional extension for this assignment, uncomment the following line.
		new NIP(new ExtensionTool(), 200, 150, 3, "one-bear.jpg", "two-bears.jpg");
	}	

}
