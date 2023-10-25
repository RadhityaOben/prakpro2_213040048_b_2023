import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class BiodataTable extends JFrame {
    
    public BiodataTable() {
        // Membuat ketika klik close maka akan berhenti
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(
                    BiodataTable.this, 
                    "Apakah anda yakin ingin keluar?", 
                    "Exit", JOptionPane.YES_NO_OPTION
                ) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        // Membuat label dan textfield untuk nama
        JLabel labelInputNama = new JLabel("Nama:");
        labelInputNama.setBounds(15, 40, 350, 10);

        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        // Membuat label dan textfield untuk no hp
        JLabel labelInputNoHP = new JLabel("No HP:");
        labelInputNoHP.setBounds(15, 100, 350, 10);

        JTextField textFieldNoHP = new JTextField();
        textFieldNoHP.setBounds(15, 120, 350, 30);

        // Membuat label dan textfield untuk jenis kelamin
        JLabel labelRadio = new JLabel("Jenis Kelamin");
        labelRadio.setBounds(15, 160, 350, 10);

        JRadioButton radioButton1 = new JRadioButton("Laki-laki");
        radioButton1.setBounds(15, 180, 350, 20);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 210, 350, 20);

        // Membuat button group untuk jenis kelamin
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        // Membuat label dan textfield untuk Alamat
        JLabel labelAlamat = new JLabel("Alamat:");
        labelAlamat.setBounds(15, 240, 350, 10);

        JTextArea textAreaAlamat = new JTextArea();
        textAreaAlamat.setBounds(15, 260, 350, 90);

        // Membuat button untuk menyimpan data
        JButton buttonSave = new JButton("Simpan");
        buttonSave.setBounds(15, 360, 100, 40);

        JButton buttonTxt = new JButton("Simpan ke Txt");
        buttonTxt.setBounds(120, 360, 150, 40);

        // Membuat tabel
        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 410, 500, 320);

        // Membuat model untuk tabel
        TableModel tableModel = new TableModel();
        table.setModel(tableModel);

        // Membuat action listener untuk button save ke txt
        buttonTxt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int confirmStatus = JOptionPane.showConfirmDialog(BiodataTable.this, "Apakah kamu yakin ingin menyimpan data ini menjadi file text?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(confirmStatus != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(BiodataTable.this, "Proses Dibatalkan!", "Cancel", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                try {
                    tableModel.saveToTxt();
                    JOptionPane.showMessageDialog(BiodataTable.this, "Proses Berhasil!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(BiodataTable.this, "Proses Gagal!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Membuat action listener untuk button save
        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mengecek apakah nama sudah diisi atau belum
                if(textFieldNama.getText().equals("")) {
                    JOptionPane.showMessageDialog(BiodataTable.this, "Tolong isi nama!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Mengecek apakah no hp sudah diisi atau belum
                if(textFieldNoHP.getText().equals("")) {
                    JOptionPane.showMessageDialog(BiodataTable.this, "Tolong isi nomor hp!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Mengatur kondisi untuk jenis kelamin
                String jk = "";
                if(radioButton1.isSelected()) {
                    jk = radioButton1.getText();
                }
                else if(radioButton2.isSelected()) {
                    jk = radioButton2.getText();
                }
                else {
                    JOptionPane.showMessageDialog(BiodataTable.this, "Tolong pilih jenis kelamin!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Mengecek apakah alamat sudah diisi atau belum
                if(textAreaAlamat.getText().equals("")) {
                    JOptionPane.showMessageDialog(BiodataTable.this, "Tolong isi alamat!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Mengatur konfirmasi sebelum menyimpan data
                int confirmStatus = JOptionPane.showConfirmDialog(BiodataTable.this, "Apakah kamu yakin ingin menyimpan data ini?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if(confirmStatus == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(BiodataTable.this, "Proses Dibatalkan!", "Cancel", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Mengambil data dari textfield
                String nama = textFieldNama.getText();
                String noHP = textFieldNoHP.getText();
                String alamat = textAreaAlamat.getText();
            
                // Menambahkan data ke tabel
                tableModel.add(new ArrayList<>(Arrays.asList(nama, noHP, jk, alamat)));

                // Mereset komponen yang sudah diisi
                textFieldNama.setText("");
                textFieldNoHP.setText("");
                bg.clearSelection();
                textAreaAlamat.setText("");

                // Menampilkan pesan berhasil
                JOptionPane.showMessageDialog(BiodataTable.this, "Proses Berhasil!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Menambahkan komponen ke frame
        this.add(buttonSave);
        this.add(buttonTxt);
        this.add(labelInputNama);
        this.add(textFieldNama);
        this.add(labelInputNoHP);
        this.add(textFieldNoHP);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelAlamat);
        this.add(textAreaAlamat);
        this.add(scrollableTable);

        // Mengatur frame
        this.setSize(550, 800);
        this.setLayout(null);
        
    }

    // Main method
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BiodataTable h = new BiodataTable();
                h.setVisible(true);
            }
        });
    }
}
