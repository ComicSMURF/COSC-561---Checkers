package ai;

import java.util.HashMap;

import ai.AIHandling.Mapping;

public class AIHandling {
	private HashMap<Integer,Mapping> map;
	
	public AIHandling(){
		map = new HashMap<Integer, Mapping>();
		setupNumbers(); 
	}
	
	public Integer[] convertCoord(Integer num){		
		return map.get(num).getSet();
	}
	
	public void setupNumbers(){
		map.put(1,new Mapping(0,1));
		map.put(2,new Mapping(0,3));
		map.put(3,new Mapping(0,5));
		map.put(4,new Mapping(0,7));
		map.put(5,new Mapping(1,0));
		map.put(6,new Mapping(1,2));
		map.put(7,new Mapping(1,4));
		map.put(8,new Mapping(1,6));
		map.put(9,new Mapping(2,1));
		map.put(10,new Mapping(2,3));
		map.put(11,new Mapping(2,5));
		map.put(12,new Mapping(2,7));
		map.put(13,new Mapping(3,0));
		map.put(14,new Mapping(3,2));
		map.put(15,new Mapping(3,4));
		map.put(16,new Mapping(3,6));
		map.put(17,new Mapping(4,1));
		map.put(18,new Mapping(4,3));
		map.put(19,new Mapping(4,5));
		map.put(20,new Mapping(4,7));
		map.put(21,new Mapping(5,0));
		map.put(22,new Mapping(5,2));
		map.put(23,new Mapping(5,4));
		map.put(24,new Mapping(5,6));
		map.put(25,new Mapping(6,1));
		map.put(26,new Mapping(6,3));
		map.put(27,new Mapping(6,5));
		map.put(28,new Mapping(6,7));
		map.put(29,new Mapping(7,0));
		map.put(30,new Mapping(7,2));
		map.put(31,new Mapping(7,4));
		map.put(32,new Mapping(7,6));
	}
	class Mapping{
		public Integer x, y;
		public Mapping(){
			
		}
		public Mapping(int i, int k){
			this.x = i;
			this.y = k;
		}
		public Integer[] getSet(){
			return new Integer[] {x,y};
		}
	}
}
