import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PACKAGE_NAME
 * Created by ThaiBinh
 * Date 12/23/2021 - 5:14 PM
 * Description: ...
 */
public class addConfirmUI extends JFrame implements ActionListener {
    private Button overwrite;
    private Button duplicate;
    private Label label;
    private String slang, mean;
    public addConfirmUI(String slang,String mean){
        this.slang = slang;
        this.mean = mean;
    }

    public void showConfirmBox() {
        BoxLayout box = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(box);

        label = new Label("Slang existed! Choose one option");
        label.setFont(new Font("Arial",Font.PLAIN,16));

        JPanel jPanel1 = new JPanel();
        BoxLayout box1 = new BoxLayout(jPanel1, BoxLayout.X_AXIS);
        jPanel1.setLayout(box1);
        jPanel1.add(Box.createRigidArea(new Dimension(20 ,0)));
        overwrite = new Button("Overwrite");
        overwrite.setActionCommand("overwrite");
        overwrite.addActionListener(this);
        overwrite.setMaximumSize(new Dimension(80,30));
        overwrite.setPreferredSize(new Dimension(80,30));
        overwrite.setFont(new Font("Arial",Font.PLAIN,16));
        duplicate = new Button("Duplicate");
        duplicate.setActionCommand("duplicate");
        duplicate.addActionListener(this);
        duplicate.setMaximumSize(new Dimension(80,30));
        duplicate.setPreferredSize(new Dimension(80,30));
        duplicate.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(overwrite);
        jPanel1.add(Box.createRigidArea(new Dimension(20 ,0)));
        jPanel1.add(duplicate);
        jPanel1.add(Box.createRigidArea(new Dimension(20 ,0)));
        jPanel1.setBackground(Color.lightGray);

        JPanel jPanel2 = new JPanel();
        BoxLayout box2 = new BoxLayout(jPanel2, BoxLayout.Y_AXIS);
        jPanel2.setLayout(box2);
        jPanel2.add(Box.createRigidArea(new Dimension(0 ,10)));
        jPanel2.add(label);
        //jPanel2.add(Box.createRigidArea(new Dimension(0 ,10)));
        jPanel2.add(jPanel1);
        jPanel2.add(Box.createRigidArea(new Dimension(0 ,10)));
        jPanel2.setBackground(Color.lightGray);

        this.add(jPanel2);

        this.getContentPane().setBackground((Color.lightGray));
        setTitle("Confirm");
        resize(300, 150);
        setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        DictManagement dictManagement = new DictManagement();
        dictManagement.DictInitialize();
        if (command == "overwrite") {
            dictManagement.AddSlangWord(slang, mean, "overwrite");
            this.dispose();
            JOptionPane.showMessageDialog(null, "Add slang word successfully!", "", JOptionPane.INFORMATION_MESSAGE);
            homeUI h = new homeUI();
            h.showMenu();
        }
        if (command == "duplicate") {
            dictManagement.AddSlangWord(slang, mean, "duplicate");
            this.dispose();;
            JOptionPane.showMessageDialog(null, "Add slang word successfully!", "", JOptionPane.INFORMATION_MESSAGE);
            homeUI h = new homeUI();
            h.showMenu();
        }
    }
}
