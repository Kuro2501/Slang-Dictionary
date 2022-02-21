import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PACKAGE_NAME
 * Created by ThaiBinh
 * Date 12/24/2021 - 1:22 AM
 * Description: ...
 */
public class deleteSlangUI extends JFrame implements ActionListener {
    private TextField slang;
    private Button delete;
    private Button back;
    private Label label1;

    public void showDeleteBox() {
        BoxLayout box = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(box);

        JPanel jPanel1 = new JPanel();
        BoxLayout box1 = new BoxLayout(jPanel1, BoxLayout.Y_AXIS);
        jPanel1.setLayout(box1);
        label1 = new Label("Input slang word");
        label1.setFont(new Font("Arial",Font.PLAIN,16));
        slang = new TextField();
        slang.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(label1);
        jPanel1.add(slang);
        jPanel1.add(Box.createRigidArea(new Dimension(10, 10)));
        jPanel1.setBackground(Color.lightGray);

        JPanel jPanel2 = new JPanel();
        BoxLayout box2 = new BoxLayout(jPanel2, BoxLayout.X_AXIS);
        jPanel2.setLayout(box2);

        delete = new Button("Delete");
        delete.setActionCommand("delete");
        delete.addActionListener(this);
        delete.setMaximumSize(new Dimension(150,30));
        delete.setPreferredSize(new Dimension(150,30));
        delete.setFont(new Font("Arial",Font.PLAIN,16));
        back = new Button("Back to homepage");
        back.setActionCommand("back");
        back.addActionListener(this);
        back.setMaximumSize(new Dimension(150,30));
        back.setPreferredSize(new Dimension(150,30));
        back.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel2.add(delete);
        jPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
        jPanel2.add(back);
        jPanel2.setBackground(Color.lightGray);

        JPanel header = new JPanel();
        BoxLayout boxHeader = new BoxLayout(header, BoxLayout.X_AXIS);
        header.setLayout(boxHeader);
        header.add(Box.createRigidArea(new Dimension(10, 0)));
        header.add(jPanel1);
        header.add(Box.createRigidArea(new Dimension(10, 0)));
        header.setBackground(Color.lightGray);

        JPanel end = new JPanel();
        BoxLayout boxEnd = new BoxLayout(end, BoxLayout.Y_AXIS);
        end.setLayout(boxEnd);
        end.add(Box.createRigidArea(new Dimension(0, 10)));
        end.add(jPanel2);
        end.add(Box.createRigidArea(new Dimension(0, 10)));
        end.setBackground(Color.lightGray);

        this.add(header, BorderLayout.PAGE_START);
        this.add(end, BorderLayout.PAGE_END);
        this.getContentPane().setBackground((Color.lightGray));
        setTitle("Delete slang word");
        resize(400, 170);
        setResizable(true);
        setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        DictManagement dictManagement = new DictManagement();
        dictManagement.DictInitialize();
        if (command == "delete" && slang.getText().length() != 0) {
            if (dictManagement.isInDictionary(slang.getText()) == true) {
                dictManagement.DeleteSlangWord(slang.getText());
                JOptionPane.showMessageDialog(null, "Delete successfully", "", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                homeUI h = new homeUI();
                h.showMenu();
            } else
                JOptionPane.showMessageDialog(null, "Slang word not exist!", "", JOptionPane.INFORMATION_MESSAGE);
        }
        if (command == "back") {
            this.dispose();
            homeUI h = new homeUI();
            h.showMenu();
        }
    }
}
