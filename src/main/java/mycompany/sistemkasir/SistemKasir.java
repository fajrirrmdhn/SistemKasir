package mycompany.sistemkasir;
import java.util.Scanner;

public class SistemKasir{
    static Scanner scanner = new Scanner(System.in); 
    static Produk[] daftarProduk = new Produk[100]; 
    static int jumlahProduk = 0; 
        
    public static void cariProduk(String namaProdukDicari){ 
        for (int i = 0; i < jumlahProduk; i++){ 
           if(daftarProduk[i].getNama().equals(namaProdukDicari)){ //
               daftarProduk[i].tampilkanInfo();
           }
        }
    }

    public static void beliProduk(String namaProdukDibeli){
        for (int i = 0; i < jumlahProduk; i++){
           if(daftarProduk[i].getNama().equals(namaProdukDibeli)){
               int stokSekarang = daftarProduk[i].getStok();
               int hargaProduk = daftarProduk[i].getHarga();
               System.out.print("Jumlah Barang: ");
               int stokDibeli = scanner.nextInt();
               scanner.nextLine();
               Transaksi hitung = new Transaksi();
               if (stokDibeli <= 1){
                   hitung.hitungTotalPembayaran(hargaProduk);
               }else{
                   hitung.hitungTotalPembayaran(hargaProduk, stokDibeli);
               }
                       
               if (stokDibeli > stokSekarang){
                   System.out.println("Stok Kurang!");
                   System.out.println();
               }else{
                   if (stokSekarang == 0){
                       System.out.println("Stok Habis!");
                   }else{
                       daftarProduk[i].setStok(stokSekarang-stokDibeli);
                   }
               }  
               
           } 
        }
    }
    
    public static void prosesReturProduk(String namaProdukDicari) {
    boolean ditemukan = false;
    for (int i = 0; i < jumlahProduk; i++) {
        if (daftarProduk[i].getNama().equalsIgnoreCase(namaProdukDicari)) {
            System.out.println("Detail Produk");
            daftarProduk[i].tampilkanInfo();
            
            System.out.println("Info Retur Produk");
            daftarProduk[i].prosesRetur(); 
            
            ditemukan = true;
            break;
        }
    }
    if (!ditemukan) {
        System.out.println("Produk tidak ditemukan!");
    }
}
    
    public static void main(String[] args){
        boolean isRunning = true;

        while (isRunning){
            System.out.println("Selamat Datang di Sistem Kasir");
            System.out.println("1. Input Barang");
            System.out.println("2. Pembayaran");
            System.out.println("3. Retur Barang");
            System.out.print("Pilih Opsi: ");
            int opsi = scanner.nextInt();
            System.out.println();

            
            switch(opsi){
                case 1:
                    if (jumlahProduk < daftarProduk.length){
                        scanner.nextLine();
                        System.out.print("Masukkan Nama Produk: ");
                        String namaProduk = scanner.nextLine();

                        System.out.print("Masukkan Harga: ");
                        int hargaProduk = scanner.nextInt();

                        System.out.print("Masukkan Stok: ");
                        int stokProduk = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Jenis Produk: ");
                        String jenisProduk = scanner.nextLine();
                        
                        
                        if(jenisProduk.equalsIgnoreCase("Makanan")){
                            System.out.print("Tanggal Expired: ");
                            String expired = scanner.nextLine();
                            ProdukMakanan produkBaru = new ProdukMakanan(namaProduk, hargaProduk, stokProduk, expired);

                            daftarProduk[jumlahProduk] = produkBaru;

                            jumlahProduk++;
                            System.out.println("Produk Berhasil Ditambahkan!");
                            System.out.println();
                        }else if(jenisProduk.equalsIgnoreCase("Elektronik")){
                            System.out.print("Garansi: ");
                            String garansi = scanner.nextLine();
                            ProdukElektronik produkBaru = new ProdukElektronik(namaProduk, hargaProduk, stokProduk, garansi);

                            daftarProduk[jumlahProduk] = produkBaru;

                            jumlahProduk++;
                            System.out.println();
                            System.out.println("Produk Berhasil Ditambahkan!");
                            System.out.println();
                        }
                    }else{
                        System.out.println("Produk Gagal Ditambahkan!");
                        System.out.println();
                    }
                break;

                case 2:
                    isRunning = true;
                    scanner.nextLine(); //membersihkan spasi sebelum input
                    while (isRunning){
                        System.out.print("Nama Barang: ");
                        String barangCari = scanner.nextLine();
                        

                        beliProduk(barangCari);

                        cariProduk(barangCari);
                        
                        System.out.println();
                        System.out.print("Apakah selesai? (y/n): ");
                        String konfir = scanner.nextLine();
                        System.out.println();
                        if (konfir.equalsIgnoreCase("y")){
                            isRunning = false;
                        }else{
                            isRunning = true;
                        }
                    }
                    break;
                  
                case 3:
                    isRunning = true;
                    scanner.nextLine(); //membersihkan spasi sebelum input
                    while (isRunning){
                        System.out.print("Nama Barang: ");
                        String barangCari = scanner.nextLine();

                        prosesReturProduk(barangCari);
                        System.out.println();
                   
                        cariProduk(barangCari);
                        
                        System.out.println();
                        System.out.print("Apakah selesai? (y/n): ");
                        String konfir = scanner.nextLine();
                        System.out.println();
                        if (konfir.equalsIgnoreCase("y")){
                            isRunning = false;
                        }else{
                            isRunning = true;
                        }
                    }
                    break;
            }    
        }
    }
}






