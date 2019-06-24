package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.EmptyField;
import GUInterface.Exception.IDIsANumber;
import model.Operation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UpdateTextOperationGUI extends JFrame {
    private final int WINDOWX1=600;
    private final int WINDOWY1=200;
    private final int WINDOWX2=345;
    private final int WINDOWY2=180;
    private final int LABELIDX1=10;
    private final int LABELIDY1=10;
    private final int LABELIDX2=300;
    private final int LABELIDY2=25;
    private final int TEXTIDX1=10;
    private final int TEXTIDY1=30;
    private final int TEXTIDX2=200;
    private final int TEXTIDY2=25;
    private final int LABELTEXTX1=10;
    private final int LABELTEXTY1=60;
    private final int LABELTEXTX2=300;
    private final int LABELTEXTY2=25;
    private final int TEXTTEXTX1=10;
    private final int TEXTTEXTY1=80;
    private final int TEXTTEXTX2=200;
    private final int TEXTTEXTY2=25;
    private final int BUTTONX1=130;
    private final int BUTTONY1=113;
    private final int BUTTONX2=60;
    private final int BUTTONY2=20;
    private final String TITLE="UpdateText operation window";
    private final String LABELID="Insert the id of the operation to modify: ";
    private final String LABELTEXT="Insert the new text for the operation: ";
    private final String BUTTON="OK";
    //private JTextField jT1;
    //private JTextField jT2;
    private String number;
    private String numCalling;

    public UpdateTextOperationGUI(String numCalling, String number) {
        this.number = number;
        this.numCalling = numCalling;
        initialize();
    }
	
    private void initialize(){
        this.setBounds(WINDOWX1,WINDOWY1,WINDOWX2,WINDOWY2);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle(TITLE);
        JLabel jL1 = new JLabel(LABELID);
        jL1.setBounds(LABELIDX1,LABELIDY1,LABELIDX2,LABELIDY2);
        this.add(jL1);
        JTextField jT1 = new JTextField("");
        jT1.setBounds(TEXTIDX1,TEXTIDY1,TEXTIDX2,TEXTIDY2);
        this.add(jT1);
        JLabel jL2 = new JLabel(LABELTEXT);
        jL2.setBounds(LABELTEXTX1,LABELTEXTY1,LABELTEXTX2,LABELTEXTY2);
        this.add(jL2);
        JTextField jT2 = new JTextField("");
        jT2.setBounds(TEXTTEXTX1,TEXTTEXTY1,TEXTTEXTX2,TEXTTEXTY2);
        this.add(jT2);
        JButton button = new JButton(BUTTON);
        button.setBounds(BUTTONX1,BUTTONY1,BUTTONX2,BUTTONY2);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket socket = null;
                if (!jT1.getText().equals("") && !jT2.getText().equals("")) {
                    if (isValid(jT1.getText())) {
                        try {
                            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                            os.writeObject(new MessageServer(MessageType.MODIFYTEXTOPERATION, numCalling, new Operation(jT1.getText().trim(), number, jT2.getText().trim())));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        end();
                    }
                    else{
                        IDIsANumber errorID = new IDIsANumber();
                        errorID.setVisible(true);
                    }
                }
                else{
                    EmptyField emptyField = new EmptyField();
                    emptyField.setVisible(true);
                }
            }
        });
        this.add(button);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method checks if the input is composed only by numbers
     * @param iDToCheck
     * @return
     */
    private boolean isValid(String iDToCheck){
        for(int i=0;i<iDToCheck.length();i++){
            if(!Character.isDigit(iDToCheck.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * This method dispose the window
     */
    private void end(){
        this.dispose();
    }

}
