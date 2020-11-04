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
-- encode = error "Not implemented"
encode []       = []
encode (x:xs)   = x : int_to_char(length_char x (x:xs)) : encode (drop_char x xs)


-- Part C

complex_encode :: String -> String
complex_encode = error "Not implemented"

complex_decode :: String -> String
complex_decode = error "Not implemented"
