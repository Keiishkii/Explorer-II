package code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Effect 
{

	BufferedImage splash, grass, shadow;
	Resource resource;
		
	public Effect(Resource r)
	{
	
		resource = r;
		
	}


	public BufferedImage getAboveEffectSprite(char tileType)
	{
		
		return resource.getAboveEffectSprite(tileType);
		
	}
	
	public int getSpriteSize(char tileType)
	{

		return resource.getSpriteSize(tileType);
		
	}
	
	public BufferedImage getBelowEffectSprite(char tileType)
	{
		
		return resource.getBelowEffectSprite(tileType);
			
	}

	public BufferedImage saveEffect(String str)
	{
		
		BufferedImage tile = null;
		try 
		{
			tile = ImageIO.read(new File("src/resources/effects/" + str + ".png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return tile;
	}
	
}
