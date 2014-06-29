package com.lojikstudio.animatchy;

import android.graphics.Bitmap;


public class Card {
	
	public Bitmap CardFront;
	  public Bitmap CardBack;
	  public String Tag;
	  public boolean IsFlipped;
	  public Integer selectedCard;

	
	public Card(){
		CardFront = null;
	    CardBack = null;
	    Tag = "";
	    IsFlipped = false;
	    selectedCard = null;
	}
	
	  public Card (Bitmap cFront, Bitmap cBack, String cTag)
	  {
	    CardFront = cFront;
	    CardBack = cBack;
	    Tag = cTag;
	    IsFlipped = false;
	    selectedCard = null;
	  }

	  public void SetFlipped(boolean flipped)
	  {
	    IsFlipped = flipped;
	  }
	  
	  public boolean IsFlipped()
	  {
	     if (IsFlipped) return true;
	     return false;
	  }
	  
	  public Integer selectedCard()
	  {
		return selectedCard;
	  }
}
