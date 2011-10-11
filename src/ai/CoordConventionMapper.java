package ai;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import ai.CoordConventionMapper.Mapping;

public class CoordConventionMapper {

	private HashMap<Integer,Mapping> map;
	
	/**
	 * Default constructor
	 */
	public CoordConventionMapper(){
		map = new HashMap<Integer, Mapping>();
		setupNumbers(); 
	}
	
	/**
	 * Converts the integer number into the appropriate
	 * coordinate number
	 * 
	 * @param num - The part of the board to retrieve the coordinate
	 * @return the coordinate converted
	 */
	public Integer[] getCoordOf(Integer num){		
		return map.get(num).getSet();
	}
	
	public void setupNumbers(){
		map.put(1,new Mapping(0,1,1));
		map.put(2,new Mapping(0,3,2));
		map.put(3,new Mapping(0,5,3));
		map.put(4,new Mapping(0,7,4));
		map.put(5,new Mapping(1,0,5));
		map.put(6,new Mapping(1,2,6));
		map.put(7,new Mapping(1,4,7));
		map.put(8,new Mapping(1,6,8));
		map.put(9,new Mapping(2,1,9));
		map.put(10,new Mapping(2,3,10));
		map.put(11,new Mapping(2,5,11));
		map.put(12,new Mapping(2,7,12));
		map.put(13,new Mapping(3,0,13));
		map.put(14,new Mapping(3,2,14));
		map.put(15,new Mapping(3,4,15));
		map.put(16,new Mapping(3,6,16));
		map.put(17,new Mapping(4,1,17));
		map.put(18,new Mapping(4,3,18));
		map.put(19,new Mapping(4,5,19));
		map.put(20,new Mapping(4,7,20));
		map.put(21,new Mapping(5,0,21));
		map.put(22,new Mapping(5,2,22));
		map.put(23,new Mapping(5,4,23));
		map.put(24,new Mapping(5,6,24));
		map.put(25,new Mapping(6,1,25));
		map.put(26,new Mapping(6,3,26));
		map.put(27,new Mapping(6,5,27));
		map.put(28,new Mapping(6,7,28));
		map.put(29,new Mapping(7,0,29));
		map.put(30,new Mapping(7,2,30));
		map.put(31,new Mapping(7,4,31));
		map.put(32,new Mapping(7,6,32));
	}

	public int returnCoord(int x, int y){
		int i = -1;
		for(Mapping k : map.values()){
			if(k.x == x && k.y == y)
				return k.keyVal;
		}
		return i;
	}
	class Mapping{
		public int keyVal;
		public Integer x, y;
		public Mapping(){
			
		}
		public Mapping(int i, int k, int val){
			this.x = i;
			this.y = k;
			this.keyVal = val;
		}
		public Integer[] getSet(){
			return new Integer[] {x,y};
		}
	}
}
