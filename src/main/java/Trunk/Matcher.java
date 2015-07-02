package Trunk;

import java.util.*;
import Trunk.Util.Edge;
import FileUtil.*;

public class Matcher {
	public Map<Integer, Integer> map3 = new HashMap<Integer, Integer>();

	public boolean check(List<Edge> ori, List<Edge> rule, boolean log) {
		boolean flag = false;

		int i, j;
		for (int k = 0; k < ori.size(); k++) {
			map3.clear();
			for (i = k, j = 0; i < ori.size() && j < rule.size();) {
				Edge eo = ori.get(i);
				Edge ro = rule.get(j);
				if (log) {
					Log.log(eo + "\t][\t" + ro);
				}
				if (eo.isEqual(ro)) {
					if (!map3.containsKey(ro.govId)) {
						map3.put(ro.govId, eo.govId);
						if (!map3.containsKey(ro.depId)) {
							map3.put(ro.depId, eo.depId);
						}
						i++;
						j++;
					} else if (isSameId(map3.get(ro.govId), eo.govId)) {
						if (!map3.containsKey(ro.depId)) {
							map3.put(ro.depId, eo.depId);
						}
						i++;
						j++;
					} else {
						i++;
					}
				} else {
					i++;
				}
			}
			if (j == rule.size()) {
				flag = true;
				break;
			}
		}
		if (log) {
			Log.log("ƥ���Ƿ�ɹ�:" + flag);
			System.out.println(map3);
		}

		return flag;
	}

	private boolean isSameId(int a, int b) {
		boolean flag = false;

		if (a == b) {
			flag = true;
		}
		if (a == -1 || b == -1) {
			flag = true;
		}

		return flag;
	}
}
