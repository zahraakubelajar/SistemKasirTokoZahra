import java.util.Scanner;

// Node
class Node {
    String kode, nama;
    int total;
    Node next;

    Node(String k, String n, int t) {
        kode = k;
        nama = n;
        total = t;
        next = null;
    }
}

// Queue (Antrian)
class Queue {
    Node front, rear;
    int count = 0;

    void enqueue(String k, String n, int t) {
        if (count >= 5) {
            System.out.println("Antrian penuh!");
            return;
        }

        Node baru = new Node(k, n, t);
        if (rear == null) {
            front = rear = baru;
        } else {
            rear.next = baru;
            rear = baru;
        }

        count++;
        System.out.println("Data pelanggan ditambahkan ke antrian!");
    }

    Node dequeue() {
        if (front == null) {
            System.out.println("Antrian kosong!");
            return null;
        }

        Node temp = front;
        front = front.next;
        count--;
        return temp;
    }

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

// Stack (Riwayat)
class Stack {
    Node top;

    void push(Node data) {
        data.next = top;
        top = data;
    }

    void tampil() {
        if (top == null) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        Node temp = top;
        System.out.println("Riwayat Transaksi:");
        while (temp != null) {
            System.out.println(temp.kode + " | " + temp.nama + " | " + temp.total);
            temp = temp.next;
        }
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue q = new Queue();
        Stack s = new Stack();
        int pilih = 0; 

        do {
            System.out.println("\n=== SISTEM KASIR TOKO ===");
            System.out.println("1. Tambah Antrian");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrian");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

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
                    Node p = q.dequeue();
                    if (p != null) {
                        System.out.println("Melayani pelanggan " + p.kode + " (" + p.nama + ")");
                        s.push(p);
                        System.out.println("Transaksi disimpan ke riwayat.");
                    }
                    break;

                case 3:
                    q.tampil();
                    break;

                case 4:
                    s.tampil();
                    break;
            }

        } while (pilih != 5);

        System.out.println("Program selesai.");
        sc.close(); 
    }
}