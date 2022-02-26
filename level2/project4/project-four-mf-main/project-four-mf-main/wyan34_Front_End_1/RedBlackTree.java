import java.util.LinkedList;

//--== CS400 File Header Information ==--
//Name: Bill Yan
//Email: wyan34@wisc.edu
//Team:  MF
//Role: Front End Developer
//TA:  Harit
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

/**
 * Modified Red Black Tree for Passage Analyzer
 * project
 * @author Bill Yan
 *
 */
public class RedBlackTree <T extends Comparable<T>> 
{
	node<T> root;
	
	static class node<T>
	{
		node<T> parent;
		node<T> left;
		node<T> right;
		boolean color; // Default is red
		T data;
		
		node(T data, node<T> parent)
		{
			this.parent = parent;
			this.data = data;
		}
		
		boolean isLeft()
		{
			return this.parent.left == this;
		}
		
		node<T> sibling()
		{
			if (this.isLeft())
				return parent.right;
			else
				return parent.left;
		}
		
		@Override
		public String toString()
		{
			LinkedList<node<T>> list = new LinkedList<node<T>>();
			list.add(this);
			String answer = "";
			
			while(!list.isEmpty())
			{
				node<T> current = list.remove();
				
				if (current == null)
					answer += "null ";
				else
				{
					answer += current.data.toString() + (current.color == true ? "(B) " : "(R) ");
					list.add(current.left);
					list.add(current.right);
				}
			}
			
			return answer;
		}
	}
	
	public void insert(T data)
	{
		// Basic insertion root case
		if (root == null)
		{
			root = new node<T>(data, null);
			root.color = true;
			return;
		}
		
		// Basic insertion other case
		node<T> child;
		node<T> current = root;
		while (true)
		{
			// insertion failed
			if (current.data.compareTo(data) == 0)
				return;
			
			if (current.data.compareTo(data) > 0)
			{
				if (current.left == null)
				{
					// insertion complete on left
					child = new node<T>(data, current);
					current.left = child;
					break;
				}
				else
					current = current.left;
			}
			else
			{
				if (current.right == null)
				{
					// insertion complete on right
					child = new node<T>(data, current);
					current.right = child;
					break;
				}
				else
					current = current.right;
			}
		}
		
		// Fixing Tree property
		// Parent black
		if (current.color == true)
			return;
		
		// Other cases
		// Rotate so everything aligns
		if (current.isLeft() && !child.isLeft())
		{
			rotate(child);
			current = child;
		}
		else if (!current.isLeft() && child.isLeft())
		{
			rotate(child);
			current = child;
		}
		
		// Case 1, current's sibling is null
		if (current.sibling() == null)
		{
			rotate(current);
			child.color = true;
			
			if (current.parent == null)
			{
				current.color = true;
				return;
			}
			
			if (current.parent.color == true)
				return;
		}
		else
			current = child;
	
		while (current.parent != null && current.parent.color == false)
		{
			node<T> uncle = current.parent.sibling();
	
			// Case 2 parent's sibling is not null, and is red
			if (uncle.color == false)
			{
				current.parent.color = true;
				uncle.color = true;
				uncle.parent.color = false;
				
				current = uncle.parent;
			}
			// Case 3 parent's sibling is not null, and is black
			else
			{
				rotate(current.parent);
				current.parent.color = true;
				uncle.parent.color = false;
				
				break;
			}
		}
		
		
		if (current.parent == null)
			root = current;
		
		root.color = true;
	}
	
