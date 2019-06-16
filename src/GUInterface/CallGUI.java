package GUInterface;

import ClientServer.MessageServer;
import ClientServer.MessageType;
import ClientServer.ServerInfo;
import GUInterface.Exception.ErrorCall;
import dataHistory.DataWriter;
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

    private boolean calling=false;
    private String st="";
    private JTextField text1;
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
    private ArrayList<String> inputString;
    private String telNumberCalled;
    private JPanel jPAll;
    private JLabel label1;
    private JPanel topPanel;
    private ShowOption showOption;
    private JButton loginRegisterButton;
    private String numberCalling;
    private DataWriter data;

    public CallGUI(String numberCalling){
        this.numberCalling=numberCalling;
        inputString=new ArrayList<>();
        showOption=new ShowOption();
        data=new DataWriter(numberCalling);
        calling=false;
        initComponents();
    }
    private void initComponents(){

        this.setSize(WIDTH,HEIGHT);
        this.setResizable(false);
        this.setLocation(600,200);
        this.setLayout(null);
        jPAll=new JPanel();
        jPAll.setLayout(null);
        jPAll.setBounds(10,50,290,290);
        topPanel=new JPanel();
        this.add(topPanel);


        jPAll.setBounds(0,10,jPAll.getWidth(),jPAll.getHeight());
        label1=new JLabel("please insert the phone number to call: ");
        label1.setBounds(10,10,290,25);
        jPAll.add(label1);

        text1=new JTextField("",20);
        text1.setEditable(false);
        text1.setBackground(Color.white);
        text1.setBounds(10,40,200,25);
        jPAll.add(text1);


        button1=new JButton("1");
        button1.addActionListener(this);
        button1.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button1.setBounds(10,120,BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(button1);


        button2=new JButton("2");
        button2.addActionListener(this);
        button2.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button2.setBounds(button1.getX()+BUTTONWIDTH,button1.getY(),BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(button2);

        button3=new JButton("3");
        button3.addActionListener(this);
        button3.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button3.setBounds(button2.getX()+BUTTONWIDTH,button1.getY(),BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(button3);

        button4=new JButton("4");
        button4.addActionListener(this);
        button4.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button4.setBounds(button1.getX(),button1.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(button4);

        button5=new JButton("5");
        button5.addActionListener(this);
        button5.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button5.setBounds(button2.getX(),button2.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(button5);

        button6=new JButton("6");
        button6.addActionListener(this);
        button6.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button6.setBounds(button3.getX(),button3.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(button6);

        button7=new JButton("7");
        button7.addActionListener(this);
        button7.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button7.setBounds(button4.getX(),button4.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(button7);

        button8=new JButton("8");
        button8.addActionListener(this);
        button8.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button8.setBounds(button5.getX(),button5.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(button8);

        button9=new JButton("9");
        button9.addActionListener(this);
        button9.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button9.setBounds(button6.getX(),button6.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(button9);

        button0=new JButton("0");
        button0.addActionListener(this);
        button0.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        button0.setBounds(button5.getX(),button8.getY()+BUTTONHEIGHT,BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(button0);

        buttonDel=new JButton("Del");
        buttonDel.addActionListener(this);
        buttonDel.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        buttonDel.setBounds(button6.getX(),button0.getY(),BUTTONWIDTH,BUTTONHEIGHT);
        jPAll.add(buttonDel);

        buttonSC=new JButton(new ImageIcon("endCall.jpg"));
        buttonSC.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        buttonSC.addActionListener(this);
        buttonSC.setActionCommand("endcall");
        buttonSC.setBounds(130,80,120,BUTTONHEIGHT);
        jPAll.add(buttonSC);

        buttonEC=new JButton(new ImageIcon("enterCall.jpg"));
        buttonEC.setBackground(Color.white);
        buttonEC.addActionListener(this);
        buttonEC.setBackground(new Color(BUTTONR,BUTTONG,BUTTONB));
        buttonEC.setActionCommand("startcall");
        buttonEC.setBounds(button1.getX(),buttonSC.getY(),buttonSC.getWidth(),BUTTONHEIGHT);
        jPAll.add(buttonEC);


        this.add(jPAll);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {
        st = text1.getText();
        if (e.getActionCommand().equalsIgnoreCase("Del")) {
            inputString.trimToSize();
            if (!inputString.isEmpty()) {
                if (inputString.size() == 1) {
                    text1.setText("");
                    inputString.clear();
                } else {
                    inputString.remove(0);
                    text1.setText(inputString.get(0));
                    st = inputString.get(0);
                }
                if(calling) {
                    printChoice(text1.getText().trim());
                    try {
                        data.updateHistory("The user deleted his last choice" );
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("endcall")) {
            if(!calling) {
                this.dispose();
                showOption.dispose();
            }
            else{
                try {
                    data.updateHistory("Call Ended");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                inputString.clear();
                text1.setText("");
                //calling=false;
                CallGUI newCall=new CallGUI(numberCalling);
                newCall.setVisible(true);
                showOption.dispose();
                this.dispose();

            }
        } else if (e.getActionCommand().equalsIgnoreCase("startcall")) {
            if(!calling){
                if (text1.getText().trim().length() < 3 || text1.getText().trim().length() > 10) {
                    ErrorCall eGUI1 = new ErrorCall();
                    eGUI1.setVisible(true);
                } else {
                    try {
                        data.updateHistory("Call Started");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    telNumberCalled = text1.getText();
                    text1.setText("");
                    inputString.clear();
                    calling = true;
                    st = "";
                    label1.setText("Insert your choice: ");
                    printChoice(st);
                }
            }
        } else {

            st += e.getActionCommand();
            text1.setText(st);

            inputString.add(0, st);
            if (calling) {
                try {
                    data.updateHistory("The User pressed: "+e.getActionCommand());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                printChoice(st);
            }
        }
    }

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
                CallGUI callGUI=new CallGUI(numberCalling);
                callGUI.setVisible(true);
                RegisterLogin a = new RegisterLogin(numberCalling,telNumberCalled);
                a.setVisible(true);
                close();
                showOption.dispose();


                /*}
                else{
                    NumberToCallNotFound error=new NumberToCallNotFound();
                    error.setVisible(true);
                }*/
            }
        });
        jPAll.setBounds(10,50,290,290);
        showOption.updateOptions(arrayList);
        showOption.setVisible(true);
        this.repaint();
    }
    private void close(){
        this.dispose();
    }

}
