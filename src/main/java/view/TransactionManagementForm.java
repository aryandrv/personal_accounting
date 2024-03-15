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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class TransactionManagementForm extends JPanel {

    public TransactionManagementForm() {
        initComponent();
        drawGui();
    }

    private void initComponent() {
        panel1 = new JLabel();
        panel1.setBorder(BorderFactory.createTitledBorder("Filter by"));

        bankAccount_CheckBox = new JCheckBox("Account name");
        bankAccount_CheckBox.addActionListener(e -> {
            bankAccount_Combobox.setEnabled(bankAccount_CheckBox.isSelected());
            sorter.sort();
        });
        bankAccount_Combobox = new JComboBox();
        bankAccount_Combobox.setEnabled(false);
        bankAccount_Combobox.addActionListener(e -> {
            sorter.sort();
        });

        titles_CheckBox = new JCheckBox("Title");
        titles_CheckBox.addActionListener(e -> {
            titles_Combobox.setEnabled(titles_CheckBox.isSelected());
            sorter.sort();
        });
        titles_Combobox = new JComboBox();
        titles_Combobox.setEnabled(false);
        titles_Combobox.addActionListener(e -> {
            sorter.sort();
        });

        amountFrom_CheckBox = new JCheckBox("Amount from");
        amountFrom_CheckBox.addActionListener(e -> {
            amountFrom_TextField.setEnabled(amountFrom_CheckBox.isSelected());
            sorter.sort();
        });
        amountFrom_TextField = new JTextField();
        amountFrom_TextField.setEnabled(false);

        amountFrom_TextField.addKeyListener(new KeyAdapterImpl());


        amountTo_CheckBox = new JCheckBox("Amount to");
        amountTo_CheckBox.addActionListener(e -> {
            amountTo_TextField.setEnabled(amountTo_CheckBox.isSelected());
            sorter.sort();
        });
        amountTo_TextField = new JTextField();
        amountTo_TextField.setEnabled(false);

        amountTo_TextField.addKeyListener(new KeyAdapterImpl());

        description_CheckBox = new JCheckBox("Description");
        description_CheckBox.addActionListener(e -> {
            description_TextField.setEnabled(description_CheckBox.isSelected());
            sorter.sort();
        });
        description_TextField = new JTextField();
        description_TextField.setEnabled(false);
        description_TextField.addKeyListener(new KeyAdapterImpl());

        type_CheckBox = new JCheckBox("Type");
        type_CheckBox.addActionListener(e -> {
            type_Combobox.setEnabled(type_CheckBox.isSelected());
            sorter.sort();
        });
        type_Combobox = new JComboBox(TypeEnum.values());
        type_Combobox.setEnabled(false);

        type_Combobox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                sorter.sort();
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                sorter.sort();
                fillTitleComboBox();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                sorter.sort();
            }
        });

        date_CheckBox = new JCheckBox("Date");
        datePicker = new JXDatePicker();

        Object[] headers = {"Row", "ID", "User", "Bank", "Type", "Titles", "Amount", "Transaction date", "Description" , "DTO"};

        tableModel = new DefaultTableModel(headers, 0);
        table = new JTable(tableModel);
        TableColumn row_Column = table.getColumnModel().getColumn(row_columnNo);
        row_Column.setPreferredWidth(30);
        TableColumn id_Column = table.getColumnModel().getColumn(id_columnNo);
        id_Column.setPreferredWidth(30);

        TableColumn dto_column = table.getColumnModel().getColumn(dto_columnNo);
        dto_column.setWidth(0);
        dto_column.setMinWidth(0);
        dto_column.setMaxWidth(0);
        dto_column.setPreferredWidth(0);
        doLayout();

        sorter = new TableRowSorter<>(tableModel);
        sorter.setRowFilter(new CustomRowFilter());
        table.setRowSorter(sorter);

