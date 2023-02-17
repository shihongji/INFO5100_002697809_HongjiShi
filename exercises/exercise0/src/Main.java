public class Main {
    public static void main(String[] args) {
        Blog blog = new Blog("Google", "Hongji");
        PS5 ps5 = new PS5("DRID387674", "White");
        Song newSong = new Song("The last of us", "HBO", 560);
        Bike bike = new Bike(2233482);
        System.out.println("The initial state: " + bike.getState());
        bike.takeToRepairShop();
        System.out.println("Now state: "+bike.getState());
        bike.repair();
        bike.returnFromRepairShop();
    }
}