public class GUI_Helper
{
    public static void intToGui(int number, boolean zero) {
        
    }
    
    public static void animation() {
        System.out.println(Segments.e.Bit);    
    }
    
    
    
    
    public enum Segments {
        a(0x0), b(0x1), c(0x2), d(0x3), e(0x4), f(0x5), g(0x6), dp(0x7), one(0x8);
        
        public int Bit;
        
        Segments(int bit) {
            Bit = bit;
        }
    }
}


