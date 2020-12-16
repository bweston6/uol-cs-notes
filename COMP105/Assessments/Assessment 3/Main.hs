module Main (get_maze, print_maze, is_wall, place_player, move, can_move, game_loop, get_path, main) where 

import System.Environment

maze_path = "overwrite this with your own path!"

-- Useful code from Lecture 25
-- You may use this freely in your solutions

get :: [String] -> Int -> Int -> Char
get maze x y = (maze !! y) !! x 

modify_list :: [a] -> Int -> a -> [a]
modify_list list pos new =
    let
        before = take  pos    list
        after  = drop (pos+1) list
    in
        before ++ [new] ++ after

set :: [String] -> Int -> Int -> Char -> [String]
set maze x y char = 
    let
        line = maze !! y
        new_line = modify_list line x char
        new_maze = modify_list maze y new_line
    in
        new_maze

---- Part A

-- Question 1

get_maze :: String -> IO [String]
get_maze = error "Not implemented"

-- Question 2

print_maze :: [String] -> IO ()
print_maze = error "Not implemented"

-- Question 3

is_wall :: [String] -> (Int, Int) -> Bool
is_wall = error "Not implemented"

-- Question 4

place_player :: [String] -> (Int, Int) -> [String]
place_player = error "Not implemented"


---- Part B

-- Question 5

move :: (Int, Int) -> Char -> (Int, Int)
move = error "Not implemented"

-- Question 6

can_move :: [String] -> (Int, Int) -> Char -> Bool
can_move = error "Not implemented"

-- Question 7

game_loop :: [String] -> (Int, Int) -> IO ()
game_loop = error "Not implemented"



---- Part C

-- Question 8

get_path :: [String] -> (Int, Int) -> (Int, Int) -> [(Int, Int)]
get_path = error "Not implemented"

-- Question 9

main :: IO ()
main = error "Not implemented"
