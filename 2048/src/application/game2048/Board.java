package application.game2048;

import java.util.ArrayList;
import java.util.Random;

public class Board {
	
	private Square [] board;
	private ArrayList<Square> vacios;
	private int score;
	
	public Board(){
		board = new Square [16];
		vacios = new ArrayList<Square>();
		score =0;
		for (int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				board[i*4+j]=new Square(i,j,0);
				vacios.add(board[i*4+j]);
			}
		}
	}
	public void generateNewRamdom(){
		int random=0;
		Random randomGenerator = new Random();
		  random=randomGenerator.nextInt(vacios.size());
		  vacios.get(random).setValue(2);
		  vacios.remove(random);
		  
	}
	public String getValue(int row, int column){
		String value ="";
		value =Integer.toString(board[row*4+column].getValue());
		if(value.compareTo("0")==0)
			return "";
		return value;
		
	}
	public void topSwipe(){
		if(moveBlockUp()>0)
			generateNewRamdom();
		setSquaresToCanMove();
	}	
	public void bottomSwipe(){
		if(moveBlockDown()>0)
			generateNewRamdom();
		setSquaresToCanMove();		
	}
	public void leftSwipe(){
		if(moveBlockLeft()>0)
			generateNewRamdom();
		setSquaresToCanMove();			
	}
	public void rigthSwipe(){
		if(moveBlockRight()>0)
			generateNewRamdom();
		setSquaresToCanMove();			
	}	
	public boolean moveSquare(Square origin,Square destiny){
		boolean moved=false;
		if(origin.getValue()!=0 && destiny.getValue()==0){
			destiny.setValue(origin.getValue());
			origin.setValue(0);
			vacios.add(origin);
			vacios.remove(destiny);
			moved=true;
		}else if(origin.getValue()!=0 && origin.getValue()==destiny.getValue() && origin.isCanMove() && destiny.isCanMove()){
			score +=origin.getValue()+destiny.getValue();
			destiny.setValue(origin.getValue()+destiny.getValue());
			origin.setValue(0);
			vacios.add(origin);
			destiny.setCanMove(false);
			moved=true;
		}
		return moved;
	}
	//rows 0 to 3 and they have to be together and in range
	public int moveRow(int rowOrigin,boolean moveUp){
		int moved =0;
		if(moveUp){
			for(int i=0;i<4;i++)
				if(moveSquare(board[rowOrigin*4+i],board[rowOrigin*4-4+i]))
					moved++;				
		}
		else{
			for(int i=0;i<4;i++)
				if(moveSquare(board[rowOrigin*4+i],board[rowOrigin*4+4+i]))
					moved++;			
		}
		return moved;
	}
	public int moveColumn(int columnOrigin,boolean moveRight){
		int moved =0;
		if(moveRight){
			for(int i=0;i<4;i++)
				if(moveSquare(board[i*4+columnOrigin],board[i*4+columnOrigin+1]))
					moved++;				
		}
		else{
			for(int i=0;i<4;i++)
				if(moveSquare(board[i*4+columnOrigin],board[i*4+columnOrigin-1]))
					moved++;			
		}
		return moved;
	}	
	public int moveBlockUp(){
		int moved=0;
		for(int j=0;j<3;j++)
			for (int i=1;i<4;i++)//rows we can move: 1,2,3
				moved +=moveRow(i,true);	
		return moved;
	}
	public int moveBlockDown(){
		int moved=0;
		for(int j=0;j<3;j++)
			for (int i =2;i>=0;i--)//rows we can move: 2,1,0
				moved +=moveRow(i,false);	
		return moved;
	}	
	public int moveBlockRight(){
		int moved=0;
		for(int j=0;j<3;j++)
			for (int i =2;i>=0;i--)//columns we can move: 2,1,0
				moved +=moveColumn(i,true);	
		return moved;
	}	
	public int moveBlockLeft(){
		int moved=0;
		for(int j=0;j<3;j++)
			for (int i =1;i<4;i++)//columns we can move: 1,2,3
				moved +=moveColumn(i,false);	
		return moved;
	}		
	public void setSquaresToCanMove()
	{
		for (int i=0;i<16;i++)
			board[i].setCanMove(true);
	}
	public String getScore(){
		return Integer.toString(score);
	}
}
