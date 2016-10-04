public class TestPeriod
{

    public static void runTests()
   {
       
       // standaard wordt een periode aangemaakt van 1 dag: vandaag
       Period kwartaal1 = new Period();
       
       // periode 1 loopt vanaf maandag 30 augustus.
       kwartaal1.setStart(2010, 8, 30);
       System.out.println("kwartaal1 is nu " + kwartaal1.numberOfDays() + " dagen bezig. (inclusief weekenden)");

       
      // periode 1 loopt tot 7 november.
       kwartaal1.setEnd(2010, 11, 7);
       System.out.println("kwartaal1 bestaan uit " + kwartaal1.numberOfDays() + " dagen. (inclusief weekenden)");

   }
}
