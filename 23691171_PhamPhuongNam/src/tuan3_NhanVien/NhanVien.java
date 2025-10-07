package tuan3_NhanVien;

import java.util.Objects;

public class NhanVien {
	private int id, tuoi;
	private String ho, ten, phai;
	private double luong;
	
	public NhanVien(int id, int tuoi, String ho, String ten, String phai, double luong) {
		super();
		this.id = id;
		this.tuoi = tuoi;
		this.ho = ho;
		this.ten = ten;
		this.phai = phai;
		this.luong = luong;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getPhai() {
		return phai;
	}

	public void setPhai(String phai) {
		this.phai = phai;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ho, id, luong, phai, ten, tuoi);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(ho, other.ho) && id == other.id
				&& Double.doubleToLongBits(luong) == Double.doubleToLongBits(other.luong)
				&& Objects.equals(phai, other.phai) && Objects.equals(ten, other.ten) && tuoi == other.tuoi;
	}

	
}
