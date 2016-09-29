public class GUI_Digitis_Helper
{
    public class Segments
    {
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

    public static void snakeAnimation(int... addresses)
    {
        animation(false, addresses, 100, true, Segments.dp, Segments.d, Segments.e, Segments.g, Segments.b, Segments.a, Segments.f, Segments.g, Segments.c, Segments.d);
    }

    public static void loopAnimation(int... addresses)
    {
        animation(false, addresses, 100, true, Segments.dp, Segments.d, Segments.e, Segments.f, Segments.a, Segments.b, Segments.c, Segments.d); 
    }

    public static void animationMultipleseg(int... addresses)
    {
        animation(false, addresses, 200, true, Segments.a, Segments.f | Segments.b, Segments.g, Segments.e | Segments.c, Segments.d);
    }

    public static void movieAnimation()
    {
        movieAnimation(false, 0x30);
        movieAnimation(false, 0x32);
        movieAnimation(false, 0x34);
        movieAnimation(true, 0x30, 0x32, 0x34);     
    }

    private static void movieAnimation(boolean simultaneous, int... addresses)
    {
        // step 1
        animation(simultaneous, addresses, 100, true, Segments.a, Segments.b, Segments.c, Segments.d, Segments.e, Segments.f);
        animation(simultaneous, addresses, 100, true, Segments.a, Segments.b, Segments.c, Segments.d, Segments.e, Segments.f);      
        // step 2
        animation(simultaneous, addresses, 100, false, Segments.a, Segments.b, Segments.c, Segments.d, Segments.e, Segments.f);        
        // step 3
        animation(simultaneous, addresses, 100, true,
            Segments.b | Segments.c | Segments.d | Segments.e | Segments.f,
            Segments.c | Segments.d | Segments.e | Segments.f,
            Segments.d | Segments.e | Segments.f,
            Segments.e | Segments.f,
            Segments.f);
        // step 4
        animation(simultaneous, addresses, 100, true, Segments.a, Segments.f | Segments.b, Segments.g | Segments.e | Segments.c, Segments.d);  
    }

    public static void animation(boolean simultaneous, int[] addresses, int delay, boolean clear, int... animation)
    { 
        if (simultaneous)
        {
            for (int j = 0; j < animation.length; j++)
            {
                for (int i = 0; i < addresses.length; i++)                
                    writeAnimation(clear, addresses[i], animation[j], 0x0);                
                IO.delay(delay);
            }
            if (clear) clear(addresses);        
        }
        else
        {
            for (int i = 0; i < addresses.length; i++)
            {
                int last = 0x0;
                for (int j = 0; j < animation.length; j++)
                {
                    writeAnimation(clear, addresses[i], animation[j], last);
                    IO.delay(delay);
                }
                if (clear) clear(addresses[i]);                
            }
        }
    }

    private static void writeAnimation(boolean clear, int address, int animation, int last)
    {
        if (clear)
            IO.writeShort(address, 0x100 | animation);                    
        else
        {
            last = 0x100 | IO.readShort(address) | animation;
            IO.writeShort(address, last);
        }        
    }

    public static void clear(int... addresses)
    {
        for (int i = 0; i < addresses.length; i++)
            IO.writeShort(addresses[i], 0x100);
    }
}

