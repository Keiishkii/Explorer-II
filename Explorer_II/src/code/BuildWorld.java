package code;

public class BuildWorld 
{

	char[] buildMapArray;
	
	public char[] getMap()
	{
		return buildMapArray;
	}
	
	public void setMap(int width, int height, char[] array)
	{
		buildMapArray = new char[(height * width)];
	}
		
	public void updateMap(Player player, String direction, char tile, int mapWidth)
	{
		if (direction == "up")
			buildMapArray[((int) (player.getXPos() / 16) + (player.getYPos() / 16 * mapWidth)) - (mapWidth)] = tile;

		if (direction == "down")
			buildMapArray[((int) (player.getXPos() / 16) + (player.getYPos() / 16 * mapWidth)) + (mapWidth)] = tile;

		if (direction == "left" && (player.xPos / 16) % mapWidth != 0)
			buildMapArray[((int) (player.getXPos() / 16) + (player.getYPos() / 16 * mapWidth)) - (1)] = tile;

		if (direction == "right" && (1 + player.xPos / 16) % mapWidth != 0)
			buildMapArray[((int) (player.getXPos() / 16) + (player.getYPos() / 16 * mapWidth)) + (1)] = tile;
		
	}
	
	public char getTile(Player player, String direction, char tile, int mapWidth)
	{
		if (direction == "up")
			return buildMapArray[((int) (player.getXPos() / 16) + (player.getYPos() / 16 * mapWidth)) - (mapWidth)];
		else
		if (direction == "down")
			return buildMapArray[((int) (player.getXPos() / 16) + (player.getYPos() / 16 * mapWidth)) + (mapWidth)];
		else
		if (direction == "left" && (player.xPos / 16) % mapWidth != 0)
			return buildMapArray[((int) (player.getXPos() / 16) + (player.getYPos() / 16 * mapWidth)) - (1)];
		else
		if (direction == "right" && (1 + player.xPos / 16) % mapWidth != 0)
			return buildMapArray[((int) (player.getXPos() / 16) + (player.getYPos() / 16 * mapWidth)) + (1)];
		else
			return '\u0000';
		
		
	}
}
