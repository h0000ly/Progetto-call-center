package GUInterface.Exception;



public class PasswordNotEqual extends MasterClassGUIException {
    private static final String TOSHOW="The passwords are not the same";
    private static final String TITLE="Error";
    public PasswordNotEqual(){
        super(TITLE,TOSHOW);
        /*this.setBounds(600,400,300,150);
        this.setLayout(null);
        this.setTitle("Error");
        this.setResizable(false);
        JLabel text=new JLabel("The Passwords are not equal");
        text.setBounds(10,10,200,50);
        this.add(text);
        JButton b=new JButton("OK");
        b.setBounds(120,60,60,35);
        this.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    private void close(){
        this.dispose();*/
    }
}
