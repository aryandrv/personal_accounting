package view;

import model.entity.User;

import javax.swing.*;

public class TransactionSettingForm extends JFrame {
    public TransactionSettingForm(){
        initComponent();
        drawGui();
    }

    private void initComponent() {

    }

    private void drawGui() {

    }

    public void fillForm(User user){
        this.user = user;

    }


    private User user;
}