	public node<T> delete(T data)
	{
		node<T> target = search(data);
		
		if (target == null)
			return target;
		
		// Check how many children it has
		// Two children, convert to one or no child
		if (target.left != null && target.right != null)
		{
			// Find in-order precedent
			node<T> precedent = target.left;
			
			while (precedent.right != null)
				precedent = precedent.right;
			
			target.data = precedent.data;
			target = precedent;
		}
		
		// One child, then current node must be black, and the child must be red
		if (target.left != null)
		{
			if (target.isLeft())
				target.parent.left = target.left;
			else
				target.parent.right = target.left;
			
			target.left.parent = target.parent;
			target.left.color = true;
			target.data = data;
			
			return target;
		}
		else if (target.right != null)
		{
			if (target.isLeft())
				target.parent.left = target.right;
			else
				target.parent.right = target.right;
			
			target.right.parent = target.parent;
			target.right.color = true;
			target.data = data;
			
			return target;
		}
		
		// No children, could be red or black
		// Case red
		if (target.color == false)
		{
			if (target.isLeft())
				target.parent.left = null;
			else
				target.parent.right = null;
			
			target.data = data;
			
			return target;
		}
		
		// Case black
		// Keep a pointer to the node we actually want to delete at the end
		node<T> current = target;
		
		while(current != root)
		{
			// Case sibling is red, then parent is black, and sibling's children are black
			if (current.sibling().color == false)
			{
				// Convert to sibling is black
				rotate(current.sibling());
			}
			
			// Case sibling is black, the same side child of sibling is red and other side is black
			if (current.isLeft() && (current.sibling().left != null && current.sibling().left.color == false) && (current.sibling().right == null || current.sibling().right.color == true) ||
					!current.isLeft() && (current.sibling().right != null && current.sibling().right.color == false) && (current.sibling().left == null || current.sibling().left.color == true))
			{
				// Convert to sibling's other child is red case
				if (current.isLeft())
				{
					rotate(current.sibling().left);
					current.sibling().color = true;
					current.sibling().right.color = false;
				}
				else
				{
					rotate(current.sibling().right);
					current.sibling().color = true;
					current.sibling().left.color = false;
				}
			}
			
			// Case sibling is black, the other side child of sibling is red
			// Case parent is red
			if (current.parent.color == false && (current.isLeft() && current.sibling().right != null && current.sibling().right.color == false 
					|| !current.isLeft() && current.sibling().left != null && current.sibling().left.color == false))
			{
				rotate(current.sibling());
				
				// Recolor
				current.parent.color = true;
				current.parent.parent.color = false;
				current.parent.sibling().color = true;
				
				// Exit loop
				break;
			}
			// Case parent is red and sibling and its children are all black
			else if (current.parent.color == false)
			{
				// Recolor
				current.parent.color = true;
				current.sibling().color = false;
				
				// Exit loop
				break;
			}
			
			// Case parent is black
			else
			{
				rotate(current.sibling());
				
				// Case the now parent's sibling is red
				if (current.parent.sibling() != null && current.parent.sibling().color == false)
				{
					current.parent.sibling().color = true;
					break;
				}
				// Case the now parent's sibling is black, then current's sibling is also black
				else
				{
					current.parent.color = false;
					current = current.parent.parent;
				}
			}
		}
		
		// Delete the node
		if (target.isLeft())
			target.parent.left = null;
		else
			target.parent.right = null;
		
		target.data = data;
		
		return target;
	}
	
	public boolean contains(T data)
	{
		return search(data) != null;
	}
	
	public node<T> search(T data)
	{
		node<T> current = root;
		
		while(current != null)
		{
			if (current.data.compareTo(data) == 0)
				break;
			else if (current.data.compareTo(data) > 0)
				current = current.left;
			else
				current = current.right;
		}
		
		return current;
	}
	
	private void rotate(node<T> child)
	{
		node<T> parent = child.parent;
		
		// Right rotation
		if (child.isLeft())
		{
			parent.left = child.right;
			child.parent = parent.parent;
			child.right = parent;
			
			if (child.parent != null)
				if (parent.isLeft())
					child.parent.left = child;
				else
					child.parent.right = child;
			else
				root = child;
			
			parent.parent = child;
			
			if (parent.left != null)
				parent.left.parent = parent;
		}
		else
		{
			parent.right = child.left;
			child.parent = parent.parent;
			child.left = parent;
			
			if (child.parent != null)
				if (parent.isLeft())
					child.parent.left = child;
				else 
					child.parent.right = child;
			else
				root = child;
			
			parent.parent = child;
			
			if (parent.right != null)
				parent.right.parent = parent;
		}
		
		if (child.parent == null)
			root = child;
	}
	
	public String inOrderTraversal(node<T> current)
	{
		if (current == null)
			return "";
		
		String answer = inOrderTraversal(current.left);
		answer += current.data.toString();
		answer += inOrderTraversal(current.right);
		
		return answer;
	}
	
	public static void main(String[] in)
	{
		RedBlackTree<Integer> test = new RedBlackTree<Integer>();
		test.insert(8);
		test.insert(7);
		test.insert(9);
		test.insert(6);
		for (int i = 5; i > -4; i--)
		{
			test.insert(i);
		}
		System.out.println(test.root.toString());
		
		for (int i = 0; i < 9; i+=2)
		{
			test.delete(i);
			System.out.println(test.root.toString());
		}
	}
}
