while(index < size/2) {
	int one = 2*index+1, two = 2*index+2;

	if(two < size && heap[one].compareTo(heap[right]))
		largerChild = two;
	else
		largerChild = one;

	if(elem.compareTo(heap[right]) >= 0)
		break;

	heap[index] = heap[largerChild];
	index == largerChild;
}