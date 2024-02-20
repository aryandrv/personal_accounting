package view;

import controller.UserController;
import model.entity.User;
import utils.ImageIconUtil;

import javax.swing.*;
import java.time.LocalDateTime;

public class TopoForm extends JFrame {

    public TopoForm(User user) {
        this.user = user;
        initComponent();
        drawGui();
    }

    private void initComponent() {
        titles = "salam";

        left_Panel = new JPanel();
        left_Panel.setBorder(BorderFactory.createTitledBorder("Dashbord"));
        right_Panel = new JPanel();
        right_Panel.setBorder(BorderFactory.createTitledBorder(titles));


        profile_Icon = new JLabel(ImageIconUtil.USER);

        username = new JLabel("Welcome : " + user.getUsername());
        timeToLogin = new JLabel("Time to login : " + LocalDateTime.now().toLocalDate());

        profile_button = new JButton("Profile");
        home_button = new JButton("Home");
        transaction_button = new JButton("Transaction");
        bankAccount_button = new JButton("Bank account");
        titles_button = new JButton("Titles");

        profile_button.addActionListener(e -> {
            if (profileMamagementForm == null) {
                titles = "Profile";
                profileMamagementForm = new ProfileMamagementForm();
                profileMamagementForm.fillForm(user, this);
                profileMamagementForm.setVisible(true);
                right_Panel.add(profileMamagementForm);
                this.repaint();
                this.validate();
            }
        });

        refresh_button = new JButton("Refresh");
        refresh_button.addActionListener(e -> {
            refresh_ButtonActionPreform();

        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    private void drawGui() {
        GroupLayout left_groupLayout = new GroupLayout(left_Panel);
        left_Panel.setLayout(left_groupLayout);
        left_groupLayout.setHorizontalGroup(left_groupLayout.createSequentialGroup()
                .addGap(20)
                .addGroup(left_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(profile_Icon, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                        .addComponent(username)
                        .addComponent(timeToLogin)
                        .addComponent(home_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(profile_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(transaction_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bankAccount_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(titles_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(refresh_button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20)
        );
        left_groupLayout.setVerticalGroup(left_groupLayout.createSequentialGroup()
                .addComponent(profile_Icon, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                .addGap(5)
                .addComponent(username)
                .addGap(5)
                .addComponent(timeToLogin)
                .addGap(10)
                .addComponent(home_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(profile_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addComponent(left_Panel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(right_Panel, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(left_Panel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                        .addComponent(right_Panel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(50)
        );
        pack();
    }

    public void refresh_ButtonActionPreform() {
        try {
            user = UserController.getController().findById(user.getId());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");

        }
    }

    public static void main(String[] args) {
        try {
            TopoForm topoForm = new TopoForm(UserController.getController().findById(1));
            topoForm.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ProfileMamagementForm profileMamagementForm = null;

    private JPanel left_Panel;
    private JPanel right_Panel;
    private JLabel profile_Icon;
    private JLabel username;
    private JLabel timeToLogin;
    private JButton profile_button;
    private JButton home_button;
    private JButton transaction_button;
    private JButton titles_button;
    private JButton bankAccount_button;
    private JButton refresh_button;

    private String titles;

    public User user;


}
