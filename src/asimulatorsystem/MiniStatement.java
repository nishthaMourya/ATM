

package ASimulatorSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class MiniStatement extends JFrame implements ActionListener {

    JButton b1;
    JLabel l1;
    DefaultTableModel model;
    JTable table;
    
    MiniStatement(String pin) {
        super("Mini Statement");
        getContentPane().setBackground(Color.WHITE);
        setSize(600, 450);
        setLocation(20, 20);
        
        model = new DefaultTableModel();
        table = new JTable(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 550, 250);
        add(scrollPane);
        
        l1 = new JLabel();
        l1.setBounds(20, 380, 550, 20);
        add(l1);

        JLabel l2 = new JLabel("Indian Bank");
        l2.setBounds(250, 20, 100, 20);
        add(l2);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '" + pin + "'");
            while (rs.next()) {
                l1.setText("Card Number:    " + rs.getString("cardno").substring(0, 4) + "XXXXXXXX" + rs.getString("cardno").substring(12));
            }
        } catch (Exception e) {}

        model.addColumn("Date");
        model.addColumn("Type");
        model.addColumn("Amount");

        try {
            int balance = 0;
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '" + pin + "'");
            while (rs.next()) {
                String[] row = {
                    rs.getString("date"),
                    rs.getString("type"),
                    rs.getString("amount")
                };
                model.addRow(row);

                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            l1.setText("Your total Balance is Rs " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(null);
        b1 = new JButton("Exit");
        add(b1);

        b1.addActionListener(this);

        b1.setBounds(20, 350, 100, 25);
    }

    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new MiniStatement("").setVisible(true);
    }
}
