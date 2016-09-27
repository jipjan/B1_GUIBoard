public class GUI_Helper
{
    public class Segments {
        public static final int a = 0x1;
        public static final int b = 0x2;
        public static final int c = 0x4;
        public static final int d = 0x8;
        public static final int e = 0x10;
        public static final int f = 0x20;
        public static final int g = 0x40;
        public static final int dp = 0x80;
        public static final int one = 0x100;
    } 
    
    public static void snakeAnimation(int... addresses) {
        animation(addresses, 100, true, Segments.dp, Segments.d, Segments.e, Segments.g, Segments.b, Segments.a, Segments.f, Segments.g, Segments.c, Segments.d);
    }
    
    public static void loopAnimation(int... addresses) {
        animation(addresses, 100, true, Segments.dp, Segments.d, Segments.e, Segments.f, Segments.a, Segments.b, Segments.c, Segments.d); 
    }
    
    public static void animationMultipleseg(int... addresses) {
        animation(addresses, 200, true, Segments.a, Segments.f | Segments.b, Segments.g, Segments.e | Segments.c, Segments.d);
    }
    
    public static void tuimelaarAnimation(int... addresses) {
        animation(addresses, 100, true, Segments.a, Segments.f | Segments.b, Segments.g | Segments.e | Segments.c, Segments.d);
    }
    
    public static void movieAnimation() {
        movieAnimation(0x30, 0x32, 0x34);        
        new Thread(() -> movieAnimation(0x30)).start();
        new Thread(() -> movieAnimation(0x32)).start();
        new Thread(() -> movieAnimation(0x34)).start();        
    }
    
    public static void movieAnimation(int... addresses) {
        for (int i = 0; i < addresses.length; i++) {
            // step 1
            animation(new int[] { addresses[i], addresses[i] }, 100, true, Segments.a, Segments.b, Segments.c, Segments.d, Segments.e, Segments.f);
            // step 2
            animation(new int[] { addresses[i] }, 100, false, Segments.a, Segments.b, Segments.c, Segments.d, Segments.e, Segments.f);
            // step 3
            animation(new int[] { addresses[i] }, 100, true,
                Segments.b | Segments.c | Segments.d | Segments.e | Segments.f,
                Segments.c | Segments.d | Segments.e | Segments.f,
                Segments.d | Segments.e | Segments.f,
                Segments.e | Segments.f,
                Segments.f);
            // step 4
            tuimelaarAnimation(addresses[i]);
        }        
    }
    
    public static void animation(int[] addresses, int delay, boolean clear, int... animation) { 
        for (int i = 0; i < addresses.length; i++) {
            int last = 0x0;
            for (int j = 0; j < animation.length; j++) {
                if (clear) {
                    IO.writeShort(addresses[i], 0x100 | animation[j]);
                }
                else {
                    last = 0x100 | IO.readShort(addresses[i]) | animation[j];
                    IO.writeShort(addresses[i], last);
                }
                IO.delay(delay);
            }
            if (clear) IO.writeShort(addresses[i], 0x100);
        }
    }
    
}


