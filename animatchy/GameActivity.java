package com.lojikstudio.animatchy;

import java.util.Random;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class GameActivity extends Activity {
	public ImageView[] iView;
	public Card card;
	public Card[] cards;
	public int iview=-1;
	Resources res;
	private Bitmap baseCard;
	private Bitmap[] animal;
	private String[] animalTag;
	public Bitmap cFront;
	private ImageView[] selectedView = new ImageView[2];
	public int cardsFlipped=0;
	public int sv=0;
	public int sc=0;
	public enum gamestate {
		noCards, oneCard, twoCards;
	}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		res = getResources();
		setImageViews();
		//shuffleMatchCards(cards);
	}
	public void shuffleMatchCards (){
			Random randomGenerator = new Random();
			for (int i =cards.length-1; i >0; i--){
				int index = randomGenerator.nextInt(i+1);
				Card a = cards[index];
				cards[index] = cards[i];
				cards[i] = a;
			}
		}

	public boolean checkForMatch (){
			disableOnClick();
			boolean isMatched = false;
								if(selectedView[0].getTag()==selectedView[1].getTag()){
									isMatched = true;
								} else {
									isMatched = false;
								}
			cardsFlipped=0;
			sv=0;
			return isMatched;
		}
	
		private void disableOnClick() {
			for(int i=0;i<8;i++){
				iView[i].setClickable(false);
		}
		
	}

		/*
		@Override
		protected void onPostExecute(Boolean result){
			if(result==true){
				selectedView[0].setVisibility(4);
				selectedView[1].setVisibility(4);
				cardsFlipped=0;
			}
			 else {
				selectedView[0].setImageBitmap(baseCard);
				selectedView[0].setTag(baseCard);
				selectedView[1].setImageBitmap(baseCard);
				selectedView[1].setTag(baseCard);
				cardsFlipped=0;
			}
			}
		}
		*/
		
	


	

	private void setDeck() {
		cards = new Card[iView.length];
		boolean empty = true;
		int dupDeck =4;
		int checkAnimal=0;
		Random randomGenerator = new Random();
	    for (int cardIndex = 0; cardIndex < (iView.length/2); cardIndex++)
	    { int randomAnimal = randomGenerator.nextInt(animal.length);
	    if(empty != true && cards[cardIndex] != cards[0])
	    	while (animal[randomAnimal]==cards[checkAnimal].CardFront){		
	    	randomAnimal = randomGenerator.nextInt(animal.length);
	    	checkAnimal++;
	    	if(checkAnimal > cards.length){
	    		break;
	    	}
	    }
	      cards[cardIndex] = new Card(animal[randomAnimal], baseCard,animalTag[randomAnimal]);
		  cards[dupDeck] = new Card(animal[randomAnimal], baseCard,animalTag[randomAnimal]);
		  empty = false;
		  dupDeck++;
		  }
	}

	public Bitmap setBaseImage() {
		baseCard = BitmapFactory.decodeResource(res,R.drawable.animal_base_card);
		return baseCard;
		
	}

	public void setTags() {
		animalTag = new String[12];
		animalTag[0]="monkey";
		animalTag[1]="zebra";
		animalTag[2]="hippo";
		animalTag[3]="tiger";
		animalTag[4]="bear";
		animalTag[5]="deer";
		animalTag[6]="elephant";
		animalTag[7]="flamingo";
		animalTag[8]="lion";
		animalTag[9]="panda";
		animalTag[10]="giraffe";
		animalTag[11]="crocodile";
		
	}
	
	public void setOnClickListeners(ImageView viewClick){
		viewClick.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				iview++;
				new setClickedImage().execute();
			}
			
		});
	}
	
	public class setClickedImage extends AsyncTask<Void, Void, Integer> {

		@Override
		protected Integer doInBackground(Void... params) {
			return null;
		
			
		}
		@Override
		protected void onPostExecute(Integer result){
			int p = iview;
			iView[p].setImageBitmap(cards[p].CardFront);
			iView[p].setTag(cards[p].Tag);
			cards[p].IsFlipped = true;
			super.onPostExecute(result);
		}
		
		
	}

	private void setAnimals() {
		animal = new Bitmap[12];
		animal[0] = BitmapFactory.decodeResource(res, R.drawable.monkey);
		animal[1]=BitmapFactory.decodeResource(res, R.drawable.zebra);
		animal[2]=BitmapFactory.decodeResource(res, R.drawable.hippo);
		animal[3]=BitmapFactory.decodeResource(res, R.drawable.tiger);
		animal[4]=BitmapFactory.decodeResource(res, R.drawable.bear);
		animal[5]=BitmapFactory.decodeResource(res, R.drawable.deer);
		animal[6]=BitmapFactory.decodeResource(res, R.drawable.elephant);
		animal[7]=BitmapFactory.decodeResource(res, R.drawable.flamingo);
		animal[8]=BitmapFactory.decodeResource(res, R.drawable.lion);
		animal[9]=BitmapFactory.decodeResource(res, R.drawable.panda);
		animal[10]=BitmapFactory.decodeResource(res, R.drawable.giraffe);
		animal[11]=BitmapFactory.decodeResource(res, R.drawable.crocodile);
		
	}

	public void returnSelectedView() {
		selectedView[sv]=iView[iview];
		selectedView[sv].setTag(iView[iview].getTag());
	}

	public void checkGameState() {
			switch(cardsFlipped){
			case 1:
				sv++;
				cardsFlipped++;
			case 2:
				sv++;
				cardsFlipped++;
			case 3:
				checkForMatch();
			}
	}

	private void findViews() {
			iView = new ImageView[8];
			iView[0] = (ImageView) findViewById(R.id.imageView1);
			iView[1] = (ImageView) findViewById(R.id.imageView2);
			iView[2] = (ImageView) findViewById(R.id.imageView3);
			iView[3] = (ImageView) findViewById(R.id.imageView4);
			iView[4] = (ImageView) findViewById(R.id.imageView5);
			iView[5] = (ImageView) findViewById(R.id.imageView6);
			iView[6] = (ImageView) findViewById(R.id.imageView7);
			iView[7] = (ImageView) findViewById(R.id.imageView8);
		}
		


	private void setImageViews() {
		setBaseImage();
		setAnimals();
		setTags();
		findViews();
		setDeck();
		shuffleMatchCards();
		for(int i=0;i<8;i++){
			iView[i].setImageBitmap(baseCard);
			iView[i].setId(i);
			setOnClickListeners(iView[i]);
	}
	}
}




