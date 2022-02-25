package package1;
/*
============================================= VOLATILE ===============================================================
 Volatile (uçucu, geçici, kalıcı olmayan) keyword'u, Bir degiskenin farklı threadler tarafından kullanılırken degerinin
 degismesini saglamak icin kullanılmaktadir. Aynı zamanda bir class'ı thread-safe(thread güvenliği ile çalışma) yapmak icin de kullanılır.
  Yani eş zamanlı olarak volatile variable threadler tarafından erişip güncelleyebilir ve Volatile keywordu sadece degiskenler ile
  (primitif veya non-primitif) kullanılabilir. Obj, method ve Class'lara konulmaz

 Volatile keywordu kullanılan bir veriable'ın degeri cache bellege saklanmaz. Her defasında ilgili class'ın process bellegi
 (MAIN MEMORY) den okunur. Dolayısıyla farklı thread'ler degiskeni guncellese de her defasında en guncel deger okunmus olur.
 Bu özellikleri sayesinde Synchronization yönteminin daha iyi bir alternatifi olarak düşünülebilir.
 */

public class MT05Volatile {

   volatile public static int yas = 0;

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (yas == 0) {
                        System.out.println("yas hala 0 ");
                    } else {
                        break;
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
              //  try {
              //      Thread.sleep(5000);
              //  } catch (InterruptedException e) {
              //      e.printStackTrace();
              //  }
                yas = 1;
                System.out.println("yas 1 oldu");
            }
        });

        thread1.start();
        thread2.start();

    }
}
