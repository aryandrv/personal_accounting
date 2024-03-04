package view;

import enums.TypeEnum;
import lombok.Synchronized;
import model.entity.User;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class TransactionManagementForm extends JPanel {

    public TransactionManagementForm() {
        initComponent();
        drawGui();
    }

    private void initComponent() {
        panel1 = new JLabel();
        panel1.setBorder(BorderFactory.createTitledBorder("Filter by"));

        bankAccount_Label = new JLabel("Name");
        bankAccount_Combobox = new JComboBox();

        titles_Label = new JLabel("Title");
        titles_Combobox = new JComboBox();

        amountFrom_Label = new JLabel("Amount from");
        amountFrom_TextField = new JTextField();

        amountTo_Label = new JLabel("Amount to");
        amountTo_TextField = new JTextField();

        description_Label = new JLabel("Description");
        description_TextField = new JTextField();

        type_Label = new JLabel("Type");
        type_Combobox = new JComboBox(TypeEnum.values());

        date_Label = new JLabel("Date");
        datePicker = new JXDatePicker();

        Object[] headers = {"Row", "ID", "User", "Bank", "Type", "Titles", "Amount", "Transaction date", "Description"};

        tableModel = new DefaultTableModel(headers, 0);
        table = new JTable(tableModel);
        TableColumn rowColumn =table.getColumnModel().getColumn(0);
        rowColumn.setPreferredWidth(30);
        TableColumn id_olumn =table.getColumnModel().getColumn(1);
        id_olumn.setPreferredWidth(30);
        scrollPane = new JScrollPane(table);

        add_Button = new JButton("Add");
        add_Button.addActionListener(e -> {
            add_ButtonActionPreform();
        });

        filter_Button = new JButton("Filter");
        filter_Button.addActionListener(e -> {
            modify_ButtonActionPreform();
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
                        .addComponent(bankAccount_Label)
                        .addComponent(bankAccount_Combobox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(type_Label)
                        .addComponent(type_Combobox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(amountFrom_Label)
                        .addComponent(amountFrom_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(amountTo_Label)
                        .addComponent(amountTo_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(titles_Label)
                        .addComponent(titles_Combobox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(description_Label)
                        .addComponent(description_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(date_Label)
                        .addComponent(datePicker, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(filter_Button, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                )

                .addGap(10)

        );
        input_groupLayout.setVerticalGroup(input_groupLayout.createSequentialGroup()
                .addGap(20)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(bankAccount_Label)
                        .addComponent(amountFrom_Label)
                        .addComponent(titles_Label)
                        .addComponent(date_Label)
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
                        .addComponent(type_Label)
                        .addComponent(amountTo_Label)
                        .addComponent(description_Label)
                )
                .addGap(5)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(type_Combobox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(amountTo_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(description_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(filter_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
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
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private JLabel bankAccount_Label;
    private JLabel type_Label;
    private JLabel amountFrom_Label;
    private JLabel amountTo_Label;

    private JLabel date_Label;
    private JLabel titles_Label;
    private JLabel description_Label;


    private JComboBox bankAccount_Combobox;
    private JComboBox type_Combobox;
    private JXDatePicker datePicker;
    private JTextField amountFrom_TextField;
    private JTextField amountTo_TextField;
    private JComboBox titles_Combobox;
    private JTextField description_TextField;

    private JButton add_Button;
    private JButton modify_Button;
    private JButton delete_Button;
    private JButton refresh_Button;
    private JButton filter_Button;


}
