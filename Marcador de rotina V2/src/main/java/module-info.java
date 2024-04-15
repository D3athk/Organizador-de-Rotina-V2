module com.example.marcadorderotina {
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires java.sql;
    requires java.prefs;

    opens com.example.marcadorderotina ;
    exports com.example.marcadorderotina;
}