/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bike;

/**
 *
 * @author user
 */
public class TestNews {
    public static void main(String[] args) {
        AdminNews ad2 = new AdminNews();
        ad2.insertNews("Samsungaa", "Galaxy s9 Now open in 19 april 2018");
        System.out.println(ad2.showNews());
        AdminNews ad3= new AdminNews();
        ad3.insertNews("Samsungaa", "Galaxy s9 Now open in 19 april 2018");
        System.out.println(ad3.showNews());
        AdminNews ad4 = new AdminNews();
        ad4.insertNews("Samsungaa", "Galaxy s9 Now open in 19 april 2018");
        System.out.println(ad4.showNews());
        AdminNews ad5 = new AdminNews();
        ad5.insertNews("Samsungaa", "Galaxy s9 Now open in 19 april 2018");
        System.out.println(ad5.showNews());
//        ad.deleteNews(1);
        UserNews n = new UserNews();
        n.getNews(12345);
    }
}
