package sn.esp.ManSSI.Entities;

import sn.esp.ManSSI.MySQL.DatabaseReader;
import sn.esp.ManSSI.MySQL.DatabaseWriter;

public class TestEntities {
    public static void main(String[] args) throws Exception {
//        Entity silver = new Entity("221","21","s431","k","silver","http://google.com", true);
//        Candidate gold = new Candidate("312","212","d33","k","gold","http:me.com",true,"les fous","http://lesfou.sn");

//        PublicKey[] pb_keys = new PublicKey[3];
//        pb_keys[1] = silver.getRsa().getPublicKey();
//        pb_keys[2] = gold.getRsa().getPublicKey();
//
//        for (int i = 0; i < pb_keys.length; i++) {
//            System.out.println("Pour " + i + " on a comme cle public " + pb_keys[i]);
//        }
//
//        String messageGold = "Vote pour moi bouffon";
//
//        byte[] goldMessageEncrypt = silver.getRsa().encrypt(messageGold.getBytes());
//        byte[] receivedMessageSilver = silver.getRsa().decrypt(goldMessageEncrypt);
//
//        System.out.println("Message original : " + messageGold);
//        System.out.println("Message chiffre : " + new String(goldMessageEncrypt));
//        System.out.println("Message dechiffre : " + new String(receivedMessageSilver));

        String url = "jdbc:mysql://mysql-silver.alwaysdata.net:3306/silver_mssi";
        String username = "silver";
        String password = "H93frSeYu92NjFhcVYvb";

        DatabaseWriter databaseWriter = new DatabaseWriter(url, username, password);

        // Écrire un message dans la table test
        String message = "Ceci est un message de test";
        databaseWriter.insertTestMessage(message);
    }
}
