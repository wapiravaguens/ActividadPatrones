/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Estudiante
 */
public class PooledObjectPool {
	private static long expTime = 6000;//6 seconds
	public static HashMap<Waiter, Long> available = new HashMap<Waiter, Long>();
	public static HashMap<Waiter, Long> inUse = new HashMap<Waiter, Long>();
	
	
	public synchronized static Waiter getObject() {
		long now = System.currentTimeMillis();
		if (!available.isEmpty()) {
			for (Map.Entry<Waiter, Long> entry : available.entrySet()) {
				if (now - entry.getValue() > expTime) { //object has expired
					popElement(available);
				} else {
					Waiter po = popElement(available, entry.getKey());
					push(inUse, po, now); 
					return po;
				}
			}
		}

		// either no Waiter is available or each has expired, so return a new one
		return createPooledObject(now);
	}	
	
	private synchronized static Waiter createPooledObject(long now) {
		Waiter po = new Waiter();
		push(inUse, po, now);
		return po;
        }

	private synchronized static void push(HashMap<Waiter, Long> map,
			Waiter po, long now) {
		map.put(po, now);
	}

	public static void releaseObject(Waiter po) {
		cleanUp(po);
		available.put(po, System.currentTimeMillis());
		inUse.remove(po);
	}
	
	private static Waiter popElement(HashMap<Waiter, Long> map) {
		 Map.Entry<Waiter, Long> entry = map.entrySet().iterator().next();
		 Waiter key= entry.getKey();
		 //Long value=entry.getValue();
		 map.remove(entry.getKey());
		 return key;
	}
	
	private static Waiter popElement(HashMap<Waiter, Long> map, Waiter key) {
		map.remove(key);
		return key;
	}
	
	public static void cleanUp(Waiter po) {
		po.setPizzaBuilder(null);
	}
}
