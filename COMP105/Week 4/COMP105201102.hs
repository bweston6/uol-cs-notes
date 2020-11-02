-- NOTE: These functions have type annotations (the lines with ::). We will
-- cover this in lectures soon.


import Data.Char

-- Encoder

char2int :: Char -> Int
char2int c = ord c - ord 'a'

int2char :: Int -> Char
int2char i = chr (i + ord 'a')

shift :: Char -> Int -> Char
shift c offset = 
    let
        converted = char2int c
        is_lower = converted >= 0 && converted < 26
    in
        if is_lower
        then int2char ((converted + offset) `mod` 26)
        else c

caesar_enc :: String -> Int -> String
caesar_enc [] _ = []
caesar_enc (x:xs) offset = shift x offset
                            : caesar_enc xs offset


-- Decoder

caesar_dec :: String -> Int -> String
caesar_dec [] _ = []
caesar_dec (x:xs) offset = shift x (-offset) 
                            : caesar_dec xs offset

-- Cracker

-- The table of frequencies for the English language
eng_freqs :: [Float]
eng_freqs = [0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733, 0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633, 0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011]

-- Counts the number of times that c appears in the string
count :: Char -> [Char] -> Int
count _ [] = 0
count c (x:xs) 
    | c == x = 1 + rest
    | otherwise = rest
    where rest = count c xs

-- Gets the frequency of a single character
freq :: Char -> [Char] -> Float
freq c list = fromIntegral (count c list) / fromIntegral (length list)


-- Gets the table of frequencies for the input string
get_freqs :: [Char] -> Int -> [Float]
get_freqs _      26 = []
get_freqs string c  = freq (int2char c) string : get_freqs string (c + 1)
                         

-- Implements the chi squared statistic for two given lists of frequences. The second argument is the expected
-- frequency.
chi_squared :: [Float] -> [Float] -> Float
chi_squared [] [] = 0
chi_squared (x:xs) (y:ys) = (x - y)**2/y + chi_squared xs ys


-- Computes the chi_squared statistic for a given string, against the expected
-- frequencies from the freqs table.
chi_squared_string :: String -> Float
chi_squared_string string =
    let 
        string_freqs = get_freqs string 0
    in
        chi_squared string_freqs eng_freqs

-- Computes a list with 26 elements. Element i contains the chi_squared value
-- for key i.
chi_squared_list :: String -> Int -> [(Float, String)]
chi_squared_list _ 26 = []
chi_squared_list string i = 
    let
        decoded = caesar_dec string i
        score = chi_squared_string decoded
     in
        (score, decoded) : chi_squared_list string (i+1)

-- This function takes a list with pairs of numbers and strings, and returns the
-- pair that has the lowest number
get_best :: [(Float, String)] -> (Float, String)
get_best [(score, string)] = (score, string)
get_best ((score, string):xs) =
    let
        (tail_score, tail_string) = get_best xs
    in
        if score < tail_score 
        then (score, string)
        else (tail_score, tail_string)


caesar_crack :: String -> String
caesar_crack string =
    let 
        scores = chi_squared_list string 0
        (score, best) = get_best scores
    in
        best




