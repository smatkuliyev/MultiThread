package package1;

import java.util.concurrent.RunnableFuture;

@SuppressWarnings("SpellCheckingInspection")
public class MT02MethodLevelSynchronization {
    /*
       =========================================   SYNCHRONIZED  ==========================================================
            multi-threading çalışma koşullarında birden fazla thread'in aynı kaynağa (değişken metot, class, bellek vb)(Habil-Kabil kavgası )
        erişimi (okuma veya yazma) sırasında verinin güncellenmesi ve tutarlılığı ile ilgili sorunlar çıkabilir.
        Bu tutarsızlığı engellemek için synchronized keywordu kullanılabilir.
        Kısaca, Syncronization bir kaynağın tread'ler tarafından eş zamanlı kullanımına kapatılması (Lock) işlemidir.
        Synchronized keywordunun farklı kullanımları bulunmaktadır.

     1- Eğer bir metot "synchronized" yapılırsa (Method-Level Syncronization) bu metota aynı andan birden fazla thread'in
        erişimine izin verilmez.
     2- Eğer bir metot yerine o metodun ait olduğu class'ı aynı anda birden fazla thread'in kullanımına kapatmak
        (class-level Synchronization) istersek o zaman "synchronized static" kullanmalıyız.
     3- Eğer bir metot içerisinde belirli bir kismin eş zamanlı thread kullanımına kapatılmasını istenire
        "synchronized block" (block-level Synchronization) kullanılmalı.
     */
    public static int sayac = 0;

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Sayici.say("thread1");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Sayici.say("thread2");
            }
        });

        thread1.start();
        //thread1.join(); // thread'i tamamen kisitlar/oldurur. join() kullanmak riskli
        // icerisinde ez zamanli olmayan baska method'lari da call edebilir
        // synchronized- sadece methodu lock eder. join() bu thread i  tumunden lock eder.
        thread2.start();
    }
}

class Sayici {

    public synchronized static void say(String thread) { // synchronized, bir bitmeden digeri calismasin
        for (int i = 1; i <= 20; i++) {
            MT02MethodLevelSynchronization.sayac++;
            System.out.println("sayac : " + thread + " : " + MT02MethodLevelSynchronization.sayac);
        }
    }

}
