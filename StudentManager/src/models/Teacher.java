package models;

public class Teacher {
    
    private int giaoVienID;
    private String hoTen;
    private String diaChi;
    private String matKhau;
    private String soDienThoai;
    private String monHoc;

    public Teacher() {
    }

    public Teacher(int giaoVienID, String hoTen, String diaChi, String matKhau, String soDienThoai, String monHoc) {
        this.giaoVienID = giaoVienID;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.monHoc = monHoc;
    }

    public int getGiaoVienID() {
        return giaoVienID;
    }

    public void setGiaoVienID(int giaoVienID) {
        this.giaoVienID = giaoVienID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }
    
    
}