//        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(table);

        add_Button = new JButton("Add");
        add_Button.addActionListener(e -> {
            add_ButtonActionPreform();
        });

        modify_Button = new JButton("Modify");
        modify_Button.addActionListener(e -> {
            modify_ButtonActionPreform();
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
                        .addComponent(bankAccount_CheckBox)
                        .addComponent(bankAccount_Combobox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(type_CheckBox)
                        .addComponent(type_Combobox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(amountFrom_CheckBox)
                        .addComponent(amountFrom_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(amountTo_CheckBox)
                        .addComponent(amountTo_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(titles_CheckBox)
                        .addComponent(titles_Combobox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(description_CheckBox)
                        .addComponent(description_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(date_CheckBox)
                        .addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )

                .addGap(10)

        );
        input_groupLayout.setVerticalGroup(input_groupLayout.createSequentialGroup()
                .addGap(20)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(bankAccount_CheckBox)
                        .addComponent(amountFrom_CheckBox)
                        .addComponent(titles_CheckBox)
                        .addComponent(date_CheckBox)
                )
                .addGap(5)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(bankAccount_Combobox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(amountFrom_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(titles_Combobox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(5)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(type_CheckBox)
                        .addComponent(amountTo_CheckBox)
                        .addComponent(description_CheckBox)
                )
                .addGap(5)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(type_Combobox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(amountTo_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(description_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )

                .addGap(20)
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 780, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 780, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(add_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modify_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(delete_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refresh_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                        )
                )
                .addGap(15)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(30)
                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                        .addComponent(add_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(modify_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(delete_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(refresh_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(30)


        );

    }

    public void fillForm(User user) {
        this.user = user;
        new Thread(() -> {
            try {
                List<Transaction> transactionList = TransactionController.getController().findByUserId(this.user.getId());
                synchronized (tableModel){
                    for (Transaction transaction : transactionList) {
                        tableModel.addRow(toArray(transaction));
                    }
                }
                bankAccount_Combobox.removeAllItems();
                List<Account> listAccount = listAccount = AccountController.getController().findByUserId(user.getId());
                for (Account account : listAccount) {
                    bankAccount_Combobox.addItem(account.getName());
                }
                fillTitleComboBox();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Can not fill form");

            }

        }).start();

    }

    private void fillTitleComboBox() {
        List<Titles> listTitles = TitlesController.getController().findByType(type_Combobox.getSelectedItem().toString());
        titles_Combobox.removeAllItems();
        for (Titles title : listTitles) {
            titles_Combobox.addItem(title.getName());
        }

    }

    private Object[] toArray(Transaction transaction){
        Object[] array = new Object[table.getColumnCount()];
        array[row_columnNo] = table.getRowCount()+1;
        array[id_columnNo] = transaction.getId();
        array[user_columnNo] = transaction.getUser().getName();
        array[bank_columnNo] = transaction.getAccount().getName();
        array[type_columnNo] = transaction.getType().toString();
        array[titles_columnNo] = transaction.getTitles().getName();
        array[amount_columnNo] = transaction.getAmount();
        array[transactionDate_columnNo] = transaction.getTransactionDate();
        array[description_columnNo] = transaction.getDescription();
        array[dto_columnNo] = transaction;

        return array;
    }

    private void add_ButtonActionPreform() {
        new Thread(() -> {
            try {
                TransactionSettingForm transactionSettingForm = new TransactionSettingForm();
                transactionSettingForm.setVisible(true);
                transactionSettingForm.fillForm(user, DomainEnum.ADD);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


    }

    private void modify_ButtonActionPreform() {
        new Thread(() -> {
            try{
                if(table.getSelectedRows().length == 1 ){
                Transaction transaction = (Transaction) tableModel.getValueAt(table.getSelectedRow(),dto_columnNo);
                TransactionSettingForm transactionSettingForm = new TransactionSettingForm();
                transactionSettingForm.setVisible(true);
                transactionSettingForm.fillForm(user, DomainEnum.MODIFY, transaction);
                }else{
                    JOptionPane.showMessageDialog(this,"Please select one row");
                }
            }catch (Exception e){
                e.printStackTrace();

            }


        }).start();

    }

    public void refresh_ButtonActionPreform() {
        while (tableModel.getRowCount() != 0) {
            tableModel.removeRow(0);
        }
        fillForm(user);
    }

    private void delete_ButtonActionPreform() {
        try {
            synchronized (tableModel) {
                int[] selectedRow = table.getSelectedRows();
                if (selectedRow.length == 1) {
                    Transaction transaction = TransactionController.getController().remove((Integer) tableModel.getValueAt(selectedRow[0], id_columnNo));
                    if (transaction != null) {
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

    private void filter_ButtonActionPreform() {

    }

    public static void main(String[] args) {
        JFrame a = new JFrame();
        TransactionManagementForm transactionManagementForm = new TransactionManagementForm();
        a.add(transactionManagementForm);
        a.pack();
        a.setVisible(true);
        transactionManagementForm.setVisible(true);
    }


    private User user;

    private JLabel panel1;

    private JTable table;
    private TableRowSorter sorter;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private JCheckBox bankAccount_CheckBox;
    private JCheckBox type_CheckBox;
    private JCheckBox amountFrom_CheckBox;
    private JCheckBox amountTo_CheckBox;

    private JCheckBox date_CheckBox;
    private JCheckBox titles_CheckBox;
    private JCheckBox description_CheckBox;


    private JComboBox bankAccount_Combobox;
    private JComboBox type_Combobox;
    private JXDatePicker datePicker;
    private JTextField amountFrom_TextField;
    private JTextField amountTo_TextField;
    private JComboBox titles_Combobox;
    private JTextField description_TextField;

    private int colNo = 0;


    private final int row_columnNo = colNo++;
    private final int id_columnNo = colNo++;
    private final int user_columnNo = colNo++;
    private final int bank_columnNo = colNo++;
    private final int type_columnNo = colNo++;
    private final int titles_columnNo = colNo++;
    private final int amount_columnNo = colNo++;
    private final int transactionDate_columnNo = colNo++;
    private final int description_columnNo = colNo++;
    private final int dto_columnNo = colNo++;

    private JButton add_Button;
    private JButton modify_Button;
    private JButton delete_Button;
    private JButton refresh_Button;

    private class CustomRowFilter extends RowFilter<Object, Object> {

        @Override
        public boolean include(RowFilter.Entry<? extends Object, ? extends Object> entry) {
            try {

                if (bankAccount_CheckBox.isSelected() && bankAccount_Combobox.getSelectedItem() != null && !entry.getStringValue(bank_columnNo).equals(bankAccount_Combobox.getSelectedItem().toString())) {
                    return false;
                }
                if (type_CheckBox.isSelected() && type_Combobox.getSelectedItem() != null && !entry.getStringValue(type_columnNo).equals(type_Combobox.getSelectedItem().toString())) {
                    return false;
                }
                if (amountFrom_CheckBox.isSelected() && amountFrom_TextField != null && !amountFrom_TextField.getText().isEmpty() && !(Double.valueOf(entry.getStringValue(amount_columnNo)) >= Double.valueOf(amountFrom_TextField.getText().trim()))) {
                    return false;
                }
                if (amountTo_CheckBox.isSelected() && amountTo_TextField != null && !amountTo_TextField.getText().isEmpty() && !(Double.valueOf(entry.getStringValue(amount_columnNo)) <= Double.valueOf(amountTo_TextField.getText().trim()))) {
                    return false;
                }

                if (titles_CheckBox.isSelected() && titles_Combobox.getSelectedItem() != null && !entry.getStringValue(titles_columnNo).equals(titles_Combobox.getSelectedItem().toString())) {
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return false;

            }
            return true;
        }
    }

    private class KeyAdapterImpl extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            sorter.sort();
        }
    }




}
