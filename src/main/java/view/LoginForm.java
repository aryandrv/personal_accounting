package view;

import controller.UserController;
import model.entity.User;

import javax.swing.*;
import java.time.LocalDateTime;

public class LoginForm extends JFrame {
    public LoginForm() {
        initComponent();
        drawGui();
    }

    private void initComponent() {

        username_Label = new JLabel("Username");
        password_Label = new JLabel("Password");

        username_TextField = new JTextField();
        password_TextField = new JTextField();

        login_Button = new JButton("Log in");
        login_Button.addActionListener(e -> {
            login_ButtonActionPreform();
        });

        close_Button = new JButton("Close");
        close_Button.addActionListener(e -> {
            System.out.println("bye");
            super.dispose();

        });
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void drawGui() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(username_Label)
                        .addComponent(password_Label)

                )
                .addGap(15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(username_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(password_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(username_Label)
                        .addComponent(username_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(password_Label)
                        .addComponent(password_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
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
        String username = username_TextField.getText();
        String password = password_TextField.getText();
        if (!username.isEmpty() && !password.isEmpty()) {
            User user = UserController.getController().findByUsernameAndPassword(username, password);
            if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                dispose();
                TopoForm topoform = new TopoForm(user);
                topoform.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Username or password incorrect");
            }
        }

    }

    public static void main(String[] args) {
        try {

        User user = UserController.getController().findByUsername("Root");
        if(user == null){
            user = UserController.getController().save(1,"root","root","Root","12345678", LocalDateTime.now());

        }
        }catch (Exception e){
            e.printStackTrace();

        }


        LoginForm page = new LoginForm();
        page.setVisible(true);

    }

    private JLabel username_Label;
    private JLabel password_Label;
    private JTextField username_TextField;
    private JTextField password_TextField;

    private JButton login_Button;
    private JButton close_Button;
}
