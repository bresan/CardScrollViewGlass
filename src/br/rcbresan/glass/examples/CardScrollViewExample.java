package br.rcbresan.glass.examples;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

/*
 * This is an example about how to use a CardScrollView on Google Glass.
 * 
 * I hope you enjoy it!
 * 
 * Developed by Rodrigo Bresan (GitHub: rcbresan)
 * 
 * */

public class CardScrollViewExample extends Activity {

	private ArrayList<Card> cardsArray = new ArrayList<Card>();
	private GestureDetector mGestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayList<String> cardsContent = new ArrayList<String>();
		cardsContent.add("Card #1");
		cardsContent.add("Card #2");
		cardsContent.add("Card #3");
		cardsContent.add("Card #4");
		cardsContent.add("Card #5");
		
		setCards(cardsContent);
	}

	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
		if (mGestureDetector != null) {
			return mGestureDetector.onMotionEvent(event);
		}
		return false;
	}

	private void setCards(ArrayList<String> content) {

		for (int i = 0; i < content.size(); i++) {
			Card newCard = new Card(this);
			newCard.setImageLayout(Card.ImageLayout.FULL); //we use a full-screen layout
			newCard.setText(content.get(i)); //set the card content
			newCard.setFootnote("Swipe to go to next screen");  //set a footnote into the card
			cardsArray.add(newCard);  //and finish by adding the new card into the array
		}
		
		CardScrollView csvCards = new CardScrollView(this);
		
		csaAdapter cvAdapter = new csaAdapter();
		csvCards.setAdapter(cvAdapter);
		csvCards.activate();
		setContentView(csvCards);
		csvCards.setOnItemClickListener(cvAdapter);
	}

	private class csaAdapter extends CardScrollAdapter implements OnItemClickListener {

		@Override
		public int getCount() {
			return cardsArray.size();
		}

		@Override
		public Object getItem(int position) {
			return cardsArray.get(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return cardsArray.get(position).getView();
		}

		@Override
		public int getPosition(Object item) {
			return cardsArray.indexOf(item);
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			//here you can set an action to be done when some card is selected
		}
	}

}
