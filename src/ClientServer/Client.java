package ClientServer;


import GUInterface.CallGUI;

import javax.swing.*;

public class Client extends Thread{

    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CallGUI cGUI =new CallGUI();
                cGUI.setVisible(true);
            }
        });
    }

}

