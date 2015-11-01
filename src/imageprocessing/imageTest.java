package imageprocessing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class imageTest {
	public ConnectedComponentImage pic = new ConnectedComponentImage("images/dots.jpg");

	@Before
	public void setUp()
	{
		pic = new ConnectedComponentImage("images/dots.jpg");
	}

	@After
	public void tearDown()
	{
		pic = null;
	}

	@Test
	public void testSize() 
	{
		assertEquals(160000,pic.height()*pic.width());

	}

	@Test
	public void testHeight()
	{
		assertEquals(400,pic.height());
	}

	@Test
	public void testWidth()
	{
		assertEquals(400,pic.width());
	}

	@Test
	public void testBinary()
	{
		assertNotEquals(pic, pic.binaryComponentImage());
	}

	@Test
	public void testShowImage()
	{
		assertNotEquals(pic.orignalImage(), null);
	}

}
