package tuan4;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SachGUI extends JFrame implements ActionListener{
	JTextField txt_id, txt_tuaSach, txt_namXuatBan, txt_soTrang, txt_tacGia, txt_nhaXuatBan, txt_donGia, txt_isbn, txt_timSach;
	JButton btn_them, btn_xoaRong, btn_xoa, btn_sua, btn_luu;
	TableModel tableModel;
	JTable table;
	
	public SachGUI() {
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setBorder(BorderFactory.createTitledBorder("Records Editior"));
		JLabel lbl_id, lbl_tuaSach, lbl_namXuatBan, lbl_soTrang, lbl_tacGia, lbl_nhaXuatBan, lbl_donGia, lbl_isbn, lbl_timSach;
		
		JPanel row1 = new JPanel();
		row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
		lbl_id = new JLabel("Mã sách", JLabel.LEFT);
		txt_id = new JTextField();
		txt_id.setMaximumSize(new Dimension(250, 20));
		row1.add(lbl_id);
		row1.add(Box.createHorizontalStrut(30));
		row1.add(txt_id);
		row1.add(Box.createHorizontalStrut(410));
		
		JPanel row2 = new JPanel();
		row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));
		lbl_tuaSach = new JLabel("Tựa sách", JLabel.LEFT);
		txt_tuaSach = new JTextField();
		lbl_tacGia = new JLabel("Tác giả", JLabel.LEFT);
		txt_tacGia = new JTextField();
		txt_tuaSach.setMaximumSize(new Dimension(250, 20));
		txt_tacGia.setMaximumSize(new Dimension(250, 20));
		row2.add(lbl_tuaSach);
		row2.add(Box.createHorizontalStrut(30));
		row2.add(txt_tuaSach);
		row2.add(Box.createHorizontalStrut(100));
		row2.add(lbl_tacGia);
		row2.add(Box.createHorizontalStrut(30));
		row2.add(txt_tacGia);
		
		JPanel row3 = new JPanel();
		row3.setLayout(new BoxLayout(row3, BoxLayout.X_AXIS));
		lbl_namXuatBan = new JLabel("Năm xuất bản", JLabel.LEFT);
		txt_namXuatBan = new JTextField();
		lbl_nhaXuatBan = new JLabel("Nhà xuất bản", JLabel.LEFT);
		txt_nhaXuatBan = new JTextField();
		txt_namXuatBan.setMaximumSize(new Dimension(250, 20));
		txt_nhaXuatBan.setMaximumSize(new Dimension(250, 20));
		row3.add(lbl_namXuatBan);
		row3.add(Box.createHorizontalStrut(7));
		row3.add(txt_namXuatBan);
		row3.add(Box.createHorizontalStrut(80));
		row3.add(lbl_nhaXuatBan);
		row3.add(Box.createHorizontalStrut(15));
		row3.add(txt_nhaXuatBan);
		
		JPanel row4 = new JPanel();
		row4.setLayout(new BoxLayout(row4, BoxLayout.X_AXIS));
		lbl_soTrang = new JLabel("Số trang", JLabel.LEFT);
		txt_soTrang = new JTextField();
		lbl_donGia = new JLabel("Đơn giá", JLabel.LEFT);
		txt_donGia = new JTextField();
		txt_soTrang.setMaximumSize(new Dimension(250, 20));
		txt_donGia.setMaximumSize(new Dimension(250, 20));
		row4.add(lbl_soTrang);
		row4.add(Box.createHorizontalStrut(35));
		row4.add(txt_soTrang);
		row4.add(Box.createHorizontalStrut(95));
		row4.add(lbl_donGia);
		row4.add(Box.createHorizontalStrut(30));
		row4.add(txt_donGia);
		
		JPanel row5 = new JPanel();
		row5.setLayout(new BoxLayout(row5, BoxLayout.X_AXIS));
		lbl_isbn = new JLabel("International Standard Book Number", JLabel.LEFT);
		txt_isbn = new JTextField();
		txt_isbn.setMaximumSize(new Dimension(100, 20));
		row5.add(lbl_isbn);
		row5.add(Box.createHorizontalStrut(30));
		row5.add(txt_isbn);
		row5.add(Box.createHorizontalStrut(425));
		
		inputPanel.add(row1);
		inputPanel.add(Box.createVerticalStrut(10));
		inputPanel.add(row2);
		inputPanel.add(Box.createVerticalStrut(10));
		inputPanel.add(row3);
		inputPanel.add(Box.createVerticalStrut(10));
		inputPanel.add(row4);
		inputPanel.add(Box.createVerticalStrut(10));
		inputPanel.add(row5);
		inputPanel.add(Box.createVerticalStrut(50));
		
		//===============BUTTONS================
		
		JPanel buttonsPanel = new JPanel();
		btn_them = new JButton("Thêm");
		btn_xoaRong = new JButton("Xóa rỗng");
		btn_xoa = new JButton("Xóa");
		btn_sua = new JButton("Sửa");
		btn_luu = new JButton("Lưu");
		lbl_timSach = new JLabel("Tìm theo mã sách: ");
		//txt_timSach dung JComboBox
		
		buttonsPanel.add(btn_them);btn_them.addActionListener(this);
		buttonsPanel.add(btn_xoaRong);btn_xoaRong.addActionListener(this);
		buttonsPanel.add(btn_xoa);btn_xoa.addActionListener(this);
		buttonsPanel.add(btn_sua);btn_sua.addActionListener(this);
		buttonsPanel.add(btn_luu);btn_luu.addActionListener(this);
		buttonsPanel.add(lbl_timSach);
		buttonsPanel.add(txt_timSach);txt_timSach.addActionListener(this);
		
		//======================TABLE=================================
		JScrollPane scroll;
		String[] headers = "MaSach;TuaSach;TacGia;NamXuatBan;NhaXuatBan;SoTrang;DonGia;ISBN".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(table = new JTable(tableModel),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách các cuốn sách"));
		table.setRowHeight(20);
		scroll.setPreferredSize(new Dimension(0, 300));
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				//fillForm(row); //method tao object va them vao list
			}
		});
		
		
		
		this.setSize(800, 600);
		this.setTitle("Quản lý sách");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.add(inputPanel, BorderLayout.NORTH);
		this.add(buttonsPanel, BorderLayout.CENTER);
		
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public Sach checkValidData(String maSach, String tuaSach, String tacGia, String namXuatBan, String nhaXuatBan, String soTrang, String donGia, String ISBN) {
		Sach sach = new Sach();
		sach = null;
		
		if(maSach.isEmpty() || maSach.length() == 0) {
			JOptionPane.showMessageDialog(btn_luu, "Mã sách không được rỗng!");
			return null;
		}else {
			if(!Pattern.compile("[A-Z]\\d{3}").matcher(maSach).find()) {
				JOptionPane.showMessageDialog(btn_luu, "Mã sách phải bắt đầu là ký tự HOA và theo sau là 3 chữ số");
				return null;
			}else {
				sach.setMaSach(maSach);
			}
		}
		
		if(tuaSach.isEmpty() || tuaSach.length() == 0) {
			JOptionPane.showMessageDialog(btn_luu, "Tựa sách không được rỗng!");
			return null;
		}else {
			if(!Pattern.compile("[A-Za-z-,() 0-9]").matcher(tuaSach).find()) {
				JOptionPane.showMessageDialog(btn_luu, "Tựa sách chỉ gồm số, chữ, -, (, ), .");
				return null;
			}else {
				sach.setTuaSach(tuaSach);
			}
		}
		
		if(tacGia.isEmpty() || tacGia.length() == 0) {
			JOptionPane.showMessageDialog(btn_luu, "Tựa sách không được rỗng!");
			return null;
		}else {
			if(!Pattern.compile("[A-Za-z. ]").matcher(tuaSach).find()) {
				JOptionPane.showMessageDialog(btn_luu, "Tựa sách chỉ gồm số, chữ, -, (, ), .");
				return null;
			}else {
				sach.setTacGia(tacGia);
			}
		}
		
		if(soTrang.isEmpty() || soTrang.length() == 0 || !isNumeric(soTrang))
			sach.setSoTrang(0);
		else {
			sach.setSoTrang(Integer.parseInt(soTrang));
		}
			
		int namXB = Integer.parseInt(namXuatBan);
		int namHienTai = Calendar.YEAR;
		if(namXB < 1900 || namXB < namHienTai) {
			JOptionPane.showMessageDialog(btn_luu, "Tựa sách không được rỗng!");
			return null;
		}
		else{
			sach.setNamXuatBan(namXB);
		}
		
		if(donGia.isEmpty() || donGia.length() == 0 || !isNumeric(donGia) || Double.parseDouble(donGia) < 0)
			sach.setDonGia(0);
		else {
			sach.setDonGia(Double.parseDouble(donGia));
		}
		
		//CON ISBN
		return sach;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		Sach sach = new Sach();
		if(source == btn_them) {
			String maSach = txt_id.getText().trim();
			String tuaSach = txt_tuaSach.getText().trim();
			String tacGia = txt_tuaSach.getText().trim();
			String namXuatBan = txt_namXuatBan.getText().trim();
			String nhaXuatBan = txt_nhaXuatBan.getText().trim();
			String soTrang = txt_soTrang.getText().trim();
			String donGia = txt_donGia.getText().trim();
			String ISBN = txt_isbn.getText().trim();
			
			sach = checkValidData(maSach, tuaSach, tacGia, namXuatBan, nhaXuatBan, soTrang, donGia, ISBN);
		}
	}
	

}
