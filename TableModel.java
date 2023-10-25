import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

class TableModel extends AbstractTableModel {
    // Membuat array untuk nama kolom
    private String[] columnNames = {"Nama", "Nomor HP", "Jenis Kelamin", "Alamat"};
    // Membuat arraylist untuk data
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    // Membuat method untuk mengambil jumlah kolom
    public int getColumnCount() {
        return columnNames.length;
    }

    // Membuat method untuk mengambil jumlah baris
    public int getRowCount() {
        return data.size();
    }

    // Membuat method untuk mengambil nama kolom
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Membuat method untuk mengambil nilai dari tabel
    public Object getValueAt(int row, int col) {
        List<String> rowItem = data.get(row);
        return rowItem.get(col);
    }

    // Membuat method untuk mengatur apakah cell dapat di edit atau tidak
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    // Membuat method untuk menambahkan data ke tabel
    public void add(ArrayList<String> value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Membuat method untuk menyimpan data ke txt
    public void saveToTxt() throws Exception {
        FileWriter saveText = new FileWriter("data.txt");
        int rowLength = getRowCount();
        int colLength = getColumnCount();

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if(j != 0) saveText.write(" - ");
                saveText.write(getValueAt(i, j).toString());
            }
            saveText.write("\n");
        }

        saveText.close();
    }
}
