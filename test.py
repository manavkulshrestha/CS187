def areAnagrams(str1,str2):
	return (''.join(sorted(str1)) == ''.join(sorted(str2)))

print(areAnagrams('face','faces'))
print(areAnagrams('draw','ward'))

def isBinaryTree(node):#assuming binary tree
	if(node.hasChildren()):
		if(!(node.data > node.left_child.data && node.data < node.right_child.data)):
			return False
		if(!(isBinaryTree(node) or isBinaryTree(node)))
			return False
		return True
	return True