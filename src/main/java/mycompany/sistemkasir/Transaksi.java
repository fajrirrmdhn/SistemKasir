/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.sistemkasir;
import java.util.Scanner;
/**
 *
 * @author Fajri Ramadhan
 */
//

public class Transaksi{
    static Scanner scanner = new Scanner(System.in);
    
    public void hitungTotalPembayaran (int harga){ //method overloading
        System.out.println("Total Pembayaran: " + harga);
        System.out.println();
    } 
     
    public void hitungTotalPembayaran (int harga, int jumlah){ //method overloading 
        System.out.println("Total Pembayaran: " + harga*jumlah);
        System.out.println();
    } 
}