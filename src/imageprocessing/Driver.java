package imageprocessing;


import edu.princeton.cs.introcs.Picture;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdIn;

/**
 *
 * The function of this class is to allow you to run the program and explore the different methods involved
 *
 */

public class Driver {
	
	public static void main(String[] args)
	{
		Driver app = new Driver();
		int runProgramme;
		do
		{
			String fileLocation = app.mainMenu();
			app.chooseAction(fileLocation);
			StdOut.println("Run program again? Press (1) for yes or (2) for no");
			runProgramme = StdIn.readInt();
			while(runProgramme != 1 && runProgramme != 0)
			{
				StdOut.println("No Option available, please choose either 1 or 2");
				runProgramme = StdIn.readInt();
			}
			if(runProgramme == 0)
			{
				StdOut.println("End");
				System.exit(0);
			}
		}
		while(runProgramme == 1);
	}

	
	public String mainMenu()
	{
		StdOut.println("Please select an image>");
		StdOut.println("1) Dots");
		StdOut.println("2) Shapes");
		String choice = "";
		int option = StdIn.readInt();
		while(option < 1 || option > 2)
		{
			StdOut.println("Number out of bounds, please choose 1 or 2");
			option = StdIn.readInt();
		}
		switch(option)
		{
			case 1: choice = "images/dots.jpg";
			break;
			case 2: choice = "images/square.bmp";
		}
		return choice;
	}
	/**
	 * 
	 *Allows the user to choose an operation to perform on their selected image.
	 */
	
	public void chooseAction(String fileLocation)
	{
		ConnectedComponentImage image = new ConnectedComponentImage(fileLocation);
		StdOut.println("Select option for image");
		StdOut.println("1) Binarized image");
		StdOut.println("2) Black & White image");
		StdOut.println("3) Random colours");
		StdOut.println("4) Components highlight with red box");
		StdOut.println("5) Number of components");
		StdOut.println("6) Height & Width of image");
		StdOut.println("7) Amount of pixels in image");
		StdOut.println("8) Orignal Image");
		int option = StdIn.readInt();
		while(option < 1 || option > 8)
		{
			StdOut.println("Option not available, select between 1 to 8");
			option = StdIn.readInt();
		}
		switch(option)
		{
			case 1:  Picture picture = image.binaryComponentImage();
			         picture.show();
			break;
			case 2:  Picture picture1 = image.BandW();
			         picture1.show();
			break;
			case 3: Picture picture2 = image.colourComponentImage();
					picture2.show();
			break;
			case 4: Picture picture3 = image.identifyComponentImage();
					picture3.show();
			break;
			case 5:  StdOut.println(image.countComponents());
			break;
			case 6: String picSize = image.sizeOfPic();
			StdOut.println(picSize);
			break;
			case 7: int area = image.getAmountOfPixels();
					StdOut.println("There are " + area + " pixels in this image.");
			break;
			case 8: picture  = image.orignalImage();
			break;
		}
	}

}
