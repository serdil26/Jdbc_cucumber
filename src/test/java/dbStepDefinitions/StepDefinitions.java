package dbStepDefinitions;
import io.cucumber.java.en.Given;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {
    String url="jdbc:sqlserver://184.168.194.58:1433;databaseName=hotelmycamp ; user=techproed;password=P2s@rt65";
    String username="techproed";
    String password="P2s@rt65";

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("kullanici HMC veri tabanina baglanir")
    public void kullanici_hmc_veri_tabanina_baglanir() throws SQLException {
        // database'e baglanti kuruyoruz
        connection= DriverManager.getConnection(url,username,password);
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        // Query calistiriyoruz
        // SELECT Price FROM tHOTELROOM
        String readQuery= "SELECT "+field+" FROM "+ table;
        resultSet=statement.executeQuery(readQuery);
    }
    @Given("kullanici {string} sutunundaki verileri okur")
    public void kullanici_sutunundaki_verileri_okur(String field) throws SQLException {
        // resultSet bizim suana kadar kullandigimiz Set yapisinda degildir
        // resultSet iterator ile calisir
        // resultSet'teki bilgileri okumak istiyorsaniz
        // once iterator'i istediginiz yere gondermelisiniz
        resultSet.first(); // bu komut iterator'i ilk elemente goturur, gitti ise true, gidemezse false doner
        // iterator istenen konuma gidince artik elementi yazdirabiliriz
        System.out.println(resultSet.getString(field));
        // ikinci oda fiyatini gormek istersek
        // iterator'i yollamamiz lazim
        resultSet.next();

        System.out.println(resultSet.getString(field));

        System.out.println(resultSet.next()); //true

        System.out.println(resultSet.getString(field)); //resultSet.next nerede olursa olsun iterator'i bir sonraki elemana goturur

        //tum price sutununu yazdirmak istersek
        System.out.println("==============================");
       //resultSet.next ile calisirken iterator'in oldugu yere dikkat edilmeli
        //bastan yazilmasini istiyorsak .absolute degerini sifir vererek while'in icine aliriz
        resultSet.absolute(0);
        while(resultSet.next()){
            System.out.println(resultSet.getString(field));
        }
        //price sutununda kac data oldugunu bulalim
        //while loop ile resultSet.next false olana kadar gittigi icin iterator en sona gelir

        resultSet.last();
        System.out.println(resultSet.getRow());


        resultSet.absolute(0);
        List<Double> priceList=new ArrayList<>();

        while (resultSet.next()){
            priceList.add(resultSet.getDouble(field));
        }
        System.out.println(priceList);

    }
}