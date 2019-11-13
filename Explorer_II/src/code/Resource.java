package code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource 
{
	
	BufferedImage stone, grass, water, deepWater, sand, untextured, stoneTop;

	BufferedImage shadowEffect, grassEffect, splashEffect;
	
	BufferedImage scrollPanel, statusBar, areaSelection, quantityDisplay, wheelModeAttack, wheelModeBuild, wheelModeHarvest, elementDisplay, terraGauge, ignisGauge, aquaGauge, aerGauge;

	public Resource()
	{

		grass = saveSprite("Grass", "tiles");
		water = saveSprite("Water", "tiles");
		deepWater = saveSprite("DeepWater", "tiles");
		sand = saveSprite("Sand", "tiles");
		untextured = saveSprite("Untextured", "tiles");		
		
	}
	
	/////////////////////////////////////////
	
	public BufferedImage getTileSprite(char tileType, String level)
	{
		if (level != "Top")
		switch(tileType)
		{
		
		case 'g' : 
			if (grass == null)
				grass = saveSprite("Grass", "tiles");
			return grass;
		case 'S' : 
			if (stone == null)
				stone = saveSprite("StoneWall", "tiles");
			return stone;
		case 'w' : 
			if (water == null)
				water = saveSprite("Water", "tiles");
			return water;
		case 'W' : 
			if (deepWater == null)
				deepWater = saveSprite("DeepWater", "tiles");
			return deepWater;
		case 's' : 
			if (sand == null)
				sand = saveSprite("Sand", "tiles");
			return sand;			
		}	
		else
		switch(tileType)
		{
		case 'S' : 
			if (stoneTop == null)
				stoneTop = saveSprite("StoneWallTop", "tiles");
			return stoneTop;			
		}
		if (untextured == null)
			untextured = saveSprite("Untextured", "tiles");
		return untextured;
		
	}
	
	public BufferedImage getAboveEffectSprite(char tileType)
	{
		
		switch(tileType)
		{
		
		case 'w' : 
			if (splashEffect == null)
				splashEffect = saveSprite("Splash", "effects");
			return splashEffect;

		case 'g' : 
			if (grassEffect == null)
				grassEffect = saveSprite("Grass", "effects");
			return grassEffect;
		
		default  : return null;
			
		}
		
	}
	
	public BufferedImage getBelowEffectSprite(char tileType)
	{
		
		switch(tileType)
		{
		
		default  :  
			if (shadowEffect == null)
				shadowEffect = saveSprite("Shadow", "effects");
			return shadowEffect;
			
		}
		
	}

	public int getSpriteSize(char tileType)
	{
		switch(tileType)
		{
		
		case 'w' : 	
			if (splashEffect == null)
				splashEffect = saveSprite("Splash", "effects");
			return splashEffect.getHeight();
			
		case 'g' : 	
			if (grassEffect == null)
				grassEffect = saveSprite("Grass", "effects");
			return grassEffect.getHeight();
			
		default  : 
			if (shadowEffect == null)
				shadowEffect = saveSprite("Shadow", "effects");
			return shadowEffect.getHeight();
		
		}
	}
	
	public int getTileSpriteSize(char tileType, String string)
	{
		
		if (string == "Top")
		{
			switch(tileType)
			{	
				case 'S' :  
					if (stoneTop == null)
						stoneTop = saveSprite("Grass", "tiles");
					return stoneTop.getHeight();
			}
		
		}
		else
		{
			switch(tileType)
			{
			
			case 'g' : 
				if (grass == null)
					grass = saveSprite("Grass", "tiles");
				return grass.getHeight();
			case 'S' : 
				if (stone == null)
					stone = saveSprite("Stone", "tiles");
				return stone.getHeight();
			case 'w' : 
				if (water == null)
					water = saveSprite("Water", "tiles");
				return water.getHeight();
			case 'W' : 
				if (deepWater == null)
					deepWater = saveSprite("DeepWater", "tiles");
				return deepWater.getHeight();
			case 's' : 
				if (sand == null)
					sand = saveSprite("Sand", "tiles");
				return sand.getHeight();			
			}
		}
		if (untextured == null)
			untextured = saveSprite("Untextured", "tiles");
		return untextured.getHeight();
		
	}
	
	public BufferedImage getGui(String type)
	{
		
		if (type == "ScrollPanel")
		{
			if (scrollPanel == null)
				scrollPanel = saveSprite("ScrollPanel", "gui");
			return scrollPanel;
		}
		else if (type == "StatusBar")
		{
			if (statusBar == null)
				statusBar = saveSprite("StatusBar", "gui");
			return statusBar;
		}
		else if (type == "QuantityDisplay")
		{
			if (quantityDisplay == null)
				quantityDisplay = saveSprite("QuantityDisplay", "gui");
			return quantityDisplay;
		}
		else if (type == "ElementDisplay")
		{
			if (elementDisplay == null)
				elementDisplay = saveSprite("ElementDisplay", "gui");
			return elementDisplay;
		}
		else if (type == "WheelModeAttack")
		{
			if (wheelModeAttack == null)
				wheelModeAttack = saveSprite("WheelModeAttack", "gui");
			return wheelModeAttack;
		}
		else if (type == "WheelModeBuild")
		{
			if (wheelModeBuild == null)
				wheelModeBuild = saveSprite("WheelModeBuild", "gui");
			return wheelModeBuild;
		}
		else if (type == "WheelModeHarvest")
		{
			if (wheelModeHarvest == null)
				wheelModeHarvest = saveSprite("WheelModeHarvest", "gui");
			return wheelModeHarvest;
		}
		else if (type == "AreaSelection")
		{
			if (areaSelection == null)
				areaSelection = saveSprite("AreaSelection", "gui");
			return areaSelection;
		}
		else if (type == "TerraGauge")
		{
			if (terraGauge == null)
				terraGauge = saveSprite("TerraGauge", "gui");
			return terraGauge;
		}
		else if (type == "IgnisGauge")
		{
			if (ignisGauge == null)
				ignisGauge = saveSprite("IgnisGauge", "gui");
			return ignisGauge;
		}
		else if (type == "AerGauge")
		{
			if (aerGauge == null)
				aerGauge = saveSprite("AerGauge", "gui");
			return aerGauge;
		}
		else if (type == "AquaGauge")
		{
			if (aquaGauge == null)
				aquaGauge = saveSprite("AquaGauge", "gui");
			return aquaGauge;
		}
		else
			
			return null;

		
	}
	
	public BufferedImage saveSprite(String str, String location)
	{
		
		BufferedImage tile = null;
		try 
		{
			tile = ImageIO.read(new File("src/resources/" + location + "/" + str + ".png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return tile;
		
	}
	
	/////////////////////////////////////////
	
}
