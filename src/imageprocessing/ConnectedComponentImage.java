package imageprocessing;

import java.util.HashMap;
import java.util.Random;
import java.awt.Color;
import imageprocessing.Luminance;
import edu.princeton.cs.introcs.Picture;

/*************************************************************************
 * Compilation: javac ConnectedComponentImage.java
 * 
 * The <tt>ConnectedComponentImage</tt> class
 * <p>
 * This program is deigned to manipulate an image in a few different ways.
 * Features include being able to print out the original image, a black and white version of the image
 * a binarized image and the ability to display the size of an image through height and width as well as
 * the amount of pixels in an image.
 * The program will also work in a quick union feature where you can count how many objects
 *  are in an image and highlight them
 *  </p>
 * 
 *************************************************************************/


public class ConnectedComponentImage {
    
	Picture picture;
	String fileLocation;
	int width;
	int height;
	
	WeightedQuickUnionUF wqu;

	

	public ConnectedComponentImage(String fileLocation) {
		picture = getPicture(fileLocation);
		this.fileLocation = fileLocation;
		width  = picture.width();
	    height = picture.height();
	    wqu = new WeightedQuickUnionUF(width*height);
	}

	/**
	 * Returns the number of components identified in the image.
	 * 
	 * @return the number of components (between 1 and N)
	 */
	
	public int countComponents() {
        Picture countComponentsPicture = new Picture(picture);
        int p=0;
        for (int y = 0; y < height; y++) 
        {
           	for (int x = 0; x < width; x++) 
           	{
            	//Used to check if the pixel contains white.
            	if (Luminance.lum(countComponentsPicture.get(x, y))>128.0) 
            	{
            		//Used to determine if the pixel to the left should be connected to the current pixel.
            		if (x>0) 
            		{
            			if (Luminance.lum(countComponentsPicture.get(x-1, y))>128.0) 
            			{
            				wqu.union(p, p-1);
            			}
            		} 
            		//Used to determine if the pixel to above should be connected to the current pixel.
            		if (y>0) 
            		{
            			if (Luminance.lum(countComponentsPicture.get(x, y-1))>128.0) 
            			{
            				wqu.union(p, p-width);
            			}
            		} 
            	
            	} 
            	else 
            	{
             	  
                }
            	p++;
            } 
        }
		return wqu.count();
	}     	
	
	/**
	 * Will show original image
	 * @return
	 */
            		
	public Picture orignalImage()
	{
		Picture pic = new Picture(picture);
	
		pic.show();
		
		return pic;
	
}

	/**
	 * Returns the original image with each object bounded by a box.
	 * 
	 * @return a picture object with all components surrounded by a box
	 */
	public Picture identifyComponentImage() 
	{
		Color line = Color.RED;
		Picture identifiedPicture = new Picture(picture);
		int maxX = 0;
		int minX = identifiedPicture.width();
		int maxY = 0;
		int minY = identifiedPicture.height();
		

		for (int x = 0; x < identifiedPicture.width(); x++) 
		{
			for (int y = 0; y < identifiedPicture.height(); y++) 
			{
				if (!identifiedPicture.get(x, y).equals(Color.WHITE) ) 
				{
					if (x < minX)
						minX = x;
					if (x > maxX)
						maxX = x;
					if (y < minY)
						minY = y;
					if (y > maxY)
						maxY = y;
				}
			}
		}

		for (int x = minX; x <= maxX; x++) 
		{
			identifiedPicture.set(x, minY, line);
			identifiedPicture.set(x, maxY, line);
		}
		for (int y = minY; y <= maxY; y++) 
		{
			identifiedPicture.set(minX, y, line);
			identifiedPicture.set(maxX, y, line);
		}
		return identifiedPicture;
	}

	/**
	 * Returns a picture with each object updated to a random colour.
	 * 
	 * @return a picture object with all components coloured.
	 */
	public Picture colourComponentImage() 
	{
		Random colour = new Random();
		HashMap<Integer, Color> table = new HashMap<Integer, Color>();
		Picture colouredPicture = new Picture(picture);
	
		int p=0;
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				Color c = colouredPicture.get(x, y);
				if(Luminance.lum(c) > 128.0)
				{
					int id = wqu.find(p);
					if (!colouredPicture.get(x, y).equals(Color.WHITE) )
					{
					if(id >= 0)
					{
    					if (table.containsKey(id)) {
    						c = table.get(id);
    					} else {
    						int r = colour.nextInt(255);
    						int g = colour.nextInt(255);
    						int b = colour.nextInt(255);
    						c = new Color(r, g, b);
    						table.put(id,c);    
    					}
    					colouredPicture.set(x, y, c);
					}
				}
				p++;
			}
			
			
		}
		}
		return colouredPicture;
	}
	
	public Picture getPicture(String fileLocation) 
	{
		Picture getPicture = new Picture(fileLocation);
		return getPicture;
	}

	/**
	 * Returns a binarised version of the original image
	 * 
	 * @return a picture object with all components surrounded by a red box
	 */
	public Picture binaryComponentImage() 
	{
		Picture binarisePicture = new Picture(picture);;
        int width  = binarisePicture.width();
        int height = binarisePicture.height();
        double thresholdPixelValue = 100.0;
        //Binarises the image using the threshold value.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color c = binarisePicture.get(x, y);
                if (Luminance.lum(c) < thresholdPixelValue) {
                	binarisePicture.set(x, y, Color.BLACK);
				} else {
					binarisePicture.set(x, y, Color.WHITE);
				}
               }
        }
        return binarisePicture;
	}
	
	//Gives height and width of image 
	public String sizeOfPic()
	{
		Picture pictureSize = new Picture(picture);
		int width = pictureSize.width();
		int height = pictureSize.height();
		String size = "The width of this image is " + width + " pixels and the height is " + height + " pixels.";
		return size;
	}
	
	//Returns gray scale version of image
	public Picture BandW()
	{
		Picture pic = new Picture(picture);
		for (int i = 0; i < pic.width(); i++) {
			for (int j = 0; j < pic.height(); j++) {
				Color color = pic.get(i, j);
				pic.set(i, j, Luminance.toGray(color));
			}
		}
		return pic;
	}
	
	//Gives amount of pixels in image
	public int getAmountOfPixels()
	{
		int height = picture.height();
		int width = picture.width();
		int area = width*height;
		return area;
	}

	public int height() {
		return picture.height();
	}

	public int width() {
		
		return picture.width();
	}

}
