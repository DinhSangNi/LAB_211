package models;

public class Student {
    
    private int ID;
    private String hoVaTen;
    private String diaChi;
    private String soDienThoai;
    private int giaoVienID;

    public Student(int ID, String hoVaTen, String diaChi, String soDienThoai, int giaoVienID) {
        this.ID = ID;
        this.hoVaTen = hoVaTen;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.giaoVienID = giaoVienID;
    }

    public Student() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getGiaoVienID() {
        return giaoVienID;
    }

    public void setGiaoVienID(int giaoVienID) {
        this.giaoVienID = giaoVienID;
    }
    
    
}
