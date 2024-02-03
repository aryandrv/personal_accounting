package view;

import controller.UserController;
import model.entity.User;

import javax.swing.*;
import java.time.LocalDateTime;

public class TopoForm extends JFrame {

    public TopoForm(User user) {
        this.user = user;
        initComponent();
        drawGui();
    }

    private void initComponent() {
        username = new JLabel("Welcome : " + user.getUsername());
        timeToLogin = new JLabel("Time to login : " + LocalDateTime.now().toLocalDate());

        user_button = new JButton("User management");
        transaction_button = new JButton("Transaction");
        bankAccount_button = new JButton("Bank account");
        titles_button = new JButton("Titles");

        user_button.addActionListener(e -> {
            Profile profile = new Profile();
            profile.fillForm(user,this);
            profile.setVisible(true);
        });

        refresh_button = new JButton("Refresh");
        refresh_button.addActionListener(e -> {
            refresh_ButtonActionPreform();

        });


        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    private void drawGui() {

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(username)
                        .addComponent(timeToLogin)
                        .addComponent(user_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(transaction_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bankAccount_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(titles_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(refresh_button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(50)
                .addComponent(username)
                .addGap(5)
                .addComponent(timeToLogin)
                .addGap(10)
                        .addComponent(user_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                        .addComponent(transaction_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                        .addComponent(bankAccount_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                        .addComponent(titles_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addComponent(refresh_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(50)
        );
        pack();
    }

    public void refresh_ButtonActionPreform(){
        try{
        user = UserController.getController().findById(user.getId());

        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"eror");

        }
    }

    private JLabel username;
    private JLabel timeToLogin;
    private JButton user_button;
    private JButton transaction_button;
    private JButton titles_button;
    private JButton bankAccount_button;
    private JButton refresh_button;

    public User user;


}
