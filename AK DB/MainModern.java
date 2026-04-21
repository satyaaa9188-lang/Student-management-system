import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainModern {

    private static final String URL ="jdbc:mysql://localhost:3306/ak_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "MYSQL";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(MainModern::createUI);
    }

    private static void createUI() {
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(197, 125, 19));

        JLabel header = new JLabel("Student Management System", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.setBorder(new EmptyBorder(15, 10, 15, 10));
        mainPanel.add(header, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tabs.add("Insert", createInsertPanel());
        tabs.add("Update", createUpdatePanel());
        tabs.add("Delete", createDeletePanel());
        tabs.add("Search", createSearchPanel());
        tabs.add("Display", createDisplayPanel());

        mainPanel.add(tabs, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel stylePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    private static GridBagConstraints gbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private static JButton styledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(50, 248, 5));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return btn;
    }

    private static JTextField styledField() {
        JTextField field = new JTextField(15);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return field;
    }

    // INSERT PANEL
    private static JPanel createInsertPanel() {
        JPanel panel = stylePanel();

        JTextField id = styledField();
        JTextField name = styledField();
        JTextField age = styledField();
        JTextField address = styledField();
        JTextField mobile = styledField();
        JTextField email = styledField();
        JTextField branch = styledField();
        JTextField year = styledField();

        JButton insert = styledButton("Insert Data");

        panel.add(new JLabel("ID"), gbc(0, 0));
        panel.add(id, gbc(1, 0));

        panel.add(new JLabel("Name"), gbc(0, 1));
        panel.add(name, gbc(1, 1));

        panel.add(new JLabel("Age"), gbc(0, 2));
        panel.add(age, gbc(1, 2));

        panel.add(new JLabel("Address"), gbc(0, 3));
        panel.add(address, gbc(1, 3));

        panel.add(new JLabel("Mobile"), gbc(0, 4));
        panel.add(mobile, gbc(1, 4));

        panel.add(new JLabel("Email"), gbc(0, 5));
        panel.add(email, gbc(1, 5));

        panel.add(new JLabel("Branch"), gbc(0, 6));
        panel.add(branch, gbc(1, 6));

        panel.add(new JLabel("Year"), gbc(0, 7));
        panel.add(year, gbc(1, 7));

        panel.add(insert, gbc(1, 8));

        insert.addActionListener(e -> {
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

                String q = "INSERT INTO students_data(id,name,address,mobile_no,email_id,branch,year,age) VALUES(?,?,?,?,?,?,?,?)";

                PreparedStatement ps = con.prepareStatement(q);
                ps.setInt(1, Integer.parseInt(id.getText()));
                ps.setString(2, name.getText());
                ps.setString(3, address.getText());
                ps.setString(4, mobile.getText());
                ps.setString(5, email.getText());
                ps.setString(6, branch.getText());
                ps.setInt(7, Integer.parseInt(year.getText()));
                ps.setInt(8, Integer.parseInt(age.getText()));

                ps.executeUpdate();
                JOptionPane.showMessageDialog(panel, "Inserted Successfully");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
        });

        return panel;
    }

    // UPDATE PANEL
    private static JPanel createUpdatePanel() {
        JPanel panel = stylePanel();

        JTextField id = styledField();
        JTextField mobile = styledField();
        JTextField year = styledField();

        JButton update = styledButton("Update");

        panel.add(new JLabel("ID"), gbc(0, 0));
        panel.add(id, gbc(1, 0));

        panel.add(new JLabel("Mobile"), gbc(0, 1));
        panel.add(mobile, gbc(1, 1));

        panel.add(new JLabel("Year"), gbc(0, 2));
        panel.add(year, gbc(1, 2));

        panel.add(update, gbc(1, 3));

        update.addActionListener(e -> {
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String q = "UPDATE students_data SET mobile_no=?, year=? WHERE id=?";
                PreparedStatement ps = con.prepareStatement(q);
                ps.setString(1, mobile.getText());
                ps.setInt(2, Integer.parseInt(year.getText()));
                ps.setInt(3, Integer.parseInt(id.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(panel, "Updated Successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
        });

        return panel;
    }

    // DELETE PANEL
    private static JPanel createDeletePanel() {
        JPanel panel = stylePanel();

        JTextField id = styledField();
        JButton delete = styledButton("Delete");

        panel.add(new JLabel("ID"), gbc(0, 0));
        panel.add(id, gbc(1, 0));
        panel.add(delete, gbc(1, 1));

        delete.addActionListener(e -> {
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String q = "DELETE FROM students_data WHERE id=?";
                PreparedStatement ps = con.prepareStatement(q);
                ps.setInt(1, Integer.parseInt(id.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(panel, "Deleted Successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
        });

        return panel;
    }

    // SEARCH PANEL
    private static JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JTextField id = styledField();
        JButton search = styledButton("Search");

        JTextArea result = new JTextArea();
        result.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        result.setEditable(false);

        JPanel top = new JPanel();
        top.add(new JLabel("Enter ID:"));
        top.add(id);
        top.add(search);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(result), BorderLayout.CENTER);

        search.addActionListener(e -> {
            result.setText("");
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                String q = "SELECT * FROM students_data WHERE id=?";
                PreparedStatement ps = con.prepareStatement(q);
                ps.setInt(1, Integer.parseInt(id.getText()));
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    result.append("Name: " + rs.getString("name") + "\n");
                    result.append("Email: " + rs.getString("email_id") + "\n\n");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
        });

        return panel;
    }

    // DISPLAY PANEL
    private static JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] cols = {"ID", "Name", "Mobile No", "Branch"};
        JTable table = new JTable(new Object[0][cols.length], cols);

        JButton load = styledButton("Load Data");

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(load, BorderLayout.SOUTH);

        load.addActionListener(e -> {
            try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM students_data");

                table.setModel(new javax.swing.table.DefaultTableModel(cols, 0));
                var model = (javax.swing.table.DefaultTableModel) table.getModel();

                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("mobile_no"),
                            rs.getString("branch")
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
        });

        return panel;
    }

    public static String getURL() {
        return URL;
    }
}