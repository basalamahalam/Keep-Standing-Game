/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ViewModel;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author ACER
 */
public class BGM {
    public Clip playSound(Clip clip, String filename) {
        try {
            // Fetch BGm
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("resources/" + filename).getAbsoluteFile());
            clip = AudioSystem.getClip();
            
            clip.open(audioInput);  // Open Input
            clip.start();   // Start
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop
        } catch(UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(LineUnavailableException e) {
            e.printStackTrace();
        }
        
        return clip;
    }
    
    // Stop BGM
    public void stopSound(Clip clip) {
        clip.stop();
    }
}
