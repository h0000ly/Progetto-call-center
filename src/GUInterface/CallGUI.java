package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.ErrorCall;
import dataHistory.DataWriterClient;
import model.Operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class CallGUI extends JFrame implements ActionListener{
    private static final int BUTTONWIDTH=80;
    private static final int BUTTONHEIGHT=30;
    private static final int HEIGHT=300;
    private static final int WIDTH=280;
    private static final int BUTTONR=204;
    private static final int BUTTONG=225;
    private static final int BUTTONB=228;
    private static final String DELETEBUTTONPRESSED="The user deleted his last choice";
    private static final String STARTCALL="Call started";
    private static final String ENDCALL="Call ended";
    private static final String NUMBERBUTTONPRESSED="The user pressed the button";
    private boolean calling=false;
    private String st="";
    private JTextField textOnDisplay;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button0;
    private JButton buttonSC;
    private JButton buttonEC;
    private JButton buttonDel;
    private ArrayList<String> optionToShow;
    private String telNumberCalled;
    private JPanel buttonAndTextPanel;
    private JLabel descriptionLabel;

    private ShowOption showOption;
    private JButton loginRegisterButton;
    private String numberCalling;
    private DataWriterClient data;

    public CallGUI(String numberCalling){
        this.numberCalling=numberCalling;
        optionToShow =new ArrayList<>();
        showOption=new ShowOption();
        data=new DataWriterClient(numberCalling);
        calling=false;
        initComponents();
    }
    private void initComponents(){

        this.setSize(WIDTH,HEIGHT);
        this.setResizable(false);
        this.setLocation(600,200);
        this.setLayout(null);
        buttonAndTextPanel =new JPanel();
        buttonAndTextPanel.setLayout(null);
        buttonAndTextPanel.setBounds(10,50,290,290);


        buttonAndTextPanel.setBounds(0,10, buttonAndTextPanel.getWidth(), buttonAndTextPanel.getHeight());
        descriptionLabel =new JLabel("please insert the phone number to call: ");
        descriptionLabel.setBounds(10,10,290,25);
        buttonAndTextPanel.add(descriptionLabel);

        textOnDisplay =new JTextField("",20);
        textOnDisplay.setEditable(false);
        textOnDisplay.setBackground(Color.white);
        textOnDisplay.setBounds(10,40,200,25);
        buttonAndTextPanel.add(textOnDisplay);


        button1=new JButton("1");
        button1.addActionListener(this);
        button1.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button1.setBounds(10,120,BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(button1);


        button2=new JButton("2");
        button2.addActionListener(this);
        button2.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button2.setBounds(button1.getX()+BUTTONWIDTH,button1.getY(),BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(button2);

        button3=new JButton("3");
        button3.addActionListener(this);
        button3.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button3.setBounds(button2.getX()+BUTTONWIDTH,button1.getY(),BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(button3);

        button4=new JButton("4");
        button4.addActionListener(this);
        button4.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button4.setBounds(button1.getX(),button1.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(button4);

        button5=new JButton("5");
        button5.addActionListener(this);
        button5.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button5.setBounds(button2.getX(),button2.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(button5);

        button6=new JButton("6");
        button6.addActionListener(this);
        button6.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button6.setBounds(button3.getX(),button3.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(button6);

        button7=new JButton("7");
        button7.addActionListener(this);
        button7.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button7.setBounds(button4.getX(),button4.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(button7);

        button8=new JButton("8");
        button8.addActionListener(this);
        button8.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button8.setBounds(button5.getX(),button5.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(button8);

        button9=new JButton("9");
        button9.addActionListener(this);
        button9.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button9.setBounds(button6.getX(),button6.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(button9);

        button0=new JButton("0");
        button0.addActionListener(this);
        button0.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button0.setBounds(button5.getX(),button8.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(button0);

        buttonDel=new JButton("Del");
        buttonDel.addActionListener(this);
        buttonDel.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        buttonDel.setBounds(button6.getX(),button0.getY(),BUTTONWIDTH,BUTTONHEIGHT);
        buttonAndTextPanel.add(buttonDel);

        buttonSC=new JButton(new ImageIcon("endCall.jpg"));
        buttonSC.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        buttonSC.addActionListener(this);
        buttonSC.setActionCommand("endcall");
        buttonSC.setBounds(130,80,120,BUTTONHEIGHT);
        buttonAndTextPanel.add(buttonSC);

        buttonEC=new JButton(new ImageIcon("enterCall.jpg"));
        buttonEC.setBackground(Color.white);
        buttonEC.addActionListener(this);
        buttonEC.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        buttonEC.setActionCommand("startcall");
        buttonEC.setBounds(button1.getX(),buttonSC.getY(),buttonSC.getWidth(),BUTTONHEIGHT);
        buttonAndTextPanel.add(buttonEC);


        this.add(buttonAndTextPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                showOption.dispose();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
    public void actionPerformed(ActionEvent e) {
        st = textOnDisplay.getText();
        if (e.getActionCommand().equalsIgnoreCase("Del")) {
            optionToShow.trimToSize();
            if (!optionToShow.isEmpty()) {
                if (optionToShow.size() == 1) {
                    textOnDisplay.setText("");
                    optionToShow.clear();
                } else {
                    optionToShow.remove(0);
                    textOnDisplay.setText(optionToShow.get(0));
                    st = optionToShow.get(0);
                }
                if(calling) {
                    printChoice(textOnDisplay.getText().trim());
                        data.updateHistory(DELETEBUTTONPRESSED);
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("endcall")) {
            if(!calling) {
                this.dispose();
                showOption.dispose();
            }
            else{
                data.updateHistory(ENDCALL);
                optionToShow.clear();
                textOnDisplay.setText("");
                //calling=false;
                CallGUI newCall=new CallGUI(numberCalling);
                newCall.setVisible(true);
                showOption.dispose();
                this.dispose();

            }
        } else if (e.getActionCommand().equalsIgnoreCase("startcall")) {
            if(!calling){
                if (textOnDisplay.getText().trim().length() < 3 || textOnDisplay.getText().trim().length() > 10) {
                    ErrorCall eGUI1 = new ErrorCall();
                    eGUI1.setVisible(true);
                } else {

                        data.updateHistory(STARTCALL);

                    telNumberCalled = textOnDisplay.getText();
                    textOnDisplay.setText("");
                    optionToShow.clear();
                    calling = true;
                    st = "";
                    descriptionLabel.setText("Insert your choice: ");
                    printChoice(st);
                }
            }
        } else {

            st += e.getActionCommand();
            textOnDisplay.setText(st);

            optionToShow.add(0, st);
            if (calling) {
                    data.updateHistory(NUMBERBUTTONPRESSED+e.getActionCommand());

                printChoice(st);
            }
        }
    }

    /**
     * This method is called to show all the available operations for a defined number called
     * @param toTest
     */
    private void printChoice(String toTest){
        Socket socket = null;
        ArrayList<Operation> options=new ArrayList<>();
        ArrayList<String> optionString=new ArrayList<>();
        try {
            socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            os.writeObject(new MessageServer(MessageType.RETURNAVAILABLECHOICES, numberCalling,telNumberCalled,toTest));
            options = (ArrayList<Operation>)is.readObject();
            for(Operation o:options){
                optionString.add(o.getId().charAt(o.getId().length()-1)+" - "+o.getTextOp());
            }
            showOptions(optionString);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * this method change the graphic of the window
     * @param arrayList
     */
    private void showOptions(ArrayList<String> arrayList){

        this.setSize(290,350);
        loginRegisterButton=new JButton("Login/Register");

        loginRegisterButton.setBounds(120,5,150,30);
        this.add(loginRegisterButton);
        loginRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //THE COMMENTS BELOW MUST BE USED IF U WANT TO CHECK IF THERE ARE ALREADY OPTIONS FOR THE NUMBER IN
                // INPUT. WITHOUT IT EVERYONE CAN CALL A NUMBER AND BECOME AN OPERATOR OF THAT NUMBER

                //if(!arrayList.isEmpty()) {
                RegisterLogin a = new RegisterLogin(numberCalling,telNumberCalled);
                a.setVisible(true);
                //close();

                /*}
                else{
                    NumberToCallNotFound error=new NumberToCallNotFound();
                    error.setVisible(true);
                }*/
            }
        });
        buttonAndTextPanel.setBounds(10,50,290,290);
        showOption.updateOptions(arrayList);
        showOption.setVisible(true);
        this.repaint();
    }

}
