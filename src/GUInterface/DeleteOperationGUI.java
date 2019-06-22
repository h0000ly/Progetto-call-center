package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.EmptyField;
import GUInterface.Exception.IDIsANumber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DeleteOperationGUI extends JFrame{
    private JTextField jT1;
    private String number;
    private String numCalling;

    public DeleteOperationGUI(String numCalling,String number) {
        this.number = number;
        this.numCalling=numCalling;
        initiate();
    }


    private void initiate(){
        this.setBounds(600,200,350,150);//height= 200
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Delete operation window");
        JLabel jL1=new JLabel("Insert the id of the operation to delete: ");
        jL1.setBounds(10,10,300,25);
        this.add(jL1);
        jT1=new JTextField("");
        jT1.setBounds(10,40,150,25);
        this.add(jT1);
        JButton button=new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!jT1.getText().equals("")) {
                    if(isValid(jT1.getText())) {
                        Socket socket = null;
                        try {
                            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
                            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                            os.writeObject(new MessageServer(MessageType.DELETEOPERATION,numCalling, jT1.getText().trim(), number));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        end();
                    }
                    else{
                        IDIsANumber errorID=new IDIsANumber();
                        errorID.setVisible(true);
                    }
                }
                else{
                    EmptyField emptyError=new EmptyField();
                    emptyError.setVisible(true);
                }
            }
        });
        button.setBounds(180,40,75,20);
        this.add(button);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method dispose the window
     */
    private void end(){
        this.dispose();
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
}
