-- Lecture 4 - Ifs
gt_100 x = if x > 100 then 1 else 0
switch x y c = if c == 1 then x else y
fizzbuzz x = if (x `mod` 3 == 0) && (x `mod` 5 == 0) then "FizzBuzz!" else "Nope"

-- Lecture 4 - Let Expressions
question1 x = let 
                        a = x * x
                in
                        2 * a
question2 x = let
                        a = x + 1
                        b = a * a
                        c = 2 ** b
                in
                        a + b - c
bounded_square x = let
                        a = x * x
                in
                        if a < 100 then a else 100
                        
-- Lecture 5 - Tuples
square_and_cube x = (x * x, x * x * x)
add tuple (a, b) = a + b
swap (a, b) = (b, a)

-- Lecture 5 - Lists
head_squared list = head list ** 2
third list = list !! 2
third_head list = head (tail(tail list))
prepend_two list a b = a:b:list

-- Lecture 5 - List Functions
two_lengths list1 list2 = length list1 + length list2
make_palindrome list = list ++ reverse list
sum_and_product list = (sum list, product list)
four_through_six list = drop 3 (take 6 list)
both_in list x y = elem x list && elem y list

-- Lecture 6 - List Ranges
{-
[101..200]
[1000,1002..1050]
[20,19..1]
[999,999+3..]
-}

-- Lecture 6 - List Comprehensions
-- [ x^2 | x <- [1..10]]
only_odds list = [ x | x <- list, x `mod` 2 /= 0] 
between a b list = [ x | x <- list, x > a && x < b]
number_of_es string = length [x | x <- string, x == 'e']
proper_fizzbuzz = [
        if mod x 3 == 0 && mod x 5 == 0
        then "fizzbuzz"
        else(
                if mod x 3 == 0 && mod x 5 /= 0
                then "fizz"
                else(
                        if mod x 3 /= 0 && mod x 5 == 0
                        then "buzz"
                        else show x))
        | x <- [1..]]
