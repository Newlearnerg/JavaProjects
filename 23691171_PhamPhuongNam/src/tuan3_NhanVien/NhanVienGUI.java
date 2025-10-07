package tuan3_NhanVien;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NhanVienGUI extends JFrame implements ActionListener {

    DanhSachNhanVien danhSach;
    private NhanVienFileHelper fileHelper;
    private static final String FILE_PATH = "nhanvien.csv";
	
    // Labels & Inputs
    JLabel lbl_id, lbl_ho, lbl_ten, lbl_tuoi, lbl_luong, lbl_phai, lbl_search;
    JTextField txt_id, txt_ho, txt_ten, txt_tuoi, txt_luong, txt_search;
    JRadioButton rad_nu;

    // Buttons
    JButton btn_search, btn_them, btn_xoa_trang, btn_xoa, btn_luu;

    // Table
    JTable table;
    DefaultTableModel tableModel;

    public NhanVienGUI() {
        super("THONG TIN NHAN VIEN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        danhSach = new DanhSachNhanVien();
        fileHelper = new NhanVienFileHelper(FILE_PATH);

        // ======= TOP (TITLE) =======
        JLabel lbl_title = new JLabel("THÔNG TIN NHÂN VIÊN", JLabel.CENTER);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_title.setForeground(Color.blue);
        add(lbl_title, BorderLayout.NORTH);

        // ======= FORM (NORTH) =======
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        lbl_id = new JLabel("Mã nhân viên", JLabel.LEFT);
        lbl_ho = new JLabel("Họ", JLabel.LEFT);
        lbl_ten = new JLabel("Tên nhân viên", JLabel.LEFT);
        lbl_tuoi = new JLabel("Tuổi", JLabel.LEFT);
        lbl_luong = new JLabel("Tiền lương", JLabel.LEFT);
        lbl_phai = new JLabel("Phái", JLabel.LEFT);

        txt_id = new JTextField(); 
        txt_ho = new JTextField();
        txt_ten = new JTextField();
        txt_tuoi = new JTextField();
        txt_luong = new JTextField();

        rad_nu = new JRadioButton("Nữ");

        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        row1.add(lbl_id);
        row1.add(Box.createHorizontalStrut(50));
        row1.add(txt_id);
        
        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));
        row2.add(lbl_ho);
        row2.add(Box.createHorizontalStrut(109));
        row2.add(txt_ho);
        row2.add(lbl_ten);
        row2.add(txt_ten);
        
        JPanel row3 = new JPanel();
        row3.setLayout(new BoxLayout(row3, BoxLayout.X_AXIS));
        row3.add(lbl_tuoi);
        row3.add(Box.createHorizontalStrut(100));
        row3.add(txt_tuoi);
        row3.add(lbl_phai);
        row3.add(Box.createHorizontalStrut(50));
        row3.add(rad_nu);
        
        JPanel row4 = new JPanel();
        row4.setLayout(new BoxLayout(row4, BoxLayout.X_AXIS));
        row4.add(lbl_luong);
        row4.add(Box.createHorizontalStrut(60));
        row4.add(txt_luong);
        
        formPanel.add(row1);
        formPanel.add(row2);
        formPanel.add(row3);
        formPanel.add(row4);
        
        centerPanel.add(formPanel);
        centerPanel.add(Box.createVerticalStrut(15));
                // ======= TABLE (CENTER) =======
        String[] columns = {"Mã NV", "Họ", "Tên", "Phái", "Tuổi", "Tiền lương"};
        tableModel = new DefaultTableModel(columns, 0); // tạo model TRƯỚC
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        centerPanel.add(scrollPane);
        add(centerPanel, BorderLayout.CENTER);

        // ======= BOTTOM (SOUTH) =======
        JSplitPane bottomPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        // Search
        JPanel searchPanel = new JPanel();
        lbl_search = new JLabel("Nhập mã số cần tìm:");
        txt_search = new JTextField(12);
        btn_search = new JButton("Tìm");
        searchPanel.add(lbl_search);
        searchPanel.add(txt_search);
        searchPanel.add(btn_search);

        // Functions
        JPanel functionsPanel = new JPanel();
        btn_them = new JButton("Thêm");
        btn_xoa_trang = new JButton("Xóa trắng");
        btn_xoa = new JButton("Xóa");
        btn_luu = new JButton("Lưu");
        functionsPanel.add(btn_them);
        functionsPanel.add(btn_luu);
        functionsPanel.add(btn_xoa);
        functionsPanel.add(btn_xoa_trang);

        bottomPanel.add(searchPanel);
        bottomPanel.add(functionsPanel);
        add(bottomPanel, BorderLayout.SOUTH);

        // ======= LISTENERS =======
        txt_id.addActionListener(this);
        txt_ho.addActionListener(this);
        txt_ten.addActionListener(this);
        txt_tuoi.addActionListener(this);
        txt_luong.addActionListener(this);
        rad_nu.addActionListener(this);
        txt_search.addActionListener(this);
        btn_search.addActionListener(this);
        btn_them.addActionListener(this);
        btn_xoa_trang.addActionListener(this);
        btn_xoa.addActionListener(this);
        btn_luu.addActionListener(this);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int r = table.getSelectedRow();
                    if (r >= 0) {
                        txt_id.setText(s(tableModel.getValueAt(r, 0)));
                        txt_ho.setText(s(tableModel.getValueAt(r, 1)));
                        txt_ten.setText(s(tableModel.getValueAt(r, 2)));
                        String phai = s(tableModel.getValueAt(r, 3));
                        rad_nu.setSelected("Nữ".equalsIgnoreCase(phai) || "Nu".equalsIgnoreCase(phai));
                        txt_tuoi.setText(s(tableModel.getValueAt(r, 4)));
                        txt_luong.setText(s(tableModel.getValueAt(r, 5)));
                    }
                }
            }
        });

        for (NhanVien nv : fileHelper.loadFromFile()) {
            danhSach.ThemNhanVien(nv);
        }
        refreshTableFromList();
        
        // ======= FRAME SIZE =======
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void refreshTableFromList() {
        tableModel.setRowCount(0);
        for (NhanVien nv : danhSach.getAll()) {
            tableModel.addRow(new Object[]{
                nv.getId(),
                nv.getHo(),
                nv.getTen(),
                nv.getPhai(),
                nv.getTuoi(),
                nv.getLuong()
            });
        }
    }
    
    private static String s(Object o) { return o == null ? "" : o.toString(); }

    // Lấy giới tính từ radio
    private String getPhai() {
        return rad_nu.isSelected() ? "Nữ" : "Nam";
    }

    private Integer getSelectedId() {
        int r = table.getSelectedRow();
        if (r < 0) return null;
        Object v = tableModel.getValueAt(r, 0);
        if (v == null) return null;
        try {
            return Integer.parseInt(v.toString().trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    // Validate form
    private boolean validateForm(boolean showDialog) {
        String id = txt_id.getText().trim();
        String ho = txt_ho.getText().trim();
        String ten = txt_ten.getText().trim();
        String tuoiStr = txt_tuoi.getText().trim();
        String luongStr = txt_luong.getText().trim();
        String phai = getPhai();

        if (id.isEmpty() || ho.isEmpty() || ten.isEmpty() || tuoiStr.isEmpty() || luongStr.isEmpty() || phai.isEmpty()) {
            if (showDialog)
                JOptionPane.showMessageDialog(this, "Thông tin không được để trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            int tuoi = Integer.parseInt(tuoiStr);
            if (tuoi <= 0 || tuoi > 120) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            if (showDialog)
                JOptionPane.showMessageDialog(this, "Tuổi phải là số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Double.parseDouble(luongStr);
        } catch (NumberFormatException ex) {
            if (showDialog)
                JOptionPane.showMessageDialog(this, "Tiền lương phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    // Kiểm tra ID trùng
    private int findRowById(String id) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (id.equalsIgnoreCase(s(tableModel.getValueAt(i, 0)))) return i;
        }
        return -1;
    }

    // Xóa trắng form
    private void clearForm() {
        txt_id.setText("");
        txt_ho.setText("");
        txt_ten.setText("");
        txt_tuoi.setText("");
        txt_luong.setText("");
        table.clearSelection();
        txt_id.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
        Object source = e.getSource();
        boolean existing;

        if (source == btn_them) {
            if (!validateForm(true)) return;

            String ho = txt_ho.getText().trim();
            String ten = txt_ten.getText().trim();
            String phai = getPhai();
            String tuoi = txt_tuoi.getText().trim();
            String luong = txt_luong.getText().trim();
            String id = txt_id.getText().trim();

            NhanVien temp = new NhanVien(Integer.parseInt(id), Integer.parseInt(tuoi), ho, ten, phai, Double.parseDouble(luong));
             existing = danhSach.KiemTraTonTaiNhanVien(temp);
            if (existing) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Thêm vào bảng
            tableModel.addRow(new Object[]{
                    id,
                    ho,
                    ten,
                    phai,
                    tuoi,
                    luong,
            });

            //them nhan vien vao trong list
            danhSach.ThemNhanVien(temp);

            JOptionPane.showMessageDialog(this, "Đã thêm nhân viên.");
            clearForm();
        }
        else if (source == btn_luu) {
            fileHelper.saveToFile(danhSach.getAll());
            JOptionPane.showMessageDialog(this, "Đã lưu dữ liệu vào file.");
        }
        else if (source == btn_xoa) {
            Integer id = getSelectedId();
            if (id == null) {
                JOptionPane.showMessageDialog(this, "Hãy chọn một dòng để xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(
                this, "Xóa nhân viên có mã " + id + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // 1) Xóa trong list
                boolean ok = danhSach.XoaNhanVienTheoId(id);
                if (!ok) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy trong danh sách!");
                    return;
                }
                // 2) Xóa trong bảng (dựa selection hiện tại)
                int r = table.getSelectedRow();
                if (r >= 0) tableModel.removeRow(r);

                // (tuỳ chọn) lưu ngay xuống file:
                // fileHelper.saveToFile(danhSach.getAll());

                clearForm();
            }
        }

        else if (source == btn_xoa_trang) {
            clearForm();
        }
        else if (source == btn_search) {
            String idFind = txt_search.getText().trim();
            if (idFind.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nhập mã cần tìm!");
                return;
            }
            int r = findRowById(idFind);
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã: " + idFind);
            } else {
                table.setRowSelectionInterval(r, r);
                table.scrollRectToVisible(table.getCellRect(r, 0, true));
            }
        }
    }

}

