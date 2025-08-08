package component;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

@SuppressWarnings("serial")
public class Example extends JFrame {
    private JLabel lblTitle;
    private JButton btnAddItem;
    private JButton btnGetItem;
    private JButton btnDeleteItem;
    private JButton btnEditItem;
    private JTextField txtTextField;
    private JTable tblTable;
    private JList<String> lstOption;
    private JPanel pnMain;
    private DefaultListModel<String> items;
    private String[] column;
    private String[][] data;
    private int selectedIndex = -1;

    public Example() {
        pnMain = new JPanel(new FlowLayout());
        setContentPane(pnMain);

        // 1. Button Add
        btnAddItem = new JButton("Add");
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.addElement(txtTextField.getText());
            }
        });

        // 2. Label
        lblTitle = new JLabel("Item: ");
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTitle.setForeground(Color.BLUE);

        // 3. TextField
        txtTextField = new JTextField(20);

        // 4. List
        items = new DefaultListModel<>();
        items.addElement("Item 1");
        items.addElement("Item 2");
        items.addElement("Item 3");
        items.addElement("Item 4");

        lstOption = new JList<>(items);
        lstOption.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorRemoved(AncestorEvent event) {
                System.out.println("Removed - " + LocalDateTime.now());
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                System.out.println("Moved - " + LocalDateTime.now());
            }

            @Override
            public void ancestorAdded(AncestorEvent event) {
                System.out.println("Added - " + LocalDateTime.now());
            }
        });

        // 5. Button Get
        btnGetItem = new JButton("Get");
        btnGetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valueSelected = lstOption.getSelectedValue();
                if (valueSelected != null) {
                    txtTextField.setText(valueSelected);
                    selectedIndex = lstOption.getSelectedIndex();
                    System.out.println(selectedIndex + " - " + valueSelected);
                }
            }
        });

        // 6. Button Delete
        btnDeleteItem = new JButton("Delete");
        btnDeleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = lstOption.getSelectedIndex();
                if (index != -1) {
                    items.remove(index);
                    clear();
                    System.out.println("Delete Success!");
                } else {
                    System.out.println("Cannot remove item at index [" + index + "]");
                }
            }
        });

        // 7. Button Edit
        btnEditItem = new JButton("Edit");
        btnEditItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex != -1) {
                    items.set(selectedIndex, txtTextField.getText());
                    clear();
                }
            }
        });

        // 8. Table
        column = new String[]{"ID", "Name", "SALARY"};
        data = new String[][]{
            {"1", "ABC1", "1000000"},
            {"2", "ABC2", "2000000"},
            {"4", "ABC4", "4000000"},
            {"5", "ABC5", "5000000"},
        };
        tblTable = new JTable(data, column);
        tblTable.setRowSelectionAllowed(true);
        tblTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane sp = new JScrollPane(tblTable);

        // Add components
        pnMain.add(lblTitle);
        pnMain.add(txtTextField);
        pnMain.add(btnAddItem);
        pnMain.add(btnGetItem);
        pnMain.add(btnDeleteItem);
        pnMain.add(btnEditItem);
        pnMain.add(new JScrollPane(lstOption)); // Thêm JList vào JScrollPane để scroll
        pnMain.add(sp);

        // Frame config
        setTitle("Example");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void clear() {
        txtTextField.setText("");
        selectedIndex = -1;
    }

    public static void main(String[] args) {
        new Example();
    }
}
