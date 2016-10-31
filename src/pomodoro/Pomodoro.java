package pomodoro;

//<div>Icons made by <a href="http://www.flaticon.com/authors/madebyoliver" title="Madebyoliver">Madebyoliver</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
//http://image.flaticon.com/icons/svg/135/135702.svg

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Pomodoro implements ActionListener, WindowStateListener
                                              {

    private final JFrame frame;
    private final JPanel panel;
    private final JLabel label;
    private int duration;
    private int seconds;
    
    public static final int WIDTH = 60;
    public static final int HEIGHT = 30;

    public Pomodoro(){
        this.duration = 24;
        this.seconds = 60;
        this.label = new JLabel();
        this.frame = new JFrame();
        this.panel = new JPanel();
		
        showFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Pomodoro();
            }
        });
    }

    private void showFrame() {
        
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        
        label.setText(String.valueOf(this.duration)+":"+String.valueOf(this.seconds));
        label.setFont(new Font("serif", Font.BOLD, 24));
        label.setHorizontalAlignment(JLabel.CENTER);        
        
        panel.add(label);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true); 
        frame.setBounds(screenDimension.width-WIDTH, (screenDimension.height-HEIGHT)/2,WIDTH, HEIGHT);
        frame.setContentPane(panel);
        frame.setBackground(new Color(0, 255, 0, 0)); 
//        frame.setIconImage(getImage("pomodoro.png"));
        frame.setIconImage(getImageFromURL("http://manoharacademy.com/images/pomodoro.png"));            

        frame.addWindowStateListener(this);        
        
        Timer timer = new Timer(1000, this);
        timer.start();       
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        this.seconds--;
        if(this.seconds == 0){
            this.seconds = 60;
            this.duration--;            
        }       

        if (this.duration == -1) { 
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Pomodoro Session is complete");            
            System.exit(0);
        }else{
            label.setText(String.valueOf(this.duration)+":"+String.format("%1$02d", this.seconds));  
            frame.setTitle(String.valueOf(this.duration)+":"+String.format("%1$02d", this.seconds)); 
        }        
    }
    
    private void showWindow() throws SecurityException {
        frame.setState(JFrame.NORMAL); 
        frame.setAlwaysOnTop(true);
    }
    
    private Image getImageFromURL(String urlString){
        try {            
            return new ImageIcon(new URL(urlString)).getImage();
        } catch (MalformedURLException ex) {
            System.out.println("throws");
        }
        return null;
    }
    
    private Image getImage(String imageName){
        return new ImageIcon(this.getClass().getResource(imageName)).getImage();
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        showWindow();
    }
}
