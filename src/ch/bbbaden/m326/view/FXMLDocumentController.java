/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.m326.view;

import ch.bbbaden.m326.mermorygame;
import ch.bbbaden.m326.model.BlueCard;
import ch.bbbaden.m326.model.GreenCard;
import ch.bbbaden.m326.model.RedCard;
import ch.bbbaden.m326.model.YellowCard;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author sharu
 */
public class FXMLDocumentController implements Initializable, Runnable {

    @FXML
    private Button button;
    @FXML
    private Label card1, card2, card3, card4, card5, card6, card7, card8;

    private int firstCard = 9;
    private int secondCard = 9;

    private List<String> cards = new ArrayList<>();
    private Label[] labels = new Label[8];

    private int index = 0;

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        labels[0] = card1;
        labels[1] = card2;
        labels[2] = card3;
        labels[3] = card4;
        labels[4] = card5;
        labels[5] = card6;
        labels[6] = card7;
        labels[7] = card8;
        resetField();
        createCards();
    }

    public void resetField() {
        for (int i = 0; i < labels.length; i++) {
            labels[i].setStyle("-fx-background-color:#FFFFFF; -fx-border-color: #000000; -fx-border-width: 2");
        }
    }

    @FXML
    private void color(MouseEvent event) throws IOException, InterruptedException {

        Label temp = ((Label) event.getSource());

        if (temp.getStyle().equals("-fx-background-color:#FFFFFF; -fx-border-color: #000000; -fx-border-width: 2")) {
            temp.setStyle(cards.get(getLabelID(temp.getId())));

            if (firstCard == 9) {
                firstCard = getLabelID(temp.getId());
            } else {
                secondCard = getLabelID(temp.getId());
                disableButtons();
                if (compareCards(firstCard, secondCard) == true) {
                    firstCard = 9;
                    secondCard = 9;
                    enableButtons();
                    index++;
                    if (index == 4) {
                        stage = mermorygame.getStage();
                        Parent root = FXMLLoader.load(getClass().getResource("/ch/bbbaden/m326/view/PlayAgainView.fxml"));

                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                    }

                } else {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            setLabelsToBlank(firstCard);
                            setLabelsToBlank(secondCard);
                            firstCard = 9;
                            secondCard = 9;
                            enableButtons();
                        }
                    }).start();

                }
            }
        }

    }

    public void disableButtons() {
        for (int i = 0; i < labels.length; i++) {
            labels[i].setDisable(true);
            labels[i].setOpacity(1.0);
        }
    }

    public void enableButtons() {
        for (int i = 0; i < labels.length; i++) {
            labels[i].setDisable(false);
            labels[i].setOpacity(1.0);
        }
    }

    public boolean compareCards(int firstCard, int secondCard) {
        if (labels[firstCard].getStyle() == labels[secondCard].getStyle()) {
            return true;
        }
        return false;
    }

    public void setLabelsToBlank(int card) {
        labels[card].setStyle("-fx-background-color:#FFFFFF; -fx-border-color: #000000; -fx-border-width: 2");
    }

    public int getLabelID(String card) {
        switch (card) {
            case "card1":
                return 0;
            case "card2":
                return 1;
            case "card3":
                return 2;
            case "card4":
                return 3;
            case "card5":
                return 4;
            case "card6":
                return 5;
            case "card7":
                return 6;
            case "card8":
                return 7;
            default:
                System.out.println("gjwijg");
                break;

        }
        return 0;
    }

    public void createCards() {
        cards.clear();
        cards.add(new RedCard().getColor());
        cards.add(new RedCard().getColor());
        cards.add(new BlueCard().getColor());
        cards.add(new BlueCard().getColor());
        cards.add(new GreenCard().getColor());
        cards.add(new GreenCard().getColor());
        cards.add(new YellowCard().getColor());
        cards.add(new YellowCard().getColor());
        Collections.shuffle(cards);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
