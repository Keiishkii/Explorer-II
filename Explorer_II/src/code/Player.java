package code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player 
{

	public int xPos = 10, yPos = 10;
	BufferedImage sprite = null;
	double maxSpeed, minSpeed, speed; 
	int movement;
	
	
	public Player()
	{
		xPos = 16 * 40;
		yPos = 16 * 20;
				
		maxSpeed = 2;
		minSpeed = 1;
		speed = minSpeed;
		
	}
	
	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	public void updatePos(boolean sPressed,boolean wPressed,boolean aPressed,boolean dPressed, boolean controlPressed,int mapHeight, int mapWidth, char[] mapArray)
	{
		
		if (controlPressed)
		{
			maxSpeed = 2;
			speed += 0.2;
			if (speed > maxSpeed)
				speed = maxSpeed;
			movement = (int) speed;
		}
		else
		{
			maxSpeed = 1;
			speed += 0.5;
			if (speed > maxSpeed)
				speed = maxSpeed;
			movement = (int) speed;
		}
		
		if(sPressed && canMoveDown(mapHeight, canWalk(mapArray, movement, "down", mapWidth)))
		{
			yPos += movement;
		}
		if(wPressed && canMoveUp(canWalk(mapArray, movement, "up", mapWidth)))
		{
			yPos -= movement;
		}
		if(aPressed && canMoveLeft(canWalk(mapArray, movement, "left", mapWidth)))
		{
			xPos -= movement;
		}
		if(dPressed && canMoveRight(mapWidth, canWalk(mapArray, movement, "right", mapWidth)))
		{
			xPos += movement;
		}
				
	}
	
	public boolean canWalk(char[] mapArray, int movement, String direction, int mapWidth)
	{
		try
		{
		if (direction == "down" && isTileWalkable(mapArray[((int) xPos / 16) + (((int) (yPos + movement + 1) / 16) * mapWidth)]))
			return true;
		if (direction == "up" && isTileWalkable(mapArray[((int) xPos / 16) + (((int) (yPos - movement - 1) / 16) * mapWidth)]))
			return true;
		if (direction == "left" && isTileWalkable(mapArray[((int) (xPos - movement - 6) / 16) + (((int) yPos / 16) * mapWidth)]))
			return true;
		if (direction == "right" && isTileWalkable(mapArray[((int) (xPos + movement + 4) / 16) + (((int) yPos / 16) * mapWidth)]))
			return true;
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println(e);
		}
		return false;
	}
	
	public char tileLookingAt(char[] mapArray, String direction, int mapWidth)
	{
		try
		{
		if (direction == "down")
			return (mapArray[((int) xPos / 16) + mapWidth + (((int) (yPos) / 16) * mapWidth)]);
		if (direction == "up")
			return (mapArray[((int) xPos / 16) - mapWidth + (((int) (yPos) / 16) * mapWidth)]);
		if (direction == "left")
			return (mapArray[((int) (xPos) / 16) - 1+ (((int) yPos / 16) * mapWidth)]);
		if (direction == "right")
			return (mapArray[((int) (xPos) / 16) + 1 + (((int) yPos / 16) * mapWidth)]);
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println(e);
		}
		return '\u0000';
	}
	
	public char standingOn(char[] mapArray, int mapWidth)
	{
		return (mapArray[((int) (xPos) / 16) + (((int) yPos / 16) * mapWidth)]);
	}
	
	public boolean isTileWalkable(char tile)
	{
		if (tile == 'W' || tile == 'S')
			return false;
		else
			return true;
	}
	
	public BufferedImage getPlayerSprite(Boolean top)
	{
		if (top == false)
			try 
		{
			sprite = ImageIO.read(new File("src/resources/entitys/Eia.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		else
			try 
		{
			sprite = ImageIO.read(new File("src/resources/entitys/EiaTop.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return sprite;
		
	}
	
	public int getXOfset(int width, int mapWidth, int tileSize)
	{

		int offset;

		// Left of screen
		if (xPos < (width*0.5))
		{
			offset = xPos;		
		}
		else if (xPos > (mapWidth * tileSize) - (width * 0.5))
		{
			offset = (xPos + width - (mapWidth * tileSize));
		}
		else
		{	
			offset = width / 2;
		}
		
		return offset - 8;
		
	}
	
	public int getYOfset(int height, int mapHeight, int tileSize)
	{
		
		int offset;

		// Top of screen
		if (yPos < (height*0.5))
		{
			offset = yPos;		
		}
		else if (yPos > (mapHeight * tileSize) - (height * 0.5))
		{
			offset = (yPos + height - (mapHeight * tileSize));
		}
		else
		{	
			offset = height / 2;
		}
		
		return offset - 22;
		
	}
	
	public boolean canMoveLeft(boolean tileMovement)
	{
		if (xPos > 8 && tileMovement)
			return true;
		else
			return false;
	}
	
	public boolean canMoveRight(int width, boolean tileMovement)
	{
		if (xPos < width * 16 - 8 && tileMovement)
			return true;
		else
			return false;
	}
	
	public boolean canMoveDown(int height, boolean tileMovement)
	{
		if (yPos < height * 16 - 2 && tileMovement)
			return true;
		else
			return false;
	}
	
	public boolean canMoveUp(boolean tileMovement)
	{
		if (yPos > 24 && tileMovement)
			return true;
		else
			return false;
	}
	
}
