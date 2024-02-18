import java.util.Scanner;
public class InventarisGudang {
    static Scanner input = new Scanner(System.in);
    static boolean idSudahAda = false;
    static Barang[] barang = new Barang[100];
    static int jumlahBarang = 0, totalKBaik = 0, totalKBuruk = 0, totalSemua = 0, pilihanMenu, id;
    static String nama, jawaban;

    public static void main(String[] args) {
        do{
            System.out.println("Menu Pilihan:");
            System.out.println("1. Input Data Barang");
            System.out.println("2. Update Stock");
            System.out.println("3. Pencarian Data Bahan");
            System.out.println("4. Laporan Inventaris Bahan");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihanMenu = input.nextInt();
            input.nextLine(); 

            switch(pilihanMenu){
                case 1:
                    masukDataBarang();
                    break;
                case 2:
                    updateStock();
                    break;
                case 3:
                    cariData();
                    break;
                case 4:
                    laporanData();
                    break;
                case 0:
                    System.out.println("Terima kasih! Sampai jumpa.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
            System.out.println();
        } while(pilihanMenu != 0);
    }

    public static void masukDataBarang(){
        System.out.print("Masukkan Banyak Barang Yang Datanya Akan Di Input: ");
        int jumlahBarangInput = input.nextInt();
        input.nextLine();
        System.out.print("Apakah ingin menampilkan data bahan terlebih dahulu? (y/n): ");
        String tampilData = input.nextLine();
        if(tampilData.equalsIgnoreCase("y")){
            tampilDataBahan();
        }
        
        for(int i = 0; i < jumlahBarangInput; i++){
            do{
                idSudahAda = false;
                System.out.println("----------------------------------------------------");
                System.out.println("Data barang ke-" + (i + 1) + ":");
                System.out.print("Masukkan ID bahan: ");
                id = input.nextInt();
                input.nextLine();
                
                for(int j = 0; j<jumlahBarang; j++){
                    if(barang[j] != null && barang[j].getId() == id){
                        System.out.println("ID barang sudah ada. Apakah anda ingin memasukkan data ulang?(y/n)");
                        jawaban = input.nextLine();
                        if(jawaban.equalsIgnoreCase("n")){
                            return;
                        }else{
                            idSudahAda = true;
                            break;
                        }
                    }
                }
            }while(idSudahAda);

            System.out.print("Masukkan nama bahan: ");
            nama = input.nextLine();
            barang[jumlahBarang] = new Barang(id, nama, 0, 0);
            
            jumlahBarang++;
            System.out.println("Data bahan berhasil dimasukkan.");
        }
    }

    public static void updateStock(){
        System.out.print("Apakah ingin menampilkan data bahan terlebih dahulu? (y/n): ");
        String tampilData = input.nextLine();
        if(tampilData.equalsIgnoreCase("y")){
            tampilDataBahan();
        }
        System.out.println("Menu:");
        System.out.println("1. Barang Masuk");
        System.out.println("2. Barang Digunakan/Dibuang");
        System.out.println("3. Rubah Nama Barang");
        System.out.print("Pilihan Anda: ");
        int pilihan = input.nextInt();
        input.nextLine();
        switch(pilihan){
            case 1:
                System.out.print("Masukkan Banyak Barang Yang Akan Dimasukkan: ");
                int input1 = input.nextInt();
                input.nextLine();
                for(int i = 0; i < input1; i++){
                    System.out.println("----------------------------------------------------");
                    System.out.print("Masukkan ID barang: ");
                    int idMasuk = input.nextInt();
                    input.nextLine();
                    System.out.print("Masukkan kuantitas barang baik yang masuk: ");
                    int KBaikMasuk = input.nextInt();
                    input.nextLine();
                    System.out.print("Masukkan kuantitas barang buruk yang masuk: ");
                    int KBurukMasuk = input.nextInt();
                    input.nextLine();
                    updateKuantitas(idMasuk, KBaikMasuk, KBurukMasuk);
                    System.out.println("Barang Berhasil Dimasukkan.");
                }
                break;
            case 2:
                System.out.print("Masukkan Banyak Barang Yang Akan Digunakan/Dibuang: ");
                int input2 = input.nextInt();
                input.nextLine();
                for(int i = 0; i < input2; i++){
                    System.out.println("----------------------------------------------------");
                    System.out.print("Masukkan ID barang: ");
                    int idKeluar = input.nextInt();
                    input.nextLine();
                    System.out.print("Masukkan kuantitas barang baik yang digunakan/dibuang: ");
                    int KBaikKeluar = input.nextInt();
                    input.nextLine();
                    System.out.print("Masukkan kuantitas barang buruk yang digunakan/dibuang: ");
                    int KBurukKeluar = input.nextInt();
                    input.nextLine();
                    updateKuantitas(idKeluar, -KBaikKeluar, -KBurukKeluar);
                    System.out.println("Barang Derhasil Digunakan/Dibuang.");
                }
                break;
            case 3:
                System.out.print("Masukkan ID barang: ");
                int idUbah = input.nextInt();
                input.nextLine();
                System.out.print("Masukkan nama baru: ");
                String namaBaru = input.nextLine();
                updateNama(idUbah, namaBaru);
                System.out.println("Nama barang berhasil diubah.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }

    public static void updateKuantitas(int id, int kuantitasBaik, int kuantitasBuruk){
        for(int i = 0; i < jumlahBarang; i++){
            if(barang[i].getId() == id){
                barang[i].kuantitasbaik += kuantitasBaik;
                barang[i].kuantitasburuk += kuantitasBuruk;
                totalKBaik += kuantitasBaik;
                totalKBuruk += kuantitasBuruk;
                return;
            }
        }
        System.out.println("ID barang tidak ditemukan.");
    }

    public static void updateNama(int id, String namaBaru){
        for(int i = 0; i < jumlahBarang; i++){
            if(barang[i].getId() == id){
                barang[i].nama = namaBaru;
                return;
            }
        }
        System.out.println("ID barang tidak ditemukan.");
    }

    public static void cariData(){
        System.out.println("----------------------------------------------------");
        System.out.print("Masukkan ID barang: ");
        id = input.nextInt();
        input.nextLine();

        for(int i = 0; i < jumlahBarang; i++){
            if(barang[i].getId() == id){
                Barang cari = barang[i];
                System.out.println("Data barang dengan ID-"+id+":");
                System.out.println("Nama Barang: " + cari.getNama());
                System.out.println("Kuantitas Baik: " + cari.getKBaik());
                System.out.println("Kuantitas Buruk: " + cari.getKBuruk());
                System.out.println("Total: " + (cari.getKBaik() + cari.getKBuruk()));
                return;
            }
        }
        System.out.println("ID barang tidak ditemukan.");
    }

    public static void laporanData(){
        totalSemua = totalKBaik + totalKBuruk;
        tampilDataBahan();
        System.out.println("Total Kuantitas Barang Baik: " + totalKBaik);
        System.out.println("Total Kuantitas Barang Buruk: " + totalKBuruk);
        System.out.println("Total Keseluruhan: " + totalSemua);
    }

    public static void tampilDataBahan(){
        //ID
        for(int i = 0; i < jumlahBarang - 1; i++){
            for(int j = 0; j < jumlahBarang - i - 1; j++){
                if(barang[j].getId() > barang[j + 1].getId()){
                    Barang temp = barang[j];
                    barang[j] = barang[j + 1];
                    barang[j + 1] = temp;
                }
            }
        }
        System.out.println("Data Bahan:");
        if(jumlahBarang == 0){
            System.out.println("Tidak ada data bahan.");
        }else{
            System.out.println("|  ID  |     Nama     |    Baik     |    Buruk    |     Total     |");
            for(int i=0; i<jumlahBarang; i++){
                Barang tampil = barang[i];
                System.out.printf("| %4d | %-12s | %-11s | %-11s | %-13d |\n",tampil.getId(), tampil.getNama(), tampil.getKBaik(), tampil.getKBuruk(), tampil.getKBaik()+tampil.getKBuruk());
            }
        }
    }
}
