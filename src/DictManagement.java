import java.io.*;
import java.lang.reflect.WildcardType;
import java.util.*;

/**
 * PACKAGE_NAME
 * Created by ThaiBinh
 * Date 12/21/2021 - 8:44 PM
 * Description: ...
 */
public class DictManagement {
    private TreeMap<String, Vector<String>> dict;

    public DictManagement() {
        this.dict = new TreeMap<>();
    }

    public DictManagement(TreeMap<String, Vector<String>> dict) {
        this.dict = dict;
    }

    public void DictInitialize() {
        FileReader fr;
        String s = "";
        try {
            fr = new FileReader("test.txt");
            int data = fr.read();
            StringBuilder line = new StringBuilder();
            while (data != -1) {
                if ((char)data == '\n') {
                    String temp = s;
                    s = temp + line.toString();
                    String[] slang = s.split("`");
                    Vector<String> meaning = new Vector<>();
                    String[] arrStr = slang[1].split("\\|");
                    for (int i = 0; i < arrStr.length; i++) {
                        meaning.add(arrStr[i].trim());
                    }
                    dict.put(slang[0], meaning);
                    s = "";
                    line.delete(0, line.length());
                    data = fr.read();
                    continue;
                }
                line.append((char)data);
                data = fr.read();
            }
            fr.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void WriteToFile() {
        FileWriter fw;
        try {
            File file = new File("test.txt");
            file.delete();
            fw = new FileWriter("test.txt");
            Set<String> tempkey = dict.keySet();
            for(String tk:tempkey){
                fw.write(tk + "`");
                int n = dict.get(tk).size();
                for (int i = 0; i < n - 1; i++) {
                    fw.write(dict.get(tk).get(i).toString() + "|");
                }
                fw.write(dict.get(tk).get(n - 1).toString() + "\n");
            }
            fw.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean isInDictionary(String slang) {
        Set<String> tempkey = dict.keySet();
        for(String tk:tempkey) {
            if (tk.toLowerCase().equals(slang.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void AddSlangWord(String slang, String meaning, String option) {
        Vector<String> m = new Vector<>();
        m.add(meaning);
        if (option == "overwrite")
            dict.put(slang, m);
        if (option == "duplicate") {
            dict.get(slang).add(meaning);
        }
        if (option == "addnew") {
            dict.put(slang, m);
        }
        WriteToFile();
    }

    public Vector<String> SearchbySlang(String slang) {
        Set<String> tempkey = dict.keySet();
        Vector<String> found = new Vector<>();
        String s;
        for(String tk:tempkey) {
            if (tk.toLowerCase().equals(slang.toLowerCase())) {
                found.add(tk);
                found.add(dict.get(tk).toString());
                return found;
            }
        }
        return null;
    }

    public Vector<String[]> SearchbyMeaning(String meaning) {
        Set<String> tempkey = dict.keySet();
        Vector<String[]> found = new Vector<>();
        boolean k = false;
        for(String tk:tempkey) {
            if (dict.get(tk).toString().toLowerCase().contains(meaning.toLowerCase())) {
                k = true;
                String []temp = new String[2];
                temp[0] = tk;
                temp[1] = dict.get(tk).toString();
                found.add(temp);
            }
        }
        if (k == true) {
            return found;
        }
        return null;
    }

    public void WriteHistory(String s) {
        FileWriter fw;
        try {
            File file = new File("history.txt");
            fw = new FileWriter(file, true);
            fw.write(s);
            fw.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getMeaning(String slang) {
        Set<String> tempkey = dict.keySet();
        for(String tk:tempkey) {
            if (tk.toLowerCase().equals(slang.toLowerCase())) {
                return dict.get(tk).toString();
            }
        }
        return "Not found";
    }

    public Vector<String[]> HistoryInitialize() {
        FileReader fr;
        String s = "";
        Vector<String[]> history = new Vector<>();;
        try {
            fr = new FileReader("history.txt");
            int data = fr.read();
            StringBuilder line = new StringBuilder();
            while (data != -1) {
                if ((char)data == '\n') {
                    String temp = s;
                    s = temp + line.toString();

                    String[] element = s.split("`");
                    history.add(element);

                    s = "";
                    line.delete(0, line.length());
                    data = fr.read();
                    continue;
                }
                line.append((char)data);
                data = fr.read();
            }
            fr.close();
            return history;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void ClearHistory() throws IOException {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("history.txt"));
            bw.write("");
            bw.close();
        }
        catch (Exception ev){ }
    }

    public void Reset() {
        InputStream is = null;
        OutputStream os = null;
        try {
            File file = new File("test.txt");
            file.delete();
            File source = new File("slang.txt");

            is = new FileInputStream(source);
            os = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void DeleteSlangWord(String slang) {
        dict.remove(slang);
        WriteToFile();
    }

    public void EditSlangWord(String slang, String meaning) {
        Vector<String> m = new Vector<>();
        String[] mean = meaning.split(",");
        m.add(mean[0]);
        for (int i = 1; i < mean.length; i++) {
            m.add(mean[i].substring(1, mean[i].length()));
        }
        dict.put(slang, m);
        WriteToFile();
    }

    public String RandomSlangWord() {
        Set<String> keySet = dict.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size() - 1;
        int randIndex = new Random().nextInt(size);

        return keyList.get(randIndex);
    }
}
