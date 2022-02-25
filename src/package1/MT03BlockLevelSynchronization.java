package package1;

@SuppressWarnings("SpellCheckingInspection")
public class MT03BlockLevelSynchronization {
    /*
     Uygulamalarda bazen tüm metotudun synchronized yapılması gerekmeyebilir.
	  Bu durumda, sadece ilgili kısımları synchronized yapıp diğer kısımların klasik multi-threading mantığı ile
	  calışmasına izin vermek performans acisindan onemli katkı saglayacaktır.

	  İstenilen kısımların synchronized yapılması için "synchronized block" kullanılır.
	  Bu durumda block içerisindeki kısıma aynı anda birden fazla thread'in erişimine izin verilmez iken dışında kalan
	  kısımlara, aktif olan threadlar tarafından eş zamanlı erişim sağlabilir.
     */

    public static void main(String[] args) {
        Parantez p1 = new Parantez();

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
                    p1.parantezKoy();
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}

@SuppressWarnings("SpellCheckingInspection")
class Parantez {

    public void parantezKoy() {

        synchronized (this) {// This code block is synchronized
            for (int i = 1; i <= 10; i++) {//eşzamanli olamaz -> [[[[[ ]]]]] olmasi icin
                if (i <= 5) {
                    System.out.print("[");
                } else {
                    System.out.print("]");
                }
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
