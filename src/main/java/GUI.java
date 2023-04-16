import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class GUI {

    private DB_API db_api;
    private JFrame frame;
    private JPanel main_panel;

    public int year = 2023, month = 1;
    public String Date = "2023-01-31";
    public double BasicSalaryDidakt = 600;
    public double BasicSalaryDoiik = 600;
    public double FamillyEpidoma = 5;
    public double LibraryEpidoma = 100;
    public double SearchEpidoma = 200;

    public GUI(DB_API db_api) {
        this.db_api = db_api;

        frame = new JFrame("Σύστημα Διαχείρισης Βάσης Δεδομένων Προσωπικού");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("database-database-icon.png")));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);

        main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(11, 1));

        createMainMenu();
        frame.add(main_panel);

        frame.setVisible(true);
    }

        public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);

        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    private void createHireMenu() {
        JFrame hire_frame = new JFrame("Πρόσληψη");
        hire_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("database-database-icon.png")));
        hire_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        hire_frame.setSize(1000, 500);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(3, 1));

        JPanel hire_panel = new JPanel();
        hire_panel.setLayout(new GridLayout(6, 2));

        JLabel name_label = new JLabel("Όνομα:");
        name_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField name_textfield = new JTextField();
        name_textfield.setFont(new Font("Arial", Font.PLAIN, 20));


        JLabel surname_label = new JLabel("Επώνυμο:");
        surname_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField surname_textfield = new JTextField();
        surname_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel address_label = new JLabel("Διεύθυνση:");
        address_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField address_textfield = new JTextField();
        address_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel phone_label = new JLabel("Τηλέφωνο:");
        phone_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField phone_textfield = new JTextField();
        phone_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel email_label = new JLabel("Email:");
        email_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField email_textfield = new JTextField();

        JLabel department_label = new JLabel("Τμήμα:");
        department_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField department_textfield = new JTextField();
        department_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel bank_label = new JLabel("Τράπεζα:");
        bank_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField bank_textfield = new JTextField();
        bank_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel prosopiko_label = new JLabel("Κατηγορίες Προσωπικού:");
        prosopiko_label.setFont(new Font("Arial", Font.BOLD, 20));
        JComboBox<String> prosopiko_list = new JComboBox<>(new String[]{"Διοικητικό προσωπικό", "Διδακτικό προσωπικό"});
        prosopiko_list.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel iban_label = new JLabel("Iban:");
        iban_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField iban_textfield = new JTextField();
        iban_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel family_label = new JLabel("Οικογενειακή Κατάσταση:");
        family_label.setFont(new Font("Arial", Font.BOLD, 20));
        JComboBox<String> family_state_list = new JComboBox<>(new String[]{"Παντρεμένος", "Ανύπαντρος"});
        family_state_list.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel children_label = new JLabel("Αριθμός παιδιών:");
        children_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField children_textfield = new JTextField();
        children_textfield.setFont(new Font("Arial", Font.PLAIN, 20));


        // add to panel
        hire_panel.add(name_label);
        hire_panel.add(name_textfield);
        hire_panel.add(surname_label);
        hire_panel.add(surname_textfield);
        hire_panel.add(address_label);
        hire_panel.add(address_textfield);
        hire_panel.add(phone_label);
        hire_panel.add(phone_textfield);
        hire_panel.add(email_label);
        hire_panel.add(email_textfield);
        hire_panel.add(department_label);
        hire_panel.add(department_textfield);
        hire_panel.add(bank_label);
        hire_panel.add(bank_textfield);
        hire_panel.add(prosopiko_label);
        hire_panel.add(prosopiko_list);
        hire_panel.add(iban_label);
        hire_panel.add(iban_textfield);
        hire_panel.add(family_label);
        hire_panel.add(family_state_list);
        hire_panel.add(children_label);
        hire_panel.add(children_textfield);

        // add hire_panel to main_panel
        main_panel.add(hire_panel);

        JPanel button_panel = new JPanel();
        JButton hire_button = new JButton("Πρόσληψη");

        button_panel.add(Box.createHorizontalGlue());
        hire_button.setFont(new Font("Arial", Font.BOLD, 20));
        button_panel.add(hire_button);
        main_panel.add(button_panel);


        hire_button.addActionListener(e -> {
            int counter = 0;
            try {
                if (isInteger(children_textfield.getText())) {
                    if (Integer.parseInt(children_textfield.getText()) != 0) {
                        for (int k = 0; k < Integer.parseInt(children_textfield.getText()); k++) {
                            String v = JOptionPane.showInputDialog("Ηλικία παιδιού " + (k + 1) + " :");

                            if (v == null) {
                                break;
                            }

                            if (isInteger(v) && Integer.parseInt(v) >= 0) {
                                if (isInteger(v) && Integer.parseInt(v) < 18) {
                                    counter++;
                                }

                                db_api.executeUpdate("INSERT INTO children(`employeid`, `age`)  VALUES ((SELECT COUNT(*) FROM EMPLOYEES)+1, " + v + ")");

                            } else {
                                JOptionPane.showMessageDialog(null, "Λάθος ηλικία παιδιού");
                                k--;
                            }

                            if (k == Integer.parseInt(children_textfield.getText()) - 1) {

                                String values = Utils.constructValues(new String[]{(name_textfield.getText() + " " + surname_textfield.getText()), address_textfield.getText(), phone_textfield.getText(), email_textfield.getText(), department_textfield.getText(), bank_textfield.getText(), iban_textfield.getText(),
                                        Utils.isMarried(String.valueOf(family_state_list.getSelectedItem())), year + "-" + (month + 1) + "-" + "01", "false", Utils.isDidakt(String.valueOf(prosopiko_list.getSelectedItem())), "1"});
                                System.err.println(values);
                                db_api.executeUpdate("INSERT INTO EMPLOYEES(name,address,phone,email,department,bankname,iban,married,startdate,issimv,isdidakt,isActive) VALUES " + values);
                                JOptionPane.showMessageDialog(null, "Επιτυχής πρόσληψη");
                            }
                        }
                    } else {
                        String values = Utils.constructValues(new String[]{(name_textfield.getText() + " " + surname_textfield.getText()), address_textfield.getText(), phone_textfield.getText(), email_textfield.getText(), department_textfield.getText(), bank_textfield.getText(), iban_textfield.getText(),
                                Utils.isMarried(String.valueOf(family_state_list.getSelectedItem())), year + "-" + (month + 1) + "-" + "01", "false", Utils.isDidakt(String.valueOf(prosopiko_list.getSelectedItem())), "1"});
                        System.err.println(values);
                        db_api.executeUpdate("INSERT INTO EMPLOYEES(name,address,phone,email,department,bankname,iban,married,startdate,issimv,isdidakt,isActive) VALUES " + values);
                        JOptionPane.showMessageDialog(null, "Επιτυχής πρόσληψη");
                    }
                }
            } catch (SQLException d) {
                d.printStackTrace();
            }
            String family;
            String search, library;
            String amount, category;
            double Fam = 0;
            double synolo;
            double BasicSalary = 0;


            if (Utils.isDidakt(String.valueOf(prosopiko_list.getSelectedItem())).equals("true")) {
                category = " μόνιμο μέλος του διδακτικού προσωπικού";
                search = String.valueOf(SearchEpidoma);
                library = String.valueOf(0);
                if (Utils.isMarried((family_state_list.getSelectedItem()).toString()).equals("true")) {
                    Fam = (FamillyEpidoma * BasicSalaryDidakt) / 100;
                    for (int k = 0; k < counter; k++) {
                        Fam = Fam + (FamillyEpidoma * BasicSalaryDidakt) / 100;
                    }

                }
                synolo = Fam + BasicSalaryDidakt + SearchEpidoma;
                family = String.valueOf(Fam);
                BasicSalary = BasicSalaryDidakt;
            } else {
                category = "Μόνιμος διοικητικός υπάλληλος";
                if (Utils.isMarried((family_state_list.getSelectedItem()).toString()).equals("true")) {
                    Fam = (FamillyEpidoma * BasicSalaryDoiik) / 100;

                    for (int k = 0; k < counter; k++) {
                        System.err.println(k);
                        Fam = Fam + (FamillyEpidoma * BasicSalaryDoiik) / 100;
                    }

                }

                family = String.valueOf(Fam);
                synolo = Fam + BasicSalaryDoiik;
                BasicSalary = BasicSalaryDoiik;
            }
            amount = String.valueOf(synolo);

            String values_2 = Utils.constructValues(new String[]{String.valueOf(BasicSalary), "11", category, amount, family, "0"});

            try {

                db_api.executeUpdate("INSERT INTO PAYDIAGRAM(salary,employeid,category, amount, family_allowance, yearsofservice) VALUES " + values_2);
                db_api.executeUpdate("UPDATE PAYDIAGRAM SET employeid=(SELECT COUNT(*) FROM EMPLOYEES) WHERE ID=(SELECT MAX(ID) FROM PAYDIAGRAM)");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        JTextArea results_textarea = new JTextArea("Αποτέλεσμα:");
        results_textarea.setFont(new Font("Arial", Font.PLAIN, 15));
        results_textarea.setEditable(false);
        results_textarea.setBackground(new Color(238, 238, 238));
        main_panel.add(results_textarea);

        // add to frame
        hire_frame.add(main_panel);

        hire_frame.setVisible(true);
    }

    private void createContractMenu() {
        JFrame contract_frame = new JFrame("Σύμβαση");
        contract_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("database-database-icon.png")));
        contract_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contract_frame.setSize(800, 500);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(3, 2));

        JPanel contract_panel = new JPanel();
        contract_panel.setLayout(new GridLayout(7, 2));

        JLabel name_label = new JLabel("Όνομα:");
        name_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField name_textfield = new JTextField();
        name_textfield.setFont(new Font("Arial", Font.PLAIN, 20));


        JLabel surname_label = new JLabel("Επώνυμο:");
        surname_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField surname_textfield = new JTextField();
        surname_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel address_label = new JLabel("Διεύθυνση:");
        address_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField address_textfield = new JTextField();
        address_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel phone_label = new JLabel("Τηλέφωνο:");
        phone_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField phone_textfield = new JTextField();
        phone_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel email_label = new JLabel("Email:");
        email_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField email_textfield = new JTextField();

        JLabel department_label = new JLabel("Τμήμα:");
        department_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField department_textfield = new JTextField();
        department_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel bank_label = new JLabel("Τράπεζα:");
        bank_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField bank_textfield = new JTextField();
        bank_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel prosopiko_label = new JLabel("Κατηγορίες Προσωπικού:");
        prosopiko_label.setFont(new Font("Arial", Font.BOLD, 20));
        JComboBox<String> prosopiko_list = new JComboBox<>(new String[]{"Διοικητικό προσωπικό", "Διδακτικό προσωπικό"});
        prosopiko_list.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel iban_label = new JLabel("Iban:");
        iban_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField iban_textfield = new JTextField();
        iban_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel salary_label = new JLabel("Μισθός:");
        salary_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField salary_textfield = new JTextField();
        salary_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel family_label = new JLabel("Οικογενειακή Κατάσταση:");
        family_label.setFont(new Font("Arial", Font.BOLD, 20));
        JComboBox<String> family_state_list = new JComboBox<>(new String[]{"Παντρεμένος", "Ανύπαντρος"});
        family_state_list.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel children_label = new JLabel("Αριθμός παιδιών:");
        children_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField children_textfield = new JTextField();
        children_textfield.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel expire_label = new JLabel("Ημερομηνία λήξης σύμβασης:");
        expire_label.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField expire_textfield = new JTextField();
        expire_textfield.setFont(new Font("Arial", Font.PLAIN, 20));
        ;

        // add to panel
        contract_panel.add(name_label);
        contract_panel.add(name_textfield);
        contract_panel.add(surname_label);
        contract_panel.add(surname_textfield);
        contract_panel.add(address_label);
        contract_panel.add(address_textfield);
        contract_panel.add(phone_label);
        contract_panel.add(phone_textfield);
        contract_panel.add(email_label);
        contract_panel.add(email_textfield);
        contract_panel.add(bank_label);
        contract_panel.add(bank_textfield);
        contract_panel.add(iban_label);
        contract_panel.add(iban_textfield);
        contract_panel.add(salary_label);
        contract_panel.add(salary_textfield);
        contract_panel.add(department_label);
        contract_panel.add(department_textfield);
        contract_panel.add(prosopiko_label);
        contract_panel.add(prosopiko_list);
        contract_panel.add(expire_label);
        contract_panel.add(expire_textfield);
        contract_panel.add(family_label);
        contract_panel.add(family_state_list);
        contract_panel.add(children_label);
        contract_panel.add(children_textfield);

        // add hire_panel to main_panel
        main_panel.add(contract_panel);

        JPanel button_panel = new JPanel();
        JButton hire_button = new JButton("Πρόσληψη");

        button_panel.add(Box.createHorizontalGlue());
        hire_button.setFont(new Font("Arial", Font.BOLD, 20));
        hire_button.addActionListener(e -> {
            int counter = 0;
            try {
                if (isInteger(children_textfield.getText())) {
                    if (Integer.parseInt(children_textfield.getText()) != 0) {
                        for (int k = 0; k < Integer.parseInt(children_textfield.getText()); k++) {
                            String v = JOptionPane.showInputDialog("Ηλικία παιδιού " + (k + 1) + " :");

                            if (v == null) {
                                break;
                            }

                            if (isInteger(v) && Integer.parseInt(v) >= 0) {
                                if (isInteger(v) && Integer.parseInt(v) < 18) {
                                    counter++;
                                }

                                db_api.executeUpdate("INSERT INTO children(`employeid`, `age`)  VALUES ((SELECT COUNT(*) FROM EMPLOYEES)+1, " + v + ")");

                            } else {
                                JOptionPane.showMessageDialog(null, "Λάθος ηλικία παιδιού");
                                k--;
                            }

                            if (k == Integer.parseInt(children_textfield.getText()) - 1) {

                                String values = Utils.constructValues(new String[]{(name_textfield.getText() + " " + surname_textfield.getText()), address_textfield.getText(), phone_textfield.getText(), email_textfield.getText(), department_textfield.getText(), bank_textfield.getText(), iban_textfield.getText(),
                                        Utils.isMarried(String.valueOf(family_state_list.getSelectedItem())), year + "-" + (month + 1) + "-" + "01", "true", Utils.isDidakt(String.valueOf(prosopiko_list.getSelectedItem())), "1"});
                                System.err.println(values);
                                db_api.executeUpdate("INSERT INTO EMPLOYEES(name,address,phone,email,department,bankname,iban,married,startdate,issimv,isdidakt,isActive) VALUES " + values);
                                JOptionPane.showMessageDialog(null, "Επιτυχής πρόσληψη");
                            }
                        }
                    } else {
                        String values = Utils.constructValues(new String[]{(name_textfield.getText() + " " + surname_textfield.getText()), address_textfield.getText(), phone_textfield.getText(), email_textfield.getText(), department_textfield.getText(), bank_textfield.getText(), iban_textfield.getText(),
                                Utils.isMarried(String.valueOf(family_state_list.getSelectedItem())), year + "-" + (month + 1) + "-" + "01", "true", Utils.isDidakt(String.valueOf(prosopiko_list.getSelectedItem())), "1"});
                        System.err.println(values);
                        db_api.executeUpdate("INSERT INTO EMPLOYEES(name,address,phone,email,department,bankname,iban,married,startdate,issimv,isdidakt,isActive) VALUES " + values);
                        JOptionPane.showMessageDialog(null, "Επιτυχής πρόσληψη");
                    }
                }

            } catch (SQLException d) {
                d.printStackTrace();
            }
            String family, search, library;
            String amount, category;
            double Fam = 0;
            double synolo;
            double Contract_salary;
            Contract_salary = Double.parseDouble(salary_textfield.getText());
            if (Utils.isDidakt(prosopiko_list.getSelectedItem().toString()).equals("true")) {
                category = " Συμβασιούχο μέλος του διδακτικού προσωπικού";

                library = String.valueOf(LibraryEpidoma);
                if (Utils.isMarried(family_state_list.getSelectedItem().toString()).equals("true")) {
                    Fam += (FamillyEpidoma * Contract_salary) / 100;
                    for (int k = 0; k < counter; k++) {
                        Fam += (FamillyEpidoma * Contract_salary) / 100;
                    }

                }
                synolo = Fam + Contract_salary + LibraryEpidoma;
                family = String.valueOf(Fam);
            } else {
                category = "Συμβασιούχος διοικητικός υπάλληλος";
                if (Utils.isMarried(family_state_list.getSelectedItem().toString()).equals("true")) {
                    Fam += (FamillyEpidoma * Contract_salary) / 100;
                    for (int k = 0; k < counter; k++) {
                        Fam += (FamillyEpidoma * Contract_salary) / 100;
                    }

                }
                family = String.valueOf(Fam);
                synolo = Fam + Contract_salary;
            }
            amount = String.valueOf(synolo);
            String values_2 = Utils.constructValues(new String[]{salary_textfield.getText(), "11", category, amount, family, "0"});
            try {
                db_api.executeUpdate("INSERT INTO PAYDIAGRAM(salary,employeid,category, amount, family_allowance, yearsofservice) VALUES " + values_2);
                db_api.executeUpdate("UPDATE PAYDIAGRAM SET employeid=(SELECT COUNT(*) FROM EMPLOYEES) WHERE ID=(SELECT MAX(ID) FROM PAYDIAGRAM)");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        button_panel.add(hire_button);
        main_panel.add(button_panel);

        JTextArea results_textarea = new JTextArea("Αποτέλεσμα:");
        results_textarea.setFont(new Font("Arial", Font.PLAIN, 15));
        results_textarea.setEditable(false);
        results_textarea.setBackground(new Color(238, 238, 238));
        main_panel.add(results_textarea);


        // add to frame
        contract_frame.add(main_panel);

        contract_frame.setVisible(true);
    }


    private void queryMenu() {
        AtomicReference<AtomicReference<ResultSet>> rs = null;
        JFrame query_frame = new JFrame("Ερωτήματα");
        query_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("database-database-icon.png")));
        query_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        query_frame.setSize(800, 600);

        JPanel hire_panel = new JPanel();
        hire_panel.setLayout(new GridLayout(10, 1));

        JLabel query_label = new JLabel("Εισάγετε το ερώτημα σας:");
        query_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextArea query_textarea = new JTextArea();
        query_textarea.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane query_scroll = new JScrollPane(query_textarea);
        query_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        query_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        JButton execute_button = new JButton("Εκτέλεση");


        JTextArea results_textarea = new JTextArea(10, 10);
        results_textarea.setLineWrap(true);
        results_textarea.setFont(new Font("Arial", Font.PLAIN, 20));
        results_textarea.setEditable(false);
        // add scroll to results_textarea
        JScrollPane result_scroll = new JScrollPane(results_textarea);
        result_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        result_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        // set color to match background
        results_textarea.setBackground(new Color(238, 238, 238));

        hire_panel.add(query_label);
        hire_panel.add(query_scroll);
        hire_panel.add(new JLabel());
        hire_panel.add(execute_button);
        hire_panel.add(new JLabel());
        hire_panel.add(result_scroll);

        execute_button.addActionListener(e -> {
            String raw_query = query_textarea.getText();
            try {
                boolean isResultSet = db_api.executeRaw(raw_query);

                if (isResultSet) {
                    ResultSet rs1 = db_api.getResultSet();
                    // print the results
                    results_textarea.setText("");
                    ResultSetMetaData rsmd = rs1.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    while (rs1.next()) {
                        for (int i = 1; i <= columnsNumber; i++) {
                            if (i > 1) results_textarea.append(",  ");
                            String columnValue = rs1.getString(i);
                            results_textarea.append(columnValue + " " + rsmd.getColumnName(i));
                        }
                        results_textarea.append("\n");
                    }
                } else {
                    results_textarea.setText("Επιτυχής εκτέλεση query!");
                }

            } catch (SQLException ex) {
                results_textarea.setText("Λανθασμένο query string!");
                ex.printStackTrace();
            }
        });

        query_frame.add(hire_panel);
        query_frame.setVisible(true);

    }

    private void changeInfoMenu() {
        JFrame changeinfo_frame = new JFrame("Αλλαγή Στοιχείων Υπαλλήλου");
        changeinfo_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("database-database-icon.png")));
        changeinfo_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changeinfo_frame.setSize(900, 900);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(2, 1));

        // now fetch the rest of the info and dispose of the id_panel
        JPanel changeinfo_panel = new JPanel();
        changeinfo_panel.setLayout(new GridLayout(17, 1));

        JPanel search_panel = new JPanel();
        search_panel.setLayout(new GridLayout(1, 3));
        JLabel search_label = new JLabel("ID Υπαλλήλου:");
        search_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField search_textfield = new JTextField();
        search_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton search_button = new JButton("Αναζήτηση");
        search_button.setFont(new Font("Arial", Font.BOLD, 20));
        // make button smaller
        search_button.setPreferredSize(new Dimension(150, 30));


        JLabel name_label = new JLabel("Όνομα:");
        name_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField name_textfield = new JTextField();
        name_textfield.setFont(new Font("Arial", Font.PLAIN, 20));


        JLabel address_label = new JLabel("Διεύθυνση:");
        address_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField address_textfield = new JTextField();
        address_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel phone_label = new JLabel("Τηλέφωνο:");
        phone_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField phone_textfield = new JTextField();
        phone_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel email_label = new JLabel("Email:");
        email_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField email_textfield = new JTextField();
        email_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel depart_label = new JLabel("Department:");
        email_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField depart_textfield = new JTextField();
        email_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel bank_label = new JLabel("Τράπεζα:");
        bank_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField bank_textfield = new JTextField();
        bank_textfield.setFont(new Font("Arial", Font.PLAIN, 20));


        JLabel iban_label = new JLabel("Iban:");
        bank_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField iban_textfield = new JTextField();
        bank_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel family_label_ = new JLabel("Οικογενειακή Κατάσταση:");
        family_label_.setFont(new Font("Arial", Font.BOLD, 20));
        JComboBox<String> family_state_list_ = new JComboBox<>(new String[]{"Παντρεμένος", "Ανύπαντρος"});
        family_state_list_.setFont(new Font("Arial", Font.PLAIN, 20));


        JLabel family_state_label = new JLabel("Προσθήκη έξτρα  Παιδιού:");
        family_state_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField family_state_textfield = new JTextField();
        family_state_textfield.setFont(new Font("Arial", Font.PLAIN, 20));


        // add to panel
        search_panel.add(search_label);
        search_panel.add(search_textfield);
        search_panel.add(search_button);

        changeinfo_panel.add(search_panel);
        changeinfo_panel.add(new JLabel());
        changeinfo_panel.add(name_label);
        changeinfo_panel.add(name_textfield);
        changeinfo_panel.add(address_label);
        changeinfo_panel.add(address_textfield);
        changeinfo_panel.add(phone_label);
        changeinfo_panel.add(phone_textfield);
        changeinfo_panel.add(email_label);
        changeinfo_panel.add(email_textfield);
        changeinfo_panel.add(depart_label);
        changeinfo_panel.add(depart_textfield);
        changeinfo_panel.add(bank_label);
        changeinfo_panel.add(bank_textfield);
        changeinfo_panel.add(iban_label);
        changeinfo_panel.add(iban_textfield);
        changeinfo_panel.add(family_state_label);
        changeinfo_panel.add(family_state_textfield);
        changeinfo_panel.add(family_label_);
        changeinfo_panel.add(family_state_list_);


        main_panel.add(changeinfo_panel);

        JPanel button_panel = new JPanel();
        JButton change_button = new JButton("Ενημέρωση");

        button_panel.add(Box.createHorizontalGlue());
        change_button.setFont(new Font("Arial", Font.BOLD, 20));
        button_panel.add(change_button);
        main_panel.add(button_panel);

        change_button.addActionListener(e -> {
            int counter = 0;
            try {
                db_api.executeUpdate("UPDATE EMPLOYEES SET name=" + "'" + name_textfield.getText() + "'" + ",address='" + address_textfield.getText() + "',phone='" + phone_textfield.getText() + "',email='" + email_textfield.getText() + "' ,department='" + depart_textfield.getText() + "',bankname='" + bank_textfield.getText() + "' ,iban='" + iban_textfield.getText() + "' WHERE id = " + search_textfield.getText());

                if (isInteger(family_state_textfield.getText())) {
                    if (Integer.parseInt(family_state_textfield.getText()) != 0) {
                        for (int k = 0; k < Integer.parseInt(family_state_textfield.getText()); k++) {
                            String v = JOptionPane.showInputDialog("Ηλικία παιδιού " + (k + 1) + " :");

                            if (v == null) {
                                break;
                            }

                            if (isInteger(v) && Integer.parseInt(v) >= 0) {
                                if (isInteger(v) && Integer.parseInt(v) < 18) {
                                    counter++;
                                }

                                db_api.executeUpdate("INSERT INTO children(`employeid`, `age`)  VALUES (" + search_textfield.getText() + ", " + v + ")");

                            } else {
                                JOptionPane.showMessageDialog(null, "Λάθος ηλικία παιδιού");
                                k--;
                            }
                        }
                    }
                }
               /* if (Utils.isMarried(family_state_list_.getSelectedItem().toString()).equals("true")) {
                    Fam += (FamillyEpidoma * Contract_salary) / 100;
                    for (int k = 0; k < counter; k++) {
                        Fam += (FamillyEpidoma * Contract_salary) / 100;
                    }

                }*/

                JOptionPane.showMessageDialog(null, "Επιτυχής ενημέρωση");
            } catch (SQLException d) {
                d.printStackTrace();
            }

        });
        search_button.addActionListener(e -> {
            family_state_textfield.setText("0");
            ArrayList<String> output = new ArrayList<>();
            try {
                ResultSet resultSet = db_api.executeQuery("SELECT name,address,phone,iban,bankname,email,department,married FROM EMPLOYEES WHERE ID=" + search_textfield.getText());
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                while (resultSet.next()) {
                    for (int j = 1; j <= columnsNumber; j++) {
                        String columnValue = resultSet.getString(j);
                        output.add(columnValue);
                    }
                }

                name_textfield.setText(output.get(0));
                address_textfield.setText(output.get(1));
                phone_textfield.setText(output.get(2));
                iban_textfield.setText(output.get(3));
                bank_textfield.setText(output.get(4));
                email_textfield.setText(output.get(5));
                depart_textfield.setText(output.get(6));


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });

        // add to frame
        changeinfo_frame.add(main_panel);
        changeinfo_frame.setVisible(true);
    }

    private void changeSalaryMenu() {
        JFrame changeinfo_frame = new JFrame("Αλλαγή Μισθοδοσίας/Eπιδόματος Υπαλλήλου");
        changeinfo_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("database-database-icon.png")));
        changeinfo_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changeinfo_frame.setSize(600, 600);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(2, 1));

        // now fetch the rest of the info and dispose of the id_panel
        JPanel changeinfo_panel = new JPanel();
        changeinfo_panel.setLayout(new GridLayout(10, 1));

        JPanel search_panel = new JPanel();
        search_panel.setLayout(new GridLayout(1, 3));


        JLabel basic_label = new JLabel("Βασικός Μισθός:");
        basic_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField basic_textfield = new JTextField();
        basic_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel epidoma_label = new JLabel("Επίδομα ανα άτομο οικογενείας σε ποσοστό:");
        epidoma_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField epidoma_textfield = new JTextField();
        epidoma_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel search_label = new JLabel("Επίδομα έρευνας:");
        search_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField search_textfield = new JTextField();
        search_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel library_label = new JLabel("Eπίδομα βιβλιοθήκης:");
        library_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField library_textfield = new JTextField();
        library_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel prosopiko_label = new JLabel("Κατηγορίες Προσωπικού:");
        prosopiko_label.setFont(new Font("Arial", Font.BOLD, 20));
        JComboBox<String> prosopiko_list = new JComboBox<>(new String[]{"Διοικητικό προσωπικό", "Διδακτικό προσωπικό"});
        prosopiko_list.setFont(new Font("Arial", Font.PLAIN, 20));

        // add


        changeinfo_panel.add(search_panel);

        changeinfo_panel.add(new JLabel());
        changeinfo_panel.add(prosopiko_label);
        changeinfo_panel.add(prosopiko_list);
        changeinfo_panel.add(basic_label);
        changeinfo_panel.add(basic_textfield);

        changeinfo_panel.add(epidoma_label);
        changeinfo_panel.add(epidoma_textfield);
        changeinfo_panel.add(search_label);
        changeinfo_panel.add(search_textfield);
        changeinfo_panel.add(library_label);
        changeinfo_panel.add(library_textfield);


        main_panel.add(changeinfo_panel);

        JPanel button_panel = new JPanel();
        JButton hire_button = new JButton("Ενημέρωση");

        button_panel.add(Box.createHorizontalGlue());
        hire_button.setFont(new Font("Arial", Font.BOLD, 20));
        button_panel.add(hire_button);
        main_panel.add(button_panel);
        hire_button.addActionListener(e -> {

            //Kanoume update to backend
            if (!library_textfield.getText().equals("")) {
                if (Double.parseDouble(library_textfield.getText()) > LibraryEpidoma)
                    LibraryEpidoma = Double.parseDouble(library_textfield.getText());
                else System.err.println("Can't be decreased");
            }
            if (!search_textfield.getText().equals("")) {
                if (Double.parseDouble(search_textfield.getText()) > SearchEpidoma)
                    SearchEpidoma = Double.parseDouble(search_textfield.getText());
                else System.err.println("Can't be decreased");
            }
            if (!epidoma_textfield.getText().equals("")) {
                if (Double.parseDouble(epidoma_textfield.getText()) > FamillyEpidoma)
                    FamillyEpidoma = Double.parseDouble(epidoma_textfield.getText());
                else System.err.println("Can't be decreased");
            }

            if (!basic_textfield.getText().equals("")) {

                if (Utils.isDidakt(String.valueOf(prosopiko_list.getSelectedItem())).equals("true")) {
                    if (Double.parseDouble(basic_textfield.getText()) > BasicSalaryDidakt)
                        BasicSalaryDidakt = Double.parseDouble(basic_textfield.getText());
                    else System.err.println("Can't be decreased");
                } else {
                    if (Double.parseDouble(basic_textfield.getText()) > BasicSalaryDoiik)
                        BasicSalaryDoiik = Double.parseDouble(basic_textfield.getText());
                    else System.err.println("Can't be decreased");
                }

            }

            if (Utils.isDidakt(String.valueOf(prosopiko_list.getSelectedItem())).equals("true")) { //Gia allages sto general data

                try {
                    db_api.executeUpdate("UPDATE `general_data` SET `mon_didakt` = '" + BasicSalaryDidakt + "', `family_allowance_per` = '" + FamillyEpidoma + "', " +
                            "`search_allowance` = '" + SearchEpidoma + "', `library_allowance` = '" + LibraryEpidoma + "' WHERE `general_data`.`id` = 1");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            } else {

                try {
                    db_api.executeUpdate("UPDATE `general_data` SET `mon_doiik` = '" + BasicSalaryDoiik + "', `family_allowance_per` = '" + FamillyEpidoma + "', " +
                            "`search_allowance` = '" + SearchEpidoma + "', `library_allowance` = '" + LibraryEpidoma + "' WHERE `general_data`.`id` = 1");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

        });

        // add to frame
        changeinfo_frame.add(main_panel);
        changeinfo_frame.setVisible(true);
    }

    private void fireOrRetireMenu() {
        JFrame fire_frame = new JFrame("Απόλυση/Συνταξιοδότηση Υπαλλήλου");
        fire_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("database-database-icon.png")));
        fire_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fire_frame.setSize(600, 600);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(2, 1));

        // now fetch the rest of the info and dispose of the id_panel
        JPanel fire_panel = new JPanel();
        fire_panel.setLayout(new GridLayout(10, 1));

        JPanel search_panel = new JPanel();
        search_panel.setLayout(new GridLayout(1, 3));
        JLabel search_label = new JLabel("ID Υπαλλήλου:");
        search_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField search_textfield = new JTextField();
        search_textfield.setFont(new Font("Arial", Font.PLAIN, 20));

        // display name and last name of employee
        JLabel name_label = new JLabel("Όνοματεπώνυμο:");
        name_label.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField name_textfield = new JTextField();
        name_textfield.setFont(new Font("Arial", Font.PLAIN, 20));
        name_textfield.setEditable(false);


        // add to panel
        search_panel.add(search_label);
        search_panel.add(search_textfield);
        search_panel.add(new JLabel());

        fire_panel.add(search_panel);
        fire_panel.add(name_label);
        fire_panel.add(name_textfield);


        main_panel.add(fire_panel);

        JPanel button_panel = new JPanel();
        button_panel.setLayout(new FlowLayout());
        JButton fire_button = new JButton("Απόλυση/Συνταξιοδότηση");
        JButton search_button = new JButton("Αναζήτηση");
        search_button.addActionListener(e -> {
            StringBuilder output = new StringBuilder();
            try {
                ResultSet resultSet = db_api.executeQuery("SELECT name FROM EMPLOYEES WHERE ID=" + search_textfield.getText());
                while (resultSet.next()) {
                    output.append(resultSet.getString("name"));
                }
                name_textfield.setText(output.toString());


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        fire_button.addActionListener(e -> {
            try {
                db_api.executeUpdate("UPDATE EMPLOYEES SET `isActive` = 0 WHERE id=" + search_textfield.getText());
                JOptionPane.showMessageDialog(null, "Επιτυχής απόλυση/συνταξιοδότηση");
            } catch (SQLException ignored) {

            }
        });
        search_button.setFont(new Font("Arial", Font.BOLD, 20));
        button_panel.add(search_button);
        fire_button.setFont(new Font("Arial", Font.BOLD, 20));
        button_panel.add(fire_button);


        main_panel.add(button_panel);

        // add to frame
        fire_frame.add(main_panel);

        fire_frame.setVisible(true);
    }

    private void payMenu() {
        JFrame pay_frame = new JFrame("Καταβολή Μισθού");
        pay_frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("database-database-icon.png")));
        pay_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pay_frame.setSize(400, 200);

        JLabel Minima = new JLabel("Eπιτυχής Μισθοδωσία " + Date);
        Minima.setFont(new Font("Arial", Font.BOLD, 20));

        if (month == 12) {
            year++;
            month = 1;
        } else {
            month++;
        }
        if (month < 10) {
            if (month % 2 == 0) {
                if (month == 2) {
                    Date = year + "-" + "0" + month + "-28";
                } else {
                    Date = year + "-" + "0" + month + "-30";
                }
            } else {
                Date = year + "-" + "0" + month + "-31";
            }

        } else {
            if (month % 2 == 0) {
                Date = year + "-" + month + "-30";
            } else {
                Date = year + "-" + month + "-31";
            }
        }

        String query = "SELECT * FROM EMPLOYEES WHERE isActive=true";

        try {
            ResultSet res = db_api.executeQuery(query);

            while (res.next()) {
                boolean didRaise = Utils.checkRaise(res.getInt("id"), Date,  res.getBoolean("isdidakt") ? BasicSalaryDidakt : BasicSalaryDoiik);

                System.err.println("didRaise = " + didRaise);

                if (didRaise) {
                    JOptionPane.showMessageDialog(null, "Aύξηση μισθού για τον υπάλληλο με ID: " + res.getInt("id"));
                }

                // get the current salary of that employee
                String salary_query = "SELECT amount FROM salarydata WHERE employeid=" + res.getInt("id");
                ResultSet salary_res = db_api.executeQuery(salary_query);
                salary_res.next();

                double amount = res.getBoolean("isdidakt") ? BasicSalaryDidakt : BasicSalaryDoiik;

                if (salary_res.isBeforeFirst()) {
                    amount = salary_res.getDouble("amount");
                }

                // insert the new salary
                String insert_query = "INSERT INTO salarydata (employeid, amount, date) VALUES (" + res.getInt("id") + ", " + amount + ", '" + Date + "')";
                db_api.executeUpdate(insert_query);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        pay_frame.add(Minima);
        pay_frame.setVisible(true);
    }


    private void createMainMenu() {
        JButton hire_button = new JButton("Πρόσληψη");
        JButton contract_button = new JButton("Σύμβαση");
        JButton changeinfo_button = new JButton("Αλλαγή Στοιχείων Υπαλλήλου");
        JButton changesalary_button = new JButton("Μεταβολή Βασικού Μισθού Υπαλλήλου");
        JButton fire_button = new JButton("Απόλυση/Συνταξιοδότηση Υπαλλήλου");
        JButton pay_button = new JButton("Καταβολή Μισθού");
        JButton query_button = new JButton("Ερωτήματα");
        JButton runfive_queries_button = new JButton("Εκτέλεση 5 Ερωτημάτων Εκφώνησης");
        JButton exit_button = new JButton("Έξοδος");

        JLabel title = new JLabel("Σύστημα Διαχείρισης Βάσης Δεδομένων Προσωπικού", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        query_button.addActionListener(e -> queryMenu());
        hire_button.addActionListener(e -> createHireMenu());
        contract_button.addActionListener(e -> createContractMenu());
        changeinfo_button.addActionListener(e -> changeInfoMenu());
        changesalary_button.addActionListener(e -> changeSalaryMenu());
        fire_button.addActionListener(e -> fireOrRetireMenu());
        pay_button.addActionListener(e -> payMenu());
        exit_button.addActionListener(e -> System.exit(0));

        try {
            // set general Data
            db_api.executeUpdate("INSERT INTO `general_data` (`id`, `mon_didakt`, `mon_doiik`, `family_allowance_per`, `search_allowance`, " +
                    "`library_allowance`) VALUES ('1', '600', '600', '5', '200', '100');");
        }catch(SQLException ex){
            System.out.println("");
        }

        runfive_queries_button.addActionListener(e -> {
            try {

                // Doiikitikos monimos ypalillos agamos xwris paidia
                db_api.executeUpdate("INSERT INTO `employees` (id,name,phone,address,department,bankname,startdate, married,email,iban,issimv,isdidakt,isActive) VALUES " +
                        "('1','Φίλιππος Παπαδάκης','6942345692','Μοίρες','CSD','ETHNA','"+Date+"',true,'csd4453@csd.uoc.gr','GR34356343422',FALSE,FALSE,TRUE);");

                db_api.executeUpdate("INSERT INTO `paydiagram`(id,salary,employeid,category,amount,family_allowance,yearsofservice)VALUES " +
                        " ('1','600','1','Mόνιμος διοικητικός υπάλληλος','600','0','2');");
            }catch (SQLException ex) {
            }

            try {
                //Didaktikos Symvasioyxos agamos xwris paidia
                db_api.executeUpdate("INSERT INTO EMPLOYEES(id,name,phone,address,department,bankname,startdate,married,email,iban,issimv,isdidakt,isActive) " +
                        "VALUES('2','Ευθύμης Μητκούσης','6942345692','Χανιά','CSD','PEIREUS','"+Date+"',false,'csd4493@csd.uoc.gr','GR34350002322',TRUE,TRUE,TRUE);");
                db_api.executeUpdate("INSERT INTO PAYDIAGRAM(id,salary,employeid,category,amount,family_allowance,yearsofservice)VALUES" +
                        "('2','1000','2','Συμβασιούχος διδακτικός υπάλληλος','1100','0','3');");
            }catch (SQLException ex) {
                ex.printStackTrace();
            }


            try{
                //Monimos didaktikos ypaloilos pantremenos me 1 paidi
                db_api.executeUpdate("INSERT INTO EMPLOYEES(id,name,phone,address,department,bankname,startdate,married,email,iban,issimv,isdidakt,isActive)" +
                        " VALUES('3','Ευγενια Κουναλακη','6943013200','Θερισσος','CSD','PANCRETA','"+Date+"',true,'csd4365@csd.uoc.gr','GR252523523523525',false,true,true);");
                db_api.executeUpdate("INSERT INTO CHILDREN(id,employeid,age)VALUES(1,3,10)");
                db_api.executeUpdate("INSERT INTO PAYDIAGRAM(id,salary,employeid,category,amount,family_allowance,yearsofservice)VALUES" +
                        "('3','600','3','Μόνιμος διδακτικός υπάλληλος','860','60','1');");
            }catch (SQLException ex) {
                ex.printStackTrace();
            }

            try{
                //Symvasioyxos dioikitikos ypaloilos  pantremenos me 2 paidia to 1 enhliko
                db_api.executeUpdate("INSERT INTO EMPLOYEES(id,name,phone,address,department,bankname,startdate,married,email,iban,issimv,isdidakt,isActive) VALUES"+
                        "('4','Αλεξανδρος Αντωνακακης','6945532100','Χερσονησο','CSD','REVOLUT','"+Date+"',true,'csd4853@csd.uoc.gr','LT343243253',true,false,true);");
                db_api.executeUpdate("INSERT INTO CHILDREN(id,employeid,age)VALUES(2,4,10)");
                db_api.executeUpdate("INSERT INTO CHILDREN(id,employeid,age)VALUES(3,4,19)");
                db_api.executeUpdate("INSERT INTO PAYDIAGRAM(id,salary,employeid,category,amount,family_allowance,yearsofservice)VALUES('4','900','4','Συμβασιούχος διοικητικός υπάλληλος',990,90,2);");

            }catch (SQLException ex) {
                ex.printStackTrace();
            }


            try {
                //Monimos dioikitikos me 1 paidi agamos(ara den exei epidoma)
                db_api.executeUpdate("INSERT INTO EMPLOYEES(id,name,phone,address,department,bankname,startdate,married,email,iban,issimv,isdidakt,isActive) " +
                        "VALUES('5','Υφαντης Ευαγγελος','6945324302','Καμινια','CSD','CHANN','" + Date + "',false,'csd1000@csd.uoc.gr','LT40093571353',false,false,true);");
                db_api.executeUpdate("INSERT INTO CHILDREN(id,employeid,age)VALUES(4,5,1)");
                db_api.executeUpdate("INSERT INTO PAYDIAGRAM(id,salary,employeid,category,amount,family_allowance,yearsofservice)" +
                        "VALUES('5','600','5','Mόνιμος διοικητικός υπάλληλος','600','0','1');");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            runfive_queries_button.setEnabled(false);
            });

        main_panel.add(title);
        main_panel.add(contract_button);
        main_panel.add(changeinfo_button);
        main_panel.add(hire_button);
        main_panel.add(changesalary_button);
        main_panel.add(fire_button);
        main_panel.add(pay_button);
        main_panel.add(query_button);
        main_panel.add(runfive_queries_button);
        main_panel.add(exit_button);
    }
}