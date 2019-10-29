package com.chaos.TestJava.AlarmRuleEngine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AlgorithmBase<E> {
	public static Logger filterDetailsLogger = LoggerFactory.getLogger("filterDetails");
	// public E sourceobject = null;
	public Long[] tt2 = new Long[100];
	// static public int[] fielddef = new int[0];
	public int[] fielddef = new int[0];
	public HashMap<Integer, Integer> condition_defmap = new HashMap<Integer, Integer>();
	public static Logger logger = LoggerFactory.getLogger(AlgorithmBase.class);

	TIC_NEGRP_LIST TIC_NEGRP_LISTMgr = null;
	public RuleBaseOP<E> rb = null;

	// public boolean c[] = null;//new boolean[5000];

	abstract public void initalarmvalue(String alarmId, E e);

	abstract public void pre4();

	public AlgorithmBase() {
		for (int i = 0; i < fielddef.length; i++) {
			fielddef[i] = -1;
		}
	}

	public void InitAlgorithmBase() {
		for (int i = 0; i < fielddef.length; i++) {
			fielddef[i] = -1;
		}
	}

	public void addFieldDef(int def_id, int def_type, int iPos) {
		fielddef[def_id] = iPos;
		condition_defmap.put(def_id, def_type);
	}

	public String printFieldDef() {
		String retstr = "";
		for (int i = 0; i < fielddef.length; i++) {
			if (fielddef[i] != -1) {
				retstr = retstr + "[" + i + ":" + fielddef[i] + "]";
			}
		}
		return retstr;
	}

	public String printMap() {
		String retstr = "";
		if (rb != null && rb.map1 != null) {
			for (int i = 0; i < rb.map1.length; i++) {
				if (rb.map1[i] != null) {
					retstr = retstr + "[" + i + ":" + rb.map1[i] + "]";
				}
			}
		}
		return retstr;
	}

	boolean f_Vector(long field, Vector<Long> value, boolean IN) {
		// for(int i=0;i<value.size();i++){
		if (value.contains(field)) {
			return IN;
		}
		// }
		return !IN;
	}

	boolean f_8(Long negroupid, Long value, boolean IN) {
		HashSet<Long> values = TIC_NEGRP_LISTMgr.getIntidSetByNeGroupId(negroupid);
		if (values == null) {
			return !IN;
		}
		if (values.contains(value))
			return IN;
		else
			return !IN;
		/*
		 * boolean flag = f_Vector(value,values,IN); if(flag==IN) return IN;
		 * return !IN;
		 */
	}

	boolean f_8(Integer negroupid, Integer value, boolean IN) {
		if (value == null) {
			return false;
		}
		HashSet<Long> values = TIC_NEGRP_LISTMgr.getIntidSetByNeGroupId(negroupid);
		if (values == null) {
			return !IN;
		}
		long longValue = Long.valueOf(value);
		if (values.contains(longValue))
			return IN;
		else
			return !IN;
		/*
		 * boolean flag = f_Vector(value,values,IN); if(flag==IN) return IN;
		 * return !IN;
		 */
	}

	public void f_negroup_v(String alarmId, int idef_id, String value) {
		String key = value;
		int iPos = getPos(idef_id);
		Iterator<String> it = rb.map1[iPos].keySet().iterator();
		for (; it.hasNext();) {
			key = it.next();
			if (key == null || "".equals(key)) {
				continue;
			}
			if (value == null || "".equals(value)) {
				continue;
			}
			boolean flag = f_8(Long.parseLong(key), Long.parseLong(value), true);
			if (flag) {
				Vector<Integer> conditionPos = rb.map1[iPos].get(key);
				int conditionPosSize = 0;
				if (conditionPos != null) {
					conditionPosSize = conditionPos.size();
				}
				for (int i = 0; i < conditionPosSize; i++) {
					rb.c[conditionPos.get(i)] = true;
				}
			}
		}
	}

	public void f_negroup(String alarmid, int idef_id, int def_type, String targetFieldName, Object value) {
		try {

			// logger.error("@@@ alarmid:"+alarmid+"
			// ,idef_id:"+idef_id+",def_type:"+def_type+",targetFieldName:"+targetFieldName+",value:"+value);
			if (value == null)
				return;

			Long longValue = null;
			if (value instanceof Long) {
				longValue = (Long) value;
			} else if (value instanceof Integer) {
				Integer intValue = (Integer) value;
				longValue = Long.valueOf(intValue);
			} else if (value instanceof String) {
				String valueStr = (String) value;
				longValue = parseLong(valueStr);
			}
			if (longValue == null) {
				return;
			}
			String key = null;// String.valueOf(value);
			int iPos = getPos(idef_id);
			Iterator<String> it = rb.map1[iPos].keySet().iterator();
			Set<Integer> needRest = new HashSet<Integer>();
			while (it.hasNext()) {
				try {
					key = it.next();
					if (key == null || "".equals(key)) {
						continue;
					}
					if (value == null || "".equals(value)) {
						continue;
					}

					Long longKey = parseLong(key);
					if (longKey == null) {
						continue;
					}
					boolean flag = f_8(longKey, longValue, true);

					Vector<Integer> conditionPos = rb.map1[iPos].get(key);
					if (flag) {
						needRest.addAll(conditionPos);
					}

					// logger.error("@@@@
					// longKey:"+longKey+",longValue:"+longValue+",flag:"+flag+",conditionPos:"+conditionPos);
					int conditionPosSize = 0;
					if (conditionPos != null) {
						conditionPosSize = conditionPos.size();
					}
					for (int i = 0; i < conditionPosSize; i++) {
						rb.c[conditionPos.get(i)] = flag;
					}
				} catch (Exception e) {
					logger.error("f_negroup Exception", e);
				}

			}
			ResetInId(needRest);
		} catch (Exception e) {
			logger.error("f_negroup Exception", e);
		}
	}

	private void ResetInId(Set<Integer> needRest) {
		if (needRest != null && needRest.size() > 0) {
			for (Integer pos : needRest) {
				rb.c[pos] = true;
			}
		}
	}

	public void regula_expression1(String alarmId, int idef_id, String targetFieldName, String value) {
		String key = value;
		int iPos = getPos(idef_id);
		Iterator<String> it = rb.map1[iPos].keySet().iterator();
		for (; it.hasNext();) {
			key = it.next();
			// if(match(value,key)){
			// if(StringContains_SunDay(value,key)){
			if (value.indexOf(key) > -1) {
				Vector<Integer> conditionPos = rb.map1[iPos].get(key);
				int conditionPosSize = 0;
				if (conditionPos != null) {
					conditionPosSize = conditionPos.size();
				}
				for (int i = 0; i < conditionPosSize; i++) {
					rb.c[conditionPos.get(i)] = true;
				}
			}
		}
	}

	// char[] source = null;

	// boolean b[] = new boolean[65536];
	// int bb[][] = new int[65536][200];

	// String[] result = new String[500];
	// int resultlen = 0;
	// char[][] keyss = new char[700][];
	// boolean[] delkeyss = new boolean[700];

	// 婢舵碍膩閸栧綊鍘ら敍宀�波閺嬫粍鏂乺esult娑擄拷
	boolean myindexOf_5(String sources, int sourceOffset, int sourceCount, char[] target, int targetOffset,
			int minitargetCount, int fromIndex) {
		boolean f1 = myindexOf_5_old(sources, sourceOffset, sourceCount, target, targetOffset, minitargetCount,
				fromIndex);
		boolean f2 = myindexOf_5_patch(sources, sourceOffset, sourceCount, target, targetOffset, minitargetCount,
				fromIndex);
		return (f1 || f2);
	}

	boolean myindexOf_5_patch(String sources, int sourceOffset, int sourceCount, char[] target, int targetOffset,
			int minitargetCount, int fromIndex) {
		boolean ret = false;
		// for (int i = 1; i < rb.keyss_patch[0]; i++) {
		// String targetv = new String(rb.keyss[rb.keyss_patch[i]]);
		// String[] targetvv = targetv.split("\\.\\*");
		//
		// int flag = 0;
		// for (int j = 0; j < targetvv.length; j++) {
		// if (sources.indexOf(targetvv[j]) < 0) {
		// break;
		// }
		// flag++;
		// }
		// if (flag == targetvv.length) {
		// rb.result[rb.resultlen++] = targetv;
		// ret = true;
		// }
		// }
		return ret;
	}

	boolean myindexOf_5_old(String sources, int sourceOffset, int sourceCount, char[] target, int targetOffset,
			int minitargetCount, int fromIndex) {
		if (sourceCount < 1)
			return false;
		boolean flag = false;
		// minitargetCount = 1;
		// char[] sourceArray = sources.toCharArray();
		// // char first = target[targetOffset];
		// // int max = sourceOffset + (sourceCount - targetCount) + 1;
		// int max = sourceOffset + (sourceCount - minitargetCount) + 1;
		//
		// for (int deli = 0; deli < rb.delkeyss.length; deli++) {
		// rb.delkeyss[deli] = false;
		// }
		//
		//
		// rb.resultlen = 0;
		//
		// for (int i = sourceOffset + fromIndex; i < max; i++) {
		// /* Look for first character. */
		// if (rb.b[sourceArray[i]]) {
		// while (++i < max && rb.b[sourceArray[i]])
		// ;
		// }
		//
		// int targetCount = minitargetCount;
		//
		// int keycount = rb.bb[sourceArray[i]][0];
		// for (int index = 0; index < keycount; index++) {
		// target = rb.keyss[rb.bb[sourceArray[i]][index + 1]];
		// if (rb.delkeyss[rb.bb[sourceArray[i]][index + 1]])
		// continue;
		// targetCount = target.length;
		// int currmax = sourceOffset + (sourceCount - targetCount) + 1;
		// /* Found first character, now look at the rest of v2 */
		// // if(targetCount==1) return true;
		// if (i < currmax) {
		// int j = i;
		// int end = i + targetCount - 1;
		// for (int k = targetOffset + targetCount - 1; j < end &&
		// sourceArray[end] == target[k]; end--, k--)
		// ;
		//
		// if (j == end) {
		// /* Found whole string. */
		// rb.result[rb.resultlen++] = new String(target);
		// rb.delkeyss[rb.bb[sourceArray[i]][index + 1]] = true;
		// flag = true;
		// }
		// }
		// }
		// }
		return flag;
	}

	boolean myindexOf_98(String sources) {
		// boolean f1 = myindexOf_98_old(sourceOffset, sourceCount, target,
		// targetOffset, minitargetCount, fromIndex);
		boolean f2 = myindexOf_98_patch(sources);
		return f2;
	}

	boolean myindexOf_98_old(String sources, char[] target, int targetOffset, int minitargetCount, int fromIndex) {

		if (sources == null) {
			return false;
		}
		boolean flag = false;
		// char[] source = sources.toCharArray();
		//
		// int sourceOffset = 0;
		// int sourceCount = source.length;
		// if (sourceCount < 1)
		// return false;
		// minitargetCount = 1;
		// // char first = target[targetOffset];
		// // int max = sourceOffset + (sourceCount - targetCount) + 1;
		// int max = sourceOffset + (sourceCount - minitargetCount) + 1;
		//
		// for (int deli = 0; deli < rb.delkeyss_98.length; deli++) {
		// rb.delkeyss_98[deli] = false;
		// }
		//

		// rb.resultlen = 0;
		//
		// for (int i = sourceOffset + fromIndex; i < max; i++) {
		// /* Look for first character. */
		// if (rb.b_98[source[i]]) {
		// while (++i < max && rb.b_98[source[i]])
		// ;
		// }
		//
		// int targetCount = minitargetCount;
		//
		// int keycount = rb.bb_98[source[i]][0];
		// for (int index = 0; index < keycount; index++) {
		// target = rb.keyss_98[rb.bb_98[source[i]][index + 1]];
		// if (rb.delkeyss_98[rb.bb_98[source[i]][index + 1]])
		// continue;
		// targetCount = target.length;
		// int currmax = sourceOffset + (sourceCount - targetCount) + 1;
		// /* Found first character, now look at the rest of v2 */
		// // if(targetCount==1) return true;
		// if (i < currmax) {
		// int j = i;
		// int end = i + targetCount - 1;
		// for (int k = targetOffset + targetCount - 1; j < end && source[end]
		// == target[k]; end--, k--)
		// ;
		//
		// if (j == end) {
		// /* Found whole string. */
		// rb.result[rb.resultlen++] = new String(target);
		// rb.delkeyss_98[rb.bb_98[source[i]][index + 1]] = true;
		// flag = true;
		// }
		// }
		// }
		// }
		return flag;
	}

	boolean myindexOf_98_patch(String sources) {
		boolean ret = false;
		// for (int i = 1; i < rb.keyss_98_patch[0]; i++) {
		// String targetv = new String(rb.keyss_98[rb.keyss_98_patch[i]]);
		// String[] targetvv = targetv.split("\\.\\*");
		//
		// int flag = 0;
		// for (int j = 0; j < targetvv.length; j++) {
		// if (sources.indexOf(targetvv[j]) < 0) {
		// break;
		// }
		// flag++;
		// }
		// if (flag == targetvv.length) {
		// rb.result[rb.resultlen++] = targetv;
		// ret = true;
		// }
		// }
		return ret;
	}

	//
	public static void main1(String[] args) {
		String strSource = "fsdfsdfsttttt市电故障再生,164-定州明月店"; // "gosun &
															// 机房","再生.*市电故障","下线",
		String[] strTarget = new String[] { "test-yy888.*fsdfsdfs", "市电故障.*再生.*164-定州明月店",
				"lss_ossecinfomodificationdetected", "负载熔丝", "test-yy888", "市电故障", "同源关联",
				"lss_internalcommunicationfailure", "大面积" };

		for (String target : strTarget) {
			boolean flag = myindexOf_patch(strSource, target, 1);

			boolean flag2 = myindexOf(strSource, target);

			if (flag != flag2) {
				logger.error("strSource: " + strSource + " ,target: " + target + ".flag:" + flag + ",flag2:" + flag2);
			} else {
				logger.info("strSource: " + strSource + " ,target: " + target + ".flag:" + flag + ",flag2:" + flag2);
			}

		}

		strSource = "系统二级过负荷告警"; // "gosun & 机房","再生.*市电故障","下线",

		String target = "系统二级过负荷告警";
		boolean flag = myindexOf_patch(strSource, target, 1);
		logger.error("strSource: " + strSource + " ,target: " + target + ".flag:" + flag);

	}

	// static boolean myindexOf(char[] source, int sourceOffset, int
	// sourceCount,
	// char[] target, int targetOffset, int targetCount, int fromIndex,
	// int def_type) {
	//
	// // boolean f1 = myindexOf_old(source, sourceOffset, sourceCount, target,
	// // targetOffset, targetCount, fromIndex);
	// boolean f2 = myindexOf_patch(source, sourceOffset, sourceCount, target,
	// targetOffset, targetCount, fromIndex, def_type);
	//
	// // if(f1!=f2)
	// // {
	// // logger.error("myindexOf Error.f1:"+f1+",f2:"+f2);
	// // }
	// return f2;
	// }

	static boolean myindexOf(String source, String target) {
		String[] targetArray = target.split("\\.\\*");
		int targetArrayLength = targetArray.length;
		String temp_target = null;
		if (source == null) {
			return false;
		}
		source = source.trim();
		if (source.length() == 0) {
			return false;
		}
		char[] sourceCharArray = source.toCharArray();

		for (int i = 0; i < targetArrayLength; i++) {
			temp_target = targetArray[i];

			boolean flag = myindexOf_old(sourceCharArray, temp_target);
			if (!flag) {
				return false;
			}
		}

		return true;
	}

	static boolean myindexOf_old(char[] sourceCharArray, String target) {
		try {

			if (target == null) {
				return false;
			}
			target = target.trim();
			if (target.length() == 0) {
				return false;
			}

			char[] targetCharArray = target.toCharArray();
			int sourceCharArrayLength = sourceCharArray.length;
			int targetCharArrayLength = targetCharArray.length;
			if (sourceCharArrayLength < targetCharArrayLength) {
				return false;
			}

			/*
			 * if (fromIndex >= sourceCount) { return (targetCount == 0 ? true :
			 * false); } if (fromIndex < 0) { fromIndex = 0; } if (targetCount
			 * == 0) { return true; }
			 */
			char target_first_char = targetCharArray[0];

			int max = (sourceCharArrayLength - targetCharArrayLength);

			for (int i = 0; i <= max; i++) {
				/* Look for first character. */
				if (sourceCharArray[i] != target_first_char) {

					continue;
				}

				boolean flag = false;
				for (int target_index = 1; target_index < targetCharArrayLength; target_index++) {

					if (targetCharArray[target_index] != sourceCharArray[i + target_index]) {
						flag = false;
						break;
					} else {
						flag = true;
					}
				}
				if (flag) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {

			logger.error("myindexOf_old", e);
		}
		return false;
	}

	public static boolean myindexOf_patch(String source, String target, int idef_id) {
		try {

			if (source == null) {
				return false;
			}

			source = source.trim();
			if (source.length() == 0) {
				return false;
			}
			// if(idef_id==)
			// {
			// filterDetailsLogger.info("myindexOf_patch,source:" + source +
			// ",target:" + target);
			// }
			if (target == null) {
				return true;
			}
			target = target.trim();
			if (target.length() == 0) {
				return true;
			}

			String[] targetvv = target.split("\\.\\*");

			int targetvvLength = targetvv.length;
			if (targetvvLength < 1) {
				return true;
			}

			for (int i = 0; i < targetvvLength; i++) {

				String strTarget = targetvv[i];
				if (source.indexOf(strTarget) < 0) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {

			logger.error("myindexOf_patch,source:" + source + ",target:" + target, e);
		}
		return false;
	}

	public void regula_expression_v(String alarnmId, int idef_id, String targetFieldName, String value) {
		String key = value;
		int iPos = getPos(idef_id);
		char[] vv = value.toCharArray();
		int len = value.length();
		for (int j = 0; j < rb.map2[iPos].length; j++) {
			key = (String) rb.map2[iPos][j];
			char[] kv = key.toCharArray();
			int keylen = key.length();
			// b = System.nanoTime();
			try {
				if (myindexOf_patch(value, key, idef_id)) {
					// if(value.indexOf(key)>-1){
					Vector<Integer> conditionPos = rb.map1[iPos].get(key);
					int conditionPosSize = 0;
					if (conditionPos != null) {
						conditionPosSize = conditionPos.size();
					}
					for (int i = 0; i < conditionPosSize; i++) {
						rb.c[conditionPos.get(i)] = true;
					}
				}
			} catch (Exception e) {
				logger.error("regula_expression_v  idef_id:" + idef_id + ",key:" + key + ",value:" + value
						+ ",alarnmId:" + alarnmId, e);
			}
		}
	}

	public void regula_expression_indexof(String alarmId, int idef_id, int targetid, int def_type,
			String targetFieldName, String value) {
		String key = null;
		try {

			int iPos = getPos(idef_id);

			boolean debugFlag = false;
			// if (targetFieldName != null &&
			// targetFieldName.equals("title_text") && value != null &&
			// value.indexOf("二级过负荷") > -1) {
			// debugFlag = true;
			// }

			String[] rmmap2 = rb.map2[iPos];

			int rmmap2Length = 0;
			if (rmmap2 != null) {
				rmmap2Length = rmmap2.length;
			}
			int rbcArrayLength = rb.c.length;
			for (int j = 0; j < rmmap2Length; j++) {
				boolean matchFlag = false;

				try {

					String[] keyMap = rb.map2[iPos];
					if (keyMap == null) {
						continue;
					}

					key = keyMap[j];
					if (key == null) {
						continue;
					}
					key = key.trim();
					if (key.length() == 0) {
						continue;
					}

					matchFlag = myindexOf_patch(value, key, idef_id);

					if (matchFlag == false) {
						// if (debugFlag) {
						// filterDetailsLogger.info("regula_expression_indexof,debug
						// matchFlag is false.idef_id:" + idef_id + ",def_type:"
						// + def_type + ",key:" + key + ",value:" + value
						// + ",rbcArrayLength:" + rbcArrayLength + ",matchFlag:"
						// + matchFlag + ",alarmId:" + alarmId);
						// }
						continue;
					}

					// if(value.indexOf(key)>-1){
					HashMap<String, Integer[]> conditionPosMap = rb.map0[iPos];
					Integer[] conditionPos = null;
					if (conditionPosMap != null) {
						conditionPos = conditionPosMap.get(key);
					}

					int conditionPosLength = 0;
					if (conditionPos != null) {
						conditionPosLength = conditionPos.length;
					}

					List<Integer> debug_conditionPos_list = new ArrayList<Integer>();

					Integer conditionKey = null;
					for (int i = 0; i < conditionPosLength; i++) {

						try {
							conditionKey = conditionPos[i];
							if (conditionKey != null && conditionKey >= 0 && conditionKey < rbcArrayLength) {

								debug_conditionPos_list.add(conditionKey);

								rb.c[conditionKey] = true;

							} else {
								filterDetailsLogger.error("regula_expression_indexof conditionKey is null,idef_id:"
										+ idef_id + ",def_type:" + def_type + ",conditionKey:" + conditionKey + "value:"
										+ value + ",rbcArrayLength:" + rbcArrayLength + ",matchFlag:" + matchFlag
										+ ",alarmId:" + alarmId);

							}
						} catch (Exception e) {
							filterDetailsLogger.error("regula_expression_indexof  setRbcResult Exception,idef_id:"
									+ idef_id + ",def_type:" + def_type + ",conditionKey:" + conditionKey + ",value:"
									+ value + ",rbcArrayLength:" + rbcArrayLength + ",matchFlag:" + matchFlag
									+ ",alarmId:" + alarmId, e);

						}

					}

					if (debugFlag) {
						filterDetailsLogger.info("regula_expression_indexof,debug matchFlag is true.idef_id:" + idef_id
								+ ",def_type:" + def_type + ",key:" + key + ",value:" + value
								+ ",rbcArrayLength:" + rbcArrayLength + ",matchFlag:" + matchFlag + ",alarmId:"
								+ alarmId + ",debug_conditionPos_list:" + debug_conditionPos_list);
					}

				} catch (Exception e) {
					filterDetailsLogger.error("regula_expression_indexof Exception,index:" + j + ",idef_id:" + idef_id
							+ ",def_type:" + def_type + ",value:" + value + ",rbcArrayLength:"
							+ rbcArrayLength + ",matchFlag:" + matchFlag + ",alarmId:" + alarmId, e);

				}
			}
		} catch (Exception e) {
			logger.info("regula_expression_indexof   Exception,idef_id:" + idef_id + ",def_type:" + def_type + ",value:"
					+ value + ",alarmId:" + alarmId, e);

		}
	}

	public void regula_expression(String alarmId, int idef_id, int targetid, int def_type, String targetFieldName,
			Object val) {
		try {
			// regula_expression_indexof(idef_id,value);
			// if(true)return;
			if (val == null) {
				return;
			}
			if (!(val instanceof String)) {
				return;
			}
			String value = (String) val;

			// String key = value;
			// 2013.12.25 大小写不敏感
			String lower_value = value.toLowerCase();

			regula_expression_indexof(alarmId, idef_id, targetid, def_type, targetFieldName, lower_value);

		} catch (Exception e) {

			logger.error("regula_expression Exception.idef_id:" + idef_id + ",targetid:" + targetid + ",def_type:"
					+ def_type + "value:" + val + ",alarmId:" + alarmId, e);

		} catch (Throwable e) {

			logger.error("regula_expression Throwable.idef_id:" + idef_id + ",targetid:" + targetid + ",def_type:"
					+ def_type + "value:" + val + ",alarmId:" + alarmId, e);
		}
	}

	public void Comma_expression_v(String alarmId, int idef_id, String targetFieldName, String value) {
		if (value == null)
			value = "null";
		String key = value;
		int iPos = getPos(idef_id);
		if (rb.map1[iPos].containsKey(key)) {
			Vector<Integer> conditionPos = rb.map1[iPos].get(key);
			if (conditionPos != null) {
				for (int i = 0; i < conditionPos.size(); i++) {
					int iSUBC = conditionPos.get(i);
					rb.c[iSUBC] = true;
				}
			}
		}
	}

	public void Comma_expression(String alarmId, int idef_id, int def_type, String targetFieldName, String value) {
		if (value == null)
			value = "null";
		// String key = value;
		// 2013.12.25 大小写不敏感
		String key = value.toLowerCase().trim();
		value = key;

		int iPos = getPos(idef_id);

		if (def_type == 7) {
			Comma_expression_7(alarmId, iPos, targetFieldName, value);
			return;
		}

		if (rb.map1[iPos].containsKey(key)) {
			Integer[] conditionPos = rb.map0[iPos].get(key);
			if (conditionPos != null) {
				for (int i = 0; i < conditionPos.length; i++) {
					int iSUBC = conditionPos[i];
					rb.c[iSUBC] = true;
				}

			}
		}
	}

	private void Comma_expression_7(String alarmId, int iPos, String targetFieldName, Object value) {
		Iterator<Entry<String, Vector<Integer>>> iterator = rb.map1[iPos].entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Vector<Integer>> entry = iterator.next();
			String mapKey = entry.getKey();
			if (mapKey == null) {
				continue;
			}

			boolean flag = deal_expression_7(mapKey, value.toString());
			if (flag) {
				Integer[] conditionPos = rb.map0[iPos].get(mapKey);
				int conditionPosLength = 0;
				if (conditionPos != null) {
					conditionPosLength = conditionPos.length;
				} else {

					logger.error("deal_expression_7," + ",mapKey:" + mapKey + ",iPos:" + iPos + ",targetFieldName:"
							+ targetFieldName + ",value:" + value);

				}
				for (int i = 0; i < conditionPosLength; i++) {
					int iSUBC = conditionPos[i];
					rb.c[iSUBC] = true;
				}
			}
		}

	}

	public static Long parseLong(String longStr) {
		try {
			if (longStr == null) {
				return null;
			}
			longStr = longStr.trim();
			if (longStr.length() == 0) {
				return null;
			}
			return Long.valueOf(longStr);
		} catch (Exception e) {
			logger.error("parseLong Exception.longStr=" + longStr);
		}
		return null;
	}

	// public void Comma_expression_7(int idef_id, int def_type, Object val) {
	// String value = null;
	// if (val != null) {
	// value = val.toString();
	// }
	//
	// // 条件值为 1,为等于1.
	// // 条件值为1-5，为 大于等于1,小于等于5
	// // 条件值为1-，为大于等于1
	// // 条件值为-5，为小于等于5
	// String key = value.toLowerCase().trim();
	// value = key;
	// String[] vv = value.split(",");
	//
	// int iPos = getPos(idef_id);
	// for (int jj = 0; jj < vv.length; jj++) {
	// key = vv[jj];
	// if (rb.map1[iPos].containsKey(key)) {
	// Integer[] conditionPos = rb.map0[iPos].get(key);
	// for (int i = 0; i < conditionPos.length; i++) {
	// int iSUBC = conditionPos[i];
	// rb.c[iSUBC] = true;
	// }
	// break;
	// }
	// }
	//
	// }

	public static boolean deal_expression_7(String mapKey, String value) {

		try {
			// 条件值为 1,为等于1.
			// 条件值为1-5，为 大于等于1,小于等于5
			// 条件值为1-，为大于等于1
			// 条件值为-5，为小于等于5

			int index = mapKey.indexOf("-");

			Long eq_key = null;
			Long begin_key = null;
			Long end_key = null;
			if (index < 0) {
				eq_key = parseLong(mapKey);
			} else if (index > 0) {

				String begin_key_str = mapKey.substring(0, index);
				begin_key = parseLong(begin_key_str);

				if (index + 1 < mapKey.length()) {
					String end_key_str = mapKey.substring(index + 1);
					end_key = parseLong(end_key_str);
				}

			} else if (index == 0) {
				String end_key_str = mapKey.substring(index + 1);
				end_key = parseLong(end_key_str);

			}

			Long long_value = parseLong(value);
			boolean flag = false;
			if (eq_key != null) {
				if (eq_key.equals(long_value)) {

					flag = true;
				} else {
					flag = false;
				}
			} else {

				boolean begin_flag = true;
				if (begin_key != null) {

					if (begin_key <= long_value) {
						begin_flag = true;
					} else {
						begin_flag = false;
					}
				}
				boolean end_flag = true;
				if (end_key != null) {

					if (end_key >= long_value) {
						end_flag = true;
					} else {
						end_flag = false;
					}
				}
				if (begin_flag && end_flag) {
					flag = true;
				} else {
					flag = false;
				}
			}
			return flag;
		} catch (Exception e) {

			logger.error("deal_expression_7,mapKey=" + mapKey + ",value=" + value, e);
		}
		return false;
	}

	public void Comma_expression_8_9(String alarmId, int idef_id, int def_type, String targetFieldName, Object val) {
		String value = null;
		if (val != null) {
			value = val.toString();
		}
		if (value == null) {
			value = "NULL";
		}
		// String key = value;
		// 2013.12.25 大小写不敏感
		value = value.toLowerCase().trim();

		// if(value.equalsIgnoreCase("1234"))
		// {
		// System.out.println("888888");
		// }

		String[] vv = value.split(",");

		int iPos = getPos(idef_id);
		String key = "";
		for (int jj = 0; jj < vv.length; jj++) {
			key = vv[jj];
			HashMap<String, Vector<Integer>> coditionMap = rb.map1[iPos];
			if (coditionMap.containsKey(key)) {
				Integer[] conditionPos = rb.map0[iPos].get(key);
				int conditionPosLength = 0;
				if (conditionPos != null) {
					conditionPosLength = conditionPos.length;
				} else {
					logger.error("Comma_expression_8_9,iPos:" + iPos + ",key:" + key + ",value:" + value + ",alarmId:"
							+ alarmId);

				}
				for (int i = 0; i < conditionPosLength; i++) {
					int iSUBC = conditionPos[i];
					rb.c[iSUBC] = true;
				}
				break;
			}
		}
	}

	public void Comma_expression_v(String alarmId, int idef_id, String targetFieldName, Long value) {
		if (value == null)
			return;
		String key = String.valueOf(value);
		int iPos = getPos(idef_id);
		if (rb.map1[iPos].containsKey(key)) {
			Vector<Integer> conditionPos = rb.map1[iPos].get(key);
			int conditionPosLength = 0;
			if (conditionPos != null) {
				conditionPosLength = conditionPos.size();
			} else {
				logger.error("Comma_expression_8_9,iPos:" + iPos + ",key:" + key + ",value:" + value + ",alarmId:"
						+ alarmId);

			}

			for (int i = 0; i < conditionPosLength; i++) {
				int iSUBC = conditionPos.get(i);
				rb.c[iSUBC] = true;
			}

		}
	}

	public void Comma_expression_b(String alarmId, int idef_id, int def_type, String targetFieldName, Object value) {
		if (value instanceof Long) {
			this.Comma_expression(alarmId, idef_id, def_type, targetFieldName, (Long) value);
		} else if (value instanceof Integer) {
			this.Comma_expression(alarmId, idef_id, def_type, targetFieldName, (Integer) value);
		} else if (value != null) {
			this.Comma_expression(alarmId, idef_id, def_type, targetFieldName, value.toString());
		}
	}

	public void Comma_expression(String alarmId, int idef_id, int def_type, String targetFieldName, Long value) {
		if (value == null)
			value = Long.MIN_VALUE;
		/*
		 * if (value == null) return;
		 */
		String key = String.valueOf(value);
		int iPos = getPos(idef_id);

		if (def_type == 7) {
			Comma_expression_7(alarmId, iPos, targetFieldName, value);
			return;
		}
		if (rb.map1[iPos].containsKey(key)) {
			Integer[] conditionPos = rb.map0[iPos].get(key);

			int conditionPosLength = 0;
			if (conditionPos != null) {
				conditionPosLength = conditionPos.length;
			} else {
				logger.error("def_type:" + def_type + ",idef_id:" + idef_id + ",iPos:" + iPos + ",key:" + key
						+ ",value:" + value);

			}
			for (int i = 0; i < conditionPosLength; i++) {
				int iSUBC = conditionPos[i];
				rb.c[iSUBC] = true;
			}
		}
	}

	public void Comma_expression_v(String alarmId, int idef_id, String targetFieldName, Integer value) {
		if (value == null)
			return;
		String key = String.valueOf(value);
		int iPos = getPos(idef_id);
		if (rb.map1[iPos].containsKey(key)) {
			Vector<Integer> conditionPos = rb.map1[iPos].get(key);
			int conditionPosLength = 0;
			if (conditionPos != null) {
				conditionPosLength = conditionPos.size();
			} else {
				logger.error("Comma_expression_v:idef_id:" + idef_id + ",iPos:" + iPos + ",key:" + key + ",value:"
						+ value + ",alarmId:" + alarmId);

			}

			for (int i = 0; i < conditionPosLength; i++) {
				int iSUBC = conditionPos.get(i);
				rb.c[iSUBC] = true;
			}

		}
	}

	public boolean Comma_expression(String alarmId, int idef_id, int def_type, String targetFieldName, Integer value) {
		if (value == null)
			value = Integer.MIN_VALUE;
		/*
		 * if (value == null) return;
		 */
		String key = String.valueOf(value);
		int iPos = getPos(idef_id);

		if (def_type == 7) {
			Comma_expression_7(alarmId, iPos, targetFieldName, value);
			return false;
		}

		if (iPos < 0) {

			logger.error("Runtime Error, idef_id is:" + idef_id + ",def_type:" + def_type + ",value:" + value
					+ ",alarmId:" + alarmId + ",filed:" + printFieldDef());

			return false;
		}

		HashMap<String, Vector<Integer>> map1 = rb.map1[iPos];

		boolean flag = map1.containsKey(key);
		// if(targetFieldName.equals("province_id"))
		// {
		// String map1Str= this.getString(map1);
		// filterDetailsLogger.error("Comma_expression:"+",flag"+flag+",value:"+value+",map1Str:"+map1Str);
		// }
		if (flag) {
			Integer[] conditionPos = rb.map0[iPos].get(key);
			int conditionPosLength = 0;
			if (conditionPos != null) {
				conditionPosLength = conditionPos.length;
			} else {
				logger.error("Comma_expression:idef_id is:" + idef_id + ",def_type:" + def_type + ",key:" + key
						+ ",value:" + value + ",alarmId:" + alarmId);

			}

			for (int i = 0; i < conditionPosLength; i++) {
				int iSUBC = conditionPos[i];
				rb.c[iSUBC] = true;
			}

		}
		return false;
	}

	private String getString(HashMap<String, Vector<Integer>> map1) {
		StringBuffer stringBuffer = new StringBuffer();
		Iterator<Entry<String, Vector<Integer>>> it = null;
		if (map1 != null) {
			it = map1.entrySet().iterator();
		}
		Entry<String, Vector<Integer>> entry = null;
		String key = null;
		Vector<Integer> valueVector = null;

		while (it != null && it.hasNext()) {
			entry = it.next();

			key = entry.getKey();
			valueVector = entry.getValue();
			stringBuffer.append(key + "#" + valueVector).append(";");
		}

		return stringBuffer.toString();

	}

	public void time_y2s_start(String alarmId, int idef_id, int def_type, String targetFieldName, Object value) {
		int iPos = -1;
		String valueStr = "";
		Date eventtime = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (value == null) {
				return;
			}

			if (value instanceof String) {
				valueStr = (String) value;
				iPos = getPos(idef_id);

				eventtime = simpleDateFormat.parse(valueStr);

			} else if (value instanceof Date) {
				eventtime = (Date) value;
				valueStr = eventtime.toGMTString();

			} else if (value instanceof Long) {
				long long_eventtime = (Long) value;
				eventtime = new Date(long_eventtime);
				valueStr = eventtime.toGMTString();

			}
			if (eventtime == null) {
				return;
			}
			long hourMinutes = eventtime.getTime();

			// String key = String.valueOf(value);

			Iterator<String> keys = rb.map1[iPos].keySet().iterator();

			while (keys.hasNext()) {
				try {
					String vv = keys.next();

					Long ivv = null;

					ivv = simpleDateFormat.parse(vv).getTime();

					boolean begin_flag = false;
					if (ivv == null || hourMinutes >= ivv) {
						// 如果空则为true
						begin_flag = true;
					}

					if (begin_flag) {
						Integer[] conditionPos = rb.map0[iPos].get(vv);
						int conditionPosLength = 0;
						if (conditionPos != null) {
							conditionPosLength = conditionPos.length;
						} else {
							logger.error("time_y2s_start,def_type:" + def_type + ",idef_id:" + idef_id + ",iPos:" + iPos
									+ ",vv:" + vv + ",targetFieldName:" + targetFieldName + ",valueStr:" + valueStr
									+ ",alarmId:" + alarmId);

						}
						for (int i = 0; i < conditionPosLength; i++) {
							int iSUBC = conditionPos[i];
							rb.c[iSUBC] = true;
						}
					}
				} catch (Exception e) {
					logger.error("time_y2s_start:def_type:" + def_type + ",idef_id:" + idef_id + ",iPos:" + iPos
							+ ",hourMinutes:" + hourMinutes + ",targetFieldName:" + targetFieldName + ",value:"
							+ valueStr + ",alarmId:" + alarmId, e);

				}
			}
		} catch (Exception e) {
			logger.error("time_y2s_start:def_type:" + def_type + ",idef_id:" + idef_id + ",iPos:" + iPos
					+ ",targetFieldName:" + targetFieldName + ",value:" + valueStr + ",alarmId:" + alarmId, e);

		}
	}

	public void time_y2s_stop(String alarmId, int idef_id, int def_type, String targetFieldName, Object value) {

		int iPos = -1;
		String valueStr = "";
		Date eventtime = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (value == null) {
				return;
			}

			if (value instanceof String) {
				valueStr = (String) value;
				iPos = getPos(idef_id);

				eventtime = simpleDateFormat.parse(valueStr);

			} else if (value instanceof Date) {
				eventtime = (Date) value;
				valueStr = eventtime.toGMTString();

			} else if (value instanceof Long) {
				long long_eventtime = (Long) value;
				eventtime = new Date(long_eventtime);
				valueStr = eventtime.toGMTString();

			}
			if (eventtime == null) {
				return;
			}
			long hourMinutes = eventtime.getTime();

			// String key = String.valueOf(value);

			Iterator<String> keys = rb.map1[iPos].keySet().iterator();

			while (keys.hasNext()) {
				try {
					String vv = keys.next();

					Long ivv = null;

					ivv = simpleDateFormat.parse(vv).getTime();

					boolean begin_flag = false;
					if (ivv == null || hourMinutes < ivv) {
						// 如果空则为true
						begin_flag = true;
					}

					if (begin_flag) {
						Integer[] conditionPos = rb.map0[iPos].get(vv);
						int conditionPosLength = 0;
						if (conditionPos != null) {
							conditionPosLength = conditionPos.length;
						} else {
							logger.error("time_y2s_stop:def_type:" + def_type + ",idef_id:" + idef_id + ",iPos:" + iPos
									+ ",vv:" + vv + ",targetFieldName:" + targetFieldName + ",valueStr:" + valueStr
									+ ",alarmId:" + alarmId);

						}
						for (int i = 0; i < conditionPosLength; i++) {
							int iSUBC = conditionPos[i];
							rb.c[iSUBC] = true;
						}
					}
				} catch (Exception e) {
					logger.error("time_y2s_stop:def_type:" + def_type + ",idef_id:" + idef_id + ",iPos:" + iPos
							+ ",hourMinutes:" + hourMinutes + ",targetFieldName:" + targetFieldName + ",value:"
							+ valueStr + ",alarmId:" + alarmId, e);

				}
			}
		} catch (Exception e) {
			logger.error("time_y2s_stop:def_type:" + def_type + ",idef_id:" + idef_id + ",iPos:" + iPos
					+ ",targetFieldName:" + targetFieldName + ",value:" + valueStr + ",alarmId:" + alarmId, e);

		}

	}

	public void time_h2m_start2(String alarmId, int idef_id, int def_type, String targetFieldName, Object value) {
		int iPos = -1;
		String valueStr = "";
		Date eventtime = null;
		try {
			if (value == null) {
				return;
			}

			if (value instanceof String) {
				valueStr = (String) value;
				iPos = getPos(idef_id);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				eventtime = simpleDateFormat.parse(valueStr);

			} else if (value instanceof Date) {
				eventtime = (Date) value;
				valueStr = eventtime.toGMTString();

			}
			if (eventtime == null) {
				return;
			}
			int hourMinutes = eventtime.getHours() * 60 + eventtime.getMinutes();

			// String key = String.valueOf(value);

			Iterator<String> keys = rb.map1[iPos].keySet().iterator();

			while (keys.hasNext()) {
				try {
					String vv = keys.next();
					String[] ivv = vv.split("-");

					Integer ivv1 = null;
					Integer ivv2 = null;

					if (ivv.length == 1) {
						ivv1 = parseInteger(ivv[0]);
					} else if (ivv.length > 1) {
						ivv1 = parseInteger(ivv[0]);
						ivv2 = parseInteger(ivv[1]);
					}
					boolean begin_flag = false;
					if (ivv1 == null || hourMinutes >= ivv1) {
						// 如果空则为true
						begin_flag = true;
					}
					boolean end_flag = false;
					if (ivv2 == null || hourMinutes <= ivv2) {
						// 如果空则为true
						end_flag = true;
					}

					if (begin_flag && end_flag) {
						Integer[] conditionPos = rb.map0[iPos].get(vv);
						int conditionPosLength = 0;
						if (conditionPos != null) {
							conditionPosLength = conditionPos.length;
						} else {
							logger.error("Comma_expression:def_type:" + def_type + ",idef_id:" + idef_id + ",iPos:"
									+ iPos + ",vv:" + vv + ",targetFieldName:" + targetFieldName + ",valueStr:"
									+ valueStr + ",alarmId:" + alarmId);

						}
						for (int i = 0; i < conditionPosLength; i++) {
							int iSUBC = conditionPos[i];
							rb.c[iSUBC] = true;
						}
					}
				} catch (Exception e) {
					logger.error("time_h2m_start2:def_type:" + def_type + ",idef_id:" + idef_id + ",iPos:" + iPos
							+ ",hourMinutes:" + hourMinutes + ",targetFieldName:" + targetFieldName + ",value:"
							+ valueStr + ",alarmId:" + alarmId, e);

				}
			}
		} catch (Exception e) {
			logger.error("time_h2m_start2:def_type:" + def_type + ",idef_id:" + idef_id + ",iPos:" + iPos
					+ ",targetFieldName:" + targetFieldName + ",value:" + valueStr + ",alarmId:" + alarmId, e);

		}
	}

	private static Integer parseInteger(String str) {
		try {
			Integer ivv1;
			ivv1 = Integer.parseInt(str);
			return ivv1;
		} catch (Exception e) {
			logger.error("parseInteger Exception.str:" + str);
		}
		return null;
	}

	public static void main(String[] args) {
		parseInteger("2001-03-05 21:03:44");
	}
	// public void time_h2m_stop2(String alarmId, int idef_id, int def_type,
	// String targetFieldName, Object value) {
	// return;
	// }

	public void set_f(String alarmId, int idef_id, Integer ivalue, String targetname, int def_type) {
		String value = String.valueOf(ivalue);
		set_f(alarmId, idef_id, def_type, targetname, value);
	}

	public void set_f(String alarmId, int idef_id, Long ivalue, String targetname, int def_type) {
		String value = String.valueOf(ivalue);
		set_f(alarmId, idef_id, def_type, targetname, value);
	}

	public void set_f(String alarmId, int idef_id, List ivalue, String targetname, int def_type) {
		if (ivalue == null) {
			set_f(alarmId, idef_id, def_type, targetname, "");
			return;
		}
		for (int i = 0; i < ivalue.size(); i++) {
			String value = String.valueOf(ivalue.get(i));
			set_f(alarmId, idef_id, def_type, targetname, value);
		}
	}

	static public int getPos_old(int idef_id) {
		int iPos = idef_id;
		if (idef_id > 100) {
			iPos = idef_id % 1000 + 100;
		}
		return iPos;
	}

	// shilt 2013/11/14
	public int getPos(int idef_id) {
		int iPos = fielddef[idef_id];
		if (iPos < 0) {
			System.out.println("Runtime Error, def_id is:" + idef_id + ",filed:" + printFieldDef());
			logger.error("Runtime Error, def_id is:" + idef_id + " filed:" + printFieldDef());
		}
		/*
		 * if(rb ==null || rb.map1 ==null || rb.map1[iPos]==null){
		 * System.out.println("Runtime Error, def_id is:" + idef_id + "Object:"
		 * + " filed:"+printFieldDef());
		 * logger.error("Runtime Error, def_id is:" + idef_id + "Object:" +
		 * " filed:"+printFieldDef()); logger.error("Runtime Error,map1:" +
		 * printMap()); }
		 */
		return iPos;
	}

	public void set_f(String alarmId, int idef_id, int def_type, String targetFieldName, String value) {
		if (value == null) {
			value = "";
		}
		if (def_type == 5 && targetFieldName.equals("ne_group")) { // idef_id==8
			f_negroup(alarmId, idef_id, def_type, targetFieldName, value);
		} else if (def_type == 5 && targetFieldName.equals("eqp_ne_group")) { // idef_id==8
			f_negroup(alarmId, idef_id, def_type, targetFieldName, value);
		} else if (def_type == 3) { // idef_id==5 || idef_id==100 || idef_id==98
									// ){ 濮濓絽鍨悰銊ㄦ彧瀵拷
			int targetid = -1;
			if (targetFieldName.equals("alarm_text")) {
				targetid = 5;
			}
			if (targetFieldName.equals("standard_alarm_id")) {
				targetid = 98;
			}
			regula_expression(alarmId, idef_id, targetid, def_type, targetFieldName, value);
		} else {
			Comma_expression(alarmId, idef_id, def_type, targetFieldName, value);
		}
	}

	/*
	 * public void set_f(int idef_id, String value) { if (value == null) { value
	 * = ""; } if (idef_id == 8) { // idef_id==8 f_negroup(idef_id, value); }
	 * else if (idef_id == 5 || idef_id == 100 || idef_id == 98) { //
	 * 濮濓絽鍨悰銊ㄦ彧瀵拷 regula_expression(idef_id, value); } else {
	 * Comma_expression(idef_id, value); } }
	 */

	/*
	 * public void set_f(int idef_id, Integer ivalue) { String value =
	 * String.valueOf(ivalue); set_f(idef_id, value); }
	 * 
	 * public void set_f(int idef_id, Long ivalue) { String value =
	 * String.valueOf(ivalue); set_f(idef_id, value); }
	 */
	public static boolean match(String src1, String pattern1) {
		byte[] src = src1.getBytes();
		byte[] pattern = pattern1.getBytes();

		int patternLen = pattern.length;
		int srcLen = src.length;

		if (srcLen == 0 || srcLen < patternLen) {
			return false;
		}

		long patternHashCode = 0;
		long srcHashCode = 0;

		for (int i = 0; i < patternLen; i++) {
			patternHashCode += pattern[i];// .GetHashCode();
			srcHashCode += src[i];// .GetHashCode();
		}

		int j = 0;

		do {
			if (patternHashCode == srcHashCode) {
				return true;
			}
			if (j + patternLen < srcLen) {
				srcHashCode = srcHashCode - src[j] + src[j + patternLen];// .GetHashCode();
			}
		} while (j++ < srcLen);
		return false;
	}

	public static boolean StringContains_SunDay(String sourceString, String patternString) {
		// Covert the char array
		char[] sourceList = sourceString.toCharArray();
		char[] patternList = patternString.toCharArray();

		int sourceLength = sourceList.length;
		int patternLength = patternList.length;
		// System.out.println(sourceLength+" "+patternLength);
		int sCount = 0, pCount = 0;
		int loc = 0;

		if (sourceLength < patternLength) {
			return false;
		}

		int end = sourceLength - patternLength;
		while (sCount < end && pCount < patternLength) {
			// if equals to move next character
			if (sourceList[sCount] == patternList[pCount]) {
				sCount++;
				pCount++;
			} else {
				// sAim:the location of char to judge
				// pAim:the last location of the pattern string
				int sAim = sCount + patternLength;
				char aimChar = sourceList[sAim];
				int pAim = patternLength - 1;
				// to judge char from back to front,the pAim is the equal
				// location
				while (pAim > 0) {
					if (patternList[pAim] == aimChar) {
						break;
					}
					pAim--;
				}
				// record the equal location with loc.
				// sCount:move the judge location of source string
				// pCount:move the begin of the pattern string
				sCount = sCount + patternLength - pAim;
				loc = sCount;
				pCount = 0;
			}
		}
		// if pattern string don't match completed,return -1
		if (pCount < patternLength) {
			return false;
		}
		// else return the location
		return true;// loc;
	}

	public boolean SundaySearch(String pattern1) {
		char[] text = text1.toCharArray();
		char[] pattern = pattern1.toCharArray();
		int i = 0;
		int j = 0;
		int pe = pattern.length - 1;
		int tb = i;
		int te = text.length - 1;
		while (i < text.length - 2 && j < pattern.length) {
			if (text[i] == pattern[j]) {
				i++;
				j++;
			} else {
				int k = pattern.length - 1;
				while (k >= 0 && text[pe + 1] != pattern[k]) {
					k--;
				}
				int gap = pattern.length - k;
				i += gap;
				pe = i + pattern.length - 1;
				tb = i;
				j = 0;
			}
		}
		if (i <= text.length) {
			return true;
		}
		return false;
	}

	public String text1 = null;

	public int QfindPos(String str, String find, int pos, int fin_length) {

		int returnPos = str.length();
		char[] Schr = str.toCharArray();
		char[] Sfin = find.toCharArray();

		if ((pos + fin_length) < str.length()) {
			char chrFind = Schr[pos + fin_length];// 鐟曚焦澹橀惃鍕摟缁楋拷
			if (fin_length >= 1) {
				if (find.lastIndexOf(chrFind) > -1) {
					returnPos = pos + fin_length - find.lastIndexOf(chrFind);
				} else {
					returnPos = pos + fin_length + 1;
				}
			}
		}
		return returnPos;
	}

}