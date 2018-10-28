def areAnagrams(str1,str2):
	return (''.join(sorted(str1)) == ''.join(sorted(str2)))

print(areAnagrams('face','faces'))
print(areAnagrams('draw','ward'))

class Node:
	left_child = None
	right_child = None

	def _init_(left_child, right_child):
		self.left_child = left_child
		self.right_child = right_child
	
	def hasChildren():
		return (self.left_child != None and self.right_child != None)


def isBinaryTree(node):#assuming binary tree (DOESN'T WORK, SUB BINARY SEARCH TREE)
	if(node.hasChildren()):
		if((node.data < node.left_child.data or node.data > node.right_child.data)):
			return False
		 if(!(isBinaryTree(node) or isBinaryTree(node))):
		 	return False
		return True
	return True

def move_non_zero_to_left(arr):


#find  first non repeating character in string