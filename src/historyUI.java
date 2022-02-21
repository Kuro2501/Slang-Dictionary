import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

/**
 * PACKAGE_NAME
 * Created by ThaiBinh
 * Date 12/22/2021 - 4:38 PM
 * Description: ...
 */
public class historyUI extends JFrame implements ActionListener {
    private JTable histTable;
    String[] tableLabel = {"Date", "Time", "Slang searched", "Definition"};
    public DefaultTableModel mod;
    private Button clear;
    private Button back;
    DictManagement dictManagement = new DictManagement();

    public void showHistory() {
        BoxLayout box = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(box);

        JPanel jPanel1 = new JPanel();
        BoxLayout box1 = new BoxLayout(jPanel1, BoxLayout.X_AXIS);
        jPanel1.setLayout(box1);
        clear = new Button("Clear history");
        clear.setActionCommand("clear");
        clear.addActionListener(this);
        clear.setMaximumSize(new Dimension(300,60));
        clear.setPreferredSize(new Dimension(300,60));
        clear.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(clear);
        jPanel1.setBackground(Color.gray);
        jPanel1.add(Box.createRigidArea(new Dimension(50, 10)));

        back = new Button("Back to homepage");
        back.setActionCommand("back");
        back.addActionListener(this);
        back.setMaximumSize(new Dimension(300,60));
        back.setPreferredSize(new Dimension(300,60));
        back.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(back);

        JPanel jPanel2 = new JPanel();
        BoxLayout box2 = new BoxLayout(jPanel2, BoxLayout.Y_AXIS);
        jPanel2.setLayout(box2);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 10)));
        jPanel2.add(jPanel1);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 10)));
        jPanel2.setBackground(Color.gray);

        mod = new DefaultTableModel(null,tableLabel);
        histTable = new JTable(mod);
        histTable.getTableHeader().setFont(new Font("Serif", Font.PLAIN,20));
        histTable.setFont(new Font("Serif", Font.PLAIN,17));
        histTable.setRowHeight(30);
        histTable.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(histTable);

        this.add(scrollPane, BorderLayout.PAGE_START);
        this.add(jPanel2, BorderLayout.PAGE_END);
        this.getContentPane().setBackground((Color.gray));
        setTitle("Search history");
        resize(800, 500);
        setResizable(true);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if(command == "clear"){
            mod.getDataVector().removeAllElements();
            try {
                dictManagement.ClearHistory();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(command == "back") {
            this.dispose();
            homeUI h = new homeUI();
            h.showMenu();
        }
    }
}
