package modules;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws SQLException{

        ArrayList<User> users= new ArrayList<>();
        // it all works!

//        users.add(builUser("Ivan", "Petriv"));
//        users.add(builUser("Pavlo", "Ivanov"));
//        users.add(builUser("Gavrulo", "Duka"));
//        users.add(builUser("Yura", "Stanko"));
//        users.add(builUser("Igor", "Vasyuta"));
//        users.add(builUser("Lilia", "Koval"));
//        users.add(builUser("Galia", "Malush"));
//        users.add(builUser("Luba", "Banah"));
//        users.add(builUser("Andriy", "Roy"));
//        users.add(builUser("Andriy", "Dmutr"));
//        users.add(builUser("Anton", "Rudko"));
//        users.add(builUser("Igor", "Hmil"));
//        users.add(builUser("Ivan", "Kaspriak"));
//        users.add(builUser("Julia", "Fabin"));
//        users.add(builUser("Marta", "Gorbach"));
//        users.add(builUser("Arsen", "Kovalchik"));
//        users.add(builUser("Orest", "Lubinskij"));
//        users.add(builUser("Mykola", "Ryvak"));
//        users.add(builUser("Dmutro", "Smaga"));

        for (User user : users) {
            System.out.println(user);
        }
        Connection connection = DriverManager.getConnection (
                "jdbc:mysql://127.0.0.1:3306/", //localhost might be instead of 127.0.0.1
                "root",
                "2419839"
        );

        PreparedStatement statement=
                connection.prepareStatement("create database if not exists users_random character set 'utf8'");
        statement.executeUpdate();

        statement.execute("use users_random");
        statement.execute("create table if not exists users" +
                "(id int primary key auto_increment," +
                "name varchar (45),"+
                "surName varchar (45),"+
                "phoneNumber INT ,"+
                "email varchar (45),"+
                "login varchar (45),"+
                "password INT )"             // it needs to be changed to String (VARCHAR)
        );
//        statement.execute("ALTER TABLE users" +
//                "    DROP COLUMN password ;");
//        statement.execute("ALTER TABLE users" +
//                "    ADD COLUMN password VARCHAR (100);");

                PreparedStatement  ps2=
                connection.prepareStatement
                        ("INSERT INTO users (name, surName, phoneNumber, email, login, password) " +
                                "VALUES (?, ?, ?, ?, ?, ?);");

        for (User user : users) {
            ps2.setString(1, user.getName());
            ps2.setString(2, user.getSurName());
            ps2.setInt(3, user.getPhoneNumber());
            ps2.setString(4, user.getEmail());
            ps2.setString(5, user.getLogin());
            ps2.setString(6, user.getPassword());
            ps2.executeUpdate();
        }
//
//        ps2.setInt(1, 12);
//        ps2.setString(2, "Petro");
//        ps2.setString(3, "Delete this");
//        ps2.setInt(4, 3);
//        ps2.executeUpdate();

    }


    public static int generateTel(){
        Random rand = new Random();
        int number = rand.nextInt(999999999);

        return number;
    }


    public static String generateLogin(String name, String surName, int phoneNumber){

        String phone= ""+phoneNumber;
        return name.substring(0,3)+"."+surName.substring(0,3)+"."+phone.substring(6,8);
    }


    public static String generateEmail(String name, String surName){
        String domain;
        Random random = new Random();
        switch (random.nextInt(5)){
            case 1: {
                domain="ukr.net";
                break;
            }
            case 2:{
                domain="gmail.com";
                break;
            }
            case 3:{
                domain="farlep.net";
                break;
            }
            case 4:{
                domain="yahoo.com";
                break;
            }
            case 5:{
                domain="gov.ua.com";
                break;
            }
            default:
                domain="unknown.com";
        }
        return name.substring(0,3)+"_"+surName.substring(0,3)+"@"+domain;
    }

    public static String cipher(String string, int pin){

        char[] chars = string.toCharArray();

        int[] cipheredChars = new int[chars.length];

        for (int i = 0; i < cipheredChars.length; i++) {
            cipheredChars[i] = (int)chars[i]*pin;

        }
        String password="";
        for (int i = 0; i < cipheredChars.length; i++) {
            password+=cipheredChars[i]+" ";

        }
        return password;
    }

    public static User builUser(String name, String surName){
        int phoneNumber =generateTel();
        String login = generateLogin(name, surName, phoneNumber);
        String email = generateEmail(name, surName);
        String password = cipher(name, 25);

        return new User(name, surName, phoneNumber, email, login, password);
    }


}
