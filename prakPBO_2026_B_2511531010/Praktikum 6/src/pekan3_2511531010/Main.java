package pekan3_2511531010;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null;
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== SISTEM PERBANKAN MINI ===");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun");
            System.out.println("6. Cetak Mutasi (Riwayat)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan No Rekening: ");
                    String no = scanner.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    double saldoAwal = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Masukkan PIN (6 digit): ");
                    String pin = scanner.nextLine();

                    akunAktif = new Rekening(no, nama, saldoAwal, pin);
                    daftarRekening.add(akunAktif);
                    break;

                case 2:
                    if (akunAktif != null) {
                        System.out.print("Masukkan nominal setor: ");
                        double nominalSetor = scanner.nextDouble();
                        akunAktif.setorTunai(nominalSetor);
                    } else {
                        System.out.println("Error: Belum ada rekening yang dibuat!");
                    }
                    break;

                case 3:
                	if (akunAktif == null) {
                		System.out.println("Belum ada rekening aktif! Silahkan buka rekening terlebih dahulu.");
                        break;
                    }
                    System.out.print("Masukkan PIN Anda: ");
                    String pinTarik = scanner.nextLine();
 
                    if (akunAktif.otentikasi(pinTarik)) {
                        System.out.print("Masukkan nominal tarik: ");
                        double nominalTarik = scanner.nextDouble();
                        akunAktif.tarikTunai(nominalTarik);
                    } else {
                        System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                    }
                    break;

                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;
                    
                case 5:
                	if (daftarRekening.isEmpty()) {
    				    System.out.println("Belum ada rekening yang terdaftar di sistem.");
    				} else {
    				    System.out.print("Masukkan Nomor Rekening yang ingin diaktifkan: ");
    				    String cariNoRek = scanner.nextLine();
    				    boolean ditemukan = false;

    				    for (Rekening r : daftarRekening) {
    				        if (r.getNomorRekening().equalsIgnoreCase(cariNoRek)) {
    				            akunAktif = r;
    				            ditemukan = true;
    				            System.out.println("Berhasil mengganti ke akun milik: " + r.getNamaPemilik());
    				            break;
    				        }
    				    }

    				    if (!ditemukan) {
    				        System.out.println("Error: Nomor rekening tidak ditemukan!");
    				    }
    				}
    				break;

                case 6:
                	if (akunAktif == null) {
    					System.out.println("Error: Anda belum membuka rekening! Silahkan buka rekening terlebih dahulu.");
    				} else {
    					System.out.print("Masukkan PIN: ");
    					String pinMutasi = scanner.nextLine();

    					if (akunAktif.otentikasi(pinMutasi)) {
    					    akunAktif.cetakMutasi();
    					} else {
    					    System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
    					}
    				}
    				break;

                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }
}