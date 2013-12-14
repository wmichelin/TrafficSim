/**
 * Walter Michelin
 * 
 * Project 2
 * November 10th 2013
 */
import java.lang.Math;

public class Vehicle
{
	/**
	 * Vehicle instance variables
	 */
	private int vehicleNumber;
	private Street street;
	private Direction direction;
	private int arrivalTime;
	private int departureTime;
	private boolean isTurning;
	
	/**
	* Default constructor
	* creates vehicle with random direction
	* randomly decides if vehicle is turning
	* sets street based on direction
	* arrival time will be set when vehicle is queued
	* departure time will be set when vehicle is dequeued
	*/
	
	public Vehicle()
	{
		vehicleNumber = 0;
		setDirection(1 + (int)(Math.random() * ((4 - 1) + 1)));
		setStreet();
		arrivalTime = 0;
		departureTime = 0;
		isTurning = (Math.random() < 0.5);	
	}
	
	/**
	 * Takes random integer from constructor
	 * Sets direction appropriately
	 */
	public void setDirection(int index)
	{
		if(index == 1)
			direction = Direction.N;
		else if(index == 2)
			direction = Direction.E;
		else if(index == 3)
			direction = Direction.S;
		else
			direction = Direction.W;
	}
	/**
	 * Direction must be set first in order to function properly
	 * Sets street based on direction
	 */
	public void setStreet()
	{
		if(direction == Direction.N || direction == Direction.S)
		{
			street = Street.Church;
		}
		else
			street = Street.Main;
	}
	/**
	 * Simulator calls this method
	 * using it's counter as parameter
	 * this method sets the created vehicle to the appropriate number
	 */
	
	public void setNumber(int i)
	{
		vehicleNumber = i;
	}
	
	/**
	 * returns vehicle number
	 */
	
	public int getNumber()
	{
		return vehicleNumber;
	}
	
	/**
	 * returns a Street variable of the objects street
	 */
	public Street getStreet()
	{
		return street;
	}
	
	/**
	 *returns the Direction the object is headed 
	 */
	
	public Direction getDirection()
	{
		return direction;
	}
	/**
	 * Returns a string representation of what direction
	 * the vehicle is headed
	 */
	
	public String writeDirection()
	{
		if(direction == Direction.N)
			return("northbound");
		else if(direction == Direction.E)
			return("eastbound");
		else if(direction == Direction.S)
			return("southbound");
		else
			return("westbound");
	}
	/**
	 * Changes direction
	 * should only be called if vehicle isTurning
	 */
	
	public void turn()
	{
		if(getDirection() == Direction.E)
		{
			setDirection(3);
		}
		if(getDirection() == Direction.W)
		{
			setDirection(1);
		}
		if(getDirection() == Direction.N)
		{
			setDirection(2);
		}
		if(getDirection() == Direction.S)
		{
			setDirection(4);
		}

	}
	/**
	 * Mutator to be called by Simulator
	 */
	public void setArrivalTime(int time)
	{
		arrivalTime = time;
	}
	/**
	 * returns arrival time integer
	 */
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	/**
	 * Mutator to be called by Simulator
	 */	
	public void setDepartureTime(int time)
	{
		departureTime = time;
	}	
	/**
	 * returns departure time for toString
	 */	
	public int getDepartureTime()
	{
		return departureTime;
	}
	/**
	 *	returns randomly generated isTurning
	 */	
	public boolean isTurning()
	{
		return isTurning;
	}
	/**
	 * returns properly formatted string representation of vehicle object
	 * based on spec
	 */
	public String toString()
	{
		String returnVal = "";
		returnVal += "[Time " + String.format("%02d", departureTime) + "] ";
		returnVal += "Vehicle #" + vehicleNumber;
		returnVal += " (" + writeDirection() + ")";
		if(isTurning)
		{
			returnVal += " turned right and headed ";
			turn();
			returnVal += writeDirection() + ".";
		}
		else
		{
			returnVal += " continued straight.";
		}
		
		returnVal += " Total wait time: " + String.format("%02d", 
					(departureTime - arrivalTime)) + " seconds.";
		
		return returnVal;
	}
	
}