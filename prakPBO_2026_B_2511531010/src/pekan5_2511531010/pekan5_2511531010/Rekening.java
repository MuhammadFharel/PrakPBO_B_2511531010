package pekan5_2511531010;

import java.util.ArrayList;

public class Rekening {
    // 1. Mengunci atribut menjadi private
    private String nomorRekening;
    private String namaPemilik;
    private String pin; // Data sensitif!
    
    protected double saldo;
    protected ArrayList<Transaksi> riwayatTransaksi;

    // 2. Modifikasi Constructor untuk menerima PIN
    public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = saldoAwal;
        
        // Validasi PIN di Constructor
        if (pinAwal.length() == 6) {
            this.pin = pinAwal;
        } else {
            System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
            this.pin = "123456";
        }
        
        this.riwayatTransaksi = new ArrayList<>();
        System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat.");
    }

    // 3. Getter untuk atribut yang diizinkan dibaca publik
    public String getNomorRekening() { 
    	return nomorRekening; 
    	}
    public String getNamaPemilik() { 
    	return namaPemilik;
    	}

    // Method Otentikasi Internal
    public boolean otentikasi(String inputPin) {
        return this.pin.equals(inputPin);
    }
    
    public void setorTunai(double nominal) {
		if (nominal > 0) {
			saldo += nominal;
			// Merekam riwayat (Pembuatan objek Transaksi di dalam method)
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
			System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
    
    public boolean tarikTunai(double nominal) {
		if (nominal <= 0) {
			System.out.println("Gagal: Nominal tarik harus lebih dari 0!");
			return false;
		} else if (nominal > saldo) {
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
			return false;
		} else {
			saldo -= nominal;
 
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			riwayatTransaksi.add(new Transaksi(idTrx, "Debit", nominal));
 
			System.out.println("Tarik tunai Rp" + nominal + " berhasil. Sisa saldo: Rp" + saldo);
			return true; // Transaksi sukses
		}
	}
    
	// TUGAS NOMOR 1
    public void transfer(Rekening tujuan, double nominal, String pinInput) {
		if (!otentikasi(pinInput)) {
			System.out.println("Transfer Gagal: PIN yang Anda masukkan salah!");
			return;
		}
		if (tujuan == null) {
			System.out.println("Transfer Gagal: Rekening tujuan tidak ditemukan!");
			return;
		}
		if (tujuan == this) {
			System.out.println("Transfer Gagal: Tidak bisa transfer ke rekening sendiri!");
			return;
		}
		
		boolean berhasilTarik = this.tarikTunai(nominal);
		
		if (berhasilTarik) {
			tujuan.setorTunai(nominal);
			System.out.println("Transfer Rp" + nominal + " ke rekening " + tujuan.getNomorRekening()
					+ " a.n " + tujuan.getNamaPemilik() + " berhasil!");
		} else {
			System.out.println("Transfer Dibatalkan: Penarikan dari rekening sumber gagal.");
		}
	}
    
	public void cetakMutasi() {
        System.out.println("=== RIWAYAT MUTASI REKENING ===");
        if (riwayatTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi pada rekening ini");
        } else {
            for (Transaksi t : riwayatTransaksi) {
                t.cetakDetail();
            }
        }
        System.out.println("===============================");
    }
	
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " +namaPemilik);
		System.out.println("Saldo Akhir  : Rp" + saldo);
		System.out.println("----------------------");
	}
}
