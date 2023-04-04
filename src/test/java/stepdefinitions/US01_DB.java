package stepdefinitions;

import io.cucumber.java.en.Given;
import java.sql.DriverManager;
import java.sql.SQLException;

import static utilities.DbConnect.*;

public class US01_DB {

    @Given("DB ile Ssn kimlikleri dogrulanir")
    public void dbIleSsnKimlikleriDogrulanir() throws SQLException {
        connection= DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        statement= connection.createStatement(resultSet.TYPE_SCROLL_SENSITIVE, resultSet.CONCUR_UPDATABLE);
        //DB baglantisi kuruldu
        resultSet=statement.executeQuery("select * from appointment");
        int row = 0;
        while (resultSet.next()){
            row++;
        }
        System.out.println("row = " + row);

    }
}


//    @And("Tabloda fiyati en yuksek urun assert ediliri")
//    public void tablodaFiyatiEnYuksekUrunAssertEdiliri() throws SQLException {
//        DbConnect2.resultSet=DbConnect2.statement.executeQuery("select max(fiyat) from pruduct");
//        DbConnect2.resultSet.next();
//        Assert.assertTrue(DbConnect2.resultSet.getInt(1)==25);
//    }
//
//    @And("Urun isimlerinden birinin adi degistirirliri")
//    public void urunIsimlerindenBirininAdiDegistirirliri() throws SQLException {
//        DbConnect2.statement.executeUpdate("update pruduct set name='Kivi' where id=101");
//        DbConnect2.resultSet=DbConnect2.statement.executeQuery("select * from pruduct");
//        DbConnect2.resultSet.absolute(4);
//        Assert.assertEquals(DbConnect2.resultSet.getString("name"),"Kivi");
//    }
//
//    @And("Urun fiyat ortalamasi yazdirirliri")
//    public void urunFiyatOrtalamasiYazdirirliri() throws SQLException {
//        DbConnect2.resultSet=DbConnect2.statement.executeQuery("select avg(fiyat) from pruduct");
//        DbConnect2.resultSet.next();
//        System.out.println("Ortalama Fiyat = " + DbConnect2.resultSet.getInt(1));
//    }
//
//    @And("Tablodaki sutunlarin isimleri farkli yazdiriliri")
//    public void tablodakiSutunlarinIsimleriFarkliYazdiriliri() throws SQLException {
//        DbConnect2.resultSet=DbConnect2.statement.executeQuery
//                ("select id as kimliknosu, name as ismi, turu as cinsi, fiyat as maaliyeti from pruduct");
//
//        DbConnect2.resultSet.next();
//        System.out.println("Birinci satir id bilgisi = " + DbConnect2.resultSet.getString("kimliknosu"));
//    }
//
//    @And("Urun isimlerinin hepsi buyuk harf olacak sekilde yazdiriliri")
//    public void urunIsimlerininHepsiBuyukHarfOlacakSekildeYazdiriliri() throws SQLException {
//        DbConnect2.resultSet=DbConnect2.statement.executeQuery("select upper(name) from pruduct");
//        while (DbConnect2.resultSet.next()){
//            System.out.println("name bilgileri = " + DbConnect2.resultSet.getString(1));
//        }
//    }
//
//    @And("Hepsi kucuk harf olacak sekilde yazdiriliri")
//    public void hepsiKucukHarfOlacakSekildeYazdiriliri() throws SQLException {
//        DbConnect2.resultSet=DbConnect2.statement.executeQuery("select lower(name) from pruduct");
//        while (DbConnect2.resultSet.next()){
//            System.out.println("Name kucuk harf = " + DbConnect2.resultSet.getString(1));
//        }
//    }
//
//    @And("Birinci harfi buyuk olacak sekilde yazdiriliri")
//    public void birinciHarfiBuyukOlacakSekildeYazdiriliri() throws SQLException {
//        DbConnect2.resultSet=DbConnect2.statement.executeQuery("select initcap(name) from pruduct");
//
//        while (DbConnect2.resultSet.next()){
//            System.out.println("Bas harfi buyuk = " + DbConnect2.resultSet.getString(1));
//        }
//    }
//
//    @And("Belli bir fiyatin altindaki urunleri isme gore azalan olarak yazdiriliri")
//    public void belliBirFiyatinAltindakiUrunleriIsmeGoreAzalanOlarakYazdiriliri() throws SQLException {
//        DbConnect2.resultSet=DbConnect2.statement.executeQuery("select * from pruduct where fiyat<25 order by 2 ASC");
//        while (DbConnect2.resultSet.next()){
//            System.out.println("name A dan Z ye = " + DbConnect2.resultSet.getString(2));
//        }
