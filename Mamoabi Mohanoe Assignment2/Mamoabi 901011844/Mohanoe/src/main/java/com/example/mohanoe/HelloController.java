package com.example.mohanoe;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private MediaView m;

    @FXML
    private AnchorPane p;

    @FXML
    private Button pause,play,playback,stop;

    @FXML
    private Slider s,y;
    @FXML
    private Media media;
    private MediaPlayer player;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String video = getClass().getResource("maa.MP4").toExternalForm();
        media = new Media(video);
        player = new MediaPlayer(media);
        m.setMediaPlayer(player);
        s.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                player.setVolume(s.getValue()*0.01);
            }
        });

        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                y.setValue(t1.toSeconds());
            }
        });
        y.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(y.getValue()));
            }
        });
        y.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(y.getValue()));
            }
        });
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total= media.getDuration();
                y.setMax(total.toSeconds());
            }
        });

    }
    public void play(){
        player.play();
    }
    public void pause(){
        player.pause();
    }
    public void stop() {player.stop();}
    public void backAction() {
        player.seek(player.getCurrentTime().add(Duration.seconds(-15)));
        System.out.println("-15");
    }
}