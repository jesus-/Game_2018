package application.game2048;

import application.game2048.R;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity{

	private Board board;
	private TextView [][]tvBoard;
	private final int[][] tvIds ={{R.id.tv00,R.id.tv01,R.id.tv02,R.id.tv03},
			{R.id.tv10,R.id.tv11,R.id.tv12,R.id.tv13},
			{R.id.tv20,R.id.tv21,R.id.tv22,R.id.tv23},
			{R.id.tv30,R.id.tv31,R.id.tv32,R.id.tv33}};
	private TextView score;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout rel = (RelativeLayout) findViewById(R.id.completeLayout);
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
		board = new Board();
		score = (TextView) findViewById(R.id.textResult);
		tvBoard = new TextView[4][4];
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
					tvBoard[i][j]= (TextView)findViewById(tvIds[i][j]);
		board.generateNewRamdom();
		board.generateNewRamdom();
		showBoard();
		rel.setOnTouchListener( new OnSwipeTouchListener(this) {
		    public void onSwipeTop() {
		    	board.topSwipe();
		    	showBoard();
		    }
		    public void onSwipeRight() {
		    	board.rigthSwipe();
		    	showBoard();
		    }
		    public void onSwipeLeft() {
		    	board.leftSwipe();
		    	showBoard();
		    }
		    public void onSwipeBottom() {
		    	board.bottomSwipe();
		    	showBoard();
		    }

	
		});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_main, container,
//					false);
//			return rootView;
//		}
//	}
	public void showBoard(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(board.getValue(i,j).compareTo(tvBoard[i][j].getText().toString())!=0){
					tvBoard[i][j].setText(board.getValue(i,j));
					tvBoard[i][j].startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
//					if(board.getValue(i,j).compareTo("2")==0)
//							tvBoard[i][j].setBackgroundColor(0x58FA58);

				}
			}
		}
		score.setText("Score: "+board.getScore());
		
	}


}
