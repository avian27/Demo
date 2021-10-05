package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AppController implements Initializable {
	
	@FXML
	private TextField folderLocation;
	@FXML
	private ListView<String> statsListView;
	@FXML
	private ListView<String> selectedItemListView;
	@FXML
	PieChart pieChart;
	@FXML
	Label pieDisplay;
	private ObservableList<Data> pieData = FXCollections.observableArrayList();
	private ObservableList<String> listData = FXCollections.observableArrayList();
	private ObservableList<String> selectedItemData = FXCollections.observableArrayList();
	
	String userInput = null;
	
	//need to find why this is working and line 
	ArrayList<String> diskData = new ArrayList<>();
	ArrayList<String> percentageData = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	@FXML
	void clickSubmit(ActionEvent event)
	{
		userInput = folderLocation.getText();	
		try
		{
			File directoryName = new File(userInput);
			DiskTraverse disk = new DiskTraverse();
			disk.traverse(directoryName);
			diskData = disk.filesDetails();
			listData.clear();
			//for (String temp: diskData)
			//{
			//	listData.add(temp);
			//}
			listData.addAll(diskData);
			pieData.clear();
			percentageData = disk.fileTypeStorageUsed();
			for (String temp: percentageData)
			{
				float tempPer;
				String sub = temp.substring((temp.indexOf(":"))+1);
				tempPer = Float.parseFloat(sub);
				String [] sub1 = temp.split(":");
				sub = sub1[0];
				pieData.add(new PieChart.Data(sub, tempPer));
			}
			pieChart.setData(pieData);
			pieChart.getData().stream().forEach(data -> data.getNode().addEventHandler(MouseEvent.ANY, e -> pieDisplay.setText(data.getName()+ ": " + String.format("%.2f",data.getPieValue())+ " %")));
			statsListView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)-> displaySelectedItemList(newValue,disk));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
		
		void displaySelectedItemList(String selectedValue, DiskTraverse diskobj)
		{
			
			ArrayList<String> ldata = new ArrayList<>();
			String [] temp = selectedValue.split(" ");
			selectedValue = temp[3];
			selectedItemData.clear();
			if(selectedValue.contains("Files"))
			{
				JOptionPane.showMessageDialog(null, "Select the File Type", "Information ",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				ldata = diskobj.returnFileList(selectedValue);
			}
			//for (String data : ldata)
			//{
			//	selectedItemData.add(data);
			//}
			selectedItemData.addAll(ldata);
		}
		

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		statsListView.setItems(listData);
		System.out.println("statslistview called");
		selectedItemListView.setItems(selectedItemData);
		
	}

}
