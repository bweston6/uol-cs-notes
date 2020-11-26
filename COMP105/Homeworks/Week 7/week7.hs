-- Lecture 19 - Custom Types
data Direction = North | East | South | West deriving (Show, Eq, Read, Ord)

is_north :: Direction -> Bool
is_north North  = True
is_north x      = False

dir_to_int :: Direction -> Int
dir_to_int North = 1
dir_to_int East = 2
dir_to_int West = 4
dir_to_int South = 3

-- Lecture 19 - Types with Data
data Point = Point Int Int deriving (Show)

same :: Int -> Point 
same x = Point x x

is_zero :: Point -> Bool
is_zero (Point 0 0) = True
is_zero x           = False

mult_point :: Point -> Int
mult_point (Point x y) = x * y

up_two :: Point -> Point
up_two (Point x y) = Point x (y + 2)

add_points :: Point -> Point -> Point
add_points (Point a b) (Point c d) = Point (a + c) (b + d)
