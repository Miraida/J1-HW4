package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";


    public static String[] heroesAttackType ={"Physical","Magical","Mental","Golem","Lucky","Berserk","Thor","Medic"};
    public static int[] heroesHealth = {260,270,300,500,200,250,280,310};
    public static  int[] heroesDamage = {10, 20, 5, 3, 15, 7, 15 };
    public static int medicHealSize = 100;
    public static int heroeChance;
    public static int s=1;
    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()){
            round();
        }
    }
    public static void round(){
         heroeChance = heroesChance();
        System.out.println(s+" round was started!!!");
        System.out.println("Heroes chance number: "+heroeChance);
        heroesHit();
        changeBossDefence();
        if (heroeChance == 2 && heroesHealth[6] > 0 ) System.out.println("Thor stuned Boss!");
        else bossHit();
        medicHeal();

        printStatistics();
        s++;
    }

    public static void printStatistics(){
        System.out.println("------------------------------");

        System.out.println("Boss Health: "+ bossHealth);
        System.out.println("Phisical Health: "+heroesHealth[0]);
        System.out.println("Magic Health: "+heroesHealth[1]);
        System.out.println("Mental Health: "+heroesHealth[2]);
        System.out.println("Golem Health: "+heroesHealth[3]);
        System.out.println("Lucky Health: "+heroesHealth[4]);
        System.out.println("Berserk Health: "+heroesHealth[5]);
        System.out.println("Thor Health: "+heroesHealth[6]);
        System.out.println("Medic Health: "+heroesHealth[7]);

        System.out.println("------------------------------");
    }

    public static void heroesHit(){

        for (int i = 0; i <heroesHealth.length-1 ; i++) {
            if(bossHealth > 0 && heroesHealth[i] > 0 && i == 3 && heroeChance == 3 )
            {
                for (int j = 0; j <heroesHealth.length ; j++) {
                    heroesHealth[j] +=10;
                }
                heroesHealth[3] -= 80;
            }
            if (bossHealth > 0 && heroesHealth[i] > 0 && i == 4 && heroeChance == 4 )
            {
                heroesHealth[i]+=50;
            }
            if(bossHealth > 0 && heroesHealth[i] > 0 && i == 5 && heroeChance == 5 )
            {
                if(bossHealth - 25 < 0 ) bossHealth = 0;
                else bossHealth -= 25;
            }
            if (bossHealth > 0 && heroesHealth[i]>0){
                if (bossHealth - heroesDamage[i] < 0) bossHealth = 0;
                else bossHealth = bossHealth - heroesDamage[i];

            }
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0 ) {heroesHealth[i] = 0;  }
                else { heroesHealth[i] = heroesHealth[i] - bossDamage; }

            }
        }


    }
    public static void medicHeal(){
        if (heroesHealth[7] > 0) {
            for (int i = 0; i < heroesHealth.length-1; i++) {
                if (heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                    heroesHealth[i] += medicHealSize;
                    System.out.println("Medic heal "+heroesAttackType[i]);
                    break;
                }
            }
        }
    }
    public static int heroesChance(){
      Random rand = new Random();
        return rand.nextInt(5);
    }

    public static Boolean isFinished(){
        if (bossHealth <=0){
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1]<=0 && heroesHealth[2]<=0 && heroesHealth[3]<=0 && heroesHealth[4]<=0 && heroesHealth[5]<=0 && heroesHealth[6]<=0 ){
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void changeBossDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length-1);
        bossDefenceType =  heroesAttackType[randomIndex];
        System.out.println("Boss Defence Type: "+bossDefenceType);

    }

}
