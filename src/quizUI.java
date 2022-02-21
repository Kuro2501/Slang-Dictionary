import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PACKAGE_NAME
 * Created by ThaiBinh
 * Date 12/25/2021 - 12:33 AM
 * Description: ...
 */
public class quizUI extends JFrame implements ActionListener {
    private Button slang;
    private Button definition;

    public void showQuizBox() {
        BoxLayout box = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(box);

        slang = new Button("Slang quiz");
        slang.setActionCommand("slang");
        slang.addActionListener(this);
        slang.setMaximumSize(new Dimension(150,40));
        slang.setPreferredSize(new Dimension(150,40));
        slang.setFont(new Font("Arial",Font.PLAIN,16));

        definition = new Button("Definition quiz");
        definition.setActionCommand("definition");
        definition.addActionListener(this);
        definition.setMaximumSize(new Dimension(150,40));
        definition.setPreferredSize(new Dimension(150,40));
        definition.setFont(new Font("Arial",Font.PLAIN,16));

        JPanel jPanel1 = new JPanel();
        BoxLayout box1 = new BoxLayout(jPanel1, BoxLayout.X_AXIS);
        jPanel1.setLayout(box1);
        jPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        jPanel1.add(slang);
        jPanel1.add(Box.createRigidArea(new Dimension(15, 0)));
        jPanel1.add(definition);
        jPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        jPanel1.setBackground(Color.lightGray);

        this.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.PAGE_START);
        this.add(jPanel1, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.PAGE_END);
        this.getContentPane().setBackground((Color.lightGray));
        setTitle("Choose option");
        resize(400, 100);
        setResizable(true);
        setLocationRelativeTo(null);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "slang") {
            this.dispose();
            quizSlangUI quizSlang = new quizSlangUI();
            quizSlang.showQuizSlangBox();
        }
        if (command == "definition") {
            this.dispose();
            quizDefinitionUI quizDefinition = new quizDefinitionUI();
            quizDefinition.showQuizDefinitionBox();
        }
    }
}
