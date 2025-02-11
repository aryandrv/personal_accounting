package view;

import controller.UserController;
import model.entity.User;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.sql.Timestamp;

public class ProfileManagementForm extends JPanel {

    public ProfileManagementForm() {
        initComponent();
        drawGui();
    }

    private void initComponent() {

        info_Panel = new JPanel();
        info_Panel.setBorder(BorderFactory.createTitledBorder("Information"));


        modify_Panel = new JPanel();
        modify_Panel.setBorder(BorderFactory.createTitledBorder("Modify"));
        modify_Panel.setVisible(false);

        usernameInfo_Label = new JLabel("Username : ");
        usernameInfoValue_Label = new JLabel();

        passwordInfo_Label = new JLabel("Password : ");
        passwordInfoValue_Label = new JLabel();

        nameInfo_Label = new JLabel("Name : ");
        nameInfoValue_Label = new JLabel();
        familyInfo_Label = new JLabel("Family : ");
        familyInfoValue_Label = new JLabel();
        creationDateInfo_Label = new JLabel("Creation Date : ");
        creationDateInfoValue_Label = new JLabel();


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
        apply_Button.setVisible(false);
        apply_Button.addActionListener(e -> {
            apply_ButtonActionPreform();
            modify_Panel.setVisible(false);
            apply_Button.setVisible(false);
        });
        modify_Button = new JButton("Modify");
        modify_Button.addActionListener(e -> {
            modify_Panel.setVisible(true);
            apply_Button.setVisible(true);

        });



    }

    private void drawGui() {
        GroupLayout info_groupLayout = new GroupLayout(info_Panel);
        info_Panel.setLayout(info_groupLayout);
        info_groupLayout.setHorizontalGroup(info_groupLayout.createSequentialGroup()
                .addGap(15)
                .addGroup(info_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameInfo_Label)
                        .addComponent(familyInfo_Label)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(info_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameInfoValue_Label)
                        .addComponent(familyInfoValue_Label)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,15,100)
                .addGroup(info_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(usernameInfo_Label)
                        .addComponent(passwordInfo_Label)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(info_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(usernameInfoValue_Label)
                        .addComponent(passwordInfoValue_Label)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,15,100)
                .addGroup(info_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(creationDateInfo_Label)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(info_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(creationDateInfoValue_Label)
                )
                .addGap(15)
        );
        info_groupLayout.setVerticalGroup(info_groupLayout.createSequentialGroup()
                .addGap(10)
                .addGroup(info_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameInfo_Label)
                        .addComponent(nameInfoValue_Label)
                        .addComponent(usernameInfo_Label)
                        .addComponent(usernameInfoValue_Label)
                        .addComponent(creationDateInfo_Label)
                        .addComponent(creationDateInfoValue_Label)
                )
                .addGap(10)
                .addGroup(info_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(familyInfo_Label)
                        .addComponent(familyInfoValue_Label)
                        .addComponent(passwordInfo_Label)
                        .addComponent(passwordInfoValue_Label)
                )
                .addGap(10)
        );

        GroupLayout modify_groupLayout = new GroupLayout(modify_Panel);
        modify_Panel.setLayout(modify_groupLayout);

        modify_groupLayout.setHorizontalGroup(modify_groupLayout.createSequentialGroup()
                .addGap(15)
                .addGroup(modify_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(username_Label)
                        .addComponent(name_Label)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modify_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(username_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(name_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,15,200)
                .addGroup(modify_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(password_Label)
                        .addComponent(family_Label)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modify_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(password_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(family_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(15)
        );
        modify_groupLayout.setVerticalGroup(modify_groupLayout.createSequentialGroup()
                .addGap(15)
                .addGroup(modify_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(username_Label)
                        .addComponent(username_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(password_Label)
                        .addComponent(password_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(10)
                .addGroup(modify_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(name_Label)
                        .addComponent(name_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(family_Label)
                        .addComponent(family_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(15)

        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(info_Panel, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
                        .addComponent(modify_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                        .addComponent(modify_Panel, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
                        .addComponent(apply_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(10)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(30)
                .addComponent(info_Panel)
                .addGap(5)
                .addComponent(modify_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(15)
                .addComponent(modify_Panel)
                .addGap(5)
                .addComponent(apply_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(30)
        );


    }

    public void fillForm(User user, TopoForm topoform) {
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Can not fill form");
        } else {
            try {
                this.topoform = topoform;
                this.user = user;
                usernameInfoValue_Label.setText(user.getUsername());
                passwordInfoValue_Label.setText(user.getPassword());
                nameInfoValue_Label.setText(user.getName());
                familyInfoValue_Label.setText(user.getFamily());
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
                    , password.toString()
                    , user.getCreationDate());
            topoform.user = this.user;
            topoform.refresh_ButtonActionPreform();
            fillForm(user, topoform);


        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Please write correct");
        }


    }


    public static void main(String[] args) {
        JFrame a = new JFrame();
        ProfileManagementForm profileManagementForm = new ProfileManagementForm();
        a.add(profileManagementForm);
        a.pack();
        a.setVisible(true);
        profileManagementForm.setVisible(true);
    }

    private User user;
    private JPanel info_Panel;
    private JPanel modify_Panel;
    private TopoForm topoform;

    private JLabel username_Label;
    private JLabel password_Label;
    private JLabel name_Label;
    private JLabel family_Label;
    private JLabel creationDate_Label;

    private JLabel usernameInfo_Label;
    private JLabel passwordInfo_Label;
    private JLabel nameInfo_Label;
    private JLabel familyInfo_Label;
    private JLabel creationDateInfo_Label;

    private JLabel usernameInfoValue_Label;
    private JLabel passwordInfoValue_Label;
    private JLabel nameInfoValue_Label;
    private JLabel familyInfoValue_Label;
    private JLabel creationDateInfoValue_Label;

    private JTextField username_TextField;
    private JPasswordField password_TextField;
    private JTextField name_TextField;
    private JTextField family_TextField;

    private JButton apply_Button;
    private JButton modify_Button;

    private JXDatePicker creationDatePicker;

}
