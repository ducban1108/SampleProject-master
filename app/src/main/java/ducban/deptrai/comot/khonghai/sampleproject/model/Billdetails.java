package ducban.deptrai.comot.khonghai.sampleproject.model;

public class Billdetails {
    private int id;
    private String idbill;
    private String idbook;
    private int soluong;

    public Billdetails(int id, String idbill, String idbook, int soluong) {
        this.id = id;
        this.idbill = idbill;
        this.idbook = idbook;
        this.soluong = soluong;
    }

    public Billdetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdbill() {
        return idbill;
    }

    public void setIdbill(String idbill) {
        this.idbill = idbill;
    }

    public String getIdbook() {
        return idbook;
    }

    public void setIdbook(String idbook) {
        this.idbook = idbook;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
