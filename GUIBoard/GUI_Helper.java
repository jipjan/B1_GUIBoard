public class GUI_Helper
{
    public static void intToGui(int number, boolean zero) {
        
    }
    
    public static void snakeAnimation(int... addresses) {
        animation(addresses, Segments.dp, Segments.d, Segments.e, Segments.g, Segments.b, Segments.a, Segments.f, Segments.g, Segments.c, Segments.d);
    }
    
    public static void loopAnimation(int... addresses) {
        animation(addresses, Segments.dp, Segments.d, Segments.e, Segments.f, Segments.a, Segments.b, Segments.c, Segments.d); 
    }

    public static void animation(int[] addresses, Segments... animation) {
        for (int i = 0; i < addresses.length; i++) {
            for (int j = 0; j < animation.length; j++) {
                IO.writeShort(addresses[i], 0x100 | animation[j].Bit);
                IO.delay(100);
            }
            IO.writeShort(addresses[i], 0x100);
        }
    }
    
    public static void animation(int[] addresses, int... animation) { 
        for (int i = 0; i < addresses.length; i++) {
            for (int j = 0; j < animation.length; j++) {
                IO.writeShort(addresses[i], 0x100 | animation[j]);
                IO.delay(100);
            }
            IO.writeShort(addresses[i], 0x100);
        }
    }
    
    public static void animationMultipleseg(int... address){
        animation(address, Segments.a.Bit | Segments.dp.Bit);
    }
    
      
    public enum Segments {
        a(0x1), b(0x2), c(0x4), d(0x8), e(0x10), f(0x20), g(0x40), dp(0x80), one(0x100);
        
        public int Bit;
        
        Segments(int bit) {
            Bit = bit;
        }
    }
}


