#include <stdio.h>
#include <stdlib.h>
int main(void) {
	// Declare variables here (C syntax)
	int num = 10;
	_asm {
		// Put assembly code here
			mov eax, num
			sub eax, 10
			jnz store
			mov eax, 100
		store:
			mov num, eax
	}
	return 0;
}