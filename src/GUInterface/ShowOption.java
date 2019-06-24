package GUInterface;

import javax.swing.*;
import java.util.ArrayList;

public class ShowOption extends JFrame {

    private final int WINDOWX1=1000;
    private final int WINDOWY1=400;
    private final int WINDOWX2 = 300;
    private final int WINDOWY2 = 300;
    private final int PANELX1=0;
    private final int PANELY1=0;
    private final int PANELX2=300;
    private final int PANELY2=300;
    private final int PRESSLABELX1=5;
    private final int PRESSLABELY1=5;
    private final int PRESSLABELX2=200;
    private final int PRESSLABELY2=25;
    private final int OPTIONLABELX1=5;
    private final int OPTIONLABELX2=290;
    private final int OPTIONLABELY2=25;
    private final String PRESS="Press: ";
    private final String NOOPTION="No options available";
	
    private JLabel option;
    private JPanel toClean;

    public ShowOption() {
        this.setLayout(null);
        this.setBounds(WINDOWX1,WINDOWY1,WINDOWX2,WINDOWY2);
        toClean = new JPanel();
        toClean.setLayout(null);
        toClean.setBounds(PANELX1,PANELY1,PANELX2,PANELY2);
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
        JLabel pressLabel = new JLabel(PRESS);
        toClean.add(pressLabel);
        pressLabel.setBounds(PRESSLABELX1,PRESSLABELY1,PRESSLABELX2,PRESSLABELY2);
        if (!toShow.isEmpty()) {
            for(String toCheck:toShow) {
                option = new JLabel(toCheck);
                option.setBounds(OPTIONLABELX1,i,OPTIONLABELX2,OPTIONLABELY2);
                toClean.add(option);
                i+=20;
            }
        }
        else{
            pressLabel.setText(NOOPTION);
        }
        this.repaint();
    }
	
}
