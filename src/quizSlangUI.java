import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

/**
 * PACKAGE_NAME
 * Created by ThaiBinh
 * Date 12/26/2021 - 11:55 PM
 * Description: ...
 */
public class quizSlangUI extends JFrame implements ActionListener {
    private Button back;
    private Label quiz;
    private Button a;
    private Button b;
    private Button c;
    private Button d;
    String s;
    DictManagement dictManagement = new DictManagement();

    public void showQuizSlangBox() {
        dictManagement.DictInitialize();
        BoxLayout box = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(box);

        back = new Button("Back to homepage");
        back.setActionCommand("back");
        back.addActionListener(this);
        back.setMaximumSize(new Dimension(150,30));
        back.setPreferredSize(new Dimension(150,30));
        back.setFont(new Font("Arial",Font.PLAIN,16));

        quiz = new Label("quiz");
        quiz.setFont(new Font("Arial",Font.PLAIN,25));

        a = new Button("A");
        a.setActionCommand("a");
        a.addActionListener(this);
        a.setMaximumSize(new Dimension(400,60));
        a.setPreferredSize(new Dimension(400,60));
        a.setFont(new Font("Arial",Font.PLAIN,16));

        b = new Button("B");
        b.setActionCommand("b");
        b.addActionListener(this);
        b.setMaximumSize(new Dimension(400,60));
        b.setPreferredSize(new Dimension(400,60));
        b.setFont(new Font("Arial",Font.PLAIN,16));

        c = new Button("C");
        c.setActionCommand("c");
        c.addActionListener(this);
        c.setMaximumSize(new Dimension(400,60));
        c.setPreferredSize(new Dimension(400,60));
        c.setFont(new Font("Arial",Font.PLAIN,16));

        d = new Button("D");
        d.setActionCommand("d");
        d.addActionListener(this);
        d.setMaximumSize(new Dimension(400,60));
        d.setPreferredSize(new Dimension(400,60));
        d.setFont(new Font("Arial",Font.PLAIN,16));

        JPanel jPanel = new JPanel();
        BoxLayout box0 = new BoxLayout(jPanel, BoxLayout.X_AXIS);
        jPanel.setLayout(box0);
        jPanel.add(back);
        jPanel.add(Box.createRigidArea(new Dimension(800,0)));
        jPanel.setBackground(Color.lightGray);

        JPanel jPanel1 = new JPanel();
        BoxLayout box1 = new BoxLayout(jPanel1, BoxLayout.Y_AXIS);
        jPanel1.setLayout(box1);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 20)));
        quiz.setAlignment(Label.CENTER);
        jPanel1.add(quiz);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 20)));
        jPanel1.setBackground(Color.lightGray);

        JPanel jPanel2 = new JPanel();
        BoxLayout box2 = new BoxLayout(jPanel2, BoxLayout.Y_AXIS);
        jPanel2.setLayout(box2);
        jPanel2.add(a);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
        jPanel2.add(c);
        jPanel2.add(Box.createRigidArea(new Dimension(0, 20)));
        jPanel2.setBackground(Color.lightGray);

        JPanel jPanel3 = new JPanel();
        BoxLayout box3 = new BoxLayout(jPanel3, BoxLayout.Y_AXIS);
        jPanel3.setLayout(box3);
        jPanel3.add(b);
        jPanel3.add(Box.createRigidArea(new Dimension(0, 20)));
        jPanel3.add(d);
        jPanel3.add(Box.createRigidArea(new Dimension(0, 20)));
        jPanel3.setBackground(Color.lightGray);

        JPanel jPanel4 = new JPanel();
        BoxLayout box4 = new BoxLayout(jPanel4, BoxLayout.X_AXIS);
        jPanel4.setLayout(box4);
        jPanel4.add(Box.createRigidArea(new Dimension(10, 0)));
        jPanel4.add(jPanel2);
        jPanel4.add(Box.createRigidArea(new Dimension(20, 0)));
        jPanel4.add(jPanel3);
        jPanel4.add(Box.createRigidArea(new Dimension(10, 0)));
        jPanel4.setBackground(Color.lightGray);


        s = dictManagement.RandomSlangWord();
        Vector<String> list = new Vector<>();
        list.add(dictManagement.getMeaning(s));

        for (int i = 0; i < 3; i ++) {
            String temp;
            do {
                temp = dictManagement.RandomSlangWord();
            } while (list.contains(dictManagement.getMeaning(temp)) == true);
            list.add(dictManagement.getMeaning(temp));
        }

        Collections.shuffle(list);
        quiz.setText(s);
        a.setLabel(list.get(0).substring(1, list.get(0).length() - 1));
        b.setLabel(list.get(1).substring(1, list.get(1).length() - 1));
        c.setLabel(list.get(2).substring(1, list.get(2).length() - 1));
        d.setLabel(list.get(3).substring(1, list.get(3).length() - 1));


        this.add(jPanel, BorderLayout.PAGE_START);
        this.add(jPanel1, BorderLayout.CENTER);
        this.add(jPanel4, BorderLayout.PAGE_END);
        this.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.PAGE_END);
        this.getContentPane().setBackground((Color.lightGray));
        setTitle("Quiz");
        resize(1000, 500);
        setResizable(true);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        dictManagement.DictInitialize();

        String answer = dictManagement.getMeaning(s).substring(1, dictManagement.getMeaning(s).length() - 1);

        a.setBackground(Color.red);
        b.setBackground(Color.red);
        c.setBackground(Color.red);
        d.setBackground(Color.red);
        if (command == "a") {
            if (a.getLabel().equals(answer)) {
                a.setBackground(Color.green);
                JOptionPane.showMessageDialog(null, "Congratulation!", "Right answer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (b.getLabel().equals(answer)) {
                    b.setBackground(Color.green);
                }
                if (c.getLabel().equals(answer)) {
                    c.setBackground(Color.green);
                }
                if (d.getLabel().equals(answer)) {
                    d.setBackground(Color.green);
                }
                JOptionPane.showMessageDialog(null, "Good luck next time!", "Wrong answer", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (command == "b") {
            if (b.getLabel().equals(answer)) {
                b.setBackground(Color.green);
                JOptionPane.showMessageDialog(null, "Congratulation!", "Right answer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (a.getLabel().equals(answer)) {
                    a.setBackground(Color.green);
                }
                if (c.getLabel().equals(answer)) {
                    c.setBackground(Color.green);
                }
                if (d.getLabel().equals(answer)) {
                    d.setBackground(Color.green);
                }
                JOptionPane.showMessageDialog(null, "Good luck next time!", "Wrong answer", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (command == "c") {
            if (c.getLabel().equals(answer)) {
                c.setBackground(Color.green);
                JOptionPane.showMessageDialog(null, "Congratulation!", "Right answer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (b.getLabel().equals(answer)) {
                    b.setBackground(Color.green);
                }
                if (a.getLabel().equals(answer)) {
                    a.setBackground(Color.green);
                }
                if (d.getLabel().equals(answer)) {
                    d.setBackground(Color.green);
                }
                JOptionPane.showMessageDialog(null, "Good luck next time!", "Wrong answer", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (command == "d") {
            if (d.getLabel().equals(answer)) {
                d.setBackground(Color.green);
                JOptionPane.showMessageDialog(null, "Congratulation!", "Right answer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (b.getLabel().equals(answer)) {
                    b.setBackground(Color.green);
                }
                if (c.getLabel().equals(answer)) {
                    c.setBackground(Color.green);
                }
                if (a.getLabel().equals(answer)) {
                    a.setBackground(Color.green);
                }

                JOptionPane.showMessageDialog(null, "Good luck next time!", "Wrong answer", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (command == "back") {
            this.dispose();
            homeUI h = new homeUI();
            h.showMenu();
        }
        this.dispose();
        homeUI h = new homeUI();
        h.showMenu();
    }
}
