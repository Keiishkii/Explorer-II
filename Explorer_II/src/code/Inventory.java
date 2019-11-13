package code;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Inventory 
{

	char[] inventory = new char[10];
	int currentSelect;
	Resource resources;
	
	double aqua, terra, ignis, aer;
	double maxAqua = 200, maxTerra = 200, maxIgnis = 200, maxAer = 200;
	
	public Inventory(Resource r)
	{
		resources = r;
		setTile();
	}
	
	public void setTile()
	{
		
		inventory[0] = 's';
		inventory[1] = 'g';
		inventory[2] = 'w';
		inventory[3] = 'W';
		inventory[4] = 'S';
		
		aqua = 100;
		terra = 150;
		aer = 0;
		ignis = 30;
		
	}
	
	public void changeSelect(int change)
	{
		change = change * -1;
		
		if (currentSelect + change > 9)
			currentSelect = 0;		
		else if (currentSelect + change < 0)
			currentSelect = 9;
		else
			currentSelect += change;
	}

	public char getSelectedTile()
	{
		return inventory[currentSelect];
	}
	
	public char getPrevousTile()
	{
		
		if (currentSelect == 0)
			return inventory[9];
		else		
			return inventory[currentSelect - 1];
		
	}
	
	public char getNextTile()
	{

		if (currentSelect == 9)
			return inventory[0];
		else		
			return inventory[currentSelect + 1];
		
	}
	
	public BufferedImage displayScroll()
	{
		
		BufferedImage scrollDisplay = new BufferedImage(30, 68, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = scrollDisplay.createGraphics();
				
		g2.drawImage(resources.getGui("ScrollPanel"), 0, 0, null);
		
		g2.drawImage(resources.getTileSprite(getPrevousTile(), null), 7, 5, null);
		g2.drawImage(resources.getTileSprite(getSelectedTile(), null), 7, 26, null);
		g2.drawImage(resources.getTileSprite(getNextTile(), null), 7, 47, null);
		
		g2.dispose();	
		
		return scrollDisplay;
		
	}
	
	public BufferedImage displayModeWheel(int mode)
	{
		BufferedImage ModeWheelDisplay = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = ModeWheelDisplay.createGraphics();
		if (mode == 1)
			g2.drawImage(resources.getGui("WheelModeAttack"), 0, 0, null);
		if (mode == 2)
			g2.drawImage(resources.getGui("WheelModeBuild"), 0, 0, null);
		if (mode == 3)
			g2.drawImage(resources.getGui("WheelModeHarvest"), 0, 0, null);
					
		g2.dispose();	
		
		return ModeWheelDisplay;
		
	}
	
	public BufferedImage displayAmount()
	{
		BufferedImage amountDisplay = new BufferedImage(64, 20, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = amountDisplay.createGraphics();
				
		g2.drawImage(resources.getGui("QuantityDisplay"), 0, 0, null);
		g2.drawString("" + getSelectedAmount(), 16, 14);
		
		g2.dispose();	
		
		return amountDisplay;		
	}

	public double getPercentage(String type)
	{
		if (type == "Aqua"){
			System.out.println((aqua / maxAqua) * 49);
			return (aqua / maxAqua) * 49;}
		if (type == "Ignis"){
			return (ignis / maxIgnis) * 49;}
		if (type == "Aer"){
			return (aer / maxAer) * 49;}
		if (type == "Terra"){
			return (terra / maxTerra) * 49;}
		else return 0;			
	}
		
	public BufferedImage displayGauge(String type)
	{
		BufferedImage typeGauge;
		if 		(type == "Aqua")
			typeGauge = new BufferedImage((int) (1 + getPercentage("Aqua")), 4, BufferedImage.TYPE_INT_ARGB);
		else if (type == "Ignis")
			typeGauge = new BufferedImage((int) (1 + getPercentage("Ignis")), 4, BufferedImage.TYPE_INT_ARGB);
		else if (type == "Aer")
			typeGauge = new BufferedImage((int) (1 + getPercentage("Aer")), 4, BufferedImage.TYPE_INT_ARGB);
		else if (type == "Terra")
			typeGauge = new BufferedImage((int) (1 + getPercentage("Terra")), 4, BufferedImage.TYPE_INT_ARGB);
		else
			typeGauge = new BufferedImage(50, 4, BufferedImage.TYPE_INT_ARGB);
			
		Graphics2D g2 = typeGauge.createGraphics();
				
		if (type == "Aqua")
			g2.drawImage(resources.getGui("AquaGauge"), 0, 0, null);
	
		if (type == "Ignis")
			g2.drawImage(resources.getGui("IgnisGauge"), 0, 0, null);
	
		if (type == "Aer")
			g2.drawImage(resources.getGui("AerGauge"), 0, 0, null);
		
		if (type == "Terra")
			g2.drawImage(resources.getGui("TerraGauge"), 0, 0, null);
	
		g2.dispose();	
		
		return typeGauge;		
	}
	
	public int getAqua()
	{
		return (int) aqua;
	}
	public int getTerra()
	{
		return (int) terra;
	}
	public int getAer()
	{
		return (int) aer;
	}
	public int getIgnis()
	{
		return (int) ignis;
	}
	
	public void Harvest(char tile)
	{
		switch(tile)
		{
		case 's' :
			if (terra < maxTerra)
			terra += 1;
			break;
				  
		case 'w' :
			if (aqua < maxAqua)
			aqua += 1;
			break;
				  
		case 'W' :
			if (aqua < maxAqua - 1)
			aqua += 2;
			break;
			
		}
	}
	
	public void addToInventory(char tile)
	{
		switch(tile)
		{
		case 's' :
			terra += 1;
			break;
				  
		case 'S' :
			terra += 2;
			break;
				  
		case 'g' :
			terra += 1;
			break;
				  
		case 'w' :
			aqua += 1;
			break;
				  
		case 'W' :
			aqua += 2;
			break;
			
		}
	}
	
	public void updateInventory()
	{
		
		switch(inventory[currentSelect])
		{
		case 's' :
			terra -= 1;
			break;
		case 'S' :
			terra -= 2;
			break;
		case 'g' :
			terra -= 1;
			break;
		case 'w' :
			aqua -= 1;
			break;
		case 'W' :
			aqua -= 2;
			break;
		}
		
	}
	
	public int getSelectedAmount()
	{
		switch(inventory[currentSelect])
		{
		case 's' :
			return (int) terra;
		case 'S' :
			return (int) (terra / 2);
		case 'g' :
			return (int) terra;
		case 'w' :
			return (int) aqua;
		case 'W' :
			return (int) aqua / 2;
		}
		
		return 0;
		
	}

	public BufferedImage displayElementCount(int mode) 
	{
		BufferedImage elementDisplay = new BufferedImage(82, 92, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = elementDisplay.createGraphics();
				
		g2.drawImage(resources.getGui("ElementDisplay"), 0, 0, null);
		g2.drawString("" + aqua, 34, 14);
		g2.drawString("" + ignis, 34, 36);
		g2.drawString("" + terra, 34, 58);
		g2.drawString("" + aer, 34, 80);
		
		g2.dispose();	
		
		return elementDisplay;	
	}
	
	
}

