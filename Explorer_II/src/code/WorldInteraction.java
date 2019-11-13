package code;

public class WorldInteraction 
{

	char tile;
	int mouseXPos;
	int mouseYPos;
	
	int mode = 1;
	
	public WorldInteraction()
	{
		mouseXPos = -1;
		mouseYPos = -1;
	}
	
	public String getDirectionFaced(int x, int y, Player player)
	{
		
		mouseXPos = x;
		mouseYPos = y;
		
		player.getXPos();
		if (mouseXPos > player.getXPos() && Math.abs(mouseXPos - player.getXPos()) >= Math.abs(mouseYPos - player.getYPos()))
		{
			return "right";
		}
		
		else if (mouseXPos < player.getXPos() && Math.abs(mouseXPos - player.getXPos()) >= Math.abs(mouseYPos - player.getYPos()))
		{
			return "left";
		}
		
		else if (mouseYPos < player.getYPos() && Math.abs(mouseXPos - player.getXPos()) < Math.abs(mouseYPos - player.getYPos()))
		{
			return "up";
		}
		
		else if (mouseYPos > player.getYPos() && Math.abs(mouseXPos - player.getXPos()) < Math.abs(mouseYPos - player.getYPos()))
		{
			return "down";
		}
		else	
			return "nothing";
		
	}
	
	public int getMode()
	{
		return mode;		
	}
	
	public int getArrayNumLooking(Player player, NaturalWorld world)
	{
		
		
		if (getDirectionFaced(mouseXPos, mouseYPos, player) == "left" && (player.xPos / 16) % world.getMapWidth() != 0)
		{
			return (player.getXPos() / 16) + (player.getYPos() / 16 * world.getMapWidth()) - 1; 
		}
		
		if (getDirectionFaced(mouseXPos, mouseYPos, player) == "right" && ((1 + player.xPos / 16) % world.getMapWidth()) != 0)
		{
			return (player.getXPos() / 16) + (player.getYPos() / 16 * world.getMapWidth()) + 1; 
		}
		
		if (getDirectionFaced(mouseXPos, mouseYPos, player) == "up" && (1 + player.yPos / 16) != 0)
		{
			return (player.getXPos() / 16) + (player.getYPos() / 16 * world.getMapWidth()) - world.getMapWidth(); 
		}
		
		if (getDirectionFaced(mouseXPos, mouseYPos, player) == "down" && (1 + player.yPos / 16) != world.getMapHeight())
		{
			return (player.getXPos() / 16) + (player.getYPos() / 16 * world.getMapWidth()) + world.getMapWidth(); 
		}
		
		return -1;
		
	}
	
	public void Place(int mouseXPos, int mouseYPos, Player player, BuildWorld bWorld, NaturalWorld nWorld, Inventory inventory)
	{		
		if (inventory.getSelectedAmount() > 0 && player.tileLookingAt(nWorld.getCombinedMap(bWorld.getMap()), getDirectionFaced(mouseXPos, mouseYPos, player), nWorld.mapWidth) != inventory.getSelectedTile() && player.tileLookingAt(bWorld.getMap(), getDirectionFaced(mouseXPos, mouseYPos, player), nWorld.mapWidth) == '\u0000')
		{
			bWorld.updateMap(player, getDirectionFaced(mouseXPos, mouseYPos, player), inventory.getSelectedTile(), nWorld.mapWidth);
			inventory.updateInventory();
		}
	}
	
	public void Harvest(int mouseXPos, int mouseYPos, Player player, BuildWorld bWorld, NaturalWorld nWorld, Inventory inventory)
	{
		inventory.Harvest(player.tileLookingAt(nWorld.getCombinedMap(bWorld.getMap()), getDirectionFaced(mouseXPos, mouseYPos, player), nWorld.getMapWidth()));
	}
	
	public void leftClick(int mouseXPos, int mouseYPos, Player player, BuildWorld bWorld, NaturalWorld nWorld, Inventory inventory, Sound sound)
	{
		if (mode == 1)
		{
			System.out.println("HYAAAaaaaa");
			sound.playSwing();
		}
		
		if (mode == 2)
		{
			Place(mouseXPos, mouseYPos, player, bWorld, nWorld, inventory);
		}
		
		if (mode == 3)
		{
			Harvest(mouseXPos, mouseYPos, player, bWorld, nWorld, inventory);
		}
	}
	
	public void rightClick(int mouseXPos, int mouseYPos, Player player, BuildWorld bWorld, int mapWidth, Inventory inventory) 
	{

		if (mode == 2)
		{
			tile = bWorld.getTile(player, getDirectionFaced(mouseXPos, mouseYPos, player), tile, mapWidth);
			if (tile != '\u0000')
			{
				System.out.println(tile);
				inventory.addToInventory(tile);
				bWorld.updateMap(player, getDirectionFaced(mouseXPos, mouseYPos, player), '\u0000', mapWidth);	
			}	
		}
	}

	public void middleClick() 
	{
	
		if (mode == 3)
			mode = 1;
		else
			mode += 1;
	}
	
}
