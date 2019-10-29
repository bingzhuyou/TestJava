package com.chaos.TestJava.AlarmRuleEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TIC_NEGRP_LIST {

	private long[] ne_group_ids = null;
	private final HashMap<Long, HashSet<Long>> NEGRP_Map = new HashMap<Long, HashSet<Long>>();

	public String getSizeStr() {
		int ne_group_Id_mapSize = 0;
		if (ne_group_ids != null) {
			ne_group_Id_mapSize = ne_group_ids.length;
		}

		int negrpSize = NEGRP_Map.size();

		return ne_group_Id_mapSize + "#" + negrpSize;

	}

	public void addInt_Id(Long ne_group_id, Long int_id) {
		HashSet<Long> int_idV = NEGRP_Map.get(ne_group_id);
		if (int_idV != null) {
			int_idV.add(int_id);
		} else {
			int_idV = new HashSet<Long>();
			int_idV.add(int_id);
			NEGRP_Map.put(ne_group_id, int_idV);
		}
	}

	public HashSet<Long> getIntidSetByNeGroupId(Integer ne_group_id) {

		if (ne_group_id == null) {
			return null;
		}
		Long long_ne_group_id = Long.valueOf(ne_group_id);
		return NEGRP_Map.get(long_ne_group_id);
	}

	public HashSet<Long> getIntidSetByNeGroupId(Long ne_group_id) {
		if (ne_group_id == null) {
			return null;
		}
		return NEGRP_Map.get(ne_group_id);
	}

	public void change() {

		Long[] NE_GROUP_IDS = NEGRP_Map.keySet().toArray(new Long[0]);
		ne_group_ids = new long[NE_GROUP_IDS.length];
		for (int i = 0; i < NE_GROUP_IDS.length; i++) {
			ne_group_ids[i] = NE_GROUP_IDS[i].longValue();

		}
		Arrays.sort(ne_group_ids);
	}

	public String getIntId(String ne_group_ids) {
		String[] ids = ne_group_ids.split(",");
		String V = "";
		for (int i = 0; i < ids.length; i++) {
			Long id = Long.parseLong(ids[i]);
			HashSet<Long> int_idV = NEGRP_Map.get(id);
			if (int_idV != null) {
				V = V + "," + int_idV.toString();
			}
		}
		return V;
	}

	public List<String> scriptNeGroupIdList() {
		List<String> return_list = new ArrayList<String>();

		Iterator<Entry<Long, HashSet<Long>>> it = NEGRP_Map.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<Long, HashSet<Long>> entry = it.next();

			Long key = entry.getKey();

			HashSet<Long> value = entry.getValue();

			StringBuilder neGroupScript = new StringBuilder();

			neGroupScript.append(key).append("=").append(value);

			return_list.add(neGroupScript.toString());

		}

		return return_list;

	}
}
