package AVLTree;

public class TreeNode implements Comparable<TreeNode> {
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	public int height;
	public int commonPrefixLen;
	public String diffURL;
	public TreeNode(int h, int cpl,String diffUrl, TreeNode parentNode) {
		left = null;
		right = null;
		parent = parentNode;
		height = h;
		commonPrefixLen =cpl;
		this.diffURL = diffUrl;
	}
	@Override
	public String toString() {
		String side="null";
		if(parent!=null && parent.left == this) {
			side="left";
		}
		else if(parent!=null && parent.right == this) {
			side ="right";
		}
		return this.commonPrefixLen +"-"+diffURL+"-"+"("+this.height+") on "+ side;
	}
	public int compareTo(TreeNode o) {
		return this.diffURL.compareToIgnoreCase(o.diffURL);
	}
	public int compareTo(String url) {
		return getFullString().compareToIgnoreCase(url);
	}
	
	public String getFullString() {
		if(this.parent == null) {
			return this.diffURL;
		}
		String parentStr =parent.getFullString();
		return parentStr.substring(0,this.commonPrefixLen) + this.diffURL;
		
	}
	
	public int commonPrefix(String url) {
		int cnt=0;
		int index=0;
		for(char ch : getFullString().toCharArray()) {
			if(index>=url.length() || url.charAt(index++) != ch) {
				return cnt;
			}
			cnt++;
		}
		return cnt;
	}
	
}
