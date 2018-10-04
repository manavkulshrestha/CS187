def intsum(n):
	if n < 0:
		return -((-n) + intsum((-n)-1))
	if n == 0:
		return 0
	return n + intsum(n-1)

print(intsum(-5))
print(intsum(5))
