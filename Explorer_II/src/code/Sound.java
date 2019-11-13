package code;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound 
{
	
	AudioInputStream musicWav;
	AudioInputStream oceanWav;
	AudioInputStream swingWav;
	Clip music;
	Clip ocean;
	Clip swing;

	public Sound()
	{
		
		try 
		{
			musicWav = AudioSystem.getAudioInputStream(new File("src/resources/sound/music/ora.wav").getAbsoluteFile());
			oceanWav = AudioSystem.getAudioInputStream(new File("src/resources/sound/ambiant/ocean.wav").getAbsoluteFile());
			swingWav = AudioSystem.getAudioInputStream(new File("src/resources/sound/soundEffect/woosh.wav").getAbsoluteFile());
			
			music = AudioSystem.getClip();
			ocean = AudioSystem.getClip();
			swing = AudioSystem.getClip();
			music.open(musicWav);
			ocean.open(oceanWav);
			swing.open(swingWav);
		    
		} catch (Exception e)
		{
			System.out.println(e + "------------");
		}

		FloatControl gainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-15.0f);
		gainControl = (FloatControl) ocean.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-20.0f);
		
	}
	
	public void playMusic()
	{
		
		music.loop(-1);
		ocean.loop(-1);
		
	}
	
	public void playSwing()
	{			
		swing.loop(1);	
	}
	
}
