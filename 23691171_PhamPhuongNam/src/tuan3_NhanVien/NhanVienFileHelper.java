package tuan3_NhanVien;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienFileHelper {
    private String filePath;

    public NhanVienFileHelper(String filePath) {
        this.filePath = filePath;
    }

    public List<NhanVien> loadFromFile() {
        List<NhanVien> list = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) return list;

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",", -1);
                if (parts.length < 6) continue;

                int id = Integer.parseInt(parts[0].trim());
                String ho = parts[1].trim();
                String ten = parts[2].trim();
                String phai = parts[3].trim();
                int tuoi = Integer.parseInt(parts[4].trim());
                double luong = Double.parseDouble(parts[5].trim());

                NhanVien nv = new NhanVien(id, tuoi, ho, ten, phai, luong);
                list.add(nv);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void saveToFile(List<NhanVien> list) {
        Path path = Paths.get(filePath);
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (NhanVien nv : list) {
                String line = String.format("%d,%s,%s,%s,%d,%.2f",
                        nv.getId(), nv.getHo(), nv.getTen(),
                        nv.getPhai(), nv.getTuoi(), nv.getLuong());
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

