-- Lecture 16 - Fold

list_product list = foldr (*) 1 list

list_any list = foldr (||) False list

product_of_evens list = foldr (\ x acc -> if even x then x * acc else acc) 1 list

lt10 list = foldr (\ x acc -> if x < 10 then acc + 1 else acc) 0 list

-- Lecture 17 - Scan

list_product' list = scanr (*) 1 list

list_any' list = scanr (||) False list

product_of_evens' list = scanr (\ x acc -> if even x then x * acc else acc) 1 list

lt10' list = scanr (\ x acc -> if x < 10 then acc + 1 else acc) 0 list

-- Lecture 17 - takeWhile & dropWhile

leading_caps string = takeWhile (\ x -> elem x ['A'..'Z']) string

drop_caps string = dropWhile (\ x -> elem x ['A'..'Z']) string

split_on c string = 
        let
                before  = takeWhile (c/=) string
                rest    = drop (length before) string
                after   = dropWhile (c==) rest
        in
                (before, after)

-- Lecture 17 - zipWith

mul_lists = zipWith (*)

and_lists = zipWith (&&)

isPalindrome string = and $ zipWith (==) string (reverse string)
