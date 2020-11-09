-- Lecture 5 - Partial Application

plus_ten = (+ 10)

is_twenty = (== 20)

three_power = (3 **) 

power_three = (** 3)

xisy x y = x ++ " is " ++ y
cakeis   = xisy "cake"

-- Lecture 13 - Polymorphic Types

-- func1 :: Num a => a -> a -> a

-- func2 :: (Integral a, Fractional b) => a -> b -> (a,b)

-- func3 :: Eq a => [a] -> Bool

-- func4 :: (Num a, Ord a) => [a] -> [a]
