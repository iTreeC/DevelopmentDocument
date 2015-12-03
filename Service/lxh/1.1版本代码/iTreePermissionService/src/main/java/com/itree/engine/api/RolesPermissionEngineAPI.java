/**
 * @info 
 * @author ÀîÏş»¶
 * @time 2015.10.28
 */
package com.itree.engine.api;

import java.util.List;

public interface RolesPermissionEngineAPI extends Engine {

	public Boolean update(int id, List<Integer> id2);

	public List<Integer> getPermissionID(int rid);

	public Boolean cando(int id, int id2);
}
