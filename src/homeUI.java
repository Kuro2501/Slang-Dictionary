/**
 * PACKAGE_NAME
 * Created by ThaiBinh
 * Date 12/21/2021 - 11:54 PM
 * Description: ...
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class homeUI extends JFrame implements ActionListener {
    private TextField textField;
    private Button searchSlang;
    private Button searchMean;
    private Button history;
    private Button add;
    private Button edit;
    private Button delete;
    private Button reset;
    private Button random;
    private Button quiz;
    private JTable table;
    String[] tableLabel = {"Slang", "Meaning"};
    private DefaultTableModel mod;

    public void showMenu() {
        BoxLayout box = new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS);
        this.getContentPane().setLayout(box);

        JPanel jPanel1 = new JPanel();
        BoxLayout box1 = new BoxLayout(jPanel1, BoxLayout.Y_AXIS);
        jPanel1.setLayout(box1);

        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));
        textField = new TextField();
        textField.setMaximumSize(new Dimension(300,300));
        textField.setPreferredSize(new Dimension(300,30));
        textField.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(textField);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        searchSlang = new Button("Search by Slang Word");
        searchSlang.setActionCommand("searchSlang");
        searchSlang.addActionListener(this);
        searchSlang.setMaximumSize(new Dimension(300,300));
        searchSlang.setPreferredSize(new Dimension(300,60));
        searchSlang.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(searchSlang);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        searchMean = new Button("Search by Definition");
        searchMean.setActionCommand("searchMean");
        searchMean.addActionListener(this);
        searchMean.setMaximumSize(new Dimension(300,300));
        searchMean.setPreferredSize(new Dimension(300,60));
        searchMean.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(searchMean);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 40)));

        history = new Button("Search history");
        history.setActionCommand("history");
        history.addActionListener(this);
        history.setMaximumSize(new Dimension(300,300));
        history.setPreferredSize(new Dimension(300,60));
        history.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(history);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        add = new Button("Add new Slang Word");
        add.setActionCommand("add");
        add.addActionListener(this);
        add.setMaximumSize(new Dimension(300,300));
        add.setPreferredSize(new Dimension(300,60));
        add.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(add);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        edit = new Button("Edit");
        edit.setActionCommand("edit");
        edit.addActionListener(this);
        edit.setMaximumSize(new Dimension(300,300));
        edit.setPreferredSize(new Dimension(300,60));
        edit.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(edit);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        delete = new Button("Delete");
        delete.setActionCommand("delete");
        delete.addActionListener(this);
        delete.setMaximumSize(new Dimension(300,300));
        delete.setPreferredSize(new Dimension(300,60));
        delete.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(delete);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        reset = new Button("Reset");
        reset.setActionCommand("reset");
        reset.addActionListener(this);
        reset.setMaximumSize(new Dimension(300,300));
        reset.setPreferredSize(new Dimension(300,60));
        reset.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(reset);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        random = new Button("Random");
        random.setActionCommand("random");
        random.addActionListener(this);
        random.setMaximumSize(new Dimension(300,300));
        random.setPreferredSize(new Dimension(300,60));
        random.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(random);
        jPanel1.add(Box.createRigidArea(new Dimension(0, 10)));

        quiz = new Button("Quiz");
        quiz.setActionCommand("quiz");
        quiz.addActionListener(this);
        quiz.setMaximumSize(new Dimension(300,300));
        quiz.setPreferredSize(new Dimension(300,60));
        quiz.setFont(new Font("Arial",Font.PLAIN,16));
        jPanel1.add(quiz);

        jPanel1.setBackground(Color.gray);

        JPanel jPanel2 = new JPanel();
        BoxLayout box2 = new BoxLayout(jPanel2, BoxLayout.X_AXIS);
        jPanel2.setLayout(box2);
        jPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
        jPanel2.add(jPanel1);
        jPanel2.setBackground(Color.gray);

        this.add(jPanel2, BorderLayout.WEST);
        this.add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.CENTER);

        mod = new DefaultTableModel(null,tableLabel);
        table = new JTable(mod);
        table.getTableHeader().setFont(new Font("Serif", Font.PLAIN,20));
        table.setFont(new Font("Serif", Font.PLAIN,17));
        table.setRowHeight(30);
        table.setDefaultEditor(Object.class, null);
        
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(scrollPane, BorderLayout.EAST);
        this.getContentPane().setBackground((Color.gray));
        setTitle("Menu");
        resize(1000, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        homeUI h = new homeUI();
        h.showMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        DictManagement dictManagement = new DictManagement();
        dictManagement.DictInitialize();
        if(command == "searchSlang"){
            mod.getDataVector().removeAllElements();
            Vector<String> list = new Vector<>();
            list = dictManagement.SearchbySlang(textField.getText());
            if (dictManagement.SearchbySlang(textField.getText()) == null)
                JOptionPane.showMessageDialog(null, "No slang found!", "Not existed", JOptionPane.INFORMATION_MESSAGE);
            else mod.addRow(list);

            String s = java.time.LocalDate.now().toString() + "`" + java.time.LocalTime.now().toString() + "`" + textField.getText() + "`" + dictManagement.getMeaning(textField.getText()) + "\n";
            dictManagement.WriteHistory(s);
        }
        if(command == "searchMean"){
            mod.getDataVector().removeAllElements();
            Vector<String[]> list = new Vector<>();
            list = dictManagement.SearchbyMeaning(textField.getText());
            if (dictManagement.SearchbyMeaning(textField.getText()) == null)
                JOptionPane.showMessageDialog(null, "No definition found!", "Not existed", JOptionPane.INFORMATION_MESSAGE);
            else{
                for(int i = 0; i < list.size();i++){
                    mod.addRow(list.get(i));
                }
            }
        }
        if(command == "history"){
            this.dispose();
            historyUI hist = new historyUI();
            hist.showHistory();

            Vector<String[]> list = new Vector<>();
            list = dictManagement.HistoryInitialize();
            for(int i = 0; i < list.size();i++){
                hist.mod.addRow(list.get(i));
            }
        }
        if(command == "add") {
            this.dispose();
            addSlangUI add = new addSlangUI();
            add.showAddbox();
        }
        if(command == "edit"){
            this.dispose();
            editSlangUI edit = new editSlangUI();
            edit.showEditbox();
        }
        if(command == "delete"){
            this.dispose();
            deleteSlangUI delete = new deleteSlangUI();
            delete.showDeleteBox();
        }
        if(command == "reset"){
            dictManagement.Reset();
        }
        if(command == "random"){
            //System.out.println(dictManagement.RandomSlangWord());
            this.dispose();
            randomSlangUI rand = new randomSlangUI();
            rand.showRandomBox();
        }
        if(command == "quiz"){
            this.dispose();
            quizUI quiz = new quizUI();
            quiz.showQuizBox();
        }
    }
}
