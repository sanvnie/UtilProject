package com.util.test;

public class TestStr {

	public static void main(String[] args) {
		String str="<strong>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;巴拉那瓜(PARANAGUA)</strong>是巴西东南部大西洋沿岸港口。<strong>巴拉那瓜(PARANAGUA)</strong>采用西3区标准时。<strong>巴拉那瓜(PARANAGUA)</strong>最大吃水10米，潮差1.5米。盛行东南风。强制引航。引航站在PORTAL DO SUD，配有甚高频无线电台。船舶预计到达时间提前24小时出发，12小时以前进一步确证或修改。呼叫信号PG在340千赫。引航员在航道入口处的多美尔岛上船。2000吨以上的船舶必须使用拖轮(费用较昂贵)。使用几艘拖轮，有引航员指定。在风浪过大、能见度低防碍作业时，港务长可宣布关闭港口。工作时间00：00－4：00，7：00－23：00。<br><br>　　<strong>巴拉那瓜(PARANAGUA)</strong>港口服务设施有：修船、加燃料、小艇、医疗、牵引、淡水、给养、遣返，无干船坞、排污设施。港市有丹麦、意大利、挪威、巴拉圭的领事机构。入港航道有3条：北航道只能行驶内河小船，中航道和南航道的限制吃水为8.8至9.1米。一年中有4至5次遇强南风。形成拍岸浪，船舶只能在外等候。该港泊位岸线总长2454米，有16个泊位。第1、2、3、4、5、6、7、12、13是谷物泊位(其中第2、6、7号泊位专门装卸谷物、麦片、第12号泊位兼装植物油)。谷仓总容量405000吨，有专门的谷物装载设备，有3000吨容量的植物油罐和输油管。港市内的豆油加工厂有11个贮油罐，总容量5900吨，豆油产品用油罐卡车运到码头。第11号是散装化肥和矿砂泊位。第14号是集装箱船泊位，卸杂货、集装箱，输出速溶咖啡设施。第8、9、10、3号是一般的杂货泊位，装运袋装咖啡、木材等。2个石油液化气泊位，分别长130米和184米，水深10米，有容量为为128000吨的贮油罐和5400立方米的贮气罐，专门输出库兰蒂巴炼油厂的石油产品。港区有个巴拉圭的过境区，第9A货仓属巴拉圭管理和控制，他们用封闭的卡车将进口货物运回国内。在<strong>巴拉那瓜(PARANAGUA)</strong>上游22.4公里处，是安东尼那港，航道宽120米，水深6－8米，有一个长90米的码头，是个没有风浪的港口。";
		str=TestStr.splitAndFilterString(str);
		System.out.println(str);
	}
	public static String splitAndFilterString(String input) {
		if (input == null || input.trim().equals("")) { return ""; } // 去掉所有html元素, 
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll( "<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", ""); 
	
		str = str.substring(0, str.length());
		
		return str;
		
	}
}
