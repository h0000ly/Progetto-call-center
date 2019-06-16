package ClientServer;


import GUInterface.CallGUI;
import GUInterface.NumberPhoneGUI;

import javax.swing.*;

public class Client extends Thread{

    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberPhoneGUI startGUI=new NumberPhoneGUI();
                startGUI.setVisible(true);
            }
        });
    }

}

