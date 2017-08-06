# In-Memery-Url-Compression
	In memory URL compression, pure Java implementation. Widely used in Webcrawler and url scan to avoid infinite loop. The theorem is refer to 
	https://www.researchgate.net/publication/2902229_In-memory_URL_Compression

Getting Start:
	#inidtailize the tree;	
	AVLTree tree = new AVLTree();
	
	#insert the URL into the tree;
	tree.insert("http://www.google.com");
	tree.insert("http://www.yahoo.com");

	#check the url is existed in the tree.
	tree.existUrl("http://www.google.com");

Note :
	#Currently, it does not support deleting nodes from the tree.
	#Let me know if you are interested in this project.
TEST
