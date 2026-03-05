public class ArrayOperations {

    public static void main(String[] args) {

        // 1. String array (Days of the Week)
        String[] daysOfTheWeek = new String[7];
        daysOfTheWeek[0] = "Sun";
        daysOfTheWeek[1] = "Mon";
        daysOfTheWeek[2] = "Tue";
        daysOfTheWeek[3] = "Wed";
        daysOfTheWeek[4] = "Thu";
        daysOfTheWeek[5] = "Fri";
        daysOfTheWeek[6] = "Sat";

        System.out.println("--- Days of the Week ---");
        for (String d : daysOfTheWeek) {
            System.out.println(d.toUpperCase());
        }

        // 2. Float array
        float[] fArr = {10.5f, 20.2f, 33.3f};
        
        System.out.println("\n--- Float Array ---");
        for (float f : fArr) {
            System.out.println(f);
        }

        // 3. Integer array
        int[] newArr = new int[]{25, 30, 35, 40, 45};
        
        System.out.println("\n--- Integer Array ---");
        for (int num : newArr) {
            System.out.println(num);
        }
    }
}