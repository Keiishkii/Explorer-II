package code;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Display 
{
	
	Effect effect;
	Inventory inventory;
	Resource resource;
	Graphics g;
	
	public Display(Effect e, Inventory i, Resource r)
	{
		effect = e;
		inventory = i;
		resource = r;
	}
	
	public void draw(WorldInteraction worldInteraction, NaturalWorld nWorld, BuildWorld bWorld, Player player, GameLoop start, int width, int height)
	{
		
		BufferStrategy bf = start.getBufferStrategy();
		g = bf.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		g.drawImage(fullDisplay(worldInteraction, nWorld, bWorld, player, start, width, height), 0, 0, null);
			
		g.dispose();
		bf.show();
		
	}
	
	public BufferedImage fullDisplay(WorldInteraction worldInteraction, NaturalWorld nWorld, BuildWorld bWorld, Player player, GameLoop start, int width, int height)
	{
		
		BufferedImage display = new BufferedImage(82, 92, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = display.createGraphics();
		
////////////DrawMap/////////////
		g.drawImage(nWorld.mapGraphics(bWorld, width, height, nWorld.getXOfset(player.getXPos(),width), nWorld.getYOfset(player.getYPos(),height)), 0, 0, null);
	////////////////////////////////
		
	////////////////////////////////
		if (worldInteraction.getMode() == 2 || worldInteraction.getMode() == 3)
			g.drawImage(resource.getGui("AreaSelection"), ((worldInteraction.getArrayNumLooking(player, nWorld) % nWorld.getMapWidth()) * 16) + nWorld.getXOfset(player.getXPos(), width), ((worldInteraction.getArrayNumLooking(player, nWorld) - (worldInteraction.getArrayNumLooking(player, nWorld) % nWorld.getMapWidth())) / nWorld.getMapWidth() * 16) + nWorld.getYOfset(player.getYPos(), height), null);
	////////////////////////////////
	
	////////////DrawCharacter///////
		///Effects///
			g.drawImage(effect.getBelowEffectSprite(player.standingOn(nWorld.getCombinedMap(bWorld.getMap()), nWorld.getMapWidth())), (player.getXOfset(width, nWorld.getMapWidth(), nWorld.getTileSize())), (player.getYOfset(height, nWorld.getMapHeight(), nWorld.getTileSize()) + (26 - effect.getSpriteSize(player.standingOn(nWorld.getCombinedMap(bWorld.getMap()), nWorld.getMapWidth())))), null);
		g.drawImage(player.getPlayerSprite(false), (player.getXOfset(width, nWorld.getMapWidth(), nWorld.getTileSize())), (player.getYOfset(height, nWorld.getMapHeight(), nWorld.getTileSize())), null);
			g.drawImage(effect.getAboveEffectSprite(player.standingOn(nWorld.getCombinedMap(bWorld.getMap()), nWorld.getMapWidth())), (player.getXOfset(width, nWorld.getMapWidth(), nWorld.getTileSize())), (player.getYOfset(height, nWorld.getMapHeight(), nWorld.getTileSize()) + (26 - effect.getSpriteSize(player.standingOn(nWorld.getCombinedMap(bWorld.getMap()), nWorld.getMapWidth())))), null);
	////////////////////////////////

	////////////DrawMap/////////////
			g.drawImage(nWorld.topGraphics(bWorld, width, height, nWorld.getXOfset(player.getXPos(),width), nWorld.getYOfset(player.getYPos(),height)), 0, 0, null);
	////////////////////////////////
			g.drawImage(player.getPlayerSprite(true), (player.getXOfset(width, nWorld.getMapWidth(), nWorld.getTileSize())), (player.getYOfset(height, nWorld.getMapHeight(), nWorld.getTileSize())), null);
			
	//////////DrawGUI///////////////
		g.drawImage(inventory.displayModeWheel(worldInteraction.getMode()), 10, 12, null);
		
		g.drawImage(inventory.displayElementCount(worldInteraction.getMode()), 10, 84, null);
			g.drawImage(inventory.displayGauge("Aqua"), 38, 103, null);
			g.drawImage(inventory.displayGauge("Ignis"), 38, 125, null);
			g.drawImage(inventory.displayGauge("Terra"), 38, 147, null);
			g.drawImage(inventory.displayGauge("Aer"), 38, 169, null);
		
		if (worldInteraction.getMode() == 2)
		{
			g.drawImage(inventory.displayAmount(), 100, 34, null);
			g.drawImage(inventory.displayScroll(), 75, 10, null);				
		}
	////////////////////////////////
		
		g2.dispose();	
		
		return display;	
		
	}
	
}
