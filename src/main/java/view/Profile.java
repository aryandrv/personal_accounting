package view;

import controller.UserController;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import model.entity.User;
import oracle.sql.TIMESTAMP;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;

public class Profile extends JFrame {

    public Profile() {
        initComponent();
        drawGui();

    }

    private void initComponent() {
        username_Label = new JLabel("Username");
        username_TextField = new JTextField();
        username_TextField.setEnabled(false);

        password_Label = new JLabel("Password");
        password_TextField = new JPasswordField();

        name_Label = new JLabel("Name");
        name_TextField = new JTextField();

        family_Label = new JLabel("Family");
        family_TextField = new JTextField();

        creationDate_Label = new JLabel("Creation date");
        creationDatePicker = new JXDatePicker();
        creationDatePicker.setEnabled(false);

        apply_Button = new JButton("Apply");
        apply_Button.addActionListener(e -> {
            apply_ButtonActionPreform();
        });
        close_Button = new JButton("Close");
        close_Button.addActionListener(e -> {
            super.dispose();
        });

        setSize(500, 900);


    }

    private void drawGui() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(username_Label)
                                        .addComponent(password_Label)
                                        .addComponent(name_Label)
                                        .addComponent(family_Label)
                                        .addComponent(creationDate_Label)
                                )
                                .addGap(15)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(username_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(password_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(name_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(family_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(creationDatePicker, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                )
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(apply_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(close_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                        )
                )
                .addGap(10)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(username_Label)
                        .addComponent(username_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(password_Label)
                        .addComponent(password_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(name_Label)
                        .addComponent(name_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(family_Label)
                        .addComponent(family_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(creationDate_Label)
                        .addComponent(creationDatePicker, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(apply_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(close_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(30)
        );

        pack();
    }

    public void fillForm(User user,TopoForm topoform) {
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Can not fill form");
        } else {
            try {
                this.topoform = topoform;
                this.user = user;
                username_TextField.setText(user.getUsername());
                password_TextField.setText(user.getPassword());
                name_TextField.setText(user.getName());
                family_TextField.setText(user.getFamily());
                creationDatePicker.setDate(Timestamp.valueOf(user.getCreationDate()));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "can not fill form");
            }


        }

    }

    private void apply_ButtonActionPreform() {
        try {
            StringBuilder password = new StringBuilder();
            for (Character ch : password_TextField.getPassword()) {
                password.append(ch);
            }
            user = UserController.getController().edit(user.getId(), name_TextField.getText(),
                    family_TextField.getText(),
                    username_TextField.getText()
                    ,password.toString()
                    ,user.getCreationDate());
            topoform.user = this.user;
            topoform.refresh_ButtonActionPreform();
            super.dispose();




        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Please write correct");
        }


    }


    public static void main(String[] args) {
        Profile profile = new Profile();
        profile.setVisible(true);
    }

    private User user;
    private TopoForm topoform;
    private JLabel username_Label;
    private JLabel password_Label;
    private JLabel name_Label;
    private JLabel family_Label;
    private JLabel creationDate_Label;

    private JTextField username_TextField;
    private JPasswordField password_TextField;
    private JTextField name_TextField;
    private JTextField family_TextField;

    private JButton apply_Button;
    private JButton close_Button;

    private JXDatePicker creationDatePicker;

}
