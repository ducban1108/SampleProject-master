package ducban.deptrai.comot.khonghai.sampleproject.model;

public class Book {
    private String tensach,loaisach,tacgia,nxb;
    private String soluong,gia,masach;

    public Book(String tensach, String loaisach, String tacgia, String nxb, String soluong, String gia, String masach) {

        this.tensach = tensach;
        this.loaisach = loaisach;
        this.tacgia = tacgia;
        this.nxb = nxb;
        this.soluong = soluong;
        this.gia = gia;
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getLoaisach() {
        return loaisach;
    }

    public void setLoaisach(String loaisach) {
        this.loaisach = loaisach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public Book() {

    }


}
