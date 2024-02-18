public class Barang {
    int id;
    String nama;
    int kuantitasbaik;
    int kuantitasburuk;

    public Barang(int id, String nama, int kuantitasbaik, int kuantitasburuk){
        this.id = id;
        this.nama = nama;
        this.kuantitasbaik = kuantitasbaik;
        this.kuantitasburuk = kuantitasburuk;
    }
    public int getId(){
        return id;
    }
    public String getNama(){
        return nama;
    }
    public int getKBaik(){
        return kuantitasbaik;
    }
    public int getKBuruk(){
        return kuantitasburuk;
    }
}
