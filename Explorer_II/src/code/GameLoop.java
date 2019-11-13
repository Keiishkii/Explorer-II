package code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameLoop implements Runnable, KeyListener, MouseMotionListener, MouseListener, MouseWheelListener
{

	JFrame frame;
	
	boolean running = true;
	boolean paused = false;
	Resource resource = new Resource();
	Effect effect = new Effect(resource);
	BuildWorld bWorld = new BuildWorld();
	NaturalWorld nWorld = new NaturalWorld(bWorld, resource);
	Player player = new Player();
	Sound sound = new Sound();
	Inventory inventory = new Inventory(resource);
	Display display = new Display(effect, inventory, resource);
	WorldInteraction worldInteraction = new WorldInteraction();
	static GameLoop start = new GameLoop();
	
	boolean sPressed, wPressed, aPressed, dPressed, shiftPressed, controlPressed;
	volatile boolean escapePressed;
	int mouseXPos;
	int mouseYPos;
	
	public static void main(String args[])
	{
		
		Thread loop = new Thread(start);
		loop.start();
		
	}
	
	public GameLoop()
	{
		frame = new JFrame();
		
		frame.setTitle("Explorer_II");
		
		frame.setIconImage(new ImageIcon("src/resources/gui/Icon.png").getImage());
		
		frame.setSize(800, 600);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.addKeyListener(this);	
		frame.addMouseMotionListener(this);	
		frame.addMouseListener(this);	
		frame.addMouseWheelListener(this);	

		frame.createBufferStrategy(2);
	}
	
	public void run()
	{
		
		double time1;
		double time2;
		double drawTime;
		int frameBuffer;
		sound.playMusic();
		
		while(running)
		{

			time1 = System.currentTimeMillis();
			
				if (paused)
				{
				
				}
				else
				{
					display.draw(worldInteraction, nWorld, bWorld, player, start, this.frame.getWidth(), this.frame.getHeight());
				
					player.updatePos(sPressed, wPressed, aPressed, dPressed, controlPressed, nWorld.getMapHeight(), nWorld.getMapWidth(), nWorld.getCombinedMap(bWorld.getMap()));
				
				}		
			
			time2 = System.currentTimeMillis();
			drawTime = time2 - time1;
	
			frameBuffer = (int) ((1000/120) - drawTime);
			System.out.println(drawTime + " = DrawTime");
			System.out.println(frameBuffer + " = FrameBuffer");
			
			if (frameBuffer > 0)
			{
				try  
				{
				Thread.sleep((frameBuffer));
				} 
				catch (InterruptedException e) 
				{
				}
			}

			time2 = System.currentTimeMillis();
			System.out.println("Time = " + (time2 - time1) + " ms" + "Fps = " + (1000 / (time2 - time1)));
			
		}
	}

	public void keyPressed(KeyEvent key) 
	{
	      if (key.getKeyCode() == KeyEvent.VK_W)
	         wPressed = true;
	      if (key.getKeyCode() == KeyEvent.VK_S)
		     sPressed = true;
	      if (key.getKeyCode() == KeyEvent.VK_A)
		     aPressed = true;
	      if (key.getKeyCode() == KeyEvent.VK_D)
		     dPressed = true;
	      if (key.getKeyCode() == KeyEvent.VK_CONTROL)
		     controlPressed = true;
	}

	public void keyReleased(KeyEvent key) 
	{
		if (key.getKeyCode() == KeyEvent.VK_W)
			wPressed = false;
		if (key.getKeyCode() == KeyEvent.VK_S)
			sPressed = false;
		if (key.getKeyCode() == KeyEvent.VK_A)
			aPressed = false;
		if (key.getKeyCode() == KeyEvent.VK_D)
			dPressed = false;
		if (key.getKeyCode() == KeyEvent.VK_CONTROL)
			controlPressed = false;
	}

	public void keyTyped(KeyEvent key) 
	{
		
	}

///////////////////////////////////////////////////////////
	
	public void mouseDragged(MouseEvent arg0) 
	{
		
	}

	public void mouseMoved(MouseEvent mouse) 
	{
		
		mouseXPos = mouse.getX();
		mouseYPos = mouse.getY();
		worldInteraction.getDirectionFaced(mouseXPos, mouseYPos, player);
		
	}
	
///////////////////////////////////////////////////////////
	
	public void mouseClicked(MouseEvent arg0) 
	{
		
	}

	public void mouseEntered(MouseEvent arg0) 
	{
		
	}

	public void mouseExited(MouseEvent arg0) 
	{
		
	}

	public void mousePressed(MouseEvent arg0) 
	{
		if (SwingUtilities.isRightMouseButton(arg0))
		{
			worldInteraction.rightClick(mouseXPos, mouseYPos, player, bWorld, nWorld.getMapWidth(), inventory);			
		}
		if (SwingUtilities.isLeftMouseButton(arg0))
		{
			worldInteraction.leftClick(mouseXPos, mouseYPos, player, bWorld, nWorld, inventory, sound);			
		}
		if (SwingUtilities.isMiddleMouseButton(arg0))
		{
			worldInteraction.middleClick();			
		}
	}

	public void mouseReleased(MouseEvent arg0) 
	{
		
	}

	public void mouseWheelMoved(MouseWheelEvent e) 
	{
		inventory.changeSelect(e.getWheelRotation());
	}

	public BufferStrategy getBufferStrategy() 
	{
		return frame.getBufferStrategy();
	}
	
}
