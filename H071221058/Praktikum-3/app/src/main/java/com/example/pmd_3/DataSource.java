package com.example.pmd_3;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<User> users = generateDummyUsers(); // Untuk data pengguna

    private static ArrayList<User> generateDummyUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("jkt48_freya", "100M","1","Gadis Koleris yang suka berimajinasi, terangi harimu dengan senyuman karamelku. Halo, aku Freya!",R.drawable.freya, R.drawable.postfreya,R.drawable.storyfreya));
        users.add(new User("jkt48_zee", "90M","20","Si gadis tomboy yang semangatnya meletup-letup, aku Zee!",R.drawable.zee, R.drawable.postzee,R.drawable.storyzee));
        users.add(new User("jkt48_adel", "80M","19","Bagai kucing yang kalem, tapi selalu memikat hati kamu, hai aku Adel!",R.drawable.adel, R.drawable.postadel,R.drawable.storyadel));
        users.add(new User("jkt48_christy", "70M","20","Peduli dan berbaik hati, siapakah dia? Christy~",R.drawable.christy, R.drawable.postchristy,R.drawable.storychristy));
        users.add(new User("jkt48_eli", "60M","25","Aprikot aprikot aprikot aprikot, pang! Dengan energi kegembiraanku aku akan menghangatkan suasana. Halo halo aku Eli!",R.drawable.eli, R.drawable.posteli,R.drawable.storyeli));
        users.add(new User("jkt48_marsha", "50M","5","Seperti pizza yang selalu dinanti-nantikan semua orang, selalu nantikan aku ya! Halo, aku Marsha.",R.drawable.marsha, R.drawable.postmarsha,R.drawable.storymarsha));
        users.add(new User("jkt48_gita", "40M","10","Diam bukan berarti tidak memperhatikanmu, aku Gita!",R.drawable.gita, R.drawable.postgita,R.drawable.storygita));
        users.add(new User("jkt48_gracia", "30M","90","Senyumku akan terekam jelas dalam ingatanmu seperti foto dengan sejuta warna. Namaku Gracia. Always smile",R.drawable.gracia, R.drawable.postgracia,R.drawable.storygracia));
        users.add(new User("jkt48_greesel", "20M","70","Pandangan mataku akan menyinari hatimu bagaikan kunang-kunang di malam hari. Halo aku Greesel",R.drawable.greesel, R.drawable.postgreesel,R.drawable.storygreesel));
        users.add(new User("jkt48_michie", "10M","50","All is your number one. To infinity and beyond. Its Michie!",R.drawable.michie, R.drawable.postmichie,R.drawable.storymichie));
        users.add(new User("jkt48_muthe", "10M","15","Dengan kelincahanku, aku akan menari setiap hari. Panggil aku Mu Mu Mu Muthe! Dont touch, dont touch, dont touch touch touch!",R.drawable.muthe, R.drawable.postmuthe,R.drawable.storymuthe));
        users.add(new User("jkt48_shani", "99M","1","Semanis coklat, selembut sutra. Halo, aku Shani!",R.drawable.shani, R.drawable.postshani,R.drawable.storyshani));
        return users;
    }
}
