package tuan4;

import java.util.Objects;

public class Sach {
	String maSach, tuaSach, tacGia, nhaXuatBan, ISBN;
	int namXuatBan, soTrang;
	double donGia;
	
	public Sach(String maSach, String tuaSach, String tacGia, int namXuatBan, String nhaXuatBan, int soTrang, double donGia, String ISBN) {
		this.maSach = maSach;
		this.tuaSach = tuaSach;
		this.tacGia = tacGia;
		this.namXuatBan = namXuatBan;
		this.nhaXuatBan = nhaXuatBan;
		this.soTrang = soTrang;
		this.donGia = donGia;
		this.ISBN = ISBN;
	}
	
	public Sach() {
		
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTuaSach() {
		return tuaSach;
	}

	public void setTuaSach(String tuaSach) {
		this.tuaSach = tuaSach;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public String getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getNamXuatBan() {
		return namXuatBan;
	}

	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}

	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ISBN, donGia, maSach, namXuatBan, nhaXuatBan, soTrang, tacGia, tuaSach);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sach other = (Sach) obj;
		return Objects.equals(ISBN, other.ISBN)
				&& Double.doubleToLongBits(donGia) == Double.doubleToLongBits(other.donGia)
				&& Objects.equals(maSach, other.maSach) && namXuatBan == other.namXuatBan
				&& Objects.equals(nhaXuatBan, other.nhaXuatBan) && soTrang == other.soTrang
				&& Objects.equals(tacGia, other.tacGia) && Objects.equals(tuaSach, other.tuaSach);
	}
}
