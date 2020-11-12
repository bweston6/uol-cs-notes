-- Lecture 12 - Partial Application

plus_ten = (+ 10)

is_twenty = (== 20)

three_power = (3 **) 

power_three = (** 3)

xisy x y = x ++ " is " ++ y
cakeis   = xisy "cake"

-- Lecture 13 - Polymorphic Types
{-
func1 :: Num a => a -> a -> a

func2 :: (Integral a, Fractional b) => a -> b -> (a,b)

func3 :: Eq a => [a] -> Bool

func4 :: (Num a, Ord a) => [a] -> [a]
-}
-- Lecture 14 - Anonymous Functions
{-
\x -> x - 1

\x y -> show x ++ show y

\(x, y) -> (y, x)

\(x:xs) -> head xs
-}
-- Lecture 14 - Function Composition
{-
head . head $ [[1]]

(+1) . (*2) $ 4

sum . tail . tail $ [1,2,3,4]

filter (>10) . map (*2) $ [1..10]
-}
-- Lecture 15 - Map

triple = map (*3)

list_to_str list = map (show) list

second_char = map (\(_:x:_) -> x)

add_pairs = map (\(x,y) -> x + y)

-- Lecture 15 - Filter

only_odds = filter odd

between a b = filter (\x -> x < b && x > a)

ordered list = filter (\(x, y) -> x > y) list

singletons list = filter (\x -> length x == 1) list
