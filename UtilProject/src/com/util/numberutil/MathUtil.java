package com.util.numberutil;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
/**
 * 高数工具包
 * 一：线性代数 1.Vector(向量)，2.Matrix(矩阵)，3. Matrix Decomposition(矩阵分解)
 * 二：数学分析 1.Function(函数)，2.Polynomial(多项式函数)，3.Interpolation(插值)，4.Integration(积分)，5.Solver(求解)
 * 三：概率与统计1.Distribution(分布)，2.fraction and complex(分数和复数)，3.random and statistics(随机生成和统计初步)，4.cluster and regression(聚类和回归) 
 */
public class MathUtil {
	
	public void dealDouble(){
		double[][] matrixData={{1d,2d,3d},{2d,5d,3d}};
		RealMatrix m=new Array2DRowRealMatrix(matrixData);
		double[][] matrixData2 = { {1d,2d}, {2d,5d}, {1d, 7d}};
		RealMatrix n = new Array2DRowRealMatrix(matrixData2);
		RealMatrix p=m.multiply(n);
		System.out.println(p.getRowDimension());
		System.out.println(p.getColumnDimension());
		
	}
	public static void main(String[] args) {
		 double[] values = new double[] { 0.33, 1.33, 0.27333, 0.3, 0.501,  
	                0.444, 0.44, 0.34496, 0.33, 0.3, 0.292, 0.667 };  
	        /* 
	         * System.out.println( "min: " + StatUtils.min( values ) ); 
	         * System.out.println( "max: " + StatUtils.max( values ) ); 
	         * System.out.println( "mean: " + StatUtils.mean( values ) ); // Returns 
	         * the arithmetic mean of the entries in the input array, or Double.NaN 
	         * if the array is empty System.out.println( "product: " + 
	         * StatUtils.product( values ) ); //Returns the product of the entries 
	         * in the input array, or Double.NaN if the array is empty. 
	         * System.out.println( "sum: " + StatUtils.sum( values ) ); //Returns 
	         * the sum of the values in the input array, or Double.NaN if the array 
	         * is empty. System.out.println( "variance: " + StatUtils.variance( 
	         * values ) ); // Returns the variance of the entries in the input 
	         * array, or Double.NaN if the array is empty. 
	         */  
	  
	        Min min = new Min();  
	        Max max = new Max();  
	          
	        Mean mean = new Mean(); // 算术平均值  
	        Product product = new Product();//乘积  
	        Sum sum = new Sum();  
	        Variance variance = new Variance();//方差  
	        System.out.println("min: " + min.evaluate(values));  
	        System.out.println("max: " + max.evaluate(values));  
	        System.out.println("mean: " + mean.evaluate(values));  
	        System.out.println("product: " + product.evaluate(values));  
	        System.out.println("sum: " + sum.evaluate(values));  
	        System.out.println("variance: " + variance.evaluate(values));  
	  
	        Percentile percentile = new Percentile(); // 百分位数  
	        GeometricMean geoMean = new GeometricMean(); // 几何平均数,n个正数的连乘积的n次算术根叫做这n个数的几何平均数  
	        Skewness skewness = new Skewness(); // Skewness();  
	        Kurtosis kurtosis = new Kurtosis(); // Kurtosis,峰度  
	        SumOfSquares sumOfSquares = new SumOfSquares(); // 平方和  
	        StandardDeviation StandardDeviation = new StandardDeviation();//标准差  
	        System.out.println("80 percentile value: "  
	                + percentile.evaluate(values, 80.0));  
	        System.out.println("geometric mean: " + geoMean.evaluate(values));  
	        System.out.println("skewness: " + skewness.evaluate(values));  
	        System.out.println("kurtosis: " + kurtosis.evaluate(values));  
	        System.out.println("sumOfSquares: " + sumOfSquares.evaluate(values));  
	        System.out.println("StandardDeviation: " + StandardDeviation.evaluate(values));  
	          
	        System.out.println("-------------------------------------");  
	        // Create a real matrix with two rows and three columns  
	        double[][] matrixData = { {1d,2d,3d}, {2d,5d,3d}};  
	        RealMatrix m = new Array2DRowRealMatrix(matrixData);  
	        System.out.println(m);  
	        // One more with three rows, two columns  
	        double[][] matrixData2 = { {1d,2d}, {2d,5d}, {1d, 7d}};  
	        RealMatrix n = new Array2DRowRealMatrix(matrixData2);          
	        // Note: The constructor copies  the input double[][] array.           
	        // Now multiply m by n  
	        RealMatrix p = m.multiply(n);  
	        System.out.println("p:"+p);  
	        System.out.println(p.getRowDimension());    // 2  
	        System.out.println(p.getColumnDimension()); // 2           
	        // Invert p, using LU decomposition  
	        RealMatrix pInverse = new LUDecomposition(p).getSolver().getInverse();  
	        System.out.println(pInverse);  
	}
}
