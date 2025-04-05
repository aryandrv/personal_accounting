package view;

import controller.UserController;
import model.entity.User;
import utils.ImageIconUtil;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class TopoForm extends JFrame {

    public TopoForm(User user) {
        this.user = user;
        initComponent();
        drawGui();
    }

    private void initComponent() {
        setTitle("personal_accounting");

        left_Panel = new JPanel();
        left_Panel.setBorder(BorderFactory.createTitledBorder("Welcome"));
        right_Panel = new JPanel();
        right_Panel.setBorder(BorderFactory.createTitledBorder(titles));

        profile_Icon = new JLabel(ImageIconUtil.USER);

        username = new JLabel("Welcome : " + user.getUsername());
        timeToLogin = new JLabel("Time to login : " + LocalDateTime.now().toLocalDate());

        home_button = new JButton("Home");
        transaction_button = new JButton("Transaction");
        transaction_button.addActionListener(e -> {
            if (!Objects.equals(selectedButton, ButtonEnum.TRANSACTION)) {
                selectedButton = ButtonEnum.TRANSACTION;
                changeButton(selectedButton);
            }
        });
        bankAccount_button = new JButton("Bank account");
        bankAccount_button.addActionListener(e -> {
            if (!Objects.equals(selectedButton, ButtonEnum.BANKACCOUNT)) {
                selectedButton = ButtonEnum.BANKACCOUNT;
                changeButton(selectedButton);
            }
        });

        profile_button = new JButton("Profile");
        profile_button.addActionListener(e -> {
            if (!Objects.equals(selectedButton, ButtonEnum.PROFILE)) {
                selectedButton = ButtonEnum.PROFILE;
                changeButton(selectedButton);
            }
        });

        titles_button = new JButton("Titles");
        titles_button.addActionListener(e -> {
            if (!Objects.equals(selectedButton, ButtonEnum.TITLES)) {
                selectedButton = ButtonEnum.TITLES;
                changeButton(selectedButton);
            }
        });

        userForm_button = new JButton("User");
        userForm_button.addActionListener(e -> {
            if (!Objects.equals(selectedButton, ButtonEnum.USER)) {
                selectedButton = ButtonEnum.USER;
                changeButton(selectedButton);
            }
        });
        userForm_button.setVisible(true);

        if(!user.getUsername().equals("Root")){
        userForm_button.setVisible(false);
        }

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
                        .addComponent(profile_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(transaction_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bankAccount_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(titles_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(userForm_button, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                //                        .addComponent(refresh_button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
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
                //                .addGap(10)
                .addComponent(profile_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(transaction_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(bankAccount_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(titles_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                                .addComponent(userForm_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                //                .addGap(20)
                //                .addComponent(refresh_button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
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

    private void changeButton(ButtonEnum buttonEnum) {
        right_Panel.removeAll();
        switch (buttonEnum) {
            case USER:
                right_Panel.setBorder(BorderFactory.createTitledBorder("User Management Form"));

                if (userManagementForm == null) {
                    userManagementForm = new UserManagementForm();
                    userManagementForm.fillForm();
                }
                userManagementForm.setVisible(true);
                right_Panel.add(userManagementForm);
                this.repaint();
                this.validate();
                break;
            case TITLES:
                right_Panel.setBorder(BorderFactory.createTitledBorder("Titles Management Form"));

                if (titlesManagementForm == null) {
                    titlesManagementForm = new TitlesManagementForm();
                    titlesManagementForm.fillForm();
                }
                titlesManagementForm.setVisible(true);
                right_Panel.add(titlesManagementForm);
                this.repaint();
                this.validate();
                break;
            case PROFILE:
                right_Panel.setBorder(BorderFactory.createTitledBorder("Profile Management Form"));
                if (profileManagementForm == null) {
                    profileManagementForm = new ProfileManagementForm();
                    profileManagementForm.fillForm(user, this);
                }
                profileManagementForm.setVisible(true);
                right_Panel.add(profileManagementForm);
                this.repaint();
                this.validate();
                break;
            case BANKACCOUNT:
                right_Panel.setBorder(BorderFactory.createTitledBorder("Account Management Form"));
                if (bankAccountManagementForm == null) {
                    bankAccountManagementForm = new BankAccountManagementForm();
                    bankAccountManagementForm.fillForm(user);
                } else {
                    bankAccountManagementForm.refresh_ButtonActionPreform();
                }
                bankAccountManagementForm.setVisible(true);
                right_Panel.add(bankAccountManagementForm);
                this.repaint();
                this.validate();
                break;
            case TRANSACTION:
                right_Panel.setName("Transaction");
                right_Panel.setBorder(BorderFactory.createTitledBorder("Transaction Management Form"));

                if (transactionManagementForm == null) {
                    transactionManagementForm = new TransactionManagementForm();
                    transactionManagementForm.fillForm(user);
                } else {
                    transactionManagementForm.refresh_ButtonActionPreform();
                }
                transactionManagementForm.setVisible(true);
                right_Panel.add(transactionManagementForm);
                this.repaint();
                this.validate();
                break;

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

    private ProfileManagementForm profileManagementForm = null;
    private TitlesManagementForm titlesManagementForm = null;
    private BankAccountManagementForm bankAccountManagementForm = null;
    private TransactionManagementForm transactionManagementForm = null;
    private UserManagementForm userManagementForm = null;

    private ButtonEnum selectedButton = null;

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
    private JButton userForm_button;
    private JButton refresh_button;

    private String titles;

    public User user;

    private enum ButtonEnum {
        PROFILE,
        TITLES,
        USER,
        TRANSACTION,
        BANKACCOUNT;
    }

}
