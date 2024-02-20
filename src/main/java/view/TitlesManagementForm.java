package view;

import controller.TitlesController;
import enums.TypeEnum;
import model.entity.Titles;
import model.entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TitlesManagementForm extends JPanel {
    public TitlesManagementForm() {
        initComponent();
        drawGui();
    }

    private void initComponent() {
        panel1 = new JLabel();
        panel1.setBorder(BorderFactory.createTitledBorder("Input"));

        name_Label = new JLabel("Name");
        name_TextField = new JTextField();

        type_Label = new JLabel("Type");
        type_Combobox = new JComboBox(TypeEnum.values());

        Object[] headers = {"Row", "ID", "Name", "Type"};
        tableModel = new DefaultTableModel(headers, 0);
        table = new JTable(tableModel);
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
                .addComponent(name_Label)
                .addGap(5)
                .addComponent(name_TextField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(type_Label)
                .addGap(5)
                .addComponent(type_Combobox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, 300)
                .addComponent(add_Button, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                .addGap(10)

        );
        input_groupLayout.setVerticalGroup(input_groupLayout.createSequentialGroup()
                .addGap(20)
                .addGroup(input_groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(name_Label)
                        .addComponent(name_TextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(type_Label)
                        .addComponent(type_Combobox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(add_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20)
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
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
                .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                        .addComponent(modify_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(delete_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(refresh_Button, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(30)


        );
    }

    public void fillForm() {
        try {
            List<Titles> titlesList = TitlesController.getController().findAll();
            if (titlesList != null) {
                for (Titles titles : titlesList) {
                    tableModel.addRow(new Object[]{table.getRowCount() + 1, titles.getId(),
                            titles.getName(), titles.getType().toString()});
                }
            }else{
                JOptionPane.showMessageDialog(this,"IS NULL");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void add_ButtonActionPreform() {
        try {
            if (!name_TextField.getText().trim().isEmpty()) {
                Titles titles = TitlesController.getController().save(0, name_TextField.getText().trim(),
                        (TypeEnum) type_Combobox.getSelectedItem());
                if (titles != null) {
                    tableModel.addRow(new Object[]{table.getRowCount() + 1, titles.getId(),
                            titles.getName(), titles.getType().toString()});
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

    private void modify_ButtonActionPreform() {
        try{

        }catch (Exception e){

        }
    }

    private void delete_ButtonActionPreform() {
        try{

        }catch (Exception e){

        }

    }

    private void refresh_ButtonActionPreform() {
        for(int i = 0; i<=tableModel.getRowCount() ;i++){
            tableModel.removeRow(i);
        }
        fillForm();
    }

    public static void main(String[] args) {
        JFrame a = new JFrame();
        TitlesManagementForm titlesManagementForm = new TitlesManagementForm();
        a.add(titlesManagementForm);
        a.pack();
        a.setVisible(true);
        titlesManagementForm.setVisible(true);
    }

    private JLabel panel1;

    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private JLabel name_Label;
    private JLabel type_Label;

    private JTextField name_TextField;
    private JComboBox type_Combobox;

    private JButton add_Button;
    private JButton delete_Button;
    private JButton modify_Button;
    private JButton refresh_Button;

}
