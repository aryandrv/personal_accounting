package view;

import controller.UserController;
import model.entity.User;

import javax.swing.*;

public class LoginForm extends JFrame {
    public LoginForm() {
        initComponent();
        drawGui();
    }

    private void initComponent() {
        username_Lable = new JLabel("Username");
        password_Lable = new JLabel("Password");

        username_Textfield = new JTextField();
        password_Textfield = new JTextField();

        login_Button = new JButton("Log in");
        login_Button.addActionListener(e -> {
            login_ButtonActionPreform();
        });

        close_Button = new JButton("Close");
        close_Button.addActionListener(e ->{
            System.out.println("bye");
            super.dispose();

        });


    }

    private void drawGui() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(username_Lable)
                                .addGap(15)
                                .addComponent(username_Textfield, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)

                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(password_Lable)
                                .addGap(15)
                                .addComponent(password_Textfield, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(login_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(close_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                        )

                )
                .addGap(20)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(username_Lable)
                        .addComponent(username_Textfield, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(password_Lable)
                        .addComponent(password_Textfield, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(login_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(close_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(50)
        );
        pack();


    }

    private void login_ButtonActionPreform() {
        String username = username_Textfield.getText();
        String password = password_Textfield.getText();
        if(!username.isEmpty() && !password.isEmpty()){
            User user = UserController.getController().findByUsernameAndPassword(username,password);
            if(user != null && user.getUsername().equals(username) && user.getPassword().equals(password)){
                System.out.println("welcome to my room");
            }else{
                System.out.println("ridi");
            }
        }

    }

    public static void main(String[] args) {
        LoginForm page = new LoginForm();
        page.setVisible(true);
        page.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private JLabel username_Lable;
    private JLabel password_Lable;
    private JTextField username_Textfield;
    private JTextField password_Textfield;

    private JButton login_Button;
    private JButton close_Button;
}
