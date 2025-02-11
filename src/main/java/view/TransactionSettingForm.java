package view;

import controller.AccountController;
import controller.TitlesController;
import controller.TransactionController;
import enums.DomainEnum;
import enums.TypeEnum;
import model.entity.Account;
import model.entity.Titles;
import model.entity.Transaction;
import model.entity.User;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public class TransactionSettingForm extends JFrame {
    public TransactionSettingForm() {
        initComponent();
        drawGui();
    }

    private void initComponent() {
        user_Label = new JLabel("Username");
        userValue_Label = new JLabel();

        account_Label = new JLabel("Account");
        account_ComboBox = new JComboBox();

        amount_Label = new JLabel("Amount");
        amount_TextField = new JTextField();
        ((AbstractDocument) amount_TextField.getDocument()).setDocumentFilter(new DocumentFilter() {
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
                            if (amount_TextField.getText().contains(".")) {
                                getToolkit().beep();
                                return;
                            }
                        }
                    }
                    super.replace(fb, offset, length, text, attrs);
                    return;
                } else if (text.contains(".")) {
                    int i = 0;
                    for (char c : text.toCharArray()) {
                        if (!(regEx.matcher(String.valueOf(c)).matches() || c == '.')) {
                            getToolkit().beep();
                            return;
                        }
                        if (c == '.') {
                            if (i == 0) {
                                getToolkit().beep();
                                return;
                            } else {
                                if (amount_TextField.getText().contains(".")) {
                                    getToolkit().beep();
                                    return;
                                }
                            }
                        }
                        super.replace(fb, i++, 0, String.valueOf(c), attrs);
                    }
                    return;
                }
                getToolkit().beep();
            }
        });

        type_Label = new JLabel("Type");
        type_ComboBox = new JComboBox(TypeEnum.values());
        type_ComboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                fillTitleComboBox();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        title_Label = new JLabel("Title");
        titles_ComboBox = new JComboBox();

        transactionDate_Label = new JLabel("Transaction Date");
        transactionDatePicker = new JXDatePicker();

        description_Label = new JLabel("Description");
        description_TextArea = new JTextArea();


        add_Button = new JButton("Add");
        add_Button.addActionListener(e -> {
            add_ButtonActionPreform();
        });
        add_Button.setVisible(false);

        apply_Button = new JButton("Apply");
        apply_Button.addActionListener(e -> {
            apply_ButtonActionPreform();
        });
        apply_Button.setVisible(false);

        close_Button = new JButton("Close");
        close_Button.addActionListener(e -> {
            dispose();
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    private void drawGui() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(user_Label)
                                        .addComponent(type_Label)
                                )
                                .addGap(5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(userValue_Label)
                                        .addComponent(type_ComboBox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(account_Label)
                                        .addComponent(title_Label)
                                )
                                .addGap(5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(account_ComboBox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(titles_ComboBox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(10)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(amount_Label)
                                        .addComponent(transactionDate_Label)
                                )
                                .addGap(5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(amount_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(transactionDatePicker, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                )
                        )
                        .addComponent(description_Label)
                        .addComponent(description_TextArea, GroupLayout.PREFERRED_SIZE, 777, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(50, 600)
                                .addComponent(apply_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(close_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)

                        )
                )
                .addGap(20)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(user_Label)
                        .addComponent(userValue_Label)
                        .addComponent(account_Label)
                        .addComponent(account_ComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(amount_Label)
                        .addComponent(amount_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(type_Label)
                        .addComponent(type_ComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(title_Label)
                        .addComponent(titles_ComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(transactionDate_Label)
                        .addComponent(transactionDatePicker, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(15)
                .addComponent(description_Label)
                .addComponent(description_TextArea, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(apply_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(add_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(close_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(30)
        );
        pack();

    }

    public void fillForm(User user, DomainEnum domain, TransactionManagementForm transactionManagementForm) {
        fillForm(user, domain, null, transactionManagementForm);
    }

    public void fillForm(User user, DomainEnum domain, Transaction transaction,  TransactionManagementForm transactionManagementForm) {
        if (user != null) {
            this.user = user;
            this.transactionManagementForm = transactionManagementForm;

            switch (domain) {
                case ADD:
                    setTitle("Transaction Creation");
                    add_Button.setVisible(true);
                    try {
                        userValue_Label.setText(user.getUsername());
                        listAccount = AccountController.getController().findByUserId(user.getId());
                        for (Account account : listAccount) {
                            account_ComboBox.addItem(account.getName());
                        }
                        fillTitleComboBox();
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showConfirmDialog(this, "Can not fillForm");
                    }
                    break;
                case MODIFY:
                    setTitle("Transaction modify");
                    apply_Button.setVisible(true);
                    if (transaction != null) {
                        this.transaction = transaction;
                        try {
                            userValue_Label.setText(user.getUsername());
                            listAccount = AccountController.getController().findByUserId(user.getId());
                            for (Account account : listAccount) {
                                account_ComboBox.addItem(account.getName());
                            }
                            account_ComboBox.setSelectedItem(transaction.getAccount().getName());
                            type_ComboBox.setSelectedItem(transaction.getType());
                            fillTitleComboBox();
                            titles_ComboBox.setSelectedItem(transaction.getTitles().getName());
                            amount_TextField.setText(String.valueOf(transaction.getAmount() >= 0 ? transaction.getAmount() : transaction.getAmount() * -1));
                            description_TextArea.setText(transaction.getDescription());
                            transactionDatePicker.setDate(Date.valueOf(transaction.getTransactionDate().toLocalDate()));
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showConfirmDialog(this, "Can not fillForm");
                        }

                    }

                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Can not open form");
            dispose();
        }

    }

    private void fillTitleComboBox() {
        List<Titles> listTitles = TitlesController.getController().findByType(type_ComboBox.getSelectedItem().toString());
        titles_ComboBox.removeAllItems();
        for (Titles title : listTitles) {
            titles_ComboBox.addItem(title.getName());
        }

    }

    private void apply_ButtonActionPreform() {
        try {
            if (transactionDatePicker.getDate().toString().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "please put transaction date ");
            } else if (amount_TextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "please put amount");
            } else {
                TransactionController.getController().edit(transaction.getId(), user, getAccount(),
                        TypeEnum.toEnum(type_ComboBox.getSelectedItem().toString()).equals(TypeEnum.INCOME) ?
                                Double.valueOf(amount_TextField.getText().trim()) : -1 * Double.valueOf(amount_TextField.getText().trim()),
                        TitlesController.getController().findByName(titles_ComboBox.getSelectedItem().toString()),
                        new Timestamp(transactionDatePicker.getDate().getTime()).toLocalDateTime(),
                        description_TextArea.getText(),
                        TypeEnum.toEnum(type_ComboBox.getSelectedItem().toString()));
                JOptionPane.showMessageDialog(this, "Modify success!!");
                transactionManagementForm.refresh_ButtonActionPreform();
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "have some error to modify");
        }


    }

    private void add_ButtonActionPreform() {
        try {
            if (transactionDatePicker.getDate().toString().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "please put transaction date ");
            } else if (amount_TextField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "please put amount");
            } else {
                TransactionController.getController().save(0, user, getAccount(),
                        TypeEnum.toEnum(type_ComboBox.getSelectedItem().toString()).equals(TypeEnum.INCOME) ?
                                Double.valueOf(amount_TextField.getText().trim()) : -1 * Double.valueOf(amount_TextField.getText().trim()),
                        TitlesController.getController().findByName(titles_ComboBox.getSelectedItem().toString()),
                        new Timestamp(transactionDatePicker.getDate().getTime()).toLocalDateTime(),
                        description_TextArea.getText(),
                        TypeEnum.toEnum(type_ComboBox.getSelectedItem().toString()));
                JOptionPane.showMessageDialog(this, "Save success!!");
                transactionManagementForm.refresh_ButtonActionPreform();
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "have some error");
        }


    }

    private Account getAccount() {
        for (Account account : listAccount) {
            if (account_ComboBox.getSelectedItem().toString().equals(account.getName())) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TransactionSettingForm transactionSettingForm = new TransactionSettingForm();
        transactionSettingForm.setVisible(true);
    }

    private JLabel user_Label;
    private JLabel account_Label;
    private JLabel amount_Label;
    private JLabel title_Label;
    private JLabel type_Label;
    private JLabel transactionDate_Label;
    private JLabel description_Label;
    private JLabel userValue_Label;

    private JComboBox account_ComboBox;
    private JComboBox titles_ComboBox;
    private JComboBox type_ComboBox;
    private JXDatePicker transactionDatePicker;
    private JTextArea description_TextArea;
    private JTextField amount_TextField;

    private JButton add_Button;
    private JButton apply_Button;
    private JButton close_Button;


    private User user;
    private Transaction transaction;
    private TransactionManagementForm transactionManagementForm;
    private List<Account> listAccount;

}
