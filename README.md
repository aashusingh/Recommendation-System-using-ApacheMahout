# Recommendation-System-using-ApacheMahout

Collaborative Filtering Using Mahout Library. 

Data Sets Used: 

1. 100k Movie Lens Dataset.
	URL : http://grouplens.org/datasets/movielens/100k/ 

2. Book Crossing Dataset.
	URL: http://www2.informatik.uni-freiburg.de/~cziegler/BX/   

Data preprocessing: 

Movie Lens Dataset: 
The dataset has values separated by '\t' and also timestamps are reported. Since mahout based recommendation systems do not take time into account, I had to drop that field. Further I processed every row, to convert the delimiter from '\t' to ',', which is the delimited expected by Mahout. 

Book Crossing Dataset:
The dataset had values reported in form of strings, with every field enclosed as double quotes and with ';' as the delimiter. I preprocessed the dataset to change the delimiter and also change the delimiter to ','. Further strings were converted to long integers for compatibility with Apache Mahout I/p format.

Platform Used :  
Eclipse, Java 1.8 
