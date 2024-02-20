package view;

import enums.TypeEnum;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TitlesManagementForm {
    public TitlesManagementForm(){
        initComponent();
        drawGui();
    }

    private void initComponent() {
        name = new JTextField();

        type = new JComboBox();
        type.addItem(TypeEnum.values());
    }
    private void drawGui() {
    }

    private JLabel panel1;

    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField name;
    private JComboBox type;

    private JButton add_Button;
    private JButton delete_Button;
    private JButton modify_Button;

}
