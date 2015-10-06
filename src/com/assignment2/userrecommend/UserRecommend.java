package com.assignment2.userrecommend;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;

import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class UserRecommend {
   public static void main(String args[]){
	   long startTime = System.currentTimeMillis();
      try{
         
    	  DataModel datamodel = new FileDataModel(new File("data/books1.csv"));
      
    	  BufferedWriter bw = new BufferedWriter(new FileWriter("data/books_output_UserRecommendation_Euclidean_10.csv"));
    	  bw.write("User based Recommendation Euclidean Similarity" + "\n");
      
         UserSimilarity usersimilarity = new EuclideanDistanceSimilarity(datamodel);
         UserNeighborhood userneighborhood = new ThresholdUserNeighborhood(0.1, usersimilarity, datamodel);
        
         UserBasedRecommender recommender = new GenericUserBasedRecommender(datamodel, userneighborhood, usersimilarity);
        
                 		 
        int x=1;
			for(LongPrimitiveIterator users = datamodel.getUserIDs(); users.hasNext();) {
				long userId = users.nextLong();
				bw.write("For UserId : " +  userId + "\n");
				//System.out.println(userId);
				List<RecommendedItem>recommendations = recommender.recommend(userId, 5);
				
				for(RecommendedItem recommendation : recommendations) {
					//System.out.println(recommendation.getItemID() + "," + recommendation.getValue());
					bw.write(recommendation.getItemID() + "," + recommendation.getValue() + "\n");
				}
				x++;
				if(x>10) break;
			}
			bw.close();
			
         
      
      }catch (IOException e) {
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