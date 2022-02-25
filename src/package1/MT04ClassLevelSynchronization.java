package package1;

public class MT04ClassLevelSynchronization {
    /*
            Thread'ler farklı obj'ler kullanılırsa durum nasıl değişir?
        Dolayısıyla method seviyesi synchronization işe yaramayacaktır.
        Çünkü her iki thread, farklı obj'lerin parantezKoy() methodunu calistirmaktadir.
        Burada çözüm için class seviyesinde blocklama yapmaktır.
        Çünkü her iki obje aynı class'dan create edilmektedir.
     */
    public static void main(String[] args) {

        Parantez1 p1 = new Parantez1();
        Parantez1 p2 = new Parantez1();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {//5 satir  [[[[[ ]]]]]  yapar
                for (int i = 0; i < 5; i++) {
                    p1.parantezKoy();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {//5 satir [[[[[ ]]]]]  yapar
                for (int i = 0; i < 5; i++) {
                    p2.parantezKoy();
                }
            }
        });

        thread1.start();
        thread2.start();


    }
}

class Parantez1 {

    public synchronized static void parantezKoy() {// suraya static koyunca class level synchronized olmuş oldu


            for (int i = 1; i <= 10; i++) {//eşzamanli olamaz -> [[[[[ ]]]]] olmasi icin
                if (i <= 5) {
                    System.out.print("[");
                } else {
                    System.out.print("]");
                }
            }

        System.out.println();

        for (int i = 1; i < 10; i++) {//eszamanli olabilir
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
