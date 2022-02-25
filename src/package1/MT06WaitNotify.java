package package1;

/*
    ============================ WAIT, NOTIFY ==========================
   object.wait()-->(obj ile çalışır Multi-Thread ile kullanılır) methodu bir thread'i suresiz olarak askıya alir(suspent).
    Diğer bir ifade ile bu thread'in kilitlemiş (locked) olduğu bir kaynağı salıvermesini ve askıya geçmesini sağlar.
    Thread bu durumdan bir başka thread Onu bilgilendirirse (notify) çıkabilir.

   object.notify()-->(obj ile çalışır Multi-Thread ile kullanılır) metodu ise aynı obj üzerinde wait (askıya alınan) bir
    thread'in uyanmasini saglar.  notify : Bildirmek, haber vermek, ihbar etmek
   Object.notifyAll() metodu bir nesne üzerinde askıya alınan tum thread'lerin uyandirilmasini saglar.

   Bu methodlar, thread'ler arasi iletişim (inter-Thread comminication) metodu olarak kullanılır.
   Aynı  class'ta birden çok method'lar wait() ve notify() yapılabilir
 */
public class MT06WaitNotify {

    public static double bakiye = 0;

    public static void main(String[] args) {

        MT06WaitNotify islem = new MT06WaitNotify();

        Thread threadParaCek = new Thread(new Runnable() {
            @Override
            public void run() {
                islem.paraCek(200);
            }
        });

        Thread threadParaYatir = new Thread(new Runnable() {
            @Override
            public void run() {
                islem.paraYatir(500);
            }
        });

        threadParaCek.start();
        threadParaYatir.start();


    }

    public void paraCek(double miktar) {
        synchronized (this) {
            if (bakiye < 0 || bakiye < miktar) {
                System.out.println("Yetersiz bakiye !");
            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bakiye -= miktar;
            System.out.println("Pariyi cektiniz -> Yeni bakiyeniz : " + bakiye);
        }
    }

    public void paraYatir(double miktar) {
        synchronized (this) {
            bakiye += miktar;
            System.out.println("Pariyi yatirdiniz -> Yeni bakiyeniz : " + bakiye);

            notify();
        }
    }
}
