/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) {return root;}

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {return root;}
        return left == null? right : left;
    }
    /**
    The approach is pretty intuitive. Traverse the tree in a depth first manner. 
    The moment you encounter either of the nodes p or q, return some boolean flag. 
    The flag helps to determine if we found the required nodes in any of the paths.
    The least common ancestor would then be the node for which both the subtree r
    ecursions return a True flag. It can also be the node which itself 
    is one of p or q and for which one of the subtree recursions returns a True flag.
    */
}



class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // iterative way: building a parent map until find both p and q
        // this one is slower than recursive way
        // starting from p or q backwards, 
        // find the first common ancester of them
    	Stack<TreeNode> stack = new Stack<>();
    	// use map to store parent of each node
    	Map<TreeNode, TreeNode> parents = new HashMap<>();
    	stack.push(root);
    	parents.put(root, null);

    	// store all parent nodes in map until both p and q are visited
    	while (!parents.containsKey(p) || !parents.containsKey(q)) {
    		TreeNode n = stack.pop();
    		if (n.left != null) {
    			stack.push(n.left);
    			parents.put(n.left, n);
    		}
    		if (n.right != null) {
    			stack.push(n.right);
    			parents.put(n.right, n);
    		}
    	}

    	// use a set to store the ancestors along the path from q to root
    	Set<TreeNode> ancestors = new HashSet<>();
    	while (p != null) {
    		ancestors.add(p);
    		p = parents.get(p);
    	}

    	// now search all the ancestors similarly for p from p to root
    	// return the first ancestor that is also contained in q's set
    	while (q != null) {
    		if (ancestors.contains(q)) {break;}
    		q = parents.get(q);
    	}

    	return q;
    }
}



//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65236/JavaPython-iterative-solution


































