package view;

import enums.TypeEnum;
import model.entity.User;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TransactionManagementForm extends JPanel {

    public TransactionManagementForm() {
        initComponent();
        drawGui();
    }

    private void initComponent() {
        panel1 = new JLabel();
        panel1.setBorder(BorderFactory.createTitledBorder("Filter by"));

        bankAccount_CheckBox = new JCheckBox("Account name");
        bankAccount_Combobox = new JComboBox();

        titles_CheckBox = new JCheckBox("Title");
        titles_Combobox = new JComboBox();

        amountFrom_CheckBox = new JCheckBox("Amount from");
        amountFrom_TextField = new JTextField();

        amountTo_CheckBox = new JCheckBox("Amount to");
        amountTo_TextField = new JTextField();

        description_CheckBox = new JCheckBox("Description");
        description_TextField = new JTextField();

        type_CheckBox = new JCheckBox("Type");
        type_Combobox = new JComboBox(TypeEnum.values());

        date_CheckBox = new JCheckBox("Date");
        datePicker = new JXDatePicker();

        Object[] headers = {"Row", "ID", "User", "Bank", "Type", "Titles", "Amount", "Transaction date", "Description"};

        tableModel = new DefaultTableModel(headers, 0);
        table = new JTable(tableModel);
        TableColumn row_Column = table.getColumnModel().getColumn(row_columnNo);
        row_Column.setPreferredWidth(30);
        TableColumn id_Column = table.getColumnModel().getColumn(id_columnNo);
        id_Column.setPreferredWidth(30);

        sorter = new TableRowSorter<>(tableModel);
        sorter.setRowFilter(new CustomRowFilter());
        table.setRowSorter(sorter);
//        sorter.sort


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

    }

    private void add_ButtonActionPreform() {
        new Thread(() -> {
            try {
                TransactionSettingForm transactionSettingForm = new TransactionSettingForm();
                transactionSettingForm.setVisible(true);
                transactionSettingForm.fillForm(user);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


    }

    private void modify_ButtonActionPreform() {

    }

    private void refresh_ButtonActionPreform() {

    }

    private void delete_ButtonActionPreform() {

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
    private final int transaction_columnNo = colNo++;
    private final int description_columnNo = colNo++;

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
                if (amountFrom_CheckBox.isSelected() && amountFrom_TextField != null && !(Double.valueOf(entry.getStringValue(amount_columnNo)) >= Double.valueOf(amountFrom_TextField.getText().trim()))) {
                    return false;
                }
                if (amountTo_CheckBox.isSelected() && amountTo_TextField != null && !(Double.valueOf(entry.getStringValue(amount_columnNo)) <= Double.valueOf(amountTo_TextField.getText().trim()))) {
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

    ;


}
