/**
 * Walter Michelin
 * 
 * Project 2
 * November 10th 2013
 */
import java.lang.Math;
import java.io.*;


public class Simulator
{
	
	private LinkedQueue<Vehicle> EMain = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> EMainTurn = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> WMain = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> WMainTurn = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> NChurch = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> NChurchTurn = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> SChurch = new LinkedQueue<Vehicle>();
	private LinkedQueue<Vehicle> SChurchTurn = new LinkedQueue<Vehicle>();
	private int counter = 0;
	private int time = 0;
	private boolean movingChurch = false;
	String output = "";
	
	
	public Simulator()
	{}
	
	/**
	 * Calls all other methods
	 * creates 120 vehicles and populates proper queues
	 * to specification
	 */
	public void Simulate()
	{
		
		write("---Start of simulation, time set to 0\n");
		int num = (int)(Math.random() * (13 - 7)) + 7;
		changeLight();
		populate(num);
		do
		{
			move(2);
			num = (int)(Math.random() * (16 - 8)) + 8;
			if(!(counter > 120))
				populate(num);
			changeLight();
			move(3);
			num = (int)(Math.random() * (16 - 3)) + 3;
			if(!(counter > 120))
				populate(num);
			changeLight();
		}
		while(!queuesEmpty());
		
		output();
	}
	/**
	 * Creates number of vehicles based on reference integer
	 */
	public void populate(int i)
	{
		
		Vehicle car = new Vehicle();
		int localcount = 0;
		while(localcount < i)
		{
			if(counter < 120)
			{
				car = new Vehicle();
				car.setNumber(counter);
				car.setArrivalTime(time);
				pickQueue(car);
				counter ++;
			}
			
			localcount ++;
		}		
	}
	/**
	 * checks all queues
	 * returns true if they are all empty
	 */	
	public boolean queuesEmpty()
	{
		boolean allEmpty;
		
		allEmpty = EMain.isEmpty();
		
		if(allEmpty)
			allEmpty = EMainTurn.isEmpty();
		if(allEmpty)
			allEmpty = WMain.isEmpty();
		if(allEmpty)
			allEmpty = WMainTurn.isEmpty();
		if(allEmpty)
			allEmpty = NChurch.isEmpty();
		if(allEmpty)
			allEmpty = NChurchTurn.isEmpty();
		if(allEmpty)
			allEmpty = SChurch.isEmpty();
		if(allEmpty)
			allEmpty = SChurchTurn.isEmpty();
			
		return allEmpty;
	}
	/**
	 * time is advanced here
	 * checks which light is on
	 * moves as many times as specified by "i"
	 * every movement moves time forward 3
	 * also catches emptycollectionexceptions
	 */
	public void move(int i)
	{
				
		Vehicle car = new Vehicle();
		int localcount = 0;
		while(localcount < i)
		{
			time+=3;
			if(movingChurch)
			{
				try
				{
					car = NChurch.dequeue();
					car.setDepartureTime(time);
					write(car.toString());
					write("\n");
				}
				catch(EmptyCollectionException e){}

				try
				{	
					car = NChurchTurn.dequeue();
					car.setDepartureTime(time);
					write(car.toString());
					write("\n");
				}
				catch(EmptyCollectionException e){}

				try
				{	
					car = SChurch.dequeue();
					car.setDepartureTime(time);
					write(car.toString());
					write("\n");
				}
				catch(EmptyCollectionException e){}

				try
				{
					car = SChurchTurn.dequeue();
					car.setDepartureTime(time);
					write(car.toString());
					write("\n");
				}
				catch(EmptyCollectionException e){}
			
			}
			else
			{
				try
				{
					car = EMain.dequeue();
					car.setDepartureTime(time);
					write(car.toString());
					write("\n");
				}	
				catch(EmptyCollectionException e){}

				try
				{
					car = EMainTurn.dequeue();
					car.setDepartureTime(time);
					write(car.toString());
					write("\n");
				}
				catch(EmptyCollectionException e){}

				try
				{
					car = WMain.dequeue();
					car.setDepartureTime(time);
					write(car.toString());
					write("\n");
				}
				catch(EmptyCollectionException e){}
				try
				{
					car = WMainTurn.dequeue();
					car.setDepartureTime(time);
					write(car.toString());
					write("\n");
				}
				catch(EmptyCollectionException e){}
			}

				localcount ++;
		}
		
	}
	/**
	 * writes to simulator variable output
	 * for later output to text file
	 */	
	public void write(String input)
	{
		output += input;	
	}
	/**
	* Pushes output to "output.txt"
	*/	
	public void output()
	{
		try
		{
			PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
			out.println(output);
			out.close();
		}
		catch(IOException e)
		{
			System.err.println("Error writing to file");
		}
	}
	/**
	 * returns time
	 */
	public int getTime()
	{
		return time;
	}
	/**
	 * changes street to be advanced
	 */
	public void changeLight()
	{
		if(movingChurch)
		{
			
			movingChurch = false;
			if(!(counter == 0))
				write("\n");
			write("---Light changed. Now processing east/west-bound traffic---\n");
		}
		else
		{
			movingChurch = true;
			if(!(counter == 0))
				write("\n");
			write("---Light changed. Now processing north/south-bound traffic---\n");	
		}
	}
	
	/**
	 *	Takes all the information from car and places car in appropriate queue
	 */
	public void pickQueue(Vehicle car)
	{
		if(car.isTurning())
		{
			if(car.getDirection() == Direction.E)
			{
				EMainTurn.enqueue(car);
			}
			if(car.getDirection() == Direction.W)
			{
				WMainTurn.enqueue(car);
			}
			if(car.getDirection() == Direction.N)
			{
				NChurchTurn.enqueue(car);
			}
			if(car.getDirection() == Direction.S)
			{
				SChurchTurn.enqueue(car);
			}
		}
		else
		{
			if(car.getDirection() == Direction.E)
			{
				EMain.enqueue(car);
			}
			if(car.getDirection() == Direction.W)
			{
				WMain.enqueue(car);
			}
			if(car.getDirection() == Direction.N)
			{
				NChurch.enqueue(car);
			}
			if(car.getDirection() == Direction.S)
			{
				SChurch.enqueue(car);
			}
		}
	}	
}