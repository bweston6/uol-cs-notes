-- Lecture 26 - Lazy Evaluation
{-
1. Error - Divide by zero.
2. No error
3. No error
4. Error - error
5. No error
-}

-- Lecture 26 - Lazy Evaluation on Lists
{-
1. This will terminate as take returns 4 elements of the infinite list.
2. This will not terminate as drop will return the infinite list minus the first 4 elements.
3. This will not terminate as map will return the infinite list with each element multiplied by two.
4. This will terminate as the `!! 1000` will return the 1001st element of the list
-}

-- Lecture 27 - Tail Recursion
product' :: [Int] -> Int
product' list = product'_helper list 1
product'_helper []     acc = acc
product'_helper (x:xs) acc = product'_helper xs $! acc * x

sum_up_to :: Int -> Int
sum_up_to n = sum_up_to_helper [0..n] 0
sum_up_to_helper []     acc = acc
sum_up_to_helper (x:xs) acc = sum_up_to_helper xs $! acc + x

even_sum :: Int -> Int
even_sum n = sum_up_to_helper [x*2 | x <- [0..(div n 2)]] 0

-- Lecture 27 - Folds
{-
1. foldl' as the function won't produce an output for an infinite list and we will always fold the entire list.
2. foldr as laziness will help. The second element in the tuple isn't evaluated so there may be a performance benefit to evaluating lazily.
3. foldr as the function produces a list. This means that we may not have to fold the entire list to get the result, giving a performance benefit to it's laziness.
-}
