// Student ID:wo 201415467

#include <stdio.h>
#include <stdlib.h>

int main(void) {
	// Initialising messages:
	char msgMany[] = "How many numbers: ";
	char msgEnter[] = "Enter a number: ";
	char msgBar[] = "------------\n";
	char msgPositive[] = "Positive: %d\n";
	char msgNegative[] = "Negative: %d\n";
	char msgZero[] =     "Zero:     %d\n";
	char decimal[] = "%d";
	// Initialising data variables:
	int loops = 0;
	int number = 0;
	int positives = 0;
	int negatives = 0;
	int zeroes = 0;

	_asm {
		// Show prompt for "How many numbers"
		lea eax, msgMany
		push eax
		call printf
		add esp, 4
		// Assign user response to `loops`
		lea eax, loops
		push eax
		lea eax, decimal
		push eax
		call scanf
		add esp, 8
		// Start input loop
		mov ecx, loops
		inputLoop:
			push ecx
			// Show prompt for "Enter a number"
			lea eax, msgEnter
			push eax
			call printf
			add esp, 4
			// Assign user response to `number`
			lea eax, number
			push eax
			lea eax, decimal
			push eax
			call scanf
			add esp, 8
			// Increment counters
			mov eax, number
			cmp eax, 0
			jz addZero
			js addNegative
			mov eax, positives
			inc eax
			mov positives, eax
			jmp back
		addZero:
			mov eax, zeroes
			inc eax
			mov zeroes, eax
			jmp back
		addNegative:
			mov eax, negatives
			inc eax
			mov negatives, eax
			jmp back
		back:
			pop ecx
			loop inputLoop
		// Print summary
		// Load all outputs on stack
		push 1 // loop counter
		push zeroes
		lea eax, msgZero
		push eax
		push 2 // loop counter
		push negatives
		lea eax, msgNegative
		push eax
		push 3	// loop counter
		push positives
		lea eax, msgPositive
		push eax
		lea eax, msgBar
		push eax
		// Print bar
		call printf
		add esp, 4
		// Start loop to print rest
	printLoop:
		call printf
		add esp, 8
		pop ecx // pop loop counter to ecx
		loop printLoop
	}
	return 0;
}