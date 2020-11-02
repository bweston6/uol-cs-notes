-- Lecture 7 - Recursion
mult13 1 = 13
mult13 n = 13 + mult13 (n - 1) 

pow3 1 = 3
pow3 n = 3 * pow3 (n - 1)

odd_sum n
        | n == 1 = 1
        | mod  n 2 == 0 = odd_sum(n - 1)
        | otherwise = n + odd_sum(n - 1)

lucas n
        | n == 0 = 2
        | n == 1 = 1
        | otherwise = lucas (n - 1) + lucas (n - 2)

-- Lecture 8 - List Recursion
half_sum []     = 0
half_sum (x:xs) =
        let
                half = x/2
        in
                half + half_sum xs

mult2 []     = []
mult2 (x:xs) = x * 2 : mult2 xs

drop_evens [] = []
drop_evens (x:xs)
        | mod x 2 /= 0  = x : drop_evens xs
        | otherwise     = drop_evens xs

mult_adjacent []        = []
mult_adjacent [_]       = error "Odd number of elements"
mult_adjacent (x:y:xs)  = x * y : mult_adjacent xs

get_ele i []     = error "There were less than i elements in the list."
get_ele 0 (x:_)  = x
get_ele i (_:xs) = get_ele (i - 1) xs

drop_ele i [] = error "There were less than i elements in the list."
drop_ele i (x:xs)
        | i == 0    = xs
        | otherwise = x : drop_ele (i - 1) xs 

-- Lecture 9 - More Complex Recursion on Lists
div_list [] [] = []
div_list _ [] = error "Lists are not of equal length"
div_list [] _ = error "Lists are not of equal length"
div_list (x:xs) (y:ys) = (x/y) : div_list xs ys

longer [] _ = False
longer _ [] = True
longer (x:xs) (y:ys) = longer xs ys

vowels_and_consonants [] = ([],[])
vowels_and_consonants (x:xs)
        | elem x vowels =  (x: iv, nv)
        | otherwise     =  (iv, x:nv)
        where   (iv, nv) = vowels_and_consonants xs
                vowels = "aeiou"

--Challenge Question - The Collatz Problem

longest_collatz 1 = 1
longest_collatz n
        | collatz_len >= next_longest = collatz_len
        | otherwise                   = next_longest
        where   next_longest = longest_collatz (n-1)
                collatz_len  = length (collatz_gen n)

collatz_gen 1 = [1]
collatz_gen n = n : collatz_gen (collatz_helper n)

collatz_helper n
        | mod n 2 == 0 = div n 2
        | otherwise    = 3 * n + 1 

