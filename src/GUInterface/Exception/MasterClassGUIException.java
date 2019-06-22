package GUInterface.Exception;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public abstract class MasterClassGUIException extends JFrame {
    /**
     * macro for the allignment of the components
     */
    private final static int WINDOWX=600;
    private final static int WINDOWY=300;
    private final static int WINDOWHEIGTH=150;
    private final static int BUTTONWIDTH=60;
    private final static int BUTTONHEIGHT=35;
    private final static int LABELX=10;
    private final static int LABELY=10;
    private final static int LABELHEIGHT=50;
    private final static int WINDOWWIDTH=500;

    public MasterClassGUIException(String windowTitle,String stringToShow){
    this.setBounds(WINDOWX,WINDOWY,WINDOWWIDTH,WINDOWHEIGTH);

        this.setLayout(null);
        this.setTitle(windowTitle);
        this.setResizable(false);
    JLabel text=new JLabel(stringToShow);
        text.setBounds(LABELX,LABELY,WINDOWWIDTH-LABELX,LABELHEIGHT);
        this.add(text);
    JButton b=new JButton("OK");
        b.setBounds((WINDOWWIDTH-BUTTONWIDTH)/2,LABELY+40,BUTTONWIDTH,BUTTONHEIGHT);
        this.add(b);
        b.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
        }
    });
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}

    /**
     * This method is used to dispose the window
     */
    private void close() {
        this.dispose();
    }
}
