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
        animation(addresses, 100, Segments.dp, Segments.d, Segments.e, Segments.g, Segments.b, Segments.a, Segments.f, Segments.g, Segments.c, Segments.d);
    }
    
    public static void loopAnimation(int... addresses) {
        animation(addresses, 100, Segments.dp, Segments.d, Segments.e, Segments.f, Segments.a, Segments.b, Segments.c, Segments.d); 
    }
    
    public static void animationMultipleseg(int... addresses){
        animation(addresses, 200, Segments.a, Segments.f | Segments.b, Segments.g, Segments.e | Segments.c, Segments.d);
    }
    
    public static void animation(int[] addresses, int delay, int... animation) { 
        for (int i = 0; i < addresses.length; i++) {
            for (int j = 0; j < animation.length; j++) {
                IO.writeShort(addresses[i], 0x100 | animation[j]);
                IO.delay(delay);
            }
            IO.writeShort(addresses[i], 0x100);
        }
    }     
}


