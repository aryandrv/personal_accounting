package view;

import controller.AccountController;
import controller.TitlesController;
import enums.TypeEnum;
import model.entity.Account;
import model.entity.User;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
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
        amount_TextField.setText("0.0");
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
                }
                getToolkit().beep();
            }
        });

        type_Label = new JLabel("Type");
        type_ComboBox = new JComboBox(TypeEnum.values());

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
                        .addComponent(add_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(close_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(30)
        );
        pack();

    }

    public void fillForm(User user) {
        try{
            this.user = user;
            if(user != null){
                userValue_Label.setText(user.getUsername());
                List<Account> listAccount = AccountController.getController().findByUserId(user.getId());
                for (Account account : listAccount) {
                    account_ComboBox.addItem(account.getName());
                }
//                TitlesController.getController().find
//                type_ComboBox.getSelectedItem();
            }

        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showConfirmDialog(this,"Can not fillForm");

        }


    }

    private void add_ButtonActionPreform() {

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
    private JButton close_Button;


    private User user;
}
