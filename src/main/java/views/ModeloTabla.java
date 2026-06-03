package views;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {
    

    public ModeloTabla(String[] columnas, int filas) {
        super(columnas, filas);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


}
