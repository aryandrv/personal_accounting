package view;

import controller.AccountController;
import controller.UserController;
import java.time.LocalDateTime;
import model.entity.User;
import model.entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.List;
import java.util.regex.Pattern;

public class UserManagementForm extends JPanel {

    public UserManagementForm() {
        initComponent();
        drawGui();
    }

    private void initComponent() {
        panel1 = new JLabel();
        panel1.setBorder(BorderFactory.createTitledBorder("Create"));

        name_Label = new JLabel("Name");
        name_TextField = new JTextField();

        family_Label = new JLabel("family");
        family_TextField = new JTextField();

        userName_Label = new JLabel("Username");
        userName_TextField = new JTextField();

        password_Label = new JLabel("Password");
        password_TextField = new JTextField();

        Object[] headers = {"Row", "ID", "Name", "Family", "UserName", "CreationDate", "User"};
        tableModel = new DefaultTableModel(headers, 0);
        table = new JTable(tableModel);
        TableColumn column = table.getColumnModel().getColumn(6);
        column.setWidth(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
        doLayout();

        scrollPane = new JScrollPane(table);

        add_Button = new JButton("Add");
        add_Button.addActionListener(e -> {
            add_ButtonActionPreform();
        });

        delete_Button = new JButton("Delete");
        delete_Button.addActionListener(e -> {
            delete_ButtonActionPreform();
        });

        refresh_Button = new JButton("Refresh");
        refresh_Button.addActionListener(e -> {
            refresh_ButtonActionPreform();
        });

    }

    private void drawGui() {
        GroupLayout input_groupLayout = new GroupLayout(panel1);
        panel1.setLayout(input_groupLayout);
        input_groupLayout.setHorizontalGroup(input_groupLayout.createSequentialGroup()
                .addGap(10)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(name_Label)
                        .addComponent(userName_Label)
                )
                .addGap(5)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(name_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(userName_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(family_Label)
                        .addComponent(password_Label)
                )
                .addGap(5)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(family_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(password_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, 300)
                .addComponent(add_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
        );
        input_groupLayout.setVerticalGroup(input_groupLayout.createSequentialGroup()
                .addGap(20)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(name_Label)
                        .addComponent(name_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(family_Label)
                        .addComponent(family_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(10)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(userName_Label)
                        .addComponent(userName_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(password_Label)
                        .addComponent(password_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(add_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20)
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(delete_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refresh_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                        )
                )
                .addGap(15)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(30)
                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                        .addComponent(delete_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(refresh_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(30)
        );
    }

    public void fillForm() {
        try {
            List<User> usersList = UserController.getController().findAll();
            if (usersList != null) {
                for (User user : usersList) {
                    if (!user.getUsername().equals("Root")) {
                        tableModel.addRow(new Object[]{table.getRowCount() + 1, user.getId(),
                            user.getName(), user.getFamily(), user.getUsername(), user.getCreationDate().toString(), user});

                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "IS NULL");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void add_ButtonActionPreform() {
        try {
            if (!name_TextField.getText().trim().isEmpty() && !family_TextField.getText().trim().isEmpty()
                    && !userName_TextField.getText().trim().isEmpty() && !password_TextField.getText().trim().isEmpty()) {
                User checkUser = UserController.getController().findByUsername(userName_TextField.getText().trim());
                if (checkUser == null) {
                    User user = UserController.getController().save(0, name_TextField.getText().trim(),
                            family_TextField.getText().trim(), userName_TextField.getText().trim(),
                            password_TextField.getText().trim(), LocalDateTime.now());
                    if (user != null) {
                        tableModel.addRow(new Object[]{table.getRowCount() + 1, user.getId(),
                            user.getName(), user.getFamily(), user.getUsername(), user.getCreationDate().toString(), user});
                    } else {
                        JOptionPane.showMessageDialog(this, "IS NULL");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This username exists.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter the correct name");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    private void delete_ButtonActionPreform() {
        try {
            synchronized (tableModel) {
                int[] selectedRow = table.getSelectedRows();
                if (selectedRow.length == 1) {
                    User account = UserController.getController().remove((Integer) tableModel.getValueAt(selectedRow[0], 1));
                    if (account != null) {
                        tableModel.removeRow(selectedRow[0]);
                        if (table.getColumnModel().getColumn(0).getHeaderValue().toString().toLowerCase().trim().equals("row")) {
                            for (int i = 0; i < table.getRowCount(); i++) {
                                table.setValueAt((i + 1), i, 0);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select one row");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void refresh_ButtonActionPreform() {
        while (tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }
        fillForm();
    }

    private JLabel panel1;

    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private JLabel name_Label;
    private JLabel family_Label;
    private JLabel userName_Label;
    private JLabel password_Label;

    private JTextField name_TextField;
    private JTextField family_TextField;
    private JTextField userName_TextField;
    private JTextField password_TextField;

    private JButton add_Button;
    private JButton delete_Button;
    private JButton refresh_Button;
}
