package GUInterface;

import GUInterface.Exception.EmptyField;
import GUInterface.Exception.ErrorCall;
import GUInterface.Exception.NeedANumber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NumberPhoneGUI extends JFrame {
    private final int MINLENGTH=3;
    private final int MAXLENGTH=10;
    private final int WINDOWX1=650;
    private final int WINDOWY1=300;
    private final int WINDOWX2=350;
    private final int WINDOWY2=170;
    private final int LABELNUMBERX1=10;
    private final int LABELNUMBERY1=10;
    private final int LABELNUMBERX2=290;
    private final int LABELNUMBERY2=25;
    private final int TEXTNUMBERX1=10;
    private final int TEXTNUMBERY1=40;
    private final int TEXTNUMBERX2=200;
    private final int TEXTNUMBERY2=25;
    private final int BUTTONNUMBERX1=100;
    private final int BUTTONNUMBERY1=80;
    private final int BUTTONNUMBERX2=60;
    private final int BUTTONNUMBERY2=30;
    private final String TITLE="Number Phone Window";
    private final String LABEL="Please insert your phone number: ";
    private final String BUTTONTEXT="OK";


    public NumberPhoneGUI() {
        this.setBounds(WINDOWX1,WINDOWY1,WINDOWX2,WINDOWY2);
        this.setTitle(TITLE);
        this.setResizable(false);
        this.setLayout(null);
        JLabel info = new JLabel(LABEL);
        info.setBounds(LABELNUMBERX1,LABELNUMBERY1,LABELNUMBERX2,LABELNUMBERY2);
        this.add(info);
        JTextField numberText = new JTextField("");
        numberText.setBounds(TEXTNUMBERX1,TEXTNUMBERY1,TEXTNUMBERX2,TEXTNUMBERY2);
        this.add(numberText);
        JButton buttonOk = new JButton(BUTTONTEXT);
        buttonOk.setBounds(BUTTONNUMBERX1,BUTTONNUMBERY1,BUTTONNUMBERX2,BUTTONNUMBERY2);
        this.add(buttonOk);
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String toCheck = numberText.getText().trim();
                if (!toCheck.equals("")) {
                    if (isValid(toCheck)) {
                        if (toCheck.length() < MINLENGTH || toCheck.length() > MAXLENGTH) {
                            ErrorCall errorCall = new ErrorCall();
                            errorCall.setVisible(true);
                        }
                        else {
                            CallGUI call = new CallGUI(toCheck);
                            call.setVisible(true);
                            close();
                        }
                    }
                    else{
                        NeedANumber errorNumber = new NeedANumber();
                        errorNumber.setVisible(true);
                    }
                }
                else{
                    EmptyField error = new EmptyField();
                    error.setVisible(true);
                }
            }
        });
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method checks if the input is composed only by numbers
     * @param iDToCheck
     * @return
     */
    private boolean isValid(String iDToCheck){
        return iDToCheck.chars().allMatch(x -> Character.isDigit(x));
    }

    /**
     * This method is used to dispose the window
     */
    private void close(){
        this.dispose();
    }
}
