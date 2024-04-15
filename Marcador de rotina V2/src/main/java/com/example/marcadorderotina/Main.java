package com.example.marcadorderotina;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Inicia o preloader
        PreeloaderApp preloader = new PreeloaderApp();
        preloader.start(primaryStage);

        // Simula um atraso de 3 segundos antes de iniciar a aplicação principal
        new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Notifica o preloader que a aplicação principal está prestes a iniciar
            Platform.runLater(() -> preloader.notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START)));

            // Inicia a aplicação principal (Icarus)
            launchIcarus(primaryStage);
        }).start();
    }

    // Método para iniciar a aplicação principal (Icarus)
// Método para iniciar a aplicação principal (Icarus)
    private void launchIcarus(Stage primaryStage) {
        Icarus icarus = new Icarus();
        try {
            Platform.runLater(() -> {
                try {
                    icarus.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}