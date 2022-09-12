
package com.mycompany.probability;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class PrimaryController {
    
    @FXML
    private Label answeLabel;
    @FXML
    private ComboBox<Integer> redCombo;
    @FXML
    private ComboBox<Integer> yellowCombo;
    @FXML
    private ComboBox<Integer> blueCombo;
    @FXML
    private ComboBox<Integer> countCombo;
    @FXML
    private Button countButton;
    @FXML
    private TextField redText;
    @FXML
    private TextField yellowText;
    @FXML
    private TextField blueText;
    
     public void initialize() {
        
        redCombo.getItems().add(0);
        redCombo.getSelectionModel().selectFirst();
        
        yellowCombo.getItems().add(0); 
        yellowCombo.getSelectionModel().selectFirst();
        
        blueCombo.getItems().add(0);
        blueCombo.getSelectionModel().selectFirst();
        
        this.addListener();
    }
     
    @FXML
    private void count(ActionEvent event) {
        
        OneColorBalls red = new OneColorBalls("red",  Integer.valueOf(redText.getText()));
        OneColorBalls yellow = new OneColorBalls("yellow",  Integer.valueOf(yellowText.getText()));
        OneColorBalls blue = new OneColorBalls("blue",  Integer.valueOf(blueText.getText()));
        
        Box box = new Box();
        
        box.addBalls(red);
        box.addBalls(yellow);
        box.addBalls(blue);
        
        ArrayList<OneColorBalls> expectation = new ArrayList<>();
        
        if(!redText.getText().equals("")) {
            expectation.add(new OneColorBalls("red", Integer.valueOf(redCombo.getValue())));
        }
        
        if(!yellowText.getText().equals("")) {
            expectation.add(new OneColorBalls("yellow", Integer.valueOf(yellowCombo.getValue())));
        }
        
        if(!blueText.getText().equals("")) {
            expectation.add(new OneColorBalls("blue", Integer.valueOf(blueCombo.getValue())));
        } 
        
        Counting countingOb = new Counting(box, expectation, countCombo.getValue());
        
        answeLabel.setText("Chance to pick expected number of ball is " + new DecimalFormat("##.##").format(countingOb.count()) + "%");
    }
    
    
    private void addListener() {
        
        ChangeListener redComboListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                if(redCombo.getValue() != null) {
                    countCombo.getItems().clear();
                    countCombo.getItems().addAll(arrayForComboTryNumber(redCombo.getValue(),
                            yellowCombo.getValue(), blueCombo.getValue(),
                            redText, yellowText, blueText));
                    countCombo.getSelectionModel().selectFirst();
                }
            }    
        };
        
        redCombo.getSelectionModel().selectedIndexProperty().addListener(redComboListener);
        
        ChangeListener yellowComboListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                
                if(yellowCombo.getValue() != null) {
                    countCombo.getItems().clear();
                    countCombo.getItems().addAll(arrayForComboTryNumber(redCombo.getValue(),
                            yellowCombo.getValue(), blueCombo.getValue(),
                            redText, yellowText, blueText));
                    countCombo.getSelectionModel().selectFirst();
                }  
            }
        };
        
        yellowCombo.getSelectionModel().selectedItemProperty().addListener(yellowComboListener);
        
        
        ChangeListener blueComboListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                
                if(blueCombo.getValue() != null) {
                    countCombo.getItems().clear();
                    countCombo.getItems().addAll(arrayForComboTryNumber(redCombo.getValue(),
                            yellowCombo.getValue(), blueCombo.getValue(),
                            redText, yellowText, blueText));
                    countCombo.getSelectionModel().selectFirst();
                }
            }
        };
        
        blueCombo.getSelectionModel().selectedItemProperty().addListener(blueComboListener);   
        
        redText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    redText.setText(newValue.replaceAll("[^\\d]", ""));  
                } else {

                    if(!newValue.equals("")) {
                        
                        redCombo.getItems().clear();
                        redCombo.getItems().addAll(arrayForCombo(0, Integer.valueOf(newValue)));
                        redCombo.getSelectionModel().selectFirst();
                                                
                        setCountComboValues();
                         
                    } else {
                        
                        redCombo.getItems().clear();
                        redCombo.getItems().addAll(arrayForCombo(0, 0));
                        redCombo.getSelectionModel().selectFirst();

                    }
                    
                }

            }
        });
        
        yellowText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    yellowText.setText(newValue.replaceAll("[^\\d]", ""));  
                } else {

                    if(!newValue.equals("")) {
                                                
                        yellowCombo.getItems().clear();
                        yellowCombo.getItems().addAll(arrayForCombo(0, Integer.valueOf(newValue)));
                        yellowCombo.getSelectionModel().selectFirst();
                        
                        setCountComboValues();
                        
                    } else {
                        
                        yellowCombo.getItems().clear();
                        yellowCombo.getItems().addAll(arrayForCombo(0, 0));
                        yellowCombo.getSelectionModel().selectFirst();

                    }  
                }
            }
        });
        
        
        blueText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    blueText.setText(newValue.replaceAll("[^\\d]", ""));

                } else {

                    if(!newValue.equals("")) {
                                               
                        blueCombo.getItems().clear();
                        blueCombo.getItems().addAll(arrayForCombo(0, Integer.valueOf(newValue)));
                        blueCombo.getSelectionModel().selectFirst();
                        
                        setCountComboValues();
 
                    } else {
                        
                        blueCombo.getItems().clear(); 
                        blueCombo.getItems().addAll(arrayForCombo(0, 0));
                        blueCombo.getSelectionModel().selectFirst();
                                                
                    }
                }
            }
        });    
    }
    
    private ArrayList<Integer> arrayForCombo(int first, int last) {
        
        ArrayList<Integer> list = new ArrayList<>();
                
        for(int i = first; i <= last; i++){
            list.add(i);
        }
        
        return list;
    }
    
    private ArrayList<Integer> arrayForComboTryNumber (int a, int b, int c, TextField d, TextField e, TextField f) {
        
        int first = a + b + c;
        int last = 0;
        
        if(!d.getText().equals("")) {
            last += Integer.valueOf(d.getText());
        }
        
        if(!e.getText().equals("")) {
            last += Integer.valueOf(e.getText());
        }
        
        if(!f.getText().equals("")) {
            last += Integer.valueOf(f.getText());
        }
        
        ArrayList<Integer> list = arrayForCombo(first, last);

        return list;
    }
    
    private void setCountComboValues() {
        
        countCombo.getItems().clear();
        countCombo.getItems().addAll(arrayForComboTryNumber(redCombo.getValue(),
                yellowCombo.getValue(), blueCombo.getValue(),
                redText, yellowText, blueText));
        countCombo.getSelectionModel().selectFirst();
        
    }  
}
