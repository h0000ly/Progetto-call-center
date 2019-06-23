package GUInterface;

import javax.swing.*;
import java.util.ArrayList;

public class ShowOption extends JFrame {
	
    private final static int WIDTH = 300;
    private final static int HEIGHT = 300;
	
    private JLabel option;
    private JPanel toClean;

    public ShowOption() {
        this.setLayout(null);
        this.setBounds(1000,400,WIDTH,HEIGHT);
        toClean = new JPanel();
        toClean.setLayout(null);
        toClean.setBounds(0,0,WIDTH,HEIGHT);
        this.add(toClean);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is used to update the available choices for the user displayed
     * @param toShow
     */
    public void updateOptions(ArrayList<String> toShow) {
        int i = 30;
        toClean.removeAll();
        JLabel pressLabel = new JLabel("Press: ");
        toClean.add(pressLabel);
        pressLabel.setBounds(5,5,200,25);
        if (!toShow.isEmpty()) {
            for(String toCheck:toShow) {
                option = new JLabel(toCheck);
                option.setBounds(5,i,290,25);
                toClean.add(option);
                i+=20;
            }
        }
        else{
            pressLabel.setText("No options available");
        }
        this.repaint();
    }
	
}
