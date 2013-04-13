                                                                     
                                                                     
                                                                     
                                             
import java.awt.*;


public class CondMazeLab extends Maze {

	public void step() {			
		// ADD YOUR CODE HERE to move PussInBoots.
		// Note that your code need only consider
		// what to do to take a single step. Our
		// program will repeat this method to make
		// PussInBoots take multiple steps through
		// the maze.
		// Note: No looping statements are needed!
		if (puss.isFacingWall())         //Make sure that Puss can !FacingWall.
		{
			puss.left();
			if(puss.isFacingWall())
			{
				puss.right();
				puss.right();
			}
		}
		if (!puss.isFacingWall())        //Moving forward when Puss do not facing wall.
		{
			//puss.left();                 //Error here.********************************
			//if(puss.isFacingWall())
				//puss.right();
			if(!puss.isFacingWall())
			{
			    if(puss.isFacingGully())	
				{	
					puss.takeOffBoots();
					puss.stopTipToe();
					puss.jump();
					if (puss.isFacingWall())         //Make sure that Puss can !FacingWall.
					{
						puss.left();
						if(puss.isFacingWall())
						{
							puss.right();
							puss.right();
						}
					}
					else if(!puss.isFacingWall())   // Try it
					{	
						puss.left();
						if(puss.isFacingWall())
							puss.right();
					}
				}
				else if(puss.isFacingMud())
				{	
					puss.stopTipToe();
					puss.putOnBoots();
					puss.forward();	
					if (puss.isFacingWall())         //Make sure that Puss can !FacingWall.
					{
						puss.left();
						if(puss.isFacingWall())
						{
							puss.right();
							puss.right();
						}
					}
					else if(!puss.isFacingWall())   // Try it
					{	
						puss.left();
						if(puss.isFacingWall())
							puss.right();
					}
				}
				else if(puss.isFacingDog())
				{	
					puss.takeOffBoots();
					puss.startTipToe();
					puss.forward();
					if (puss.isFacingWall())         //Make sure that Puss can !FacingWall.
					{
						puss.left();
						if(puss.isFacingWall())
						{
							puss.right();
							puss.right();
						}
					}
					else if(!puss.isFacingWall())   // Try it
					{	
						puss.left();
						if(puss.isFacingWall())
							puss.right();
					}
				}
				
				else
				{
					puss.forward();
					if(puss.isFacingWall())
					{
						puss.left();
						if(puss.isFacingWall())
						{
							puss.right();
							puss.right();
						}
					}
					else if(!puss.isFacingWall())   // Try it
					{	
						puss.left();
						if(puss.isFacingWall())
							puss.right();
					}
					
					/*else
						puss.right();*/
				}
			    /*if (puss.isFacingWall())     //Try to turn right to move forward.
				{
					puss.right();	
					if(puss.isFacingWall())
						puss.left();
					/*if (puss.isFacingWall()) //Turn Puss direction if facing wall.
					{
						puss.left();
					    puss.left();
					}*/
				//}
		    }
			/*else                           
			{
				puss.left();
				if(puss.isFacingGully())	
				{	
					puss.takeOffBoots();
					puss.stopTipToe();
					puss.jump();
				}
				else if(puss.isFacingMud())
				{	
					puss.stopTipToe();
					puss.putOnBoots();
					puss.forward();			
				}
				else if(puss.isFacingDog())
				{	
					puss.takeOffBoots();
					puss.startTipToe();
					puss.forward();
				}
				
				else
				{
					puss.forward();
				}
			}*/
		}                                //End of if !Facing Wall 
	}		

	public CondMazeLab() {
		super(true);
	}

	public static void main(String[] args) {
		CondMazeLab myMaze = new CondMazeLab();	
	}
}
