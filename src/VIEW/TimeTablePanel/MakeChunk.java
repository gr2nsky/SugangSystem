package VIEW.TimeTablePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import MODEL.data.Lecture;
import MODEL.data.User;

public class MakeChunk extends JPanel{
	
	public static JLabel[][] chunk;
	public JLabel[][][] chunkSet;
	
	public MakeChunk() {
		setLayout(new GridLayout(9,6));
		chunk = new JLabel[9][6];
		Dimension dim = new Dimension(120, 80);
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 6; j++) {
				chunk[i][j] = new JLabel();
				chunk[i][j].setPreferredSize(dim);
				chunk[i][j].setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
				add(chunk[i][j]);
			}
		}
		setCategory();
		inputLecList();
	}
	
	public void setCategory() {
		String[] date = {"월", "화", "수", "목", "금"};
		String[] time = {"1", "2", "3","4","5", "6","7","8"};
		for(int i = 1; i < 6; i++) {
			chunk[0][i].setText(date[i-1]);
			chunk[0][i].setHorizontalAlignment(0);
			chunk[0][i].setFont(new Font("굴림", 0, 20));
		}
		for(int i = 1; i < 9; i++) {
			chunk[i][0].setText(time[i-1]);
			chunk[i][0].setHorizontalAlignment(0);
			chunk[i][0].setFont(new Font("굴림", 0, 20));
		}
	}
	
	public void inputLecList() {
		ArrayList<Lecture> lecList = User.getManager();
		for (Lecture l : lecList)
			 makeChunkSet(l);
	}
	
	public static void delChunkSet(Lecture l) {
		int date = calDate(l.day);
		int time[] = calTime(l.time);
		
		JLabel[] labels = calLabel(date, time);
		String[] inputData = {l.name, l.room, l.professor};
		
		for(int i = 0; i < labels.length; i++) {
			labels[i].setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
			labels[i].setText("");
		}
	}
	
	public static void makeChunkSet(Lecture l) {
			int date = calDate(l.day);
			int time[] = calTime(l.time);
			
			JLabel[] labels = calLabel(date, time);
			String[] inputData = {l.name, l.room, l.professor};
			for(int i = 0; i < labels.length; i++) {
				//MatteBorder (int top, int left, int bottom, int right, Color matteColor)
				if(i == 0) {
					labels[i].setBorder(new MatteBorder(1, 1, -1, 1, Color.black));
				}
				else if(i == labels.length-1) {
					labels[i].setBorder(new MatteBorder(-1, 1, 1, 1, Color.black));
				}
				else {
					labels[i].setBorder(new MatteBorder(-1, 1, 0, 1, Color.black));
				}
				
				if(i < 3) {
					labels[i].setText(inputData[i]);
				}
				labels[i].setHorizontalAlignment(JLabel.CENTER);
			}
	}
	
	public static JLabel[] calLabel(int date, int[] time) {
		int len = time.length;
		JLabel[] labels = new JLabel[len];
		for(int i = 0; i < len; i++) {
			labels[i] = chunk[time[i]][date];
		}
		
		return labels;
	}
	
	public static int[] calTime(String s) {
		int len = s.length();
		int[] time = new int[len];
		
		String[] split = s.split("");
		for(int i = 0; i < len; i++) {
			time[i] = Integer.parseInt(split[i]);
		}
		return time;
	}
	public static int calDate(String s) {
		switch(s) {
			case "월" :
				return 1;
			case "화" :
				return 2;
			case "수" :
				return 3;
			case "목" :
				return 4;
			case "금" :
				return 5;
			default :
				return 0;
		}
	}
	
	
}
