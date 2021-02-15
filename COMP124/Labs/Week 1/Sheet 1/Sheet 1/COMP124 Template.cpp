#include <stdio.h>
#include <stdlib.h>
int main(void) {
	// Declare variables here (C syntax)
	int num = 10;
	_asm {
		// Put assembly code here
		mov eax, num
		add eax, 12
		mov num, eax
	}
	return 0;
}