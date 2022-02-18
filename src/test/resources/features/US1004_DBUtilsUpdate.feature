Feature: kullanici kayitlari update eder

  Scenario: TC04 kullanici tablo

    Given kullanici DBUtil ile HMC veri tabanina baglanir
    Then tHOTEL tablosunda IDHotel degeri 1019 olan kaydin Email bilgisini "byebyehappiness@gmail.com" yapar