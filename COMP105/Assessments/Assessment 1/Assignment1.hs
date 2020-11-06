-- Do not alter the following line
module Assignment1 (char_to_int, repeat_char, decode, int_to_char, length_char, drop_char, encode, complex_encode, complex_decode) where

-- Part A
char_to_int :: Char -> Integer
char_to_int char
        | char == '0'   = 0
        | char == '1'   = 1 
        | char == '2'   = 2 
        | char == '3'   = 3 
        | char == '4'   = 4 
        | char == '5'   = 5
        | char == '6'   = 6
        | char == '7'   = 7
        | char == '8'   = 8
        | char == '9'   = 9
        | otherwise     = error "Please enter a character containing a number 0 to 9 inclusive"

repeat_char :: Char -> Integer -> String
repeat_char c 0 = []
repeat_char c n = c : repeat_char c (n-1)

decode :: String -> String
decode []               = []
decode (x:y:xs)         = repeat_char x (char_to_int y) ++ decode xs

-- Part B
int_to_char :: Integer -> Char
int_to_char int
        | int == 0  = '0'
        | int == 1  = '1'
        | int == 2  = '2'
        | int == 3  = '3'
        | int == 4  = '4'
        | int == 5  = '5'
        | int == 6  = '6'
        | int == 7  = '7'
        | int == 8  = '8'
        | int == 9  = '9'
        | otherwise     = error "Only repeats up to 9 (integers between 0 and 9 inclusive) are permitted."

length_char :: Char -> String -> Integer
length_char c [] = 0
length_char c (x:xs)
        | c /= x = 0
        | c == x = length_char c xs + 1 

drop_char :: Char -> String -> String
drop_char c []   = []
drop_char c (x:xs)
        | c /= x = x:xs
        | c == x = drop_char c xs

encode :: String -> String
encode []       = []
encode (x:xs)   = x : int_to_char(length_char x (x:xs)) : encode (drop_char x xs)

-- Part C
complex_encode :: String -> String
complex_encode []       = []
complex_encode (x:xs)   = x : (no_1_int_to_string(length_char x (x:xs)) ++ complex_encode (drop_char x xs))

complex_decode :: String -> String
complex_decode []       = []
complex_decode (x:[])   = [x]
complex_decode (x:y:xs)
        | number_check_string y == False = x : complex_decode (y:xs)
        | otherwise                      = repeat_char x repeat_times ++ complex_decode rest
        where
                rest            = drop_int_from_string xs
                repeat_times    = string_to_int (head_int_from_string (y:xs))

-- Helper Functions for Part C
int_to_string 0 = []
int_to_string x = let
                        rest    = div (x - current) 10
                        current = mod x 10
                in
                        int_to_string rest ++ [int_to_char current]

no_1_int_to_string 1    = ""
no_1_int_to_string x    = int_to_string x

number_check_string char
        | char == '0'   = True
        | char == '1'   = True
        | char == '2'   = True
        | char == '3'   = True
        | char == '4'   = True
        | char == '5'   = True
        | char == '6'   = True
        | char == '7'   = True
        | char == '8'   = True
        | char == '9'   = True
        | otherwise     = False

head_int_from_string [] = []
head_int_from_string (x:xs)
        | number_check_string x == False = []
        | otherwise                      = x : head_int_from_string xs

drop_int_from_string [] = []
drop_int_from_string (x:xs)
        | number_check_string x == False = x:xs
        | otherwise                      = drop_int_from_string xs

string_to_int []     = 0
string_to_int (x:xs) = 10 ^ length xs * char_to_int x + string_to_int xs
