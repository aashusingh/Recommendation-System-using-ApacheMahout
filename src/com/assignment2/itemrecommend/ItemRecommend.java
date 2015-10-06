package com.assignment2.itemrecommend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class ItemRecommend {

	public static void main(String[] args) throws IOException{
		long startTime = System.currentTimeMillis();
		try {
			DataModel dm = new FileDataModel(new File("data/books1.csv"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("data/books_output_itemRecommendation_Euclidean_10.csv"));
			ItemSimilarity sim = new EuclideanDistanceSimilarity(dm);
			
			bw.write("Item based Recommendation Euclidean Distance Similarity" + "\n");
					
			GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);
			
			int x=1;
			for(LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();) {
				long itemId = items.nextLong();
				bw.write("For Item : " + itemId + "\n");
				List<RecommendedItem>recommendations = recommender.mostSimilarItems(itemId, 5);
				
				for(RecommendedItem recommendation : recommendations) {
					//System.out.println(itemId + "," + recommendation.getItemID() + "," + recommendation.getValue());
					bw.write(itemId + "," + recommendation.getItemID() + "," + recommendation.getValue()+"\n");
				}
				x++;
				if(x>100) break;
			}
			
			bw.close();
						
			
			
		} catch (IOException e) {
			System.out.println("There was an error.");
			e.printStackTrace();
		} catch (TasteException e) {
			System.out.println("There was a Taste Exception");
			e.printStackTrace();
		}
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		
	}

}