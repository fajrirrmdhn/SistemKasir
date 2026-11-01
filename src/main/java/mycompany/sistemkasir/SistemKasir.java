package mycompany.sistemkasir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemKasir {
    static Scanner scanner = new Scanner(System.in);
    
    static ArrayList<Produk> daftarProduk = new ArrayList<>();
    static HashMap<String, String> dataPelanggan = new HashMap<>();

    public static Produk cariProduk(String namaProdukDicari) throws ProdukTidakDitemukanException {
        for (Produk p : daftarProduk) {
            if (p.getNama().equalsIgnoreCase(namaProdukDicari)) {
                return p;
            }
        }

        throw new ProdukTidakDitemukanException("Gagal: Produk '" + namaProdukDicari + "' tidak ditemukan dalam katalog!");
    }

    public static void beliProduk(String namaProdukDibeli) throws ProdukTidakDitemukanException, Exception {
        Produk produk = cariProduk(namaProdukDibeli);
        produk.tampilkanInfo();

        System.out.print("Jumlah Barang yang Dibeli: ");
        int stokDibeli = scanner.nextInt();
        scanner.nextLine();

        if (stokDibeli <= 0) {
            throw new Exception("Jumlah pembelian harus lebih dari 0!");
        }

        if (stokDibeli > produk.getStok()) {
            throw new Exception("Stok tidak mencukupi! Stok tersedia saat ini: " + produk.getStok());
        }

        Transaksi hitung = new Transaksi();
        if (stokDibeli == 1) {
            hitung.hitungTotalPembayaran(produk.getHarga());
        } else {
            hitung.hitungTotalPembayaran(produk.getHarga(), stokDibeli);
        }

        produk.setStok(produk.getStok() - stokDibeli);
        System.out.println("Pembayaran Berhasil! Sisa stok " + produk.getNama() + ": " + produk.getStok());
    }

    public static void prosesReturProduk(String namaProdukDicari) throws ProdukTidakDitemukanException {
        Produk produk = cariProduk(namaProdukDicari);
        System.out.println("Detail Produk Retur");
        produk.tampilkanInfo();
        System.out.println("Info Retur Produk:");
        produk.prosesRetur();
    }

    public static void main(String[] args) {
        boolean isRunning = true;

        try {
            daftarProduk.add(new ProdukMakanan("Indomie", 3000, 50, "2026-12-31"));
            daftarProduk.add(new ProdukMakanan("Susu UHT", 7000, 20, "2026-08-15"));
            daftarProduk.add(new ProdukElektronik("Mouse Wireless", 150000, 10, "1 Tahun"));
            daftarProduk.add(new ProdukElektronik("Keyboard", 450000, 5, "2 Tahun"));

            dataPelanggan.put("CUST001", "Perrel L Brown");
            dataPelanggan.put("CUST002", "Brandon Currington");
            dataPelanggan.put("CUST003", "Michael Willis Heard");
        } catch (Exception e) {
            System.out.println("Error Inisialisasi Data: " + e.getMessage());
        }

        while (isRunning) {
            System.out.println("       SELAMAT DATANG DI SISTEM KASIR     ");
            System.out.println("1. Input Barang Baru");
            System.out.println("2. Lihat Daftar Barang");
            System.out.println("3. Pembayaran / Beli Produk");
            System.out.println("4. Retur Barang");
            System.out.println("5. Kelola Member / Pelanggan");
            System.out.println("6. Keluar");
            System.out.print("Pilih Opsi (1-6): ");

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
                            daftarProduk.add(new ProdukMakanan(namaProduk, hargaProduk, stokProduk, expired));
                            System.out.println("Produk Makanan Berhasil Ditambahkan! Total: " + daftarProduk.size());
                        } else if (jenisProduk.equalsIgnoreCase("Elektronik")) {
                            System.out.print("Garansi: ");
                            String garansi = scanner.nextLine();
                            daftarProduk.add(new ProdukElektronik(namaProduk, hargaProduk, stokProduk, garansi));
                            System.out.println("Produk Elektronik Berhasil Ditambahkan! Total: " + daftarProduk.size());
                        } else {
                            System.out.println("Jenis produk tidak valid!");
                        }
                    }

                    case 2 -> {
                        System.out.println("Daftar Produk (ArrayList)");
                        if (daftarProduk.isEmpty()) {
                            System.out.println("Belum ada produk yang tersimpan.");
                        } else {
                            for (int i = 0; i < daftarProduk.size(); i++) {
                                System.out.println("\n[ No. " + (i + 1) + " ]");
                                daftarProduk.get(i).tampilkanInfo();
                            }
                        }
                    }

                    case 3 -> {
                        System.out.println("Pembayaran / Transaksi");
                        System.out.print("Masukkan Nama Barang yang Dibeli: ");
                        String barangCari = scanner.nextLine();

                        try {
                            beliProduk(barangCari);
                        } catch (ProdukTidakDitemukanException e) {
                            System.out.println("[PERINGATAN CUSTOM EXCEPTION]: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("[ERROR TRANSAKSI]: " + e.getMessage());
                        } finally {
                            System.out.println("[FINALLY] Sesi transaksi produk selesai dieksekusi.");
                        }
                    }

                    case 4 -> {
                        System.out.println("Retur Barang");
                        System.out.print("Masukkan Nama Barang yang Diretur: ");
                        String barangCari = scanner.nextLine();

                        try {
                            prosesReturProduk(barangCari);
                        } catch (ProdukTidakDitemukanException e) {
                            System.out.println("[PERINGATAN CUSTOM EXCEPTION]: " + e.getMessage());
                        } finally {
                            System.out.println("[FINALLY] Sesi retur barang selesai dieksekusi.");
                        }
                    }

                    case 5 -> {
                        System.out.println("Kelola Member / Pelanggan");
                        System.out.println("1. Tambah Member Baru");
                        System.out.println("2. Cari Member via ID");
                        System.out.println("3. Tampilkan Semua Member");
                        System.out.print("Pilih (1-3): ");
                        int menuMember = scanner.nextInt();
                        scanner.nextLine();

                        if (menuMember == 1) {
                            System.out.print("Masukkan ID Pelanggan (Key): ");
                            String id = scanner.nextLine();
                            System.out.print("Masukkan Nama Pelanggan (Value): ");
                            String nama = scanner.nextLine();

                            dataPelanggan.put(id, nama);
                            System.out.println("Member berhasil ditambahkan ke HashMap!");
                        } else if (menuMember == 2) {
                            System.out.print("Masukkan ID Pelanggan yang dicari: ");
                            String searchId = scanner.nextLine();

                            if (dataPelanggan.containsKey(searchId)) {
                                System.out.println("Ditemukan! Nama Member: " + dataPelanggan.get(searchId));
                            } else {
                                System.out.println("Member dengan ID '" + searchId + "' tidak terdaftar.");
                            }
                        } else if (menuMember == 3) {
                            System.out.println("Total Member Terdaftar: " + dataPelanggan.size());
                            for (String keyId : dataPelanggan.keySet()) {
                                System.out.println("- ID: " + keyId + " | Nama: " + dataPelanggan.get(keyId));
                            }
                        } else {
                            System.out.println("Pilihan menu member tidak valid.");
                        }
                    }

                    case 6 -> {
                        System.out.println("Terima kasih telah menggunakan Sistem Kasir!");
                        isRunning = false;
                    }

                    default -> System.out.println("Pilihan menu tidak valid (Pilih 1-6).");
                }

            } catch (InputMismatchException e) {
                System.out.println("[ERROR INPUT]: Format input tidak valid! Harap masukkan angka yang sesuai.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("[ERROR SISTEM]: Terjadi kesalahan -> " + e.getMessage());
            } finally {
                System.out.println("Loop eksekusi menu selesai");
            }
        }

        scanner.close();
    }
}