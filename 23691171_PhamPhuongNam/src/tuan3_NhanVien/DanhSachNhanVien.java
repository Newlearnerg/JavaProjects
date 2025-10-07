package tuan3_NhanVien;

import java.util.ArrayList;

public class DanhSachNhanVien {
	ArrayList<NhanVien> list;
	public DanhSachNhanVien() {
		list = new ArrayList<NhanVien>();
	}
	
    public void clear() { list.clear(); }
    public java.util.List<NhanVien> getAll() { return list; }
	
    public boolean KiemTraTonTaiNhanVien(NhanVien nv) {
        return list.stream().anyMatch(x -> x.getId() == nv.getId());
    }

    public void ThemNhanVien(NhanVien nv) {
        list.add(nv);
    }

    public boolean XoaNhanVienTheoId(int id) {
        return list.removeIf(nv -> nv.getId() == id);
    }
}
