#include <stdio.h>
#include <stdlib.h>
int main(void) {
	// Declare variables here (C syntax)
	signed int count1 = -10;
	int count2 = 5;
	int num = 0;

	_asm {
		// Put assembly code here
		mov eax, count1
		add eax, count2
		sub eax, 10
		mov num, eax
	}
	return 0;
}