package code;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NaturalWorld 
{
	
	int mapHeight, mapWidth;
	int tileSize = 16;
	char[] naturalMapArray;
	BufferedImage water, deepWater, sand, grass;
	BufferedImage untextured;
	boolean bool = false;
	
	Resource resource;
	
	public NaturalWorld(BuildWorld bWorld, Resource resObj)
	{

		resource = resObj;
		
		mapWidth = 50;
		mapHeight = 38;
		
		naturalMapArray = mapReader("50_38");
		
		bWorld.setMap(mapWidth, mapHeight, naturalMapArray);
		
	}
	
	public char[] mapReader(String path)
	{
		
		byte[] encoded = null;
		try 
		{
			encoded = Files.readAllBytes(Paths.get("src/resources/maps/" + path + ".txt"));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		  return new String(encoded, Charset.defaultCharset()).toCharArray();
	}

	public BufferedImage mapGraphics(BuildWorld bWorld, int screenWidth, int screenHeight, int xOffset, int yOffset)
	{
		
		int width = tileSize*mapWidth;
		int height = tileSize*mapHeight;
	
		BufferedImage completeMap = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = completeMap.createGraphics();
			
		g2.clearRect(0, 0, width, height);
				
		/////////////////////////////////////////////////////////////
			
		for (int i = 0; i < mapWidth; i++)
		{	
			// i == width							
			for (int j = 0; j < mapHeight; j++)
				{
					// j == height
					g2.drawImage(resource.getTileSprite(naturalMapArray[(j*mapWidth) + i], null), null, (tileSize*i) + xOffset, (tileSize*j) + yOffset + (16 - resource.getTileSpriteSize(bWorld.getMap()[(j*mapWidth) + i], null)));
					// displays the image at point j, i, using the character of getSprite();
				}
		}
			
		/////////////////////////////////////////////////////////////
		
		for (int i = 0; i < mapWidth; i++)
		{	
			// i == width							
			for (int j = 0; j < mapHeight; j++)
				{
					// j == height
					g2.drawImage(resource.getTileSprite(bWorld.getMap()[(j*mapWidth) + i], null), null, (tileSize*i) + xOffset, (tileSize*j) + yOffset + (16 - resource.getTileSpriteSize(bWorld.getMap()[(j*mapWidth) + i], null)));
					// displays the image at point j, i, using the character of getSprite();
				}
		}
				
		/////////////////////////////////////////////////////////////

			
		g2.dispose();	
		
	    BufferedImage croppedImage = completeMap.getSubimage(0, 0, screenWidth, screenHeight);
			
		return croppedImage;
		
	}
	
	public BufferedImage topGraphics(BuildWorld bWorld, int screenWidth, int screenHeight, int xOffset, int yOffset)
	{

		int width = tileSize*mapWidth;
		int height = tileSize*mapHeight;
		
		BufferedImage completeMap = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = completeMap.createGraphics();

		for (int i = 0; i < mapWidth; i++)
		{	
			// i == width							
			for (int j = 0; j < mapHeight; j++)
				{
					// j == height
					g2.drawImage(resource.getTileSprite(bWorld.getMap()[(j*mapWidth) + i], "Top"), null, (tileSize*i) + xOffset, (tileSize*j) + yOffset + (16 - resource.getTileSpriteSize(bWorld.getMap()[(j*mapWidth) + i], "Top")));
					// displays the image at point j, i, using the character of getSprite();
				}
		}
		
		g2.dispose();	
		
	    BufferedImage croppedImage = completeMap.getSubimage(0, 0, screenWidth, screenHeight);
			
		return croppedImage;
		
	}
	
	public char[] getMap()
	{
		return naturalMapArray;
	}
	
	public char[] getCombinedMap(char[] buildMap)
	{
		char[] combMap = naturalMapArray.clone();
		
		for (int i = 0; i < naturalMapArray.length; i++)
		{
			if (buildMap[i] != '\u0000')
			{
				combMap[i] = buildMap[i];
			}
		}
		
		return combMap;
	}
	
	public int getXOfset(int playerXPos, int width)
	{		
		if (playerXPos < width / 2)
			return 0;
		else if (playerXPos > tileSize * mapWidth - (width / 2))
			return - ((tileSize * mapWidth) - width);
		else
			return - (playerXPos - width / 2);
		
	}
	
	public int getYOfset(int playerYPos, int height)
	{
		if (playerYPos < height / 2)
			return 0;
		else if (playerYPos > tileSize * mapHeight - (height / 2))
			return - ((tileSize * mapHeight) - height);
		else
			return - (playerYPos - height / 2);
	}
	
	public int getMapWidth()
	{
		return mapWidth;
	}
	
	public int getMapHeight()
	{
		return mapHeight;
	}
	
	public int getTileSize()
	{
		return tileSize;
	}
	
}
