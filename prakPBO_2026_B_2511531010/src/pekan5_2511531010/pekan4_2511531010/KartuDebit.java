package pekan4_2511531010;

public class KartuDebit extends RekeningTabungan {
    private double limitSekaliTarik;

    // Constructor
    public KartuDebit(String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
        super(nomor, nama, saldoAwal, pinAwal, sukuBunga);
        this.limitSekaliTarik = 500000.0; 
    }

    @Override
    public void tarikTunai(double nominal) {
        if (nominal > limitSekaliTarik) {
            System.out.println("Transaksi Gagal: Penarikan lewat Kartu Debit maksimal Rp" + limitSekaliTarik + " sekali transaksi!");
        } else {
            super.tarikTunai(nominal);
        }
    }

    public double getLimitSekaliTarik() {
        return limitSekaliTarik;
    }
}