import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PACKAGE_NAME
 * Created by ThaiBinh
 * Date 12/23/2021 - 12:54 AM
 * Description: ...
 */
public class addSlangUI extends JFrame implements ActionListener {
    private TextField slang;
    private TextField meaning;
    private Button add;
    private Button back;
    private Label label1;
    private Label label2;

    public void showAddbox() {
        BoxLayout box = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(box);

        JPanel jPanel1 = new JPanel();
        BoxLayout box1 = new BoxLayout(jPanel1, BoxLayout.Y_AXIS);
        jPanel1.setLayout(box1);
        label1 = new Label("Input slang word");
        label1.setFont(new Font("Arial",Font.PLAIN,16));
        slang = new TextField();
        slang.setFont(new Font("Arial",Font.PLAIN,16));
        label2 = new Label("Input meaning");
        label2.setFont(new Font("Arial",Font.PLAIN,16));
        meaning = new TextField();
        meaning.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(label1);
        jPanel1.add(slang);
        jPanel1.add(label2);
        jPanel1.add(meaning);
        jPanel1.setBackground(Color.lightGray);

        JPanel jPanel2 = new JPanel();
        BoxLayout box2 = new BoxLayout(jPanel2, BoxLayout.X_AXIS);
        jPanel2.setLayout(box2);
        add = new Button("Add");
        add.setActionCommand("add");
        add.addActionListener(this);
        add.setMaximumSize(new Dimension(150,30));
        add.setPreferredSize(new Dimension(150,30));
        add.setFont(new Font("Arial",Font.PLAIN,16));
        back = new Button("Back to homepage");
        back.setActionCommand("back");
        back.addActionListener(this);
        back.setMaximumSize(new Dimension(150,30));
        back.setPreferredSize(new Dimension(150,30));
        back.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel2.add(add);
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
        setTitle("Add new slang word");
        resize(400, 200);
        setResizable(true);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        DictManagement dictManagement = new DictManagement();
        dictManagement.DictInitialize();
        if (command == "add" && slang.getText().length() != 0 && meaning.getText().length() != 0) {
            if (dictManagement.isInDictionary(slang.getText()) == true) {
                this.dispose();
                dictManagement.WriteToFile();
                addConfirmUI addui = new addConfirmUI(slang.getText(), meaning.getText());
                addui.showConfirmBox();
            } else {
                dictManagement.AddSlangWord(slang.getText(), meaning.getText(), "addnew");
                JOptionPane.showMessageDialog(null, "Add slang word successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                homeUI h = new homeUI();
                h.showMenu();
            }
        }
        if (command == "back") {
            this.dispose();
            homeUI h = new homeUI();
            h.showMenu();
        }
    }
}
