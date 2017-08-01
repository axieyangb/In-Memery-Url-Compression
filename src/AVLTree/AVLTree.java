package AVLTree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
	TreeNode root;
	public AVLTree() {
		root = null;
	}
	
	public int height(TreeNode node) {
		if(node == null) {
			return 0;
		}
		return node.height;
	}
	
	public TreeNode rightRotate(TreeNode node) {		
		TreeNode newParent = node.left;
		TreeNode temp = newParent.right;
		
		String url_newParent = newParent.getFullString();
		String url_node = node.getFullString();
		
		if(temp!=null) temp.parent = node;
		newParent.parent = node.parent;
		node.parent=newParent;
		
		newParent.right = node;
		node.left = temp;
		
		
		updateCommonPrefix(newParent, url_newParent);
		updateCommonPrefix(node, url_node);
		
		updateHeight(node);
		updateHeight(newParent);
		return newParent;
	}
	
	public TreeNode leftRotate(TreeNode node) {
		TreeNode newParent = node.right;
		TreeNode temp = newParent.left;
		
		String url_newParent = newParent.getFullString();
		String url_node = node.getFullString();
		//System.out.println("New parent url:" +newParentUrl);
		
		if(temp!=null) temp.parent = node;
		newParent.parent=node.parent;
		node.parent =newParent;
		
		
		newParent.left = node;
		node.right = temp;
		
		updateCommonPrefix(newParent, url_newParent);
		updateCommonPrefix(node, url_node);
		//sequence is very important
		updateHeight(node);
		updateHeight(newParent);
		return newParent;
	}
	
	public int getOffset(TreeNode node) {
		return height(node.right) - height(node.left);
	}
	
	public void insert(String url) {
		if(url.isEmpty()) return;
		url =url.toLowerCase();
		root = insertHelper(root,null,url);
	}

	private void updateHeight(TreeNode node) {
		node.height =Math.max(height(node.left), height(node.right))+1;
	}
	
	private void updateCommonPrefix(TreeNode node, String url) {
		if(node.parent == null) {
			node.commonPrefixLen=0;
		}
		else {
			node.commonPrefixLen = node.parent.commonPrefix(url);
		}
		node.diffURL = url.substring(node.commonPrefixLen);
	}
	
	
	private TreeNode insertHelper(TreeNode root, TreeNode parent, String url) {
		if(url.equals("")) {
			return root;
		}
		if(root == null) {
			int commonLen=0;
			if(parent != null) {
				commonLen = parent.commonPrefix(url);
			}
			return new TreeNode(1,commonLen ,url.substring(commonLen), parent);
		}
		if(root.compareTo(url)>0) { 
			root.left =insertHelper(root.left,root, url);
		}
		else if( root.compareTo(url)<0) {
			root.right = insertHelper(root.right, root, url);
		}
		else {
			//Duplicate is not allowed
			return root;
		}
		
		int offset = getOffset(root);

		//four sceniaros
		//			  5
		//		  /      \
		// 		4		  8
		//   /
		//  2
		//	  \
		//		3
		// right rotate
		if(offset<-1 && root.left.compareTo(url)>0) {
			return rightRotate(root);
		}
		
		
		//left rotate
		else if(offset>1 && root.right.compareTo(url) < 0) {
			return leftRotate(root);
		}
		
		
		else if(offset<-1 &&  root.left.compareTo(url) < 0) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}
		
		else if(offset > 1 && root.right.compareTo(url) > 0) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
		else {
			updateHeight(root);
			return root;
		}
		
	}
	
	@Override
	public String toString() {
		return preOrder(root);
	}
	
	private String preOrder(TreeNode cur) {
		if(cur == null) {
			return "";
		}
		return cur.toString() +" " +preOrder(cur.left ) +" "+ preOrder(cur.right);
	}

	public boolean existUrl(String url) {
		if(url.isEmpty()) return false;
		return searchUrlHelper(url,root, "");
	}
	private boolean searchUrlHelper(String url, TreeNode cur, String parentUrl) {
		if(cur == null) {
			return false;
		}
		String currentUrl = parentUrl.substring(0, cur.commonPrefixLen) + cur.diffURL;
		int cmp = currentUrl.compareToIgnoreCase(url);
		if(cmp == 0) {
			return true;
		}
		if(cmp > 0) {
			return searchUrlHelper(url , cur.left , currentUrl);
		}
		return searchUrlHelper(url, cur.right, currentUrl);
		
	}
}
