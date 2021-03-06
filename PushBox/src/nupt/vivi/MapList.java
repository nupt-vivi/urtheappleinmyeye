package nupt.vivi;
/**
 * 
 * 该类为地图类
 * map1为第一层，map2为第二层
 *
 */
public class MapList {
	public static int map1[][][] = 
	{//第一层地图，即地板层
		{
			{-1,-1,-1,1, 0, 1, 0, 1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 3, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 3, 0, 1, 0, 1, 0, 1},
			{1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,-1,-1,-1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,-1,-1,-1}
		},
		{
			{-1,-1,-1,1, 0, 1, 0, 1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 3, 0, 1, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 2, 3, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1},
			{1, 0, 1, 2, 1, 0, 1, 0, 3, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 3, 0, 1, 0, 1, 0, 1, 0, 1, 2, 3, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 1, 0, 1, 0, 1, 2, 1, 0, 1, 0, 1, 0},
			{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,-1,-1,-1},
			{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,-1,-1,-1}
		}		
	};
	public static int map2[][][] =   
	{//第二层地图
		{
			{-1,-1,-1,2, 2, 2, 2, 2,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2},
			{2, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 1, 0, 2, 0, 0, 2, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 2},
			{2, 0, 2, 2, 0, 0, 0, 0, 0, 2, 0, 1, 0, 2},
			{2, 0, 0, 0, 1, 2, 2, 0, 0, 0, 1, 0, 0, 2},
			{2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 2, 2, 0, 2},
			{2, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
			{2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2,-1,-1,-1},
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1}
		},
		{
			{-1,-1,-1,2, 2, 2, 2, 2,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2},
			{2, 2, 2, 2, 0, 2, 0, 1, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 1, 0, 0, 0, 0, 2, 2, 2, 0, 2},
			{2, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 2, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 1, 2},
			{2, 0, 2, 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2},
			{2, 0, 0, 0, 1, 2, 2, 2, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 2},
			{2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2},
			{2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2},
			{2, 0, 1, 0, 0, 1, 2, 0, 0, 0, 2, 2, 2, 2},
			{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2,-1,-1,-1},
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,-1,-1,-1}
		}
	};
}