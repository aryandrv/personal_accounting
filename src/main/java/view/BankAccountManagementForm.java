package view;

import controller.AccountController;
import controller.TitlesController;
import enums.TypeEnum;
import model.entity.Account;
import model.entity.Titles;
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

public class BankAccountManagementForm extends JPanel {

    public BankAccountManagementForm() {
        initComponent();
        drawGui();
    }

    private void initComponent() {
        panel1 = new JLabel();
        panel1.setBorder(BorderFactory.createTitledBorder("Input"));

        bankName_Label = new JLabel("Name");
        bankName_TextField = new JTextField();

        balance_Label = new JLabel("Balance");
        balance_TextField = new JTextField();
        ((AbstractDocument) balance_TextField.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws
                    BadLocationException {
                char input = text.toCharArray()[0];
                if (regEx.matcher(text).matches() || input == '.') {
                    if (input == '.') {
                        if (offset == 0) {
                            getToolkit().beep();
                            return;
                        } else {
                            if (balance_TextField.getText().contains(".")) {
                                getToolkit().beep();
                                return;
                            }
                        }
                    }
                    super.replace(fb, offset, length, text, attrs);
                    return;
                }
                getToolkit().beep();
            }
        });

        Object[] headers = {"Row", "ID", "Bank name", "Balance", "Account"};
        tableModel = new DefaultTableModel(headers, 0);
        table = new JTable(tableModel);
        TableColumn column =table.getColumnModel().getColumn(4);
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
                .addComponent(bankName_Label)
                .addGap(5)
                .addComponent(bankName_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(balance_Label)
                .addGap(5)
                .addComponent(balance_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, 300)
                .addComponent(add_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                .addGap(10)

        );
        input_groupLayout.setVerticalGroup(input_groupLayout.createSequentialGroup()
                .addGap(20)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(bankName_Label)
                        .addComponent(bankName_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(balance_Label)
                        .addComponent(balance_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                        .addComponent(delete_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(refresh_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(30)
        );
    }

    public void fillForm(User user) {
        try {
        this.user = user;
            List<Account> accountsList = AccountController.getController().findAll();
            if (accountsList != null) {
                for (Account account : accountsList) {
                    tableModel.addRow(new Object[]{table.getRowCount() + 1, account.getId(),
                            account.getName(), String.valueOf(account.getBalance()),user});
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
            if (!bankName_TextField.getText().trim().isEmpty() && !balance_TextField.getText().trim().isEmpty()) {
                Account account = AccountController.getController().save(0, bankName_TextField.getText().trim(),
                        Double.valueOf(balance_TextField.getText().trim()),user);
                if (account != null) {
                    tableModel.addRow(new Object[]{table.getRowCount() + 1, account.getId(),
                            account.getName(), String.valueOf(account.getBalance()),user});
                } else {
                    JOptionPane.showMessageDialog(this, "IS NULL");

                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter the correct name");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void refresh_ButtonActionPreform() {
        while (tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }
        fillForm(user);
    }

    private void delete_ButtonActionPreform() {

    }
    public static void main(String[] args) {
        JFrame a = new JFrame();
        BankAccountManagementForm bankAccountManagementForm = new BankAccountManagementForm();
        a.add(bankAccountManagementForm);
        a.pack();
        a.setVisible(true);
        bankAccountManagementForm.setVisible(true);
    }


    private User user;
    private JLabel panel1;

    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private JLabel bankName_Label;
    private JLabel balance_Label;

    private JTextField bankName_TextField;
    private JTextField balance_TextField;

    private JButton add_Button;
    private JButton delete_Button;
    private JButton refresh_Button;
}
