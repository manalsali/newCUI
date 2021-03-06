package DicomParse;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.dcm4che2.imageio.plugins.dcm.DicomImageReadParam;

public class dicomParser implements PixelParser
{
	public BufferedImage getDCMBufferedImage(File dcm)
	{
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("DICOM");
		BufferedImage pix = null;
		ImageReader reader = (ImageReader) readers.next();
		DicomImageReadParam param = (DicomImageReadParam) reader.getDefaultReadParam();
		
		ImageInputStream iis=null;
		try 
		{
			iis = ImageIO.createImageInputStream(dcm);
			reader.setInput(iis, true);
			pix = reader.read(0, param);
			iis.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return pix;
	}
	
	public int[][] getRGBComponents(BufferedImage image)
	{
		int height = image.getHeight();
		int width = image.getWidth();
		int[][] pixelComponents = new int[height][width];
		
		for(int i =0; i<width; i++)
		{
			for(int j=0; j<height;j++)
			{
				int rgb = image.getRGB(i, j);
				pixelComponents[i][j]= getComponent(rgb, "red");
			}
		}
		
		return pixelComponents;
	}
	
	private int getComponent(int rgb, String component)
	{
		switch(component)
		{
		case "red":
			return (rgb>>16) & 0xff;
		case "green":	
			return (rgb>>8) & 0xff;
		case "blue":
			return (rgb) & 0xff;
		default:
			return 0;
		}
	}
	
	public int[][] getRGBpixelvalues(BufferedImage image)
	{
		int height = image.getHeight();
		int width = image.getWidth();
		int[][] pixelComponents = new int[height][width];
		
		for(int i =0; i<height; i++)
		{
			for(int j=0; j<width;j++)
			{
				pixelComponents[height][width] = image.getRGB(height, width);
			}
		}
		
		return pixelComponents;
		
	}
	
	

}
