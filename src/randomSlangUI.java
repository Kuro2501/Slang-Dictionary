import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PACKAGE_NAME
 * Created by ThaiBinh
 * Date 12/24/2021 - 12:48 PM
 * Description: ...
 */
public class randomSlangUI extends JFrame implements ActionListener {
    String s;
    private Label slang;
    private Label definition;
    private Button back;
    DictManagement dictManagement = new DictManagement();

    public void showRandomBox() {
        dictManagement.DictInitialize();
        BoxLayout box = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(box);

        s = dictManagement.RandomSlangWord();
        slang = new Label(s);
        slang.setFont(new Font("Arial",Font.PLAIN,18));

        String temp = dictManagement.getMeaning(s);
        temp = temp.substring(1 , temp.length() - 1);
        definition = new Label(temp);
        definition.setFont(new Font("Arial",Font.PLAIN,16));

        back = new Button("Back to homepage");
        back.setActionCommand("back");
        back.addActionListener(this);
        back.setMaximumSize(new Dimension(150,30));
        back.setPreferredSize(new Dimension(150,30));
        back.setFont(new Font("Arial",Font.PLAIN,16));

        JPanel jPanel1 = new JPanel();
        BoxLayout box1 = new BoxLayout(jPanel1, BoxLayout.Y_AXIS);
        jPanel1.setLayout(box1);
        slang.setAlignment(Label.CENTER);
        jPanel1.add(slang);
        definition.setAlignment(Label.CENTER);
        jPanel1.add(definition);
        jPanel1.add(Box.createRigidArea(new Dimension(10, 0)));
        jPanel1.setBackground(Color.lightGray);

        JPanel jPanel2 = new JPanel();
        BoxLayout box2 = new BoxLayout(jPanel2, BoxLayout.Y_AXIS);
        jPanel2.setLayout(box2);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 15)));
        jPanel2.add(back);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 15)));
        jPanel2.setBackground(Color.lightGray);

        this.add(Box.createRigidArea(new Dimension(0, 15)), BorderLayout.PAGE_START);
        this.add(jPanel1, BorderLayout.CENTER);
        this.add(jPanel2, BorderLayout.PAGE_END);
        this.getContentPane().setBackground((Color.lightGray));
        setTitle("On this day slang word");
        resize(400, 180);
        setResizable(true);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "back") {
            this.dispose();
            homeUI h = new homeUI();
            h.showMenu();
        }
    }
}
