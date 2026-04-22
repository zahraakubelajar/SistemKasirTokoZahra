import java.util.Scanner;

// Class Node untuk menyimpan data pelanggan
class Node {
    String kode, nama; // nomor antrian & nama pelanggan
    int total;         // total belanja
    Node next;         // pointer ke node berikutnya

    // constructor
    Node(String k, String n, int t) {
        kode = k;
        nama = n;
        total = t;
        next = null;
    }
}

// Class Queue untuk antrian pelanggan
class Queue {
    Node front, rear; // depan dan belakang antrian
    int count = 0;    // jumlah data (maksimal 5)

    // method untuk menambah data ke antrian (enqueue)
    void enqueue(String k, String n, int t) {
        if (count >= 5) {
            System.out.println("Antrian penuh!");
            return;
        }

        Node baru = new Node(k, n, t);

        // jika antrian kosong
        if (rear == null) {
            front = rear = baru;
        } else {
            rear.next = baru;
            rear = baru;
        }

        count++;
        System.out.println("Data pelanggan ditambahkan ke antrian!");
    }

    // method untuk melayani pelanggan (dequeue)
    Node dequeue() {
        if (front == null) {
            System.out.println("Antrian kosong!");
            return null;
        }

        Node temp = front;
        front = front.next;
        count--;

        return temp; // data dikirim ke stack
    }

    // method untuk menampilkan seluruh antrian
    void tampil() {
        if (front == null) {
            System.out.println("Antrian kosong.");
            return;
        }

        Node temp = front;
        System.out.println("Daftar Antrian:");

        while (temp != null) {
            System.out.println(temp.kode + " | " + temp.nama + " | " + temp.total);
            temp = temp.next;
        }
    }
}

// Class Stack untuk riwayat transaksi 
class Stack {
    Node top; // elemen paling atas

    // method untuk menyimpan transaksi
    void push(Node data) {
        data.next = top;
        top = data;
    }

    // method untuk menampilkan riwayat transaksi
    void tampil() {
        if (top == null) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        Node temp = top;
        System.out.println("Riwayat Transaksi:");

        // menampilkan dari terbaru ke lama
        while (temp != null) {
            System.out.println(temp.kode + " | " + temp.nama + " | " + temp.total);
            temp = temp.next;
        }
    }
}

// Class utama
public class MainKasir {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Queue q = new Queue(); // objek antrian
        Stack s = new Stack(); // objek riwayat

        int pilih = 0; // variabel menu

        do {
            // tampilan menu
            System.out.println("\n=== SISTEM KASIR TOKO ===");
            System.out.println("1. Tambah Antrian");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrian");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            // validasi input harus angka
            if (sc.hasNextInt()) {
                pilih = sc.nextInt();
            } else {
                System.out.println("Input harus angka!");
                sc.next();
                continue;
            }
            sc.nextLine();

            switch (pilih) {
                case 1:
                    // input data pelanggan
                    System.out.print("Masukkan Nomor Antrian: ");
                    String k = sc.nextLine();

                    System.out.print("Masukkan Nama Pelanggan: ");
                    String n = sc.nextLine();

                    System.out.print("Masukkan Total Belanja: ");
                    int t = sc.nextInt();
                    sc.nextLine();

                    q.enqueue(k, n, t);
                    break;

                case 2:
                    // melayani pelanggan
                    Node p = q.dequeue();
                    if (p != null) {
                        System.out.println("Melayani pelanggan " + p.kode + " (" + p.nama + ")");
                        s.push(p); // simpan ke stack
                        System.out.println("Transaksi disimpan ke riwayat.");
                    }
                    break;

                case 3:
                    // tampilkan antrian
                    q.tampil();
                    break;

                case 4:
                    // tampilkan riwayat transaksi
                    s.tampil();
                    break;
            }

        } while (pilih != 5);

        System.out.println("Program selesai.");
        sc.close();
    }
}
