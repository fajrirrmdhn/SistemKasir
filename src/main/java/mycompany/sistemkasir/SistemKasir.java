package mycompany.sistemkasir;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemKasir {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Delegasi Pengelolaan Data ke Class Toko
        Toko tokoSaya = new Toko("Toko Wak Rusdi");
        tokoSaya.bukaToko();

        // Initial Dummy Data (3-5 Produk, 2-3 Pelanggan)
        try {
            tokoSaya.tambahProduk(new ProdukMakanan("Indomie", 3000, 50, "2026-12-31"));
            tokoSaya.tambahProduk(new ProdukMakanan("Susu UHT", 7000, 20, "2026-08-15"));
            tokoSaya.tambahProduk(new ProdukElektronik("Mouse Wireless", 150000, 10, "1 Tahun"));
            tokoSaya.tambahProduk(new ProdukElektronik("Keyboard Mechanical", 450000, 5, "2 Tahun"));

            tokoSaya.tambahPelanggan(new Pelanggan("CUST001", "Perrel L Brown"));
            tokoSaya.tambahPelanggan(new Pelanggan("CUST002", "Brandon Currington"));
            tokoSaya.tambahPelanggan(new Pelanggan("CUST003", "Michael Willis Heard"));
        } catch (Exception e) {
            System.out.println("Error Inisialisasi Data: " + e.getMessage());
        }

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("==================================");
            System.out.println("MENU UTAMA SISTEM KASIR");
            System.out.println("1. Tambah Produk Baru");
            System.out.println("2. Lihat Daftar Produk");
            System.out.println("3. Cari Produk");
            System.out.println("4. Manajemen Pelanggan");
            System.out.println("5. Transaksi Pembelian");
            System.out.println("6. Retur Barang");
            System.out.println("7. Keluar");
            System.out.print("Pilih Opsi (1-7): ");

            try {
                int opsi = scanner.nextInt();
                scanner.nextLine();

                switch (opsi) {
                    case 1 -> {
                        System.out.print("Masukkan Nama Produk: ");
                        String namaProduk = scanner.nextLine();
                        System.out.print("Masukkan Harga Produk: ");
                        int hargaProduk = scanner.nextInt();
                        System.out.print("Masukkan Stok Produk: ");
                        int stokProduk = scanner.nextInt();
                        scanner.nextLine();

                        if (hargaProduk < 0 || stokProduk < 0) {
                            throw new Exception("Harga dan stok tidak boleh negatif!");
                        }

                        System.out.print("Jenis Produk (Makanan/Elektronik): ");
                        String jenisProduk = scanner.nextLine();

                        if (jenisProduk.equalsIgnoreCase("Makanan")) {
                            System.out.print("Tanggal Expired (YYYY-MM-DD): ");
                            String expired = scanner.nextLine();
                            tokoSaya.tambahProduk(new ProdukMakanan(namaProduk, hargaProduk, stokProduk, expired));
                        } else if (jenisProduk.equalsIgnoreCase("Elektronik")) {
                            System.out.print("Garansi: ");
                            String garansi = scanner.nextLine();
                            tokoSaya.tambahProduk(new ProdukElektronik(namaProduk, hargaProduk, stokProduk, garansi));
                        } else {
                            System.out.println("Jenis produk tidak valid!");
                        }
                    }

                    case 2 -> {
                        System.out.println("\nDAFTAR PRODUK");
                        tokoSaya.lihatSemuaProduk();
                    }

                    case 3 -> {
                        System.out.print("Masukkan Nama Produk yang Dicari: ");
                        String namaCari = scanner.nextLine();
                        try {
                            Produk p = tokoSaya.cariProduk(namaCari);
                            System.out.println("\nPRODUK DITEMUKAN");
                            p.tampilkanInfo();
                        } catch (ProdukTidakDitemukanException e) {
                            System.out.println("[PERINGATAN]: " + e.getMessage());
                        }
                    }

                    case 4 -> {
                        System.out.println("\nKELOLA PELANGGAN");
                        System.out.println("1. Tambah Pelanggan Baru");
                        System.out.println("2. Cari Pelanggan via ID");
                        System.out.println("3. Lihat Semua Pelanggan");
                        System.out.print("Pilih (1-3): ");
                        int menuMember = scanner.nextInt();
                        scanner.nextLine();

                        if (menuMember == 1) {
                            System.out.print("Masukkan ID Pelanggan: ");
                            String id = scanner.nextLine();
                            System.out.print("Masukkan Nama Pelanggan: ");
                            String nama = scanner.nextLine();
                            tokoSaya.tambahPelanggan(new Pelanggan(id, nama));
                        } else if (menuMember == 2) {
                            System.out.print("Masukkan ID Pelanggan: ");
                            String searchId = scanner.nextLine();
                            tokoSaya.cariPelanggan(searchId);
                        } else if (menuMember == 3) {
                            tokoSaya.lihatSemuaPelanggan();
                        }
                    }

                    case 5 -> {
                        System.out.println("\nSISTEM TRANSAKSI");
                        System.out.print("Masukkan ID Pelanggan: ");
                        String idInput = scanner.nextLine();
                        Pelanggan pembeli = tokoSaya.getPelanggan(idInput);

                        if (pembeli == null) {
                            System.out.println("Gagal: Pelanggan belum terdaftar!");
                        } else if (tokoSaya.getJumlahProduk() == 0) {
                            System.out.println("Gagal: Belum ada produk di toko.");
                        } else {
                            tokoSaya.lihatSemuaProduk();
                            System.out.print("Pilih Nomor Produk yang ingin dibeli: ");
                            int noProduk = scanner.nextInt() - 1;
                            System.out.print("Masukkan Jumlah Beli: ");
                            int jumlahBeli = scanner.nextInt();
                            scanner.nextLine();

                            if (noProduk >= 0 && noProduk < tokoSaya.getJumlahProduk()) {
                                Produk produkDipilih = tokoSaya.getProdukInt(noProduk);
                                TransaksiPenjualan.prosesTransaksi(pembeli, produkDipilih, jumlahBeli);
                            } else {
                                System.out.println("Nomor produk tidak valid!");
                            }
                        }
                    }

                    case 6 -> {
                        System.out.print("Masukkan Nama Produk yang Diretur: ");
                        String barangCari = scanner.nextLine();
                        try {
                            Produk p = tokoSaya.cariProduk(barangCari);
                            System.out.println("\nRETUR PRODUK");
                            p.tampilkanInfo();
                            p.prosesRetur();
                        } catch (ProdukTidakDitemukanException e) {
                            System.out.println("[PERINGATAN]: " + e.getMessage());
                        }
                    }

                    case 7 -> {
                        System.out.println("Terima kasih telah menggunakan Sistem Kasir!");
                        isRunning = false;
                    }

                    default -> System.out.println("Pilihan menu tidak valid!");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERROR INPUT]: Harap masukkan angka yang valid!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("[ERROR]: " + e.getMessage());
            }
        }
        scanner.close();
    }
}